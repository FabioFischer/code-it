package main.view.screen

import javafx.scene.input.KeyCodeCombination
import javafx.application.Application
import javafx.scene.control.*
import main.controller.EditorController
import main.controller.FileController
import main.controller.TextController

abstract class AbstractScreen(val screenWidth: Double, val screenHeight: Double, val screenName: String) : Application(), IScreenBuilder {
    val fileController = FileController()
    val editorController = EditorController()
    val textController = TextController()

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

    fun addSeparator(menu: Menu) {
        menu.items.add(SeparatorMenuItem())
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