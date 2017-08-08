package main.view.handler

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import main.util.Settings
import main.view.screen.impl.SearchScreen

class SearchTextHandler {
    class onSearchFindRequest(var root: SearchScreen): EventHandler<ActionEvent> {
        override fun handle(event: ActionEvent?) {
            val editor = root.editorController.get(root.mainScreen!!.tabPane.selectionModel.selectedItem)

            if (editor != null) {
                try {
                    root.textController.findFirst(editor.textArea, root.textFind.text)
                } catch(e: IllegalArgumentException) {
                    root.showDialogMessage(Settings.APP_NAME, "Error at finding the text.", e.message!!, Alert.AlertType.ERROR)
                }
            }
        }
    }

    class onSearchFindNextRequest(var root: SearchScreen): EventHandler<ActionEvent> {
        override fun handle(event: ActionEvent?) {
            val editor = root.editorController.get(root.mainScreen!!.tabPane.selectionModel.selectedItem)

            if (editor != null) {
                try {
                    root.textController.findNext(editor.textArea, root.textFind.text)
                } catch(e: IllegalArgumentException) {
                    root.showDialogMessage(Settings.APP_NAME, "Error at finding the text", e.message!!, Alert.AlertType.ERROR)
                }
            }
        }
    }

    class onSearchReplaceRequest(var root: SearchScreen): EventHandler<ActionEvent> {
        override fun handle(event: ActionEvent?) {
            val editor = root.editorController.get(root.mainScreen!!.tabPane.selectionModel.selectedItem)

            if (editor != null) {
                try {
                    root.textController.replace(editor.textArea, root.textFind.text, root.textReplace.text)
                } catch(e: IllegalArgumentException) {
                    root.showDialogMessage(Settings.APP_NAME, "Error at replacing the match", e.message!!, Alert.AlertType.ERROR)
                }
            }
        }
    }

    class onSearchReplaceAllRequest(var root: SearchScreen): EventHandler<ActionEvent> {
        override fun handle(event: ActionEvent?) {
            val editor = root.editorController.get(root.mainScreen!!.tabPane.selectionModel.selectedItem)

            if (editor != null) {
                try {
                    root.textController.replace(editor.textArea, root.textFind.text, root.textReplace.text)
                } catch(e: IllegalArgumentException) {
                    root.showDialogMessage(Settings.APP_NAME, "Error at replacing all the matches.", e.message!!, Alert.AlertType.ERROR)
                }
            }
        }
    }
}