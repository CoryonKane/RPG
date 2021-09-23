package com.codecool.rpg.model.actor.enemy;

import com.codecool.rpg.model.map.Direction;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class Skeleton extends Enemy implements Serializable {

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void fillTileNames() {
        Map<Direction, String> map = new HashMap<>();
        map.put(Direction.UP, "skeleton");
        map.put(Direction.DOWN, "skeleton");
        map.put(Direction.RIGHT, "skeleton");
        map.put(Direction.LEFT, "skeleton");
        this.setTileNames(map);
    }
}
