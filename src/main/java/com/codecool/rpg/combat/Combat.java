package com.codecool.rpg.combat;

import com.codecool.rpg.model.actor.Actor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Combat {

    private Actor attacker;
    private Actor defender;

    public void combat() {
        int actualDamage = attacker.getDamage() - defender.getArmor();
        if (actualDamage > 0) {
            defender.setCurrHealth(defender.getCurrHealth() - actualDamage);
            if (defender.getCurrHealth() < 1) {
                defender.die(attacker);
            }
        }
    }

}
