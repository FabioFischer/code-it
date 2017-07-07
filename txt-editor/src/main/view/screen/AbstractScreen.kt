package main.view.screen

import javafx.application.Application
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.stage.Stage
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.MenuBar


abstract class AbstractScreen(val screenWidth: Double, val screenHeight: Double, val screenName: String) : Application(), IScreenBuilder {
    private val serialVersionUID = 1L
    private var primaryStage: Stage? = null

    fun initMenu(menu: Menu, menuBar: MenuBar, text: String) {
        menu.text = text
        menuBar.menus.add(menu)
    }

    fun initMenuItem(menuItem: MenuItem, menu: Menu, text: String, handler: EventHandler<ActionEvent>) {
        menuItem.text = text
        menuItem.setOnAction{handler}
        menu.items.add(menuItem)
    }
}