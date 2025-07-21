package com.example.evops.core.data.datastore

enum class AuthState(val string: String) {
    AUTHORIZED("authorized"),
    NEED_LOGIN("need_login"),
}
