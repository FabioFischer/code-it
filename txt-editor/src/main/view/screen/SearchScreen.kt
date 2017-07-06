package main.view.screen

import main.controller.TextController

class SearchScreen : AbstractScreen(800, 900, "Text Editor"), java.awt.event.ActionListener {

    val textController: TextController = TextController()

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