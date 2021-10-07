package com.codecool.rpg.model.map;

import com.codecool.rpg.model.actor.enemy.Enemy;
import com.codecool.rpg.model.actor.npc.NonPlayerCharacter;
import com.codecool.rpg.model.item.Inventory;
import com.codecool.rpg.model.map.cell.Cell;
import com.codecool.rpg.model.map.cell.Gate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private List<NonPlayerCharacter> npcList;

    public void addRow(List<Cell> row) {
        this.map.add(row);
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public void addNPC(NonPlayerCharacter npc) {
        this.npcList.add(npc);
    }

    public void setGate(Gate gate) {
        this.map.get(gate.getRow()).set(gate.getCol(), gate);
    }

    public List<Gate> getGates() {
        List<Gate> gates = new ArrayList<>();
        this.map.forEach(row -> row.forEach(cell -> {
            if (cell instanceof Gate) {
                gates.add((Gate) cell);
            }
        }));
        return gates;
    }

    public List<Inventory> getInventories() {
        List<Inventory> inventories = new ArrayList<>();
        this.map.forEach(row -> row.forEach(cell -> {
            if (cell.getItems() != null) {
                inventories.add(cell.getItems());
            }
        }));
        return inventories;
    }

    public void addInventory(Inventory inventory) {
        this.map.get(inventory.getRow()).get(inventory.getCol()).setItems(inventory);
    }
}
