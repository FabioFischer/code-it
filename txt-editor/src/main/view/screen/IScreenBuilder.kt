package main.view.screen

import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage

interface IScreenBuilder {
    fun initComponents(primaryStage: Stage)
    fun initButtons()
    fun initMenus()
    fun initScene(pane: BorderPane): Scene
}