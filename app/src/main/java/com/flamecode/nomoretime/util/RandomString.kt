package com.flamecode.nomoretime.util

/**
 * Create a random id for a user
 *
 * @param length
 */
fun getRandomString(length: Int) : String {

    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}