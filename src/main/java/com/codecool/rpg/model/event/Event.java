package com.codecool.rpg.model.event;

import com.codecool.rpg.model.map.GameMap;

public interface Event {

    void doEvent(GameMap map);

}
