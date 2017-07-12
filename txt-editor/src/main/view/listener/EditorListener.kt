package main.view.listener

import javafx.event.Event
import javafx.event.EventHandler
import main.controller.EditorController
import main.model.Editor

class EditorListener {
    class onSelectionRequest(var editorController: EditorController, var editor: Editor): EventHandler<Event> {
        override fun handle(event: Event?) {
            if (editor.tab.isSelected && editor.isActive.not()) {
                println("Adding tab...")
                editorController.enableEditor(editor)
            }
        }
    }

    class onCloseRequest(var editorController: EditorController, var editor: Editor): EventHandler<Event> {
        override fun handle(event: Event?) {
        }
    }
}