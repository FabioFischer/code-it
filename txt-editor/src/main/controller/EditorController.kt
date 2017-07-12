package main.controller;

import javafx.scene.control.Tab
import main.model.Editor
import main.util.Settings
import main.util.Validator

class EditorController: IEditorController {
    private val serialVersionUID = 1L
    private val newEditor: Editor? = Editor()

    val editors: ArrayList<Editor>? = ArrayList()

    init {
        // TODO Recover working files on previously session
        editors!!.add(newEditor!!)
        editors.add(Editor(true, Settings.NEW_FILE_NAME.replace('#', if (editors.size.toString().isNullOrEmpty()) '1' else editors.size.toChar())))
    }

    override fun get(tab: Tab): Editor? {
        return editors!!.firstOrNull { it.tab.equals(tab) }
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
            editor.fileName = name
        }
    }
}
