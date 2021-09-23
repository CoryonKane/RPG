package com.codecool.rpg.model.actor;

import com.codecool.rpg.model.event.PlayerDiesEvent;
import com.codecool.rpg.model.item.Item;
import com.codecool.rpg.model.map.Direction;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class PlayerCharacter extends Actor implements Serializable {
    private int level;
    private int experience;
    private int expNeeded;
    private int expNeedGrowth = 100;
    private int hpGrowth = 10;
    private Map<Item, Integer> inventory = new HashMap<>();

    private static PlayerCharacter instance;

    public static PlayerCharacter getInstance() {
        initiate();
        return instance;
    }

    private static void initiate() {
        if (instance == null) {
            instance = new PlayerCharacter();
        }
    }

    private PlayerCharacter() {}

    public static void newPlayer() {
        initiate();
        instance.setRow(4);
        instance.setCol(4);
        instance.setExperience(0);
        instance.setLevel(1);
        instance.setFacing(Direction.UP);
        instance.fillTileNames();
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

    public void addItems(Map<Item, Integer> items) {
        if (this.inventory == null) {
            this.inventory = new HashMap<>();
        }
        items.forEach(this::addItem);
    }

    public void addItem(Item item, Integer amount) {
        if (this.inventory.containsKey(item)) {
            this.inventory.put(item, this.inventory.get(item) + amount);
        } else {
            this.inventory.put(item, amount);
        }
    }
}
