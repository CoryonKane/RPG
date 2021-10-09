package com.codecool.rpg.util.state;

import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.map.GameMap;
import com.codecool.rpg.util.Draw;
import com.codecool.rpg.util.input.InputHandler;
import com.codecool.rpg.util.input.MovementController;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import lombok.Data;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Data
public class GameState {
    private boolean godMode = false;
    private final Map<String, GameMap> mapCache = new HashMap<>();
    private final String resources = "";
    private PlayerCharacter player;
    private GameMap activeMap;
    private String saveName = "1";
    private boolean hasSave = false;
    private Timer timer;
    private InputHandler handler;

    private static GameState instance;

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    private GameState() {
        handler = MovementController.getInstance();
    }

    public String getTemplateRoute() {
        return this.resources + "/templates/";
    }

    public String getSaveRoute() {
        return this.resources + "saves/" + saveName  + "/";
    }

    public void checkExistingSaveRoute() {
        hasSave = Files.exists(Paths.get(getSaveRoute()));
    }

    public void startTimer() {
        timer  = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    Draw.getInstance().refresh();
                });
            }
        }, 0, 600);
    }

    public void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    public void handleInput(KeyEvent keyEvent) {
        handler.handleInput(keyEvent);
    }
}
