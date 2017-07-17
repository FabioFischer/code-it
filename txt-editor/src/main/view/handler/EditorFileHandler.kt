package main.view.handler

import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.FileChooser
import javafx.stage.Stage
import main.controller.EditorController
import main.controller.FileController
import main.model.Editor
import main.util.Settings
import main.view.screen.MainScreen
import java.io.File

class EditorFileHandler(val editorController: EditorController, val fileController: FileController) {
    var primaryStage: Stage? = null
    var root: MainScreen? = null

    fun newFileRequest() {
        val editor: Editor = editorController.editors?.firstOrNull { it.isActive.not() }!!

        editorController.enableEditor(editor)
        root?.addTab(root!!.tabPane, Editor())
        root!!.tabPane.selectionModel.select(editor.tab)
    }

    fun openFileRequest() {
        val chooser = FileChooser()

        chooser.title = "Open File"
        chooser.extensionFilters.add(Settings.VALID_EXTENSIONS)
        chooser.initialDirectory = File(Settings.DEFAULT_PROJECTS_DIRECTORY)

        val editor: Editor = editorController.editors?.firstOrNull { it.isActive.not() }!!
        val file = chooser.showOpenDialog(primaryStage)

        if (file != null) {
            openFile(file, editor)
        }
    }

    fun saveFileRequest() {
        val editor: Editor? = editorController.get(root!!.tabPane.selectionModel.selectedItem)
        saveFile(editor)
    }

    fun saveAsFileRequest() {
        val editor: Editor? = editorController.get(root!!.tabPane.selectionModel.selectedItem)
        saveAsFile(editor)
    }

    fun closeFileRequest() {
        val editor: Editor? = editorController.get(root!!.tabPane.selectionModel.selectedItem)

        if (editor != null) {
            if (editor.isChanged) {
                when (root?.showDialogOptions( Settings.APP_NAME, "Do you want to save the changes you made to ${editor.fileName}?", "Your changes will be lost if you don't save them.", Alert.AlertType.WARNING)) {
                    ButtonType.YES -> {
                        saveFile(editor)
                        closeFile(editor)
                    }
                    ButtonType.NO -> {
                        closeFile(editor)
                    }
                }
            }
        }
    }

    private fun newFile(editor: Editor, path: String): Boolean {
        if (path.isNullOrEmpty().not()) {
            try {
                fileController.new(path, editor.tab.text, false)
                editor.isChanged = false

                return true
            } catch (e: IllegalArgumentException) {
                root?.showDialogMessage(Settings.APP_NAME, "Error at saving ${editor.fileName}.", e.message!!, Alert.AlertType.ERROR)
            }
        }
        return false
    }

    private fun openFile(file: File, editor: Editor) {
        editorController.enableEditor(editor, file.name)
        editor.path = file.path.toString()
        editor.isChanged = false
        editor.textArea.text = fileController.getContent(file.path.toString(), Settings.APP_CHARSET)
        root!!.tabPane.selectionModel.select(editor.tab)

        root?.addTab(root!!.tabPane, Editor())
    }

    private fun saveFile(editor: Editor?) {
        if (editor != null) {
            if (editor.path.isNullOrEmpty()) {
                val chooser = FileChooser()

                chooser.title = "Save File"
                val file = chooser.showSaveDialog(primaryStage)
                newFile(editor, file.path.toString())
            } else {
                fileController.save(editor.path!!, editor.textArea.text)
                editor.isChanged = false
            }
        }
    }

    private fun saveAsFile(editor: Editor?) {
        if (editor != null) {
            val chooser = FileChooser()

            chooser.title = "Save File"
            val file = chooser.showSaveDialog(primaryStage)
            newFile(editor, file.path.toString())
        }
    }

    private fun closeFile(editor: Editor?) {
        root!!.tabPane.tabs.remove(editor?.tab)
        editorController.editors?.remove(editor)
    }
}