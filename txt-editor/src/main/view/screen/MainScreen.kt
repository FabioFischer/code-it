package main.view.screen

import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.stage.FileChooser
import main.controller.EditorController
import main.controller.FileController
import main.model.Editor
import main.util.Settings
import main.view.handler.EditorTabHandler
import main.view.listener.EditorTextListener


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

    private var currentEditor: Editor? = Editor()

    val fileController: FileController = FileController()
    val editorController: EditorController = EditorController()

    var primaryStage: Stage? = null
    val tabPane: TabPane = TabPane()

    fun getCurrentEditor(): Editor? {
        return currentEditor
    }

    fun setCurrentEditor(editor: Editor) {
        currentEditor = editor

        linkEditorHandlers()
        fileMenuOpen.setOnAction {
            openFileRequest(primaryStage!!, currentEditor)
        }
    }

    override fun start(primaryStage: Stage) {
        val root = BorderPane()
        this.primaryStage = primaryStage

        initComponents()

        fileMenuOpen.setOnAction {
            openFileRequest(primaryStage, currentEditor)
        }

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
        linkEditorHandlers()

        tabPane.tabs.addAll(editorController.getAllTabs()!!)
    }

    override fun initButtons() {
    }

    override fun initMenus() {
        initMenuItem(fileMenuNew, fileMenu, "New")
        addSeparator(fileMenu)
        initMenuItem(fileMenuOpen, fileMenu, "Open")
        initMenuItem(fileMenuSave, fileMenu, "Save")
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

    fun linkEditorHandlers() {
        for (editor in editorController.editors!!) linkEditorHandlers(editor)
    }

    private fun linkEditorHandlers(editor: Editor) {
        editor.textAreaListener = EditorTextListener.listen(editor)
        editor.onSelectRequest = EditorTabHandler.onSelectionRequest(this, editorController)
        editor.onCloseRequest = EditorTabHandler.onCloseRequest(editorController)
    }

    fun newFileRequest() {

    }

    fun openFileRequest(primaryStage: Stage, editor: Editor?) {
        val chooser = FileChooser()

        chooser.title = "Open File"
        chooser.extensionFilters.add(Settings.VALID_EXTENSIONS)

        val file = chooser.showOpenDialog(primaryStage)
        if (file != null) {
            editorController.rename(editor, file.name)
            editor!!.textArea.text = fileController.getContent(file.path.toString())
        }
    }
}