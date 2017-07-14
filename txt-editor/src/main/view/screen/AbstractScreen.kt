package main.view.screen

import javafx.application.Application
import javafx.scene.control.*
import main.controller.EditorController
import main.controller.FileController
import main.model.Editor
import main.view.handler.EditorTabHandler
import main.view.listener.EditorTextListener

abstract class AbstractScreen(val screenWidth: Double, val screenHeight: Double, val screenName: String) : Application(), IScreenBuilder {
    fun initMenu(menu: Menu, menuBar: MenuBar, text: String) {
        menu.text = text
        menuBar.menus.add(menu)
    }

    fun initMenuItem(menuItem: MenuItem, menu: Menu, text: String) {
        menuItem.text = text
        menu.items.add(menuItem)
    }

    fun addSeparator(menu: Menu) {
        menu.items.add(SeparatorMenuItem())
    }
}