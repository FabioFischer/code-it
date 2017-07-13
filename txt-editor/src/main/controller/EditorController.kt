package main.controller;

import javafx.beans.value.ChangeListener
import javafx.event.Event
import javafx.event.EventHandler
import javafx.scene.control.Tab
import main.model.Editor
import main.util.Settings
import main.util.Validator

class EditorController: IEditorController {
    private val serialVersionUID = 1L

    val editors: ArrayList<Editor>? = ArrayList()

    init {
        // TODO Recover working files on previously session
        editors!!.add(Editor(true, getUntitledFileName()))
        editors.add(Editor())
    }

    fun getUntitledFileName(): String {
        return Settings.NEW_FILE_NAME.replace("#", if (editors!!.size.toString().isNullOrEmpty() || editors.size == 0) "1" else editors.size.toString())
    }

    fun enableEditor(editor: Editor) {
        editor.isActive = true
        rename(editor, getUntitledFileName())
    }

    override fun get(tab: Tab): Editor? {
        return editors!!.firstOrNull { it.tab == tab }
    }

    override fun getAll(): List<Editor>? {
        return editors!!
    }

    override fun getAllTabs(): List<Tab>? {
        return editors!!.map { it.tab }
    }

    override fun add(editor: Editor) {
        if (Validator.isValidEditor(editor)) {
            editors!!.add(editor)
        }
    }

    override fun delete(editor: Editor) {
        if (editors!!.contains(editor)) {
            editors.remove(editor)
        }
    }

    override fun rename(editor: Editor, name: String) {
        if (editors!!.contains(editor)) {
            editor.changeName(name)
        }
    }
}
