package main.view.handler

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
            editorController.enableEditor(editor, file.name)
            editor.path = file.path.toString()
            editor.isChanged = false
            editor.textArea.text = fileController.getContent(file.path.toString(), Settings.APP_CHARSET)
            root!!.tabPane.selectionModel.select(editor.tab)

            root?.addTab(root!!.tabPane, Editor())
        }
    }

    fun saveFileRequest() {
        val editor: Editor? = editorController.get(root!!.tabPane.selectionModel.selectedItem)

        if (editor != null) {
            if (editor.path.isNullOrEmpty()) {
                val chooser = FileChooser()

                chooser.title = "Save File"
                val file = chooser.showSaveDialog(primaryStage)
                saveFile(editor, file.path.toString())
            } else {
                fileController.save(editor.path!!, editor.textArea.text)
                editor.isChanged = false
            }
        }
    }

    fun saveAsFileRequest() {
        val editor: Editor? = editorController.get(root!!.tabPane.selectionModel.selectedItem)

        if (editor != null) {
            val chooser = FileChooser()

            chooser.title = "Save File"
            val file = chooser.showSaveDialog(primaryStage)
            saveFile(editor, file.path.toString())
        }
    }

    fun exitAppRequest() {
    }

    fun saveFile(editor: Editor, path: String) {
        fileController.new(path, editor.tab.text, false)
        editor.isChanged = false
        TODO("Implement File already exists")
    }
}