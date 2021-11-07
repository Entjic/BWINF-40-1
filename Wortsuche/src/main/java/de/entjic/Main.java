package de.entjic;

import de.entjic.grid.Grid;
import de.entjic.grid.HardGrid;

public class Main {

    public static void main(String[] args) {

        String fileName = "worte5";
        FileReader reader = new FileReader(fileName);
//        Grid easyGrid = new EasyGrid(reader.getHeight(), reader.getWidth(), reader.getWords());
//        System.out.println("---EASY---");
//        easyGrid.fillGrid();
//        Grid mediumGrid = new MediumGrid(reader.getHeight(), reader.getWidth(), reader.getWords());
//        System.out.println("---MEDIUM---");
//        mediumGrid.fillGrid();
        Grid hardGrid = new HardGrid(reader.getHeight(), reader.getWidth(), reader.getWords());
        for (int i = 0; i < 1; i++) {
            System.out.println("---HARD---");
            hardGrid.fillGrid();
            hardGrid.printDebug();
        }
/*        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m";*/
        // System.out.println(ANSI_GREEN_BACKGROUND + "This text has a green background but default text!" + ANSI_RESET);

    }


}
