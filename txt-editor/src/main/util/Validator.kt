package main.util

object Validator {
    fun validateInput(textInput: String, inputName: String?): String{
        if (textInput.equals(null) || textInput.equals(""))
            throw IllegalArgumentException("$inputName must not be empty!")

        return textInput
    }
}