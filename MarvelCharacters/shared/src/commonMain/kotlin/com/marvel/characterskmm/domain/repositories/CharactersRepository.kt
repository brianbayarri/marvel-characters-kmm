package com.marvel.characterskmm.domain.repositories

import com.marvel.characterskmm.data.CharactersResponse
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import com.marvel.characterskmm.BuildKonfig
import com.marvel.characterskmm.initLogger

class CharactersRepository {

    private val httpClient = HttpClient {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "HttpClient", message = message)
                }
            }
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }.also {
        initLogger()
    }

    suspend fun get(timestamp: Long, md5: String) : CharactersResponse {
        return httpClient.get {
            url("https://gateway.marvel.com/v1/public/characters")
            parameter("apikey", BuildKonfig.MARVEL_PUBLIC_KEY)
            parameter("ts", timestamp)
            parameter("hash", md5)
        }.body()
    }
}