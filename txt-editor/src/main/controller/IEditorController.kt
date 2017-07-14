package main.controller

import javafx.scene.control.Tab
import main.model.Editor

interface IEditorController {
    fun get(tab: Tab) : Editor?
    fun getAll() : List<Editor>?
    fun getAllTabs() : List<Tab>?
    fun add(editor: Editor)
    fun delete(editor: Editor)
    fun rename(editor: Editor?, name: String)
}