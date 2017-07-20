package main.view.screen

import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.control.ToggleButton
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import main.util.Resources
import main.util.Settings

class SearchScreen : AbstractScreen(500.0, 600.0, Settings.APP_NAME) {
    val textFind = TextField()
    val textReplace = TextField()

    val buttonFind = Button()
    val buttonFindNext = Button()
    val buttonReplace = Button()
    val buttonReplaceAll = Button()

    val toggleSearchDirection = ToggleButton()

    override fun start(primaryStage: Stage) {
        val root = BorderPane()

        initComponents(primaryStage)

        primaryStage.scene = initScene(root)
        primaryStage.title = screenName
        primaryStage.icons.add(Resources.appIcon)

        primaryStage.show()
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