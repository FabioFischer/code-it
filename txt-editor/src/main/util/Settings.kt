package main.util

import javafx.stage.FileChooser
import java.nio.charset.Charset

object Settings {
    val APP_NAME: String = "Code-iT"
    val APP_VERSION: String = "x.x.xx"
    val APP_GITHUB: String = "https://github.com/FabioFischer/code-it"

    val AUTHOR_NAME: String = "Fábio Luiz Fischer"
    val AUTHOR_EMAIL: String = "fabiolfischer32@gmail.com"

    val NEW_FILE_NAME: String = "untitled-#"
    val APP_CHARSET: Charset = Charsets.UTF_8
    val DEFAULT_PROJECTS_DIRECTORY: String = "C:/Code-iT Projects"
    val VALID_EXTENSIONS: List<FileChooser.ExtensionFilter> = listOf(FileChooser.ExtensionFilter("All files", "*"))

    val LINE_COUNTER_WIDTH: Double = 52.0
}