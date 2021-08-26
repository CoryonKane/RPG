package com.codecool.rpg.model.actor.enemy;

import com.codecool.rpg.model.actor.Actor;
import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.item.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Enemy extends Actor {

    private List<Item> loot = new ArrayList<>();
    private int rewardEXP;

    @Override
    public void die(Actor player) {
        ((PlayerCharacter) player).getEXP(this.rewardEXP);
    }

}
