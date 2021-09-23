package com.codecool.rpg.model.map;

import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.actor.enemy.Enemy;
import com.codecool.rpg.model.actor.npc.NonPlayerCharacter;
import com.codecool.rpg.model.item.Item;
import com.codecool.rpg.model.map.cell.Cell;
import com.codecool.rpg.model.map.cell.Gate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameMap {
    private String name;
    private List<List<Cell>> map;
    private int maxWidth = 0;
    private List<Enemy> enemies;
    private List<Item> items;
    private PlayerCharacter player;
    private List<NonPlayerCharacter> npcList;

    public void addRow(List<Cell> row) {
        this.map.add(row);
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addNPC(NonPlayerCharacter npc) {
        this.npcList.add(npc);
    }

    public void setGate(Gate gate) {
        this.map.get(gate.getRow()).set(gate.getCol(), gate);
    }
}
