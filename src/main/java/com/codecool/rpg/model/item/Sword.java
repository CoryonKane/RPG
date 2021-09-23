package com.codecool.rpg.model.item;

import java.io.Serializable;

public class Sword extends Item implements Serializable {

    public Sword() {
        this.setItemType(ItemType.WEAPON);
        this.setName(this.getClass().getSimpleName());
        this.setRow(0);
        this.setCol(0);
    }

    @Override
    public void fillTileNames() {

    }
}
