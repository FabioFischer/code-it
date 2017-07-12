package main.model

import javafx.event.Event
import javafx.event.EventHandler
import javafx.scene.control.Tab
import javafx.scene.control.TextArea
import javafx.scene.layout.BorderPane
import main.util.Settings
import main.view.listener.TextAreaListener

class Editor(var isActive: Boolean = false, var fileName: String? = "+") {
    val tab: Tab = Tab()
    val borderPane: BorderPane = BorderPane()
    val textArea: TextArea = TextArea("")
    val lineCounter: TextArea = TextArea("1")

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
    }

    fun componentsPrefs() {
        textArea.setPrefSize( Double.MAX_VALUE, Double.MAX_VALUE )
        textArea.textProperty().addListener(TextAreaListener.listen(lineCounter))
        lineCounter.setPrefSize( Settings.LINE_COUNTER_WIDTH, Double.MAX_VALUE )

        lineCounter.isEditable = false
        lineCounter.isDisable = true

        borderPane.center = textArea
        borderPane.left = lineCounter
    }
}