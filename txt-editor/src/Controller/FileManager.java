package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public FileManager() {
    }

    public static String getFileContent(String path) throws IOException {
        Path filePath = getCorrectFilePath(path);

        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("Arquivo \"" + filePath.getFileName() + "\" não encontrado!");
        }

        FileReader fileReader = new FileReader(filePath.toFile());
        BufferedReader reader = new BufferedReader(fileReader);

        String fileContent = "", currentLine = "";
        while ((currentLine = reader.readLine()) != null) {
            fileContent += currentLine + "\n";
        }

        return fileContent;
    }

    public static void createNewFile(String path, String fileContent, boolean override) throws IOException {
        Path filePath = getCorrectFilePath(path);

        if (!override) {
            if (Files.exists(filePath)) {
                throw new IllegalArgumentException("O arquivo \"" + filePath.getFileName() + "\" já existe.\nDeseja substitui-lo?");
            }
        }

        FileWriter fileWriter = new FileWriter(filePath.toFile());
        try (BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(fileContent);
        }
    }

    public static void saveFile(String path, String fileContent) throws IOException {
        Path filePath = getCorrectFilePath(path);

        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        FileWriter fileWriter = new FileWriter(filePath.toFile());
        try (BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(fileContent);
        }
    }

    public static void saveFilesAs(String path, String fileContent, boolean override) throws IOException {
        Path filePath = getCorrectFilePath(path);

        if (!override) {
            if (Files.exists(filePath)) {
                throw new IllegalArgumentException("Arquivo de destino \"" + path + "\" já existente!");
            }
        }

        FileWriter fileWriter = new FileWriter(filePath.toFile());
        try (BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(fileContent);
        }
    }

    private static Path getCorrectFilePath(String path) {
        if (!path.endsWith(".txt")) {
            path = path + ".txt";
        }

        return Paths.get(path);
    }
    
    public static String getFileName(String path) {
        Path filePath = Paths.get(path);
        
        return filePath.getFileName().toString().replace(".txt", "");
    }
}
