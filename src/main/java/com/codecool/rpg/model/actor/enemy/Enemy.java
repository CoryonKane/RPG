package com.codecool.rpg.model.actor.enemy;

import com.codecool.rpg.model.actor.Actor;
import com.codecool.rpg.model.event.EnemyDieEvent;
import com.codecool.rpg.model.item.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Enemy extends Actor {

    private Inventory loot;
    private int rewardEXP;

    @Override
    public EnemyDieEvent die() {
        return EnemyDieEvent.builder().deadEnemy(this).build();
    }
}
