package com.codecool.rpg.model.item;

import com.codecool.rpg.model.map.Drawable;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class Inventory implements Serializable, Drawable {
    Map<Item, Integer> inventory = new HashMap<>();
    private int row;
    private int col;

    public void addItem (Item item, int amount) {
        if (this.inventory.containsKey(item)) {
            this.inventory.replace(item, this.inventory.get(item) + amount);
        } else {
            this.inventory.put(item, amount);
        }
    }

    public void addItem (Item item) {
        addItem(item, 1);
    }

    @Override
    public String getTileName() {
        return null;
    }

    @Override
    public void fillTileNames() {

    }
}
