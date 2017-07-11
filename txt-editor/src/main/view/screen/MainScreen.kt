package main.view.screen

import javafx.event.EventHandler
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.control.*
import main.controller.FileController
import javafx.scene.layout.BorderPane
import javafx.geometry.Pos
import com.sun.javafx.robot.impl.FXRobotHelper.getChildren
import javafx.scene.layout.HBox
import javafx.scene.control.Tab
import main.model.Editor


class MainScreen : AbstractScreen(600.0, 700.0, "Text Editor") {
    private val fileController: FileController = FileController()

    private var upperMenuBar: MenuBar = MenuBar()
    private var leftMenuBar: MenuBar = MenuBar()

    private var fileMenu: Menu = Menu()
    private var editMenu: Menu = Menu()
    private var aboutMenu: Menu = Menu()

    private var fileMenuNew: MenuItem = MenuItem()
    private var fileMenuOpen: MenuItem = MenuItem()
    private var fileMenuSave: MenuItem = MenuItem()
    private var fileMenuExit: MenuItem = MenuItem()

    private var mainTab: TabPane = TabPane()

    override fun start(primaryStage: Stage) {
        val root = BorderPane()

        createComponents()
//
//        val tab = Tab("new-file")
//        val textArea: TextArea = TextArea("")
//        textArea.setPrefSize( Double.MAX_VALUE, Double.MAX_VALUE )
//
//        tab.content = textArea

        val editor: Editor = Editor()

        mainTab.tabs.add(editor.tab)

        root.top = upperMenuBar
        root.left = leftMenuBar
        root.center = mainTab

        val scene = Scene(root,  this.screenHeight, this.screenWidth)

        root.prefHeightProperty().bind(scene.heightProperty())
        root.prefWidthProperty().bind(scene.widthProperty())

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
        fileMenu.items.add(SeparatorMenuItem())
        this.initMenuItem(fileMenuOpen, fileMenu, "Open", EventHandler { println("Open") })
        this.initMenuItem(fileMenuSave, fileMenu, "Save", EventHandler {  })
        fileMenu.items.add(SeparatorMenuItem())
        this.initMenuItem(fileMenuExit, fileMenu, "Exit", EventHandler {  })

        this.initMenu(fileMenu, upperMenuBar, "File")
        this.initMenu(editMenu, upperMenuBar, "Edit")
        this.initMenu(aboutMenu, upperMenuBar, "About")
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