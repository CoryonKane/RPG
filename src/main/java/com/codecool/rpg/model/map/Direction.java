package com.codecool.rpg.model.map;

public enum Direction {
    UP(-1,0),
    DOWN(1,0),
    RIGHT(0,1),
    LEFT(0,-1);

    private final int col;
    private final int row;

    Direction (int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
