package de.entjic.grid.obj;

public enum SnippingDirection {
    VERTICAL(1, 0),
    HORIZONTAL(0, 1),
    DIAGONALRTL(1, 1),
    DIAGONALLTR(- 1, - 1);

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
