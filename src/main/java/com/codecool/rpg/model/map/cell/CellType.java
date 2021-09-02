package com.codecool.rpg.model.map.cell;

public enum CellType {
    EMPTY("empty", " "),
    WALL("wall", "#"),
    FLOOR("floor", "."),
    DOOR("door", "d"),
    OPENED_DOOR("openedDoor", "o"),
    SECRET_DOOR("", ""),
    TRAPDOOR("", ""),
    GATE("", "");

    private final String tileName;
    private final String tileCharacter;

    CellType(String tileCode, String tileCharacter) {
        this.tileName = tileCode;
        this.tileCharacter = tileCharacter;
    }

    public String getTileCharacter() {
        return this.tileCharacter;
    }

    public String getMapCode() {
        return this.tileName;
    }

}
