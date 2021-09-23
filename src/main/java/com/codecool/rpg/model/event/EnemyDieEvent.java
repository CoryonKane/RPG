package com.codecool.rpg.model.event;

import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.actor.enemy.Enemy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnemyDieEvent implements Event{

    private Enemy deadEnemy;

    @Override
    public void doEvent() {
        PlayerCharacter player = PlayerCharacter.getInstance();
        player.addEXP(deadEnemy.getRewardEXP());
        deadEnemy.getCell().addItems(deadEnemy.getLoot());
        deadEnemy.getCell().setActor(null);
        deadEnemy.setLoot(null);
        deadEnemy.setCell(null);
    }
}
