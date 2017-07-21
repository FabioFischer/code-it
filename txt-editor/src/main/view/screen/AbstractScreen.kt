package main.view.screen

import javafx.scene.input.KeyCodeCombination
import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.TextAlignment
import main.controller.EditorController
import main.controller.FileController
import main.controller.TextController

abstract class AbstractScreen(val screenWidth: Double, val screenHeight: Double, val screenName: String) : Application(), IScreenBuilder {
    val fileController = FileController()
    val editorController = EditorController()
    val textController = TextController()

    fun addSeparator(menu: Menu) {
        menu.items.add(SeparatorMenuItem())
    }

    fun initButton(button: Button, text: String, listener: EventHandler<ActionEvent>) {
        button.text = text
        button.onAction = listener
        button.setPrefSize(90.0, 28.0)
    }

    fun initHBox(hBox: HBox, spacing: Double, position: Pos, vararg elements: Node) {
        hBox.spacing = spacing
        hBox.alignment = position

        hBox.children.addAll(elements)
    }

    fun initLabel(label: Label, text: String, textAlignment: TextAlignment) {
        label.text = text
        label.textAlignment = textAlignment
    }

    fun initLabelTextField(promptText: String, spacing: Double): HBox {
        val label = Label(promptText)
        val textField = TextField()
        val hBox = HBox()

        initHBox(hBox, spacing, Pos.CENTER_RIGHT, label, textField)

        return hBox
    }

    fun initMenu(menu: Menu, menuBar: MenuBar, text: String) {
        menu.text = text
        menuBar.menus.add(menu)
    }

    fun initMenuItem(menuItem: MenuItem, menu: Menu, text: String, keyCodeCombination: KeyCodeCombination? = null) {
        menuItem.text = text
        if (keyCodeCombination != null)
            menuItem.accelerator = keyCodeCombination

        menu.items.add(menuItem)
    }

    fun initRadioButton(radioButton: RadioButton, text: String) {
        radioButton.text = text
    }

    fun initVBox(vBox: VBox, spacing: Double, position: Pos, vararg elements: Node) {
        vBox.spacing = spacing
        vBox.alignment = position

        vBox.children.addAll(elements)
    }

    fun showDialogMessage(title: String, headerMessage: String, bodyMessage: String, type: Alert.AlertType) {
        val dialog: Alert = Alert(type)

        dialog.title = title
        dialog.headerText = headerMessage
        dialog.contentText = bodyMessage
        dialog.showAndWait()
    }

    fun showDialogOptions(title: String, headerMessage: String, bodyMessage: String, type: Alert.AlertType): ButtonType? {
        val dialog: Alert = Alert(type)
        var response: ButtonType? = null

        dialog.title = title
        dialog.headerText = headerMessage
        dialog.contentText = bodyMessage
        dialog.buttonTypes.setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL)

        dialog.showAndWait().ifPresent { b ->
            response = b
        }

        return response
    }
}