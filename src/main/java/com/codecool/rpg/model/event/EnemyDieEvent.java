package com.codecool.rpg.model.event;

import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.actor.enemy.Enemy;
import com.codecool.rpg.util.state.GameState;
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
        PlayerCharacter player = GameState.getInstance().getPlayer();
        player.addEXP(deadEnemy.getRewardEXP());
        deadEnemy.getLoot().getInventory().forEach((k,v ) -> {
            deadEnemy.getCell().getItems().addItem(k, v);
        });
        deadEnemy.getCell().setActor(null);
        deadEnemy.setLoot(null);
        deadEnemy.setCell(null);
    }
}
