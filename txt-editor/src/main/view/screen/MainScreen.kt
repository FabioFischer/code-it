package main.view.screen

import javafx.event.EventHandler
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.layout.VBoxBuilder
import javafx.scene.layout.VBox
import main.controller.FileController
import java.util.Vector
import javafx.scene.control.TabPane




class MainScreen : AbstractScreen(800.0, 900.0, "Text Editor") {
    private val fileController: FileController = FileController()

    private var menuBar: MenuBar = MenuBar()

    private var fileMenu: Menu = Menu()
    private var editMenu: Menu = Menu()
    private var aboutMenu: Menu = Menu()

    private var fileMenuNew: MenuItem = MenuItem()
    private var fileMenuOpen: MenuItem = MenuItem()
    private var fileMenuSave: MenuItem = MenuItem()
    private var fileMenuExit: MenuItem = MenuItem()

    private val tabPane: TabPane = TabPane()

    override fun start(primaryStage: Stage) {
        createComponents()

        val layout: VBox = VBoxBuilder.create().spacing(10.0).children(menuBar, tabPane).build()
        layout.isFillWidth = true

        val scene = Scene(layout,  this.screenHeight, this.screenWidth)

        tabPane.prefWidthProperty().bind(scene.widthProperty())
        tabPane.prefHeightProperty().bind(scene.heightProperty())

        primaryStage.scene = scene
        primaryStage.title = this.screenName
        primaryStage.isMaximized = true
        primaryStage.show()
    }

    override fun createComponents() {
        createMenus()
    }

    override fun createButtons() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createMenus() {
        this.initMenuItem(fileMenuNew, fileMenu, "New", EventHandler { println("New") })
        this.initMenuItem(fileMenuOpen, fileMenu, "Open", EventHandler { println("Open") })
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