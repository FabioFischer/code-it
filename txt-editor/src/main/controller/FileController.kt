package main.controller

import java.io.*
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class FileController : IFileController {
    private val serialVersionUID = 1L

    override fun new(path: String, fileContent: String, override: Boolean) {
        val filePath: Path = this.getPath(path)

        if (override.not())
            if (Files.exists(filePath))
                throw IllegalArgumentException("File ${filePath.fileName} already exists. Do you want to overwrite it?")

        BufferedWriter(FileWriter(filePath.toFile())).use {
            writer -> writer.write(fileContent)
        }
    }

    override fun save(path: String, fileContent: String) {
        val filePath: Path = this.getPath(path)

        if (Files.exists(filePath))
            Files.createFile(filePath)

        BufferedWriter(FileWriter(filePath.toFile())).use {
            writer -> writer.write(fileContent)
        }
    }

    override fun saveAs(path: String, fileContent: String, override: Boolean) {
        val filePath: Path = this.getPath(path)

        if (override.not())
            if (Files.exists(filePath))
                throw IllegalArgumentException("File $path already exists!")

        BufferedWriter(FileWriter(filePath.toFile())).use {
            writer -> writer.write(fileContent)
        }
    }

    override fun getContent(path: String, charset: Charset): String {
        val filePath = getPath(path)

        if (Files.exists(filePath).not())
            throw FileNotFoundException("File ${filePath.fileName} not found!")

        return filePath.toFile().readText(charset = charset)
    }

    override fun getPath(path: String): Path = Paths.get( if (path.endsWith(".txt")) path else "$path.txt" )

    override fun getName(path: String): String = Paths.get(path).fileName.toString().replace(".txt", "")
}