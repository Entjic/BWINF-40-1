package de.entjic;

public class Main {

    public static void main(String[] args) {
        String[] words = new String[]{
                "apfel", "birne", "orange", "banane"
        };
        Grid easyGrid = new EasyGrid(1, 30, words);
        easyGrid.fillGrid();
        System.out.println(easyGrid);

    }








}
