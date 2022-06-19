package com.marvel.characterskmm.utils

import kotlin.test.Test
import kotlin.test.assertEquals

class HashGeneratorTest {

    @Test
    fun MD5_with_valid_input_should_return_md5_hash_stirng() {
        // Arrange
        val text = "Texto de prueba"

        // Act
        val result = HashGenerator.MD5(text)

        // Assert
        assertEquals(result, "62e85e5d9a24e99980bb54369d90d81d")
    }
}