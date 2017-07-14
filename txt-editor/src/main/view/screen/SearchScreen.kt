package main.view.screen

import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import main.controller.TextController

class SearchScreen : AbstractScreen(800.0, 900.0, "Text Editor"), java.awt.event.ActionListener {
    private val textController: TextController = TextController()

    override fun start(primaryStage: Stage?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun actionPerformed(e: java.awt.event.ActionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initScene(pane: BorderPane): Scene {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initComponents(primaryStage: Stage) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initButtons() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initMenus() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}