package de.entjic.game;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Integer, GameField> gameFieldMap = new HashMap<Integer, GameField>();

    public Board() {
        createBoard();
    }


    private void createBoard() {
        /*
         * 0 -> empty
         * 1 -> normal field
         * 2 -> red start
         * 3 -> red target
         * 4 -> blue start
         * 5 -> blue target
         * 6 -> green start
         * 7 -> green target
         * 8 -> yellow start
         * 9 -> yellow target
         * */

        final int[][] board = new int[][]{
                {0, 0, 0, 0, 1, 1, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 0},
                {2, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1},
                {1, 3, 3, 3, 3, 0, 7, 7, 7, 7, 1},
                {1, 1, 1, 1, 1, 9, 1, 1, 1, 1, 6},
                {0, 0, 0, 0, 1, 9, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 9, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 9, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 8, 1, 1, 0, 0, 0, 0},
        };
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; i++) {
                gameFieldMap.put(counter, translateNumberToGameField(board[i][j]));
                counter++;
            }
        }

    }

    private GameField translateNumberToGameField(int number) {
        GameField gameField = GameField.EMPTY;
        switch (number) {
            case 0:
                break;
            case 1:
                gameField = GameField.BASIC;
                break;
            case 2:
                gameField = setTeam(GameField.SPAWN, Team.RED);
                break;
            case 3:
                gameField = setTeam(GameField.END, Team.RED);
                break;
            case 4:
                gameField = setTeam(GameField.SPAWN, Team.BLUE);
                break;
            case 5:
                gameField = setTeam(GameField.END, Team.BLUE);
                break;
            case 6:
                gameField = setTeam(GameField.SPAWN, Team.GREEN);
                break;
            case 7:
                gameField = setTeam(GameField.END, Team.GREEN);
                break;
            case 8:
                gameField = setTeam(GameField.SPAWN, Team.YELLOW);
                break;
            case 9:
                gameField = setTeam(GameField.END, Team.YELLOW);
                break;
        }
        return gameField;
    }

    private GameField setTeam(GameField gameField, Team team) {
        gameField.setTeam(team);
        return gameField;
    }

    public GameField getField(int i) {
        return gameFieldMap.get(i);
    }


}
