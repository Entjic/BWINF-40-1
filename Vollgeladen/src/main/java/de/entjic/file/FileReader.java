package de.entjic.file;

import de.entjic.model.Hotel;

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
        File file = new File(current + "\\Vollgeladen\\src\\main\\resources\\examples\\" + fileName + ".txt");
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

    public int getAmountOfHotels() {
        return Integer.parseInt(content.get(0));
    }

    public int getTravelTime() {
        return Integer.parseInt(content.get(1));
    }

    public Hotel[] getHotels() {
        int size = getAmountOfHotels();
        Hotel[] hotels = new Hotel[size];
        for (int i = 0; i < size; i++) {
            String input = content.get(i + 2);
            Hotel hotel = new Hotel(Integer.parseInt(input.split(" ")[0]),
                    Float.parseFloat(input.split(" ")[1]));
            hotels[i] = hotel;
        }
        // System.out.println(Arrays.toString(hotels));
        return hotels;
    }


}
