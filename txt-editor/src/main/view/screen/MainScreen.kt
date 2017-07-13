package main.view.screen

import javafx.event.EventHandler
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.BorderPane

class MainScreen : AbstractScreen(600.0, 700.0, "Text Editor") {
    private val upperMenuBar: MenuBar = MenuBar()
    private val leftMenuBar: MenuBar = MenuBar()

    private val fileMenu: Menu = Menu()
    private val editMenu: Menu = Menu()
    private val aboutMenu: Menu = Menu()

    private val fileMenuNew: MenuItem = MenuItem()
    private val fileMenuOpen: MenuItem = MenuItem()
    private val fileMenuSave: MenuItem = MenuItem()
    private val fileMenuExit: MenuItem = MenuItem()

    override fun start(primaryStage: Stage) {
        val root = BorderPane()

        initComponents()

        primaryStage.scene = initScene(root)
        primaryStage.title = this.screenName
        primaryStage.isMaximized = true

        primaryStage.show()
    }

    override fun initScene(pane: BorderPane): Scene {
        pane.top = upperMenuBar
        pane.left = leftMenuBar
        pane.center = tabPane

        val scene = Scene(pane,  this.screenHeight, this.screenWidth)

        pane.prefHeightProperty().bind(scene.heightProperty())
        pane.prefWidthProperty().bind(scene.widthProperty())

        return scene
    }

    override fun initComponents() {
        initMenus()
        initButtons()

        tabPane.tabs.addAll(editorController.getAllTabs()!!)

        for (editor in editorController.editors!!) linkListeners(editor)
    }

    override fun initButtons() {
    }

    override fun initMenus() {
        initMenuItem(fileMenuNew, fileMenu, "New", EventHandler { println("New") })
        addSeparator(fileMenu)
        initMenuItem(fileMenuOpen, fileMenu, "Open", EventHandler { println("Open") })
        initMenuItem(fileMenuSave, fileMenu, "Save", EventHandler {  })
        addSeparator(fileMenu)
        initMenuItem(fileMenuExit, fileMenu, "Exit", EventHandler {  })

        initMenu(fileMenu, upperMenuBar, "File")
        initMenu(editMenu, upperMenuBar, "Edit")
        initMenu(aboutMenu, upperMenuBar, "About")
    }
}