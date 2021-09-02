package com.codecool.rpg.model.map.cell;

import com.codecool.rpg.model.actor.Actor;
import com.codecool.rpg.model.event.Event;
import com.codecool.rpg.model.event.TransferEvent;
import com.codecool.rpg.model.item.Item;
import com.codecool.rpg.model.map.Drawable;
import com.codecool.rpg.model.map.GameMap;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cell implements Drawable {
    private CellType cellType;
    private Actor actor;
    private Item item;
    private Gate gate;
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

    public Event arriveOn() {
        if (this.gate != null) {
            return TransferEvent.builder()
                    .gate(gate)
                    .build();
        }
        return null;
    }
}
