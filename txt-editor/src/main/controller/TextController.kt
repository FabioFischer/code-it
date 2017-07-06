package main.controller

import main.util.ValidateUtils
import javax.swing.JTextArea

class TextController : ITextController {
    val NO_MATCH_FOUND: Int = -1

    private fun selectText(textArea: JTextArea, index: Int, textSegment: String) {
        if (index != NO_MATCH_FOUND)
            textArea.select(index, (index + textSegment.length))
    }

    private fun replaceAll(text: String, textSegment: String, replaceWith: String): String?{
        return text.replace(ValidateUtils.validateInput(textSegment, "Text input"), ValidateUtils.validateInput(replaceWith, "Replace text input"))
    }

    override fun find(text: String, textSegment: String, lastIndex: Int): Int {
            val index = text.toLowerCase().indexOf(ValidateUtils.validateInput(textSegment, "Text input").toLowerCase(), lastIndex)

            if (index == NO_MATCH_FOUND)
                throw IllegalArgumentException("No match for $textSegment!")

            return index
    }

    override fun findFirst(textArea: JTextArea, textSegment: String) {
        val index = this.find(textArea.text, textSegment)
        this.selectText(textArea, index, textSegment)
    }

    override fun findNext(textArea: JTextArea, textSegment: String) {
        var selectedTxt: String? = textArea.selectedText

        if (selectedTxt.equals("") || selectedTxt.equals(textSegment).not()) {
            this.findFirst(textArea, textSegment)
        } else {
            val index = this.find(textArea.text, textSegment, textArea.selectionStart)
            this.selectText(textArea, index, textSegment)
        }
    }

    override fun replace(textArea: JTextArea, textSegment: String, replaceWith: String) {
        this.findFirst(textArea, textSegment)
        textArea.replaceSelection(replaceWith)
    }

    override fun replaceAll(textArea: JTextArea, textSegment: String, replaceWith: String) {
        textArea.text = this.replaceAll(textArea.text, textSegment, replaceWith)
    }
}