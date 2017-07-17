package main.view.screen

import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import main.controller.EditorController
import main.controller.FileController
import main.model.Editor
import main.view.handler.EditorFileHandler
import main.view.handler.EditorTabHandler
import main.view.listener.EditorTextListener


class MainScreen : AbstractScreen(600.0, 700.0, "Text Editor") {
    private val upperMenuBar = MenuBar()
    private val leftMenuBar = MenuBar()

    private val fileMenu = Menu()
    private val editMenu = Menu()
    private val aboutMenu = Menu()

    private val fileMenuNew = MenuItem()
    private val fileMenuOpen = MenuItem()
    private val fileMenuSave = MenuItem()
    private val fileMenuSaveAs = MenuItem()
    private val fileMenuExit = MenuItem()

    val fileController = FileController()
    val editorController = EditorController()
    val editorFileHandler = EditorFileHandler(editorController, fileController)

    val tabPane: TabPane = TabPane()

    override fun start(primaryStage: Stage) {
        val root = BorderPane()

        initComponents(primaryStage)

        primaryStage.scene = initScene(root)
        primaryStage.title = this.screenName
        primaryStage.isMaximized = true

        editorFileHandler.primaryStage = primaryStage
        editorFileHandler.root = this

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

    override fun initComponents(primaryStage: Stage) {
        initMenus()
        initButtons()
        linkEditorHandlers()
        linkMenuItemHandlers(primaryStage)

        tabPane.tabs.addAll(editorController.getAllTabs()!!)
    }

    override fun initButtons() {
    }

    override fun initMenus() {
        initMenuItem(fileMenuNew, fileMenu, "New")
        addSeparator(fileMenu)
        initMenuItem(fileMenuOpen, fileMenu, "Open")
        initMenuItem(fileMenuSave, fileMenu, "Save")
        initMenuItem(fileMenuSaveAs, fileMenu, "Save As...")
        addSeparator(fileMenu)
        initMenuItem(fileMenuExit, fileMenu, "Exit")

        initMenu(fileMenu, upperMenuBar, "File")
        initMenu(editMenu, upperMenuBar, "Edit")
        initMenu(aboutMenu, upperMenuBar, "About")
    }

    fun addTab(tabPane: TabPane, editor: Editor) {
        editorController.add(editor)
        tabPane.tabs.add(editor.tab)
        linkEditorHandlers(editor)
    }

    private fun linkMenuItemHandlers(primaryStage: Stage) {
        fileMenuNew.setOnAction {
            editorFileHandler.newFileRequest()
        }
        fileMenuOpen.setOnAction {
            editorFileHandler.openFileRequest()
        }
        fileMenuSave.setOnAction {
            editorFileHandler.saveFileRequest()
        }
        fileMenuSaveAs.setOnAction {
            editorFileHandler.saveAsFileRequest()
        }
        fileMenuExit.setOnAction {
            editorFileHandler.saveAsFileRequest()
        }
    }

    private fun linkEditorHandlers() {
        for (editor in editorController.editors!!) linkEditorHandlers(editor)
    }

    private fun linkEditorHandlers(editor: Editor) {
        editor.textAreaListener = EditorTextListener.listen(editor)
        editor.onSelectRequest = EditorTabHandler.onSelectionRequest(this, editorController)
        editor.onCloseRequest = EditorTabHandler.onCloseRequest(editorController)
    }
}