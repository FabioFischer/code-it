package main.view.screen

import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import main.util.Settings

class AboutScreen: AbstractScreen(800.0, 900.0, Settings.APP_NAME) {
    val labelAppName = Label()
    val labelAboutDescription = Label()
    val labelAboutAuthor = Label()
    val labelAboutVersion = Label()

    override fun start(primaryStage: Stage?) {
        val root = BorderPane()

        initComponents(primaryStage!!)
        initScene(root)
    }

    override fun initScene(pane: BorderPane): Scene {
//        pane.top = upperMenuBar
//        pane.left = leftMenuBar
//        pane.center = tabPane

        val scene = Scene(pane, screenHeight, screenWidth)

        pane.prefHeightProperty().bind(scene.heightProperty())
        pane.prefWidthProperty().bind(scene.widthProperty())

        return scene
    }

    override fun initComponents(primaryStage: Stage) {
    }

    override fun initMenus() {
    }
}