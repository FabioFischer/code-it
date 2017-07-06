package main.view.screen

import main.controller.FileController
import main.util.Resources
import java.awt.BorderLayout
import java.awt.Container
import javax.swing.*

class MainScreen : AbstractScreen(800, 600, "Text Editor"), java.awt.event.ActionListener {
    private val serialVersionUID = 1L
    private val fileController: FileController = FileController()

    private val container: Container = this.contentPane
    private val textArea: JTextArea = JTextArea()
    private val menuBar: JMenuBar = JMenuBar()

    private val menuFile: JMenu = JMenu()
    private val menuEdit: JMenu = JMenu()
    private val menuAbout: JMenu = JMenu()

    private val newFile: JMenuItem = JMenuItem()
    private val openFile: JMenuItem = JMenuItem()
    private val saveFile: JMenuItem = JMenuItem()
    private val copy: JMenuItem = JMenuItem()
    private val paste: JMenuItem = JMenuItem()

    private val mainToolbar: JToolBar = JToolBar()

    private var newButton: JButton = JButton()
    private var openButton: JButton = JButton()
    private var saveButton: JButton = JButton()
    private var aboutButton: JButton = JButton()

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        createComponents()
    }

    override fun actionPerformed(e: java.awt.event.ActionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createComponents() {
        textArea.lineWrap = true

        contentPane.layout = BorderLayout() // the BorderLayout bit makes it fill it automatically
        contentPane.add(textArea)

        createButtons()
        createMenus()
    }

    override fun createButtons() {
        initToolbarButton(this, newButton, mainToolbar, "New File", Resources.newFileIcon)
        initToolbarButton(this, openButton, mainToolbar, "Open File", Resources.openFileIcon)
        initToolbarButton(this, saveButton, mainToolbar, "Save File", Resources.saveFileIcon)
        initToolbarButton(this, aboutButton, mainToolbar, "About", Resources.AboutInfoIcon)
    }

    override fun createMenus() {
        menuFile.name = "File"
        menuEdit.name = "Edit"
        menuAbout.name = "About"

        menuBar.add(menuFile)
        menuBar.add(menuEdit)
        menuBar.add(menuAbout)

        this.jMenuBar = menuBar
    }

    override fun createKeyStrokes() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}