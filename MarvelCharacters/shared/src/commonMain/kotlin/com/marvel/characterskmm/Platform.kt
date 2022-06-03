package com.marvel.characterskmm

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

expect class Platform() {
    val platform: String
}

expect fun initLogger()