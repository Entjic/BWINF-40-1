package de.entjic.grid;


import de.entjic.grid.obj.*;

import java.util.*;
import java.util.stream.Collectors;


public abstract class Grid {
    protected final String[] words;
    protected final int h, b;
    protected final Map<Word, Spot> placedWords;
    protected Letter[][] letters;
    protected int completelyRandom, lettersDirectlyOfWords;

    public Grid(int h, int b, String[] words) {
        this.words = words;
        this.h = h;
        this.b = b;
        placedWords = new HashMap<>();
    }

    public void fillGrid() {
        completelyRandom = 0;
        lettersDirectlyOfWords = 0;
        clearGrid();
        fillWords();
        fillRemainingGrid(getLettersUsedInWordsToPlace());
    }

    public void printDebug() {
        System.out.println("Letters overall: " + h * b);
        System.out.println("Letters due to word placing " + lettersDirectlyOfWords);
        System.out.println("Letters contained in words but placed random " + (h * b - lettersDirectlyOfWords - completelyRandom));
        System.out.println("Letters completely random " + completelyRandom);
        System.out.println(this);
    }


    protected void fillWords() {
        List<String> sizeSortedWords = sortWordsFromLongToShort();

        if (impossibleToFill()) {
            System.out.println("Words arent possible to place");
            return;
        }

        List<String> placedWords = new ArrayList<>();

        for (String word : sizeSortedWords) {
            System.out.println("Current word: " + word);
            if (fillWord(new Word(word, getRandomDirection()))) {
                placedWords.add(word);
            }
        }

        if (placedWords.size() < sizeSortedWords.size()) {
            System.out.println("PATTERN INVALID - restarted placing");
            System.out.println(this);
            sizeSortedWords.removeAll(placedWords);
            System.out.println("words left: " + sizeSortedWords);
            System.out.println("---");
            fillGrid();
        }
    }

    protected Direction getRandomDirection() {
        Direction[] directions = Direction.values();
        Random random = new Random();
        return directions[random.nextInt(directions.length)];
    }

    protected List<String> sortWordsFromLongToShort() {
        List<String> list = Arrays.stream(words).sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        Collections.reverse(list);
        return list;
    }

    protected boolean impossibleToFill() {
        return Arrays.stream(words).mapToInt(String::length).sum() > b * h;
    }

    protected void clearGrid() {
        this.letters = new Letter[h][b];
    }


    protected boolean fillWord(Word word) {
        Spot spot = findRandomSuitingSpot(word);
        if (spot == null) {
            System.out.println("Were not able to place word " + word);
            return false;
        }
        // System.out.println(word.value + " at: " + spot.x + " " + spot.y + " with direction " + word.direction);
        fillWordAtPosition(spot, word.value, word.direction);
        placedWords.put(word, spot);
        lettersDirectlyOfWords += word.value.length();
        // System.out.println(this);
        return true;
    }

    protected Spot findRandomSuitingSpot(Word word) {
        Random random = new Random();
        List<Spot> spot = findAllSuitingSpots(word);
        // System.out.println(this);
        if (spot.size() == 0) {
            return null;
        }
        if (spot.size() == 1) {
            return spot.get(0);
        }
        return spot.get(random.nextInt(spot.size() - 1));
    }

    protected List<Spot> findAllSuitingSpots(Word word) {
        List<Spot> spots = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            // System.out.println("row length " + letters[i].length + " : word length " + word.length());
            for (int j = 0; j < b; j++) {
                Spot spot = new Spot(j, i);
                if (isWordSpotTaken(spot, word)) {
                    continue;
                }
                // System.out.println("suiting spot at " + j + " " + i);
                spots.add(spot);
            }
        }
        return spots;
    }

    protected boolean isWordSpotTaken(Spot spot, Word word) {
        int length = word.value.length();
        for (int i = 0; i < length; i++) {
            Spot current = new Spot(spot.x + (word.direction.getX() * i), spot.y + (word.direction.getY() * i));
            if (isSpotTaken(current)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isSpotTaken(Spot spot) {
        return spot.x < 0 || spot.x >= b || spot.y < 0 || spot.y >= h || letters[spot.y][spot.x] != null;
    }

    protected void fillWordAtPosition(Spot spot, String word, Direction direction) {
        char[] splitWord = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            Letter letter = new Letter(splitWord[i]);
            letters[spot.y + direction.getY() * i][spot.x + direction.getX() * i] = letter;
        }
    }

    protected void fillRemainingGrid(List<Letter> lettersToUse) {
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < letters[i].length; j++) {
                // i -> y achse j -> x achse
                if (letters[i][j] != null) {
                    continue;
                }
                letters[i][j] = calcLetter(new Spot(j, i), new ArrayList<>(lettersToUse));
            }
        }
    }

    protected List<Letter> getLettersUsedInWordsToPlace() {
        List<Letter> letters = new ArrayList<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (! charInLetters(c, letters)) {
                    letters.add(new Letter(c));
                }
            }
        }
        return letters;
    }


    protected Letter calcLetter(Spot spot, List<Letter> lettersUsedInWordsToPlace) {
        // TODO: 13.09.2021 wenn alle buchstaben in wörtern verwendet werden exception

        if (lettersUsedInWordsToPlace.size() == 0) {
            System.out.println(lettersUsedInWordsToPlace);
            completelyRandom++;
            Letter randomLetter = randomExcept(getLettersUsedInWordsToPlace());
            System.out.println("RANDOM LETTER " + randomLetter + " PLACED ON SPOT " + spot);
            return randomLetter;
        }
        Random random = new Random();
        Letter letter = lettersUsedInWordsToPlace.get(random.nextInt(lettersUsedInWordsToPlace.size()));
        if (! createsUnwantedWord(spot, letter)) {
            return letter;
        }
        lettersUsedInWordsToPlace.remove(letter);
        return calcLetter(spot, lettersUsedInWordsToPlace);
    }


    protected boolean createsUnwantedWord(Spot spot, Letter letter) {
        int length = getLongestWord();
        letters[spot.y][spot.x] = letter;
        List<Letter[]> snippets = snipAll(spot, length);
        for (Letter[] letters : snippets) {
            if (containsWord(letters)) {
                return true;
            }
        }
        return false;
    }

    protected int getLongestWord() {
        int i = 0;
        for (Word word : placedWords.keySet()) {
            if (word.value.length() > i) {
                i = word.value.length();
            }
        }
        return i;
    }

    protected List<Letter[]> snipAll(Spot spot, int length) {
        List<Letter[]> list = new ArrayList<>();
        for (SnippingDirection direction : SnippingDirection.values()) {
            list.add(snipDirection(spot, length, direction));
        }
        return list;
    }

    protected Letter[] snipDirection(Spot spot, int length, SnippingDirection direction) {
        Letter[] snippet = new Letter[length * 2 - 1];
        snippet[length - 1] = letters[spot.y][spot.x];
        int counter = 0;
        for (int i = - length + 1; i < length; i++) {
            Spot current = new Spot(spot.x + direction.getX() * i, spot.y + direction.getY() * i);
            counter = snip(snippet, counter, i, current);
        }
        return snippet;
    }

    protected int snip(Letter[] snippet, int counter, int i, Spot current) {
        if (i == 0 || current.x < 0 || current.x >= b || current.y < 0 || current.y >= h) {
            counter++;
            return counter;
        }
        snippet[counter] = letters[current.y][current.x];
        counter++;
        return counter;
    }

    protected boolean containsWord(Letter[] letters) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Letter letter : letters) {
            if (letter == null) continue;
            stringBuilder.append(letter.gameCharacter);
        }
        if (containsCertainString(stringBuilder.toString())) {
            return true;
        }
        stringBuilder.reverse();
        if (containsCertainString(stringBuilder.toString())) {
            return true;
        }
        return false;
    }

    protected boolean containsCertainString(String string) {
        for (Word word : placedWords.keySet()) {
            if (word.value.equals(string)) {
                return true;
            }
        }
        return false;
    }

    protected boolean charInLetters(char c, List<Letter> letters) {
        for (Letter letter : letters) {
            if (letter.gameCharacter == c) {
                return true;
            }
        }
        return false;
    }

    protected Letter randomExcept(List<Letter> except) {
        List<Character> all = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'a');
            c = Character.toUpperCase(c);
            all.add(c);
        }
        List<Letter> result = new ArrayList<>();
        for (Character character : all) {
            if (! containsChar(character, except)) {
                result.add(new Letter(character));
            }
        }
        Random r = new Random();
        return result.get(r.nextInt(result.size()));
    }

    protected boolean containsChar(Character character, List<Letter> letters) {
        for (Letter letter : letters) {
            if (letter.gameCharacter == character) {
                return true;
            }
        }
        return false;
    }

    protected Letter randomLetter() {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        c = Character.toUpperCase(c);
        return new Letter(c);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Letter[] letters : letters) {
            stringBuilder.append(Arrays.toString(letters)).append("\n");
        }
        return "Grid{" +
                "words=" + Arrays.toString(words) +
                ", h=" + h +
                ", b=" + b +
                ", chars=\n" + stringBuilder +
                '}';
    }
}
