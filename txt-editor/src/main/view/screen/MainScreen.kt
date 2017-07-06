package main.view.screen

import main.controller.FileController

class MainScreen : AbstractScreen(800, 900, "Text Editor"), java.awt.event.ActionListener {

    val fileController: FileController = FileController()

    override fun actionPerformed(e: java.awt.event.ActionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createComponents() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createButtons() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createKeyStrokes() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}