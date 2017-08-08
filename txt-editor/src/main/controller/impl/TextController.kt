package main.controller.impl

import javafx.scene.control.TextArea
import main.controller.ITextController
import main.util.Validator

class TextController : ITextController {
    private val serialVersionUID = 1L

    private val NO_MATCH_FOUND: Int = -1

    private fun selectText(textArea: TextArea, index: Int, textSegment: String) {
        if (index != NO_MATCH_FOUND)
            textArea.selectRange(index, (index + textSegment.length))
    }

    private fun replaceAll(text: String, textSegment: String, replaceWith: String): String?{
        return text.replace(Validator.validateInput(textSegment, "Text input"), Validator.validateInput(replaceWith, "Replace text input"))
    }

    private fun find(text: String, textSegment: String, lastIndex: Int): Int {
            val index = text.toLowerCase().indexOf(Validator.validateInput(textSegment, "Text input").toLowerCase(), lastIndex)

            if (index == NO_MATCH_FOUND)
                throw IllegalArgumentException("No match for $textSegment!")

            return index
    }

    override fun findFirst(textArea: TextArea, textSegment: String) {
        val index = this.find(textArea.text, textSegment, 0)
        this.selectText(textArea, index, textSegment)
    }

    override fun findNext(textArea: TextArea, textSegment: String) {
        val selectedTxt: String? = textArea.selectedText

        if (selectedTxt.equals("") || selectedTxt.equals(textSegment).not()) {
            findFirst(textArea, textSegment)
        } else {
            val index = this.find(textArea.text, textSegment, textArea.selection.start)
            selectText(textArea, index, textSegment)
        }
    }

    override fun replace(textArea: TextArea, textSegment: String, replaceWith: String) {
        findFirst(textArea, textSegment)
        textArea.replaceSelection(replaceWith)
    }

    override fun replaceAll(textArea: TextArea, textSegment: String, replaceWith: String) {
        textArea.text = this.replaceAll(textArea.text, textSegment, replaceWith)
    }
}