package main.util

object ValidateUtils {
    fun validateInput(textInput: String, inputName: String?): String{
        if (textInput.equals(null) || textInput.equals(""))
            throw IllegalArgumentException("$inputName must not be empty!")

        return textInput
    }
}