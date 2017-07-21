package main.view.screen

import javafx.scene.Scene
import javafx.stage.Stage

interface IScreenBuilder {
    fun initComponents(primaryStage: Stage)
    fun initScene(): Scene
}