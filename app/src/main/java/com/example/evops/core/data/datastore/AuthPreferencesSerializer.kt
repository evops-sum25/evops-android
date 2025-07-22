package com.example.evops.core.data.datastore

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import java.util.Base64

@Serializable
data class AuthPreferences(
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val authState: String = AuthState.NEED_LOGIN.string,
)

object AuthPreferencesSerializer : Serializer<AuthPreferences> {
    override val defaultValue: AuthPreferences
        get() = AuthPreferences()

    override suspend fun readFrom(input: InputStream): AuthPreferences {
        val encryptedBytes = withContext(Dispatchers.IO) { input.use { it.readBytes() } }
        val encryptedBytesDecoded = Base64.getDecoder().decode(encryptedBytes)
        val decryptedBytes = Crypto.decrypt(encryptedBytesDecoded)
        val decodedJsonString = decryptedBytes.decodeToString()
        return Json.decodeFromString(decodedJsonString)
    }

    override suspend fun writeTo(t: AuthPreferences, output: OutputStream) {
        val json = Json.encodeToString(t)
        val bytes = json.toByteArray()
        val encryptedBytes = Crypto.encrypt(bytes)
        val encryptedBytesBase64 = Base64.getEncoder().encode(encryptedBytes)
        withContext(Dispatchers.IO) { output.use { it.write(encryptedBytesBase64) } }
    }
}
