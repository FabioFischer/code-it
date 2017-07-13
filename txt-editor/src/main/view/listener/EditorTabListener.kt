package main.view.listener

import javafx.event.Event
import javafx.event.EventHandler
import main.controller.EditorController
import main.model.Editor
import main.view.screen.AbstractScreen

class EditorTabListener {
    class onSelectionRequest(var screen: AbstractScreen, var editorController: EditorController, var editor: Editor): EventHandler<Event> {
        override fun handle(event: Event?) {
            if (editor.tab.isSelected && editor.isActive.not()) {
                editorController.enableEditor(editor)
                screen.addTab(screen.tabPane, Editor())
            }
        }
    }

    class onCloseRequest(var editorController: EditorController, var editor: Editor): EventHandler<Event> {
        override fun handle(event: Event?) {
            editorController.delete(editor)
        }
    }
}