package main.util

import javafx.stage.FileChooser
import java.nio.charset.Charset

object Settings {
    var NEW_FILE_NAME: String = "untitled-#"
    var APP_CHARSET: Charset = Charsets.UTF_8
    var LINE_COUNTER_WIDTH: Double = 20.0
    var VALID_EXTENSIONS: FileChooser.ExtensionFilter = FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
}