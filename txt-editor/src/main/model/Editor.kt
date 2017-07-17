package main.model

import javafx.beans.value.ChangeListener
import javafx.event.Event
import javafx.event.EventHandler
import javafx.scene.control.Tab
import javafx.scene.control.TextArea
import javafx.scene.layout.BorderPane
import main.util.Settings
import java.nio.charset.Charset

class Editor(var isActive: Boolean = false, var fileName: String? = "+") {
    val tab: Tab = Tab()
    val borderPane: BorderPane = BorderPane()
    val textArea: TextArea = TextArea("")
    val lineCounter: TextArea = TextArea("1")

    var path: String? = null
    var isChanged: Boolean = true

    var textAreaListener: ChangeListener<String>? = null
        set(listener) {
            textArea.textProperty().addListener(listener)
        }

    var onSelectRequest: EventHandler<Event>? = null
        set(handler) {
            tab.onSelectionChanged = handler
        }

    var onCloseRequest: EventHandler<Event>? = null
        set(handler) {
            tab.onCloseRequest = handler
        }

    init {
        changeName(fileName)
        componentsPrefs()

        tab.content = borderPane
    }

    fun changeName(name: String?) {
        this.fileName = name
        this.tab.text = name
        this.isChanged = true
    }

    fun componentsPrefs() {
        textArea.setPrefSize( Double.MAX_VALUE, Double.MAX_VALUE )
        lineCounter.setPrefSize( Settings.LINE_COUNTER_WIDTH, Double.MAX_VALUE )

        lineCounter.isEditable = false
        lineCounter.isDisable = true

        borderPane.center = textArea
        borderPane.left = lineCounter
    }
}