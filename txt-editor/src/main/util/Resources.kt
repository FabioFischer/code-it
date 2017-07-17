package main.util

import javafx.scene.image.Image
import javax.swing.ImageIcon

object Resources {
    val lineCounterTextAreaStyle: String = "res/css/line-counter-text-area.css"

    val appIcon: Image = Image("res/icon/app-icon.png")

    val newFileIcon: ImageIcon = ImageIcon("res/icon/new-file.png")
    val openFileIcon: ImageIcon = ImageIcon("res/icon/open-file.png")
    val saveFileIcon: ImageIcon = ImageIcon("res/icon/save-file.png")
    val AboutInfoIcon: ImageIcon = ImageIcon("res/icon/about-info.png")
}