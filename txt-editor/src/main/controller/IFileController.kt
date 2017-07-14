package main.controller

import java.nio.charset.Charset
import java.nio.file.Path

interface IFileController {
    fun new (path: String, fileContent: String, override: Boolean)
    fun save (path: String, fileContent: String)
    fun saveAs (path: String, fileContent: String, override: Boolean)
    fun getPath (path: String): Path
    fun getName (path: String): String
    fun getContent (path: String, charset: Charset): String
}