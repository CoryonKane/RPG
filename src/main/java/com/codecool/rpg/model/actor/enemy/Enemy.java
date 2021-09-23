package com.codecool.rpg.model.actor.enemy;

import com.codecool.rpg.model.actor.Actor;
import com.codecool.rpg.model.event.EnemyDieEvent;
import com.codecool.rpg.model.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Enemy extends Actor {

    private Map<Item, Integer> loot;
    private int rewardEXP;

    @Override
    public EnemyDieEvent die() {
        return EnemyDieEvent.builder().deadEnemy(this).build();
    }
}
