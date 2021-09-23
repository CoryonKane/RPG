package com.codecool.rpg.model.map.cell;

import com.codecool.rpg.model.actor.Actor;
import com.codecool.rpg.model.event.EmptyEvent;
import com.codecool.rpg.model.event.Event;
import com.codecool.rpg.model.item.Item;
import com.codecool.rpg.model.map.Drawable;
import com.codecool.rpg.model.map.GameMap;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cell implements Drawable {
    private CellType cellType;
    private Actor actor;
    private Map<Item, Integer> items;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private GameMap gameMap;
    private int row;
    private int col;

    @Override
    public String getTileName() {
        return cellType.getMapCode();
    }

    @Override
    public void fillTileNames() {

    }

    public Event arriveOn() {
        return new EmptyEvent();
    }

    public void addItems(Map<Item, Integer> items) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        items.forEach(this::addItem);
    }

    public void addItem(Item item, Integer amount) {
        if (this.items.containsKey(item)) {
            this.items.put(item, this.items.get(item) + amount);
        } else {
            this.items.put(item, amount);
        }
    }
}
