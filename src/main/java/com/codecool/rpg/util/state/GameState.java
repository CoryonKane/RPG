package com.codecool.rpg.util.state;

import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.map.GameMap;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class GameState {
    private boolean godMode = false;
    private final Map<String, GameMap> mapCache = new HashMap<>();
    private final PlayerCharacter player = PlayerCharacter.getInstance();
    private final String resources = "src/main/resources";
    private GameMap activeMap;

    private static GameState instance;

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    private GameState() {}
}
