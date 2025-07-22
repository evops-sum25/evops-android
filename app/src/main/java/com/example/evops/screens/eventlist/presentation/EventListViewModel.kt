package com.example.evops.screens.eventlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evops.screens.eventlist.domain.model.EventItem
import com.example.evops.screens.eventlist.domain.usecases.GetEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(private val getEventsUseCase: GetEventsUseCase) :
    ViewModel() {
    private val _listState = MutableStateFlow(EventListState())
    val listState =
        _listState
            .onStart { processLoadFirstEvents() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = EventListState(),
            )

    fun onEvent(event: EventListEvent) {
        when (event) {
            is EventListEvent.LoadFirstEvents -> processLoadFirstEvents()
            is EventListEvent.LoadEvents -> processLoadEvents()
            is EventListEvent.UpdateSearchString -> {
                _listState.update { currentState ->
                    currentState.copy(searchString = event.searchString)
                }
            }
            is EventListEvent.AddSearchTag -> {
                _listState.update { currentState ->
                    currentState.copy(searchTagIds = currentState.searchTagIds + event.tagId)
                }
            }
            is EventListEvent.RemoveSearchTag -> {
                _listState.update { currentState ->
                    currentState.copy(searchTagIds = currentState.searchTagIds - event.tagId)
                }
            }
        }
    }

    private fun processLoadFirstEvents() {
        val searchString = _listState.value.searchString
        val tagIds = _listState.value.searchTagIds
        val flow = getEventsUseCase(searchString = searchString, tagIds = tagIds)
        viewModelScope.launch { flow.collect { result -> clearAndAppendEvents(result.data) } }
    }

    private fun processLoadEvents() {
        val searchString = _listState.value.searchString
        val tagIds = _listState.value.searchTagIds
        val lastEventId = _listState.value.events?.lastOrNull()?.id ?: return
        val flow =
            getEventsUseCase(
                lastEventId = lastEventId,
                searchString = searchString,
                tagIds = tagIds,
            )
        viewModelScope.launch { flow.collect { result -> appendEvents(result.data) } }
    }

    private fun appendEvents(newEvents: List<EventItem>?) {
        newEvents?.let { newEvents ->
            _listState.update { currentState ->
                currentState.copy(events = currentState.events?.plus(newEvents))
            }
        }
    }

    private fun clearAndAppendEvents(newEvents: List<EventItem>?) {
        _listState.update { currentState -> currentState.copy(events = newEvents) }
    }
}
