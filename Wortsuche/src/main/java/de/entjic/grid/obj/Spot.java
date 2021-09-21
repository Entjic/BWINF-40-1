package de.entjic.grid.obj;

public class Spot {
    public final int x, y;

    public Spot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
