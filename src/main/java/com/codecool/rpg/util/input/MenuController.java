package com.codecool.rpg.util.input;

import javafx.scene.input.KeyEvent;

public class MenuController implements InputHandler{

    private static MenuController instance;

    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }
        return instance;
    }

    private MenuController() {

    }

    @Override
    public void handleInput(KeyEvent keyEvent) {

    }
}
