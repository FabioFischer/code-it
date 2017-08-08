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
    val content: TextArea = TextArea("")
    val lineCounter: TextArea = TextArea("1")

    var charset: Charset = Charset.defaultCharset()
    var extension: String = ".txt"
    var isChanged: Boolean = false
    var path: String? = null

    var textAreaListener: ChangeListener<String>? = null
        set(listener) {
            content.textProperty().addListener(listener)
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
        fileName = name
        tab.text = name
        isChanged = content.text.isNullOrEmpty().not()
    }

    fun componentsPrefs() {
        content.setPrefSize(borderPane.width, borderPane.height )
        lineCounter.setPrefSize(Settings.LINE_COUNTER_WIDTH, borderPane.height )

        lineCounter.isEditable = false
        lineCounter.isDisable = true

        borderPane.center = content
        borderPane.left = lineCounter
    }
}