package com.codecool.rpg.model.map.cell;

public enum CellType {
    EMPTY("empty", " ", false),
    WALL("wall", "#", false),
    FLOOR("floor", ".", true),
    DOOR("door", "d", false),
    OPENED_DOOR("openedDoor", "o", true),
    SECRET_DOOR("", "", false),
    TRAPDOOR("", "", true);

    private final String tileName;
    private final String tileCharacter;
    private final boolean movable;

    CellType(String tileCode, String tileCharacter, boolean movable) {
        this.tileName = tileCode;
        this.tileCharacter = tileCharacter;
        this.movable = movable;
    }

    public String getTileCharacter() {
        return this.tileCharacter;
    }

    public String getMapCode() {
        return this.tileName;
    }

    public boolean isMovable() {
        return movable;
    }
}
