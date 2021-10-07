package com.codecool.rpg.model.item;

public enum ItemType {
    WEAPON(""),
    ARMOR(""),
    QUEST(""),
    CONSUMABLE(""),
    OTHER("");

    private final String tileName;

    ItemType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
