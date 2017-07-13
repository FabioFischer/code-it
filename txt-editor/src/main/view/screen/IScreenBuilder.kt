package main.view.screen

import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import main.model.Editor

interface IScreenBuilder {
    fun initComponents()
    fun initButtons()
    fun initMenus()
    fun initScene(pane: BorderPane): Scene
}