package main.view.screen

import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.BorderPane
import main.model.Editor
import main.util.Resources
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
    private val fileMenuCloseFile = MenuItem()

    val editorFileHandler = EditorFileHandler(editorController, fileController)
    val tabPane: TabPane = TabPane()

    override fun start(primaryStage: Stage) {
        val root = BorderPane()

        initComponents(primaryStage)

        primaryStage.scene = initScene(root)
        primaryStage.title = screenName
        primaryStage.icons.add(Resources.appIcon)
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
        linkEditorHandlers()
        linkMenuItemHandlers()

        tabPane.tabs.addAll(editorController.getAllTabs()!!)
    }

    override fun initMenus() {
        initMenuItem(fileMenuNew, fileMenu, "New", KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN))
        addSeparator(fileMenu)
        initMenuItem(fileMenuOpen, fileMenu, "Open", KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN))
        initMenuItem(fileMenuSave, fileMenu, "Save", KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN))
        initMenuItem(fileMenuSaveAs, fileMenu, "Save As...", KeyCodeCombination(KeyCode.S, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN))
        addSeparator(fileMenu)
        initMenuItem(fileMenuCloseFile, fileMenu, "Close Tab", KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN))

        initMenu(fileMenu, upperMenuBar, "File")
        initMenu(editMenu, upperMenuBar, "Edit")
        initMenu(aboutMenu, upperMenuBar, "About")
    }

    fun addTab(tabPane: TabPane, editor: Editor) {
        editorController.add(editor)
        tabPane.tabs.add(editor.tab)
        linkEditorHandlers(editor)
    }

    private fun linkMenuItemHandlers() {
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
        fileMenuCloseFile.setOnAction {
            editorFileHandler.closeFileRequest()
        }
    }

    private fun linkEditorHandlers() {
        for (editor in editorController.editors!!) linkEditorHandlers(editor)
    }

    private fun linkEditorHandlers(editor: Editor) {
        editor.textAreaListener = EditorTextListener.listen(editor)
        editor.onSelectRequest = EditorTabHandler.onSelectionRequest(this)
        editor.onCloseRequest = EditorTabHandler.onCloseRequest(this)
    }
}