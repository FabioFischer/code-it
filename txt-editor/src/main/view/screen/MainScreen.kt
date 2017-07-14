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
import java.io.File


class MainScreen : AbstractScreen(600.0, 700.0, "Text Editor") {
    private val upperMenuBar: MenuBar = MenuBar()
    private val leftMenuBar: MenuBar = MenuBar()

    private val fileMenu: Menu = Menu()
    private val editMenu: Menu = Menu()
    private val aboutMenu: Menu = Menu()

    private val fileMenuNew: MenuItem = MenuItem()
    private val fileMenuOpen: MenuItem = MenuItem()
    private val fileMenuSave: MenuItem = MenuItem()
    private val fileMenuSaveAs: MenuItem = MenuItem()
    private val fileMenuExit: MenuItem = MenuItem()

    val fileController: FileController = FileController()
    val editorController: EditorController = EditorController()

    val tabPane: TabPane = TabPane()

    override fun start(primaryStage: Stage) {
        val root = BorderPane()

        initComponents(primaryStage)

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

    fun linkMenuItemHandlers(primaryStage: Stage) {
        fileMenuNew.setOnAction {
            newFileRequest()
        }
        fileMenuOpen.setOnAction {
            openFileRequest(primaryStage)
        }
        fileMenuSave.setOnAction {
            saveFileRequest(primaryStage)
        }
        fileMenuSaveAs.setOnAction {
            saveAsFileRequest(primaryStage)
        }
        fileMenuExit.setOnAction {
            saveAsFileRequest(primaryStage)
        }
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
        val editor: Editor = editorController.editors?.firstOrNull { it.isActive.not() }!!

        editorController.enableEditor(editor)
        addTab(tabPane, Editor())
        tabPane.selectionModel.select(editor.tab)
    }

    fun openFileRequest(primaryStage: Stage) {
        val chooser = FileChooser()

        chooser.title = "Open File"
        chooser.extensionFilters.add(Settings.VALID_EXTENSIONS)
        chooser.initialDirectory = File(Settings.DEFAULT_PROJECTS_DIRECTORY)

        val editor: Editor = editorController.editors?.firstOrNull { it.isActive.not() }!!
        val file = chooser.showOpenDialog(primaryStage)

        if (file != null) {
            editorController.enableEditor(editor, file.name)
            editor.textArea.text = fileController.getContent(file.path.toString(), Settings.APP_CHARSET)
            tabPane.selectionModel.select(editor.tab)

            addTab(tabPane, Editor())
        }
    }

    fun saveFileRequest(primaryStage: Stage) {
    }

    fun saveAsFileRequest(primaryStage: Stage) {
    }

    fun exitAppRequest(primaryStage: Stage) {
    }
}