package main.view.screen

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.*
import main.controller.EditorController
import main.controller.FileController
import main.model.Editor
import main.view.listener.EditorTabListener
import main.view.listener.EditorTextListener


abstract class AbstractScreen(val screenWidth: Double, val screenHeight: Double, val screenName: String) : Application(), IScreenBuilder {
    val fileController: FileController = FileController()
    val editorController: EditorController = EditorController()

    val tabPane: TabPane = TabPane()

    fun initMenu(menu: Menu, menuBar: MenuBar, text: String) {
        menu.text = text
        menuBar.menus.add(menu)
    }

    fun initMenuItem(menuItem: MenuItem, menu: Menu, text: String, handler: EventHandler<ActionEvent>) {
        menuItem.text = text
        menuItem.setOnAction{handler}
        menu.items.add(menuItem)
    }

    fun addSeparator(menu: Menu) {
        menu.items.add(SeparatorMenuItem())
    }

    fun addTab(tabPane: TabPane, editor: Editor) {
        editorController.add(editor)
        tabPane.tabs.add(editor.tab)
        linkListeners(editor)
    }

    fun linkListeners(editor: Editor) {
        editor.textAreaListener = EditorTextListener.listen(editor)
        editor.onSelectRequest = EditorTabListener.onSelectionRequest(this, editorController, editor)
        editor.onCloseRequest = EditorTabListener.onCloseRequest(editorController, editor)
    }
}