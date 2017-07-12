package main.view

import javafx.application.Application
import main.view.screen.MainScreen

object App {
    @JvmStatic fun main(args: Array<String>) {
        Application.launch(MainScreen::class.java)
    }
}