package main.controller

import main.model.Editor

interface IEditorController {
    fun add(editor: Editor)
    fun delete(editor: Editor)
}