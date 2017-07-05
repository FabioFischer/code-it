package main.view

import main.controller.FileController
import javax.swing.JFrame


abstract class AbstractView(val screenWidth: Int, val screenHeight: Int, val screenX: Int, val screenY: Int, val screenName: String) : JFrame() {

    val fileController: FileController = FileController()

    init {
    }

}