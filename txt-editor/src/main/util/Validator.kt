package main.util

import main.model.Editor

object Validator {
    fun isValidEditor(editor: Editor) : Boolean {
        return true
    }

    fun validateInput(textInput: String, inputName: String?): String{
        if (textInput.equals(null) || textInput.equals(""))
            throw IllegalArgumentException("$inputName must not be empty!")

        return textInput
    }
}