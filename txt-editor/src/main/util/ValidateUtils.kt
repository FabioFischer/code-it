package main.util

object ValidateUtils {
    fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }
}