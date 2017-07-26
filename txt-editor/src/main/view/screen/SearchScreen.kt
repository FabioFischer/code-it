package main.view.screen

import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import main.util.Resources
import main.util.Settings
import main.view.handler.SearchTextHandler

class SearchScreen : AbstractScreen(200.0, 400.0, "${Settings.APP_NAME} - Search") {
    var mainScreen: MainScreen? = null

    var boxTextFind = HBox()
    var boxTextReplace = HBox()

    val textFind = TextField()
    val textReplace = TextField()

    val boxWrap = HBox()
    val boxLeft = VBox()
    val boxRight = VBox()

    val buttonFind = Button()
    val buttonFindNext = Button()
    val buttonReplace = Button()
    val buttonReplaceAll = Button()

    val radioSearchDirection = RadioButton()

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
        boxTextFind = initLabelTextField(textFind, "Find", 5.0)
        boxTextReplace = initLabelTextField(textReplace, "Replace", 5.0)

        initRadioButton(radioSearchDirection, "Search Up")

        initButton(buttonFind, "Find", SearchTextHandler.onSearchFindRequest(this))
        initButton(buttonFindNext, "Find Next", SearchTextHandler.onSearchFindRequest(this))
        initButton(buttonReplace, "Replace", SearchTextHandler.onSearchFindRequest(this))
        initButton(buttonReplaceAll, "Replace All", SearchTextHandler.onSearchFindRequest(this))

        initVBox(boxLeft, 7.0, Pos.CENTER_RIGHT, boxTextFind, boxTextReplace, radioSearchDirection)
        initVBox(boxRight, 7.0, Pos.CENTER_LEFT, buttonFind, buttonFindNext, buttonReplace, buttonReplaceAll)
        initHBox(boxWrap, 35.0, Pos.CENTER, boxLeft, boxRight)
    }
}