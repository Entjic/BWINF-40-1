package de.entjic.grid.obj;

public enum SnippingDirection {
    HORIZONTAL(1, 0),
    VERTICAL(0, 1),
    DIAGONALLTR(1, 1),
    DIAGONALRTL(-1, 1);


    private final int x, y;

    SnippingDirection(int x, int y) {
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
