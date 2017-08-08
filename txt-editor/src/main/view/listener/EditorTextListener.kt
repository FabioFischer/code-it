package main.view.listener

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import main.model.Editor
import main.view.screen.impl.MainScreen

class EditorTextListener(var root: MainScreen): ChangeListener<String>{
    override fun changed(observable: ObservableValue<out String>?, oldValue: String?, newValue: String?) {
        val editor: Editor? = root.editorController.get(root.tabPane.selectionModel.selectedItem)

        if (editor != null) {
            updateEditorInfo(editor)
            writeLineCounter(editor, newValue!!.lines())
            if (oldValue.isNullOrEmpty().not() && oldValue != newValue) editor.isChanged = true
        }
    }

    fun writeLineCounter(editor: Editor, textLines: List<String>) {
        var str: String = ""

        for (i in 1..textLines.size) {str += "$i\n"}

        editor.lineCounter.text = str
        editor.lineCounter.scrollTopProperty().bindBidirectional(editor.content.scrollTopProperty())
    }

    fun updateEditorInfo(editor: Editor) {
        // TODO: Add listener on editor content TextArea to update caret position
        root.updateFileCharsetLabel("UTF-8")
        root.updateFileExtensionLabel("Plain Text .txt")
        root.updateCaretPosition(1, 1)
    }

    companion object  {
        fun listen(root: MainScreen): EditorTextListener = EditorTextListener(root)
    }
}