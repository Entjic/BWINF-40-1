package de.entjic;

public class Letter {
    public char gameCharacter;
    public boolean taken;

    public Letter(char gameCharacter){
        this.gameCharacter = gameCharacter;
    }

    @Override
    public String toString() {
        return gameCharacter + "";
    }
}
