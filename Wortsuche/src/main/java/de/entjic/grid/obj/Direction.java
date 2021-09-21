package de.entjic.grid.obj;

public enum Direction {
    LEFT(- 1, 0),
    RIGHT(1, 0),
    UP(0, - 1),
    DOWN(0, 1),
    UDLR(1, 1),
    UDRL(- 1, 1),
    DULR(1, - 1),
    DURL(- 1, - 1);

    private final int x, y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
