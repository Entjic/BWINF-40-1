package de.entjic.grid;

import de.entjic.grid.obj.Direction;
import de.entjic.grid.obj.Letter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EasyGrid extends Grid {
    public EasyGrid(int h, int b, String[] words) {
        super(h, b, words);
    }

    @Override
    public void fillGrid() {
        clearGrid();
        fillWords();
        System.out.println(this);
        fillRemainingGrid(getRandomizedLetters());
        printDebug();
    }

    private List<Letter> getRandomizedLetters() {
        List<Letter> letters = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'a');
            c = Character.toUpperCase(c);
            letters.add(new Letter(c));
        }
        Collections.shuffle(letters);
        return letters;
    }

    @Override
    protected Direction getRandomDirection() {
        Random random = new Random();
        return random.nextInt(2) == 0 ? Direction.RIGHT : Direction.DOWN;
    }
}
