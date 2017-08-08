package main.view.listener

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import main.model.Editor

class EditorTextListener(var editor: Editor): ChangeListener<String>{
    override fun changed(observable: ObservableValue<out String>?, oldValue: String?, newValue: String?) {
        writeLineCounter(newValue!!.lines())

        if (oldValue.isNullOrEmpty().not() && oldValue != newValue) editor.isChanged = true
    }

    fun writeLineCounter(textLines: List<String>) {
        var str: String = ""

        for (i in 1..textLines.size) {str += "$i\n"}

        println("T Size ${editor.textArea.text.lines().size}")
        println("E Size ${editor.lineCounter.text.lines().size}")

        editor.lineCounter.text = str
        editor.lineCounter.scrollTopProperty().bindBidirectional(editor.textArea.scrollTopProperty())
        editor.lineCounter.scrollLeftProperty().bindBidirectional(editor.textArea.scrollLeftProperty())
    }

    companion object  {
        fun listen(editor: Editor): EditorTextListener = EditorTextListener(editor)
    }
}