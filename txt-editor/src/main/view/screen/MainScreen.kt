package main.view.screen

import javafx.application.Application
import javafx.event.EventHandler
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.input.KeyEvent
import javafx.scene.layout.StackPane
import javafx.scene.layout.GridPane.setFillWidth
import javafx.scene.layout.VBoxBuilder
import javafx.scene.layout.VBox



class MainScreen : AbstractScreen(800.0, 900.0, "Text Editor") {
    var menuBar: MenuBar = MenuBar()

    var fileMenu: Menu = Menu()
    var editMenu: Menu = Menu()
    var aboutMenu: Menu = Menu()

    var fileMenuNew: MenuItem = MenuItem()
    var fileMenuOpen: MenuItem = MenuItem()
    var fileMenuSave: MenuItem = MenuItem()
    var fileMenuExit: MenuItem = MenuItem()

    override fun start(primaryStage: Stage) {
        createComponents()

        val layout: VBox = VBoxBuilder.create().spacing(10.0).children(menuBar).build()
        layout.setFillWidth(true)

        val scene = Scene(layout,  this.screenHeight, this.screenWidth)

        primaryStage.scene = scene
        primaryStage.title = this.screenName
        primaryStage.show()
    }

    override fun createComponents() {
        createMenus()
    }

    override fun createButtons() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createMenus() {
        this.initMenuItem(fileMenuNew, fileMenu, "New", EventHandler {  })
        this.initMenuItem(fileMenuOpen, fileMenu, "Open", EventHandler {  })
        this.initMenuItem(fileMenuSave, fileMenu, "Save", EventHandler {  })
        this.initMenuItem(fileMenuExit, fileMenu, "Exit", EventHandler {  })

        this.initMenu(fileMenu, menuBar, "File")
        this.initMenu(editMenu, menuBar, "Edit")
        this.initMenu(aboutMenu, menuBar, "About")
    }

    override fun createKeyStrokes() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MainScreen::class.java)
        }
    }
}