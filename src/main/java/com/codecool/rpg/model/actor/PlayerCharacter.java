package com.codecool.rpg.model.actor;

import com.codecool.rpg.model.event.PlayerDiesEvent;
import com.codecool.rpg.model.item.Inventory;
import com.codecool.rpg.model.item.Item;
import com.codecool.rpg.model.map.Direction;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor

public class PlayerCharacter extends Actor {
    private int level;
    private int experience;
    private int expNeeded;
    private int expNeedGrowth = 100;
    private int hpGrowth = 10;
    private Inventory inventory = new Inventory();

    public void newPlayer() {
        this.setRow(4);
        this.setCol(4);
        this.setExperience(0);
        this.setLevel(1);
        this.setFacing(Direction.UP);
        this.fillTileNames();
    }

    @Override
    public void fillTileNames() {
        Map<Direction, String> map = new HashMap<>();
        map.put(Direction.UP, "player");
        map.put(Direction.DOWN, "player");
        map.put(Direction.RIGHT, "player");
        map.put(Direction.LEFT, "player");
        this.setTileNames(map);
    }

    @Override
    public void move(Direction direction) {
        this.setCol(this.getCol() + direction.getCol());
        this.setRow(this.getRow() + direction.getRow());
        this.setFacing(direction);
    }

    @Override
    public PlayerDiesEvent die() {
        return new PlayerDiesEvent();
    }

    public void levelUp() {
        this.level++;
        this.setMaxHealth(this.getMaxHealth() + this.hpGrowth);
        this.setCurrHealth(this.getMaxHealth());
        this.experience -= expNeeded;
        expNeeded += expNeedGrowth;
    }

    public void addEXP(int exp) {
        this.experience += exp;
        if (this.expNeeded < this.experience) {
            levelUp();
        }
    }

    public void addItems(Inventory items) {
        items.getInventory().forEach((k, v) -> {
            inventory.addItem(k, v);
        });
    }
}
