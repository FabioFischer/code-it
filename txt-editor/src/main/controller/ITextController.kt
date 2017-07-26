package main.controller

import javafx.scene.control.TextArea

interface ITextController {
    fun findFirst(textArea: TextArea, textSegment: String)
    fun findNext(textArea: TextArea, textSegment: String)
    fun replace(textArea: TextArea, textSegment: String, replaceWith: String)
    fun replaceAll(textArea: TextArea, textSegment: String, replaceWith: String)
}