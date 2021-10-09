package com.codecool.rpg.util.input;

import com.codecool.rpg.util.state.GameState;
import com.codecool.rpg.util.state.StateSaver;
import javafx.scene.input.KeyEvent;

public class MenuController implements InputHandler{

    private static MenuController instance;
    private GameState state;

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
        state = GameState.getInstance();
        switch (keyEvent.getCode()) {
            case ENTER:
                System.out.println("Continue");
                state.startTimer();
                state.setHandler(MovementController.getInstance());
                break;
            case S:
                System.out.println("Saving");
                StateSaver.getInstance().saveState();
                break;
            case ESCAPE:
                System.exit(0);
                break;
            default:
        }
    }
}
