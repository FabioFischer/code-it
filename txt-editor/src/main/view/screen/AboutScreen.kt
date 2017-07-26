package main.view.screen

import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.TextAlignment
import javafx.stage.Stage
import main.util.Resources
import main.util.Settings

class AboutScreen: AbstractScreen(250.0, 400.0, "${Settings.APP_NAME} - About") {
    var mainScreen: MainScreen? = null

    val boxWrap = VBox()
    val boxAppInfo = VBox()
    val boxAboutInfo = VBox()

    val labelAppName = Label()
    val labelAboutVersion = Label()
    val labelAboutProjectLink = Label()

    val labelAboutDescription = Label()
    val labelAboutAuthor = Label()

    fun startScreen(mainScreen: MainScreen) {
        this.mainScreen = mainScreen
        start(Stage())
    }

    override fun start(primaryStage: Stage) {
        stage = primaryStage
        initComponents(primaryStage)

        primaryStage.scene = initScene()
        primaryStage.title = screenName
        primaryStage.icons.add(Resources.appIcon)

        primaryStage.show()
    }

    override fun initScene(): Scene {
        val scene = Scene(boxWrap, screenHeight, screenWidth)

        boxWrap.prefHeightProperty().bind(scene.heightProperty())
        boxWrap.prefWidthProperty().bind(scene.widthProperty())

        return scene
    }

    override fun initComponents(primaryStage: Stage) {
        initLabel(labelAppName, Settings.APP_NAME, TextAlignment.CENTER)
        initLabel(labelAboutVersion, "Version: ${Settings.APP_VERSION}", TextAlignment.CENTER)
        initLabel(labelAboutProjectLink, "GitHub: ${Settings.APP_GITHUB}", TextAlignment.LEFT)

        initLabel(labelAboutDescription, "Multi tabbed text editor implementation using Kotlin and JavaFX. \nApp made only for learning purposes.", TextAlignment.CENTER)
        initLabel(labelAboutAuthor, "Made by ${Settings.AUTHOR_NAME}", TextAlignment.LEFT)

        initVBox(boxAppInfo, 5.0, Pos.CENTER, labelAppName, labelAboutVersion, labelAboutProjectLink)
        initVBox(boxAboutInfo, 15.0, Pos.CENTER, labelAboutDescription, labelAboutAuthor)

        initVBox(boxWrap, 50.0, Pos.CENTER, boxAppInfo, boxAboutInfo)
    }
}