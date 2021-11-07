package de.entjic.grid.obj;

public class Word {
    public final String value;
    public final Direction direction;


    public Word(String value, Direction direction) {
        this.value = value;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                ", direction=" + direction +
                '}';
    }
}
