package main.view.handler

import javafx.event.Event
import javafx.event.EventHandler
import javafx.scene.control.Tab
import main.controller.EditorController
import main.model.Editor
import main.view.screen.MainScreen

class EditorTabHandler {
    class onSelectionRequest(var root: MainScreen, var editorController: EditorController): EventHandler<Event> {
        override fun handle(event: Event?) {
            val editor = editorController.get(tab = event?.source as Tab)

            if (editor != null) {
                if (editor.tab.isSelected) {
                    if (editor.isActive.not()) {
                        editorController.enableEditor(editor)
                        root.addTab(root.tabPane, Editor())
                    }
                }
            }
        }
    }

    class onCloseRequest(var editorController: EditorController): EventHandler<Event> {
        override fun handle(event: Event?) {
            if (event != null) {
                editorController.delete(editorController.get(tab = event.source as Tab)!!)
            }
        }
    }
}