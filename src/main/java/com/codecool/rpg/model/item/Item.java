package com.codecool.rpg.model.item;

import com.codecool.rpg.model.map.Drawable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Item implements Drawable {
    private ItemType itemType;
    private String name;
    private String tileName;
    private int row;
    private int col;

    @Override
    public String getTileName() {
        return this.tileName;
    }
}
