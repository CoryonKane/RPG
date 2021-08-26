package com.codecool.rpg.model.map.cell;

import com.codecool.rpg.model.actor.Actor;
import com.codecool.rpg.model.item.Item;
import com.codecool.rpg.model.map.Drawable;
import com.codecool.rpg.model.map.GameMap;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cell implements Drawable {
    private CellType cellType;
    private Actor actor;
    private Item item;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private GameMap gameMap;

    @Override
    public String getTileName() {
        return cellType.getMapCode();
    }

    @Override
    public void fillTileNames() {

    }
}
