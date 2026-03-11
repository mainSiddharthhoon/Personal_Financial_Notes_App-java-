package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static void writeToFile(String filePath, String content, boolean append) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, append))) {
            writer.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            // Create file if it doesn't exist
            File file = new File(filePath);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return lines;
    }
}