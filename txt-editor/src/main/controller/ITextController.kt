package main.controller

import javax.swing.JTextArea

/**
 * Created by fabio.fischer on 05/07/2017.
 */
interface ITextController {
    fun find(text: String, textSegment: String, lastIndex: Int = -1): Int
    fun findFirst(textArea: JTextArea, textSegment: String)
    fun findNext(textArea: JTextArea, textSegment: String)
    fun replace(textArea: JTextArea, textSegment: String, replaceWith: String)
    fun replaceAll(textArea: JTextArea, textSegment: String, replaceWith: String)
}