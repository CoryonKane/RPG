package com.codecool.rpg.util.input;

import com.codecool.rpg.combat.Combat;
import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.map.Direction;
import com.codecool.rpg.model.map.GameMap;
import com.codecool.rpg.model.map.cell.Cell;
import javafx.scene.input.KeyEvent;
import lombok.Getter;
import lombok.Setter;


public class MovementController implements InputHandler{

    @Getter
    @Setter
    private PlayerCharacter player;
    @Getter
    @Setter
    private GameMap map;

    private static MovementController instance;

    public static MovementController getInstance() {
        if (instance == null) {
            instance = new MovementController();
        }
        return instance;
    }

    private MovementController() {
        this.player = PlayerCharacter.getInstance();
    }

    public void handleInput(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
            case W:
                moveAction(Direction.UP);
                break;
            case DOWN:
            case S:
                moveAction(Direction.DOWN);
                break;
            case RIGHT:
            case D:
                moveAction(Direction.RIGHT);
                break;
            case LEFT:
            case A:
                moveAction(Direction.LEFT);
                break;
            case SPACE:
                interactAction(player.getFacing());
                break;
            case ESCAPE:
                System.out.println("Menu");
                break;
        }
    }

    private void moveAction(Direction direction) {
        try {
            Cell cell = getNextCell(direction);
            if (checkNextCellForMovement(cell)) {
                player.move(direction);
                cell.arriveOn().doEvent();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Player movement: " + e);
        }
    }

    private void interactAction(Direction direction) {
        Cell cell = getNextCell(direction);
        if (cell.getActor() != null) {
            Combat combat = Combat.builder()
                    .attacker(player)
                    .defender(cell.getActor())
                    .build();
            combat.combat();
        } else if (cell.getItems() != null) {
            player.addItems(cell.getItems());
            cell.setItems(null);
        }
    }

    private Cell getNextCell(Direction direction) {
        return map.getMap().get(player.getCol() + direction.getCol()).get(player.getRow() + direction.getRow());
    }

    private boolean checkNextCellForMovement(Cell cell) {
        return cell.getActor() == null && cell.getItems() == null && cell.getCellType().isMovable();
    }

}
