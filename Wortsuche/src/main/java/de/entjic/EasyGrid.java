package de.entjic;

import java.util.*;


public class EasyGrid implements Grid {
    private final String[] words;
    private final int h, b;
    private final Letter[][] letters;

    public EasyGrid(int h, int b, String[] words) {
        this.words = words;
        this.h = h;
        this.b = b;
        this.letters = new Letter[h][b];
    }

    public void fillGrid() {
        fillWords();
        fillRemainingGrid();
    }


    private void fillWords() {
        for(String word : words){
            // System.out.println("Current word: " + word);
            fillWord(word);
        }
    }

    private void fillWord(String word){
        Spot spot = findRandomSuitingSpot(word);
        if(spot == null){
            System.out.println("Were not able to place word " + word);
            return;
        }
        System.out.println(word + " at: " + spot.y + " " + spot.x);
        fillWordAtPosition(spot.x, spot.y, word);
    }

    private Spot findRandomSuitingSpot(String word){
        Random random = new Random();
        List<Spot> spot = findAllSuitingSpots(word);
        System.out.println(this);
        if(spot.size() == 0){
            return null;
        }
        return spot.get(random.nextInt(spot.size() - 1));
    }

    private List<Spot> findAllSuitingSpots(String word){
        List<Spot> spots = new ArrayList<>();
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < letters[i].length - word.length(); j++) {
                if(isWordSpotTaken(j, i, word.length())){
                    continue;
                }
                spots.add(new Spot(j, i));
            }
        }
        return spots;
    }


    private boolean isWordSpotTaken(int b, int h, int length){
        for(int i = 0; i < length; i++){
            if(isSpotTaken(b + i , h)){
                // System.out.println("spot taken at " + h + " : " + b);
                return true;
            }
        }
        return false;
    }

    private boolean isSpotTaken(int x, int y){
        // System.out.println(y + " : " + x);
        return letters[y][x] != null;
    }

    private void fillWordAtPosition(int x, int y, String word){
        char[] splitWord = word.toCharArray();
        for (int i = 0; i < splitWord.length; i++) {
            Letter letter = new Letter(splitWord[i]);
            letters[y][x + i] = letter;
        }
    }

    private void fillRemainingGrid(){
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < letters[i].length; j++) {
                if(letters[i][j] != null ){
                    continue;
                }
                letters[i][j] = randomLetter();
            }
        }
    }

    private Letter randomLetter(){
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        Letter letter = new Letter(c);
        return letter;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "words=" + Arrays.toString(words) +
                ", h=" + h +
                ", b=" + b +
                ", chars=" + Arrays.deepToString(letters) +
                '}';
    }
}
