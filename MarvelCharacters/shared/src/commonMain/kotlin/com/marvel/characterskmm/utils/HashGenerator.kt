package com.marvel.characterskmm.utils

import okio.ByteString.Companion.encodeUtf8

object HashGenerator {

    fun MD5(string: String) : String {
        try {
            val byteString = string.encodeUtf8()
            return byteString.md5().hex()
        } catch (e: Exception) {
            return ""
        }
    }
}