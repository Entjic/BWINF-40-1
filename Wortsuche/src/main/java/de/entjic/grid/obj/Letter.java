package de.entjic.grid.obj;

public class Letter {
    public char gameCharacter;

    public Letter(char gameCharacter){
        this.gameCharacter = gameCharacter;
    }

    @Override
    public String toString() {
        return gameCharacter + "";
    }
}
