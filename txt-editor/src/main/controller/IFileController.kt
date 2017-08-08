package main.controller

import java.nio.charset.Charset
import java.nio.file.Path

interface IFileController {
    fun new (path: String, extension: String, fileContent: String, override: Boolean)
    fun save (path: String, extension: String, fileContent: String)
    fun saveAs (path: String, extension: String, fileContent: String, override: Boolean)
    fun getPath (path: String, extension: String): Path
    fun getContent (path: String, extension: String, charset: Charset): String
    fun getName (path: String): String
}