package com.codecool.rpg.util;

import com.codecool.rpg.model.map.Direction;
import com.codecool.rpg.model.map.GameMap;
import javafx.scene.input.KeyEvent;


public class MovementController {

    private static MovementController instance;

    public static MovementController getInstance() {
        if (instance == null) {
            instance = new MovementController();
        }
        return instance;
    }

    private MovementController() {}

    public void kexPressed(KeyEvent keyEvent, GameMap map) {
        switch (keyEvent.getCode()) {
            case UP:
            case W:
                System.out.println("UP");
                break;
            case DOWN:
            case S:
                System.out.println("DOWN");
                break;
            case RIGHT:
            case D:
                System.out.println("RIGHT");
                break;
            case LEFT:
            case A:
                System.out.println("LEFT");
                break;
        }
    }

    public boolean checkNextCellForMovement(GameMap map, int x, int y, Direction direction) {
        return false;
    }

}
