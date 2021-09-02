package com.codecool.rpg.model.actor;

import com.codecool.rpg.model.item.Item;
import com.codecool.rpg.model.map.Direction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerCharacter extends Actor {
    private int level;
    private int experience;
    private int expNeeded;
    private int expNeedGrowth = 100;
    private int hpGrowth = 10;
    private boolean godMode;
    private List<Item> inventory = new ArrayList<>();

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
    public void move() {
    }

    @Override
    public void die(Actor actor) {

    }

    public void levelUp() {
        this.level++;
        this.setMaxHealth(this.getMaxHealth() + this.hpGrowth);
        this.setCurrHealth(this.getMaxHealth());
        this.experience -= expNeeded;
        expNeeded += expNeedGrowth;
    }

    public void getEXP(int exp) {
        this.experience += exp;
        if (this.expNeeded < this.experience) {
            levelUp();
        }
    }
}
