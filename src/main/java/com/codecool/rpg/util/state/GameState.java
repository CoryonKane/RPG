package com.codecool.rpg.util.state;

import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.map.GameMap;
import lombok.Data;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Data
public class GameState {
    private boolean godMode = false;
    private final Map<String, GameMap> mapCache = new HashMap<>();
    private final String resources = "src/main/resources";
    private PlayerCharacter player;
    private GameMap activeMap;
    private String saveName = "1";

    private static GameState instance;

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    private GameState() {}

    public String getTemplateRoute() {
        return this.resources + "/templates/";
    }

    public String getSaveRoute() {
        return this.resources + "/saves/" + saveName  + "/";
    }

    public boolean checkExistingSaveRoute(String route) {
        return Files.exists(Paths.get(route));
    }
}
