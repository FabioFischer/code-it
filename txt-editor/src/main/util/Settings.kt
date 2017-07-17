package main.util

import javafx.stage.FileChooser
import java.nio.charset.Charset

object Settings {
    val APP_NAME: String = "Code-iT"
    val NEW_FILE_NAME: String = "untitled-#"
    val APP_CHARSET: Charset = Charsets.UTF_8
    val LINE_COUNTER_WIDTH: Double = 20.0
    val DEFAULT_PROJECTS_DIRECTORY: String = "C:/Users/fabio.fischer/Desktop"
    val VALID_EXTENSIONS: FileChooser.ExtensionFilter = FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
}