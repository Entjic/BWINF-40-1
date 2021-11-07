package de.entjic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    private String fileName;
    private List<String> content;

    public FileReader(String fileName) {
        this.fileName = fileName;
        content = readFile();
    }

    private List<String> readFile() {
        String current = new File("").getAbsolutePath();
        File file = new File(current + "/Wortsuche/src/main/resources/examples/" + fileName + ".txt");
        List<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            // Für jede Zeile wiederholen
            while (scanner.hasNextLine()) {
                // Lese einzelne Zeile aus Wortliste
                String data = scanner.nextLine();
                // Füge Zeile zur Liste hinzu
                list.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Zurückgeben der vollständigen Liste
        return list;
    }

    public int getHeight() {
        return Integer.parseInt(content.get(0).split(" ")[0]);
    }

    public int getWidth() {
        return Integer.parseInt(content.get(0).split(" ")[1]);
    }

    public String[] getWords() {
        int size = Integer.parseInt(content.get(1));
        String[] words = new String[size];
        for (int i = 2; i < 2 + size; i++) {
            words[i - 2] = content.get(i);
        }
        return words;
    }


}
