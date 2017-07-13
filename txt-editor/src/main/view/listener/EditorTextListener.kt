package main.view.listener

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import main.model.Editor

class EditorTextListener(var editor: Editor? = null): ChangeListener<String>{
    override fun changed(observable: ObservableValue<out String>?, oldValue: String?, newValue: String?) {
        writeLineCounter(newValue!!.lines())
    }

    fun writeLineCounter(textLines: List<String>) {
        var str: String = ""

        for (i in 1..textLines.size) {str += "$i\n"}

        editor!!.lineCounter.text = str
    }

    companion object  {
        fun listen(editor: Editor? = null): EditorTextListener = EditorTextListener(editor)
    }
}