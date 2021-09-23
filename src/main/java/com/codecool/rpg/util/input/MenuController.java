package com.codecool.rpg.util.input;

import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.map.GameMap;
import javafx.scene.input.KeyEvent;
import lombok.Getter;
import lombok.Setter;

public class MenuController implements InputHandler{

    @Getter
    @Setter
    private PlayerCharacter player;
    @Getter
    @Setter
    private GameMap map;

    private static MenuController instance;

    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }
        return instance;
    }

    private MenuController() {
        this.player = PlayerCharacter.getInstance();
    }

    @Override
    public void handleInput(KeyEvent keyEvent) {

    }
}
