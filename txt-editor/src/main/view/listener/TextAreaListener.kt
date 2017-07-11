package main.view.listener

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.control.TextArea

class TextAreaListener(var lineCounterTextArea : TextArea? = null) : ChangeListener<String>{
    override fun changed(observable: ObservableValue<out String>?, oldValue: String?, newValue: String?) {
//        println("lines: ${newValue!!.lines()} ||| size: ${newValue!!.lines().size}")
        writeLineCounter(newValue!!.lines())
    }

    fun writeLineCounter(textLines: List<String>) {
        var str: String = ""

        for (i in 1..textLines.size) {str += "$i\n"}

        lineCounterTextArea!!.text = str
    }

    companion object  {
        fun listen(lineCounterTextArea : TextArea? = null): TextAreaListener = TextAreaListener(lineCounterTextArea)
    }
}