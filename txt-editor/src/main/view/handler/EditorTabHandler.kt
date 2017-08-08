package main.view.handler

import javafx.event.Event
import javafx.event.EventHandler
import javafx.scene.control.Tab
import main.model.Editor
import main.util.Settings
import main.view.screen.impl.MainScreen

class EditorTabHandler {
    class onSelectionRequest(var root: MainScreen): EventHandler<Event> {
        override fun handle(event: Event?) {
            val editor = root.editorController.get(tab = event?.source as Tab)

            if (editor != null) {
                if (editor.tab.isSelected) {
                    if (editor.isActive.not()) {
                        root.editorController.enableEditor(editor)
                        root.addTab(root.tabPane, Editor())
                    }

                    root.rename("${editor.fileName} - ${Settings.APP_NAME}")
                }
            }
        }
    }

    class onCloseRequest(var root: MainScreen): EventHandler<Event> {
        override fun handle(event: Event?) {
            if (event != null) {
                root.editorFileHandler.closeFileRequest()
            }
        }
    }
}