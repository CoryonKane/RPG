package com.codecool.rpg.util;

import com.codecool.rpg.model.actor.enemy.Enemy;
import com.codecool.rpg.model.item.Item;
import com.codecool.rpg.model.map.GameMap;
import com.codecool.rpg.model.map.Tiles;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Data;

@Data
public class Draw {

    private GraphicsContext context;
    private Canvas canvas;
    private GameMap map;
    private boolean isRefreshing = false;

    private final Tiles tiles;

    private static Draw instance;

    public static Draw getInstance() {
        if (instance == null) {
            instance = new Draw();
        }
        return instance;
    }

    private Draw() {
        this.tiles = new Tiles();
    }

    public void refresh() {
        if (isRefreshing) {
            return;
        }
        isRefreshing = true;
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getMaxWidth(); x++) {
            for (int y = 0; y < map.getMap().size(); y++) {
                tiles.drawTile(context, map.getMap().get(y).get(x), x, y);
            }
        }
        for (Enemy e : map.getEnemies()) {
            tiles.drawTile(context, e, e.getRow(), e.getCol());
        }
        for (Item i : map.getItems()) {
            tiles.drawTile(context, i, i.getRow(), i.getCol());
        }
        tiles.drawTile(context, map.getPlayer(), map.getPlayer().getRow(), map.getPlayer().getCol());
        isRefreshing = false;
    }
}
