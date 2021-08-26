package com.codecool.rpg.model.map;

import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.actor.enemy.Enemy;
import com.codecool.rpg.model.item.Item;
import com.codecool.rpg.model.map.cell.Cell;
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

    public void addRow(List<Cell> row) {
        this.map.add(row);
    }
}
