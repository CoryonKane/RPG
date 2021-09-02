package com.codecool.rpg.model.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Tiles {
    public  int TILE_WIDTH = 32;

    private final Image tileset = new Image("/images/tiles.png", 543 * 2, 543 * 2, true, false);
    private final Map<String, Tile> tileMap = new HashMap<>();
    public class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    public Tiles () {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(25, 1));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("spider", new Tile(30, 5));
        tileMap.put("orc", new Tile(24, 3));
        tileMap.put("Sword", new Tile(0, 29));
        tileMap.put("Key", new Tile(17, 23));
        tileMap.put("Heart", new Tile(23, 22));
        tileMap.put("door", new Tile(3, 9));
        tileMap.put("openedDoor", new Tile(6, 9));
        tileMap.put("tree", new Tile(0, 1));
        tileMap.put("stoneRoad", new Tile(16, 0));
        tileMap.put("house", new Tile(0, 19));
        tileMap.put("Xp", new Tile(16, 22));
        tileMap.put("stairs", new Tile(2, 6));
        tileMap.put("ghost", new Tile(25, 8));
        tileMap.put("grass", new Tile(5,0));
        tileMap.put("chest", new Tile(5,15));
        tileMap.put("torch", new Tile(4, 15));
        tileMap.put("wateredge", new Tile(9, 5));
        tileMap.put("objective", new Tile(12, 24));
        tileMap.put("shop", new Tile(1, 12));
        tileMap.put("placeholder", new Tile(12, 10));
        tileMap.put("diggable", new Tile(12, 18));
        tileMap.put("centerwater", new Tile(8, 5));
        tileMap.put("topleftwater", new Tile(20, 30));
        tileMap.put("toprightwater", new Tile(21, 30));
        tileMap.put("bottomleftwater", new Tile(20, 31));
        tileMap.put("bottomrightwater", new Tile(21, 31));
        tileMap.put("topwateredge", new Tile(23, 31));
        tileMap.put("leftwateredge", new Tile(22, 30));
        tileMap.put("rightwateredge", new Tile(23, 30));
        tileMap.put("bottomwateredge", new Tile(22, 31));
        tileMap.put("darkness", new Tile(0, 0));
        tileMap.put("centercave", new Tile(28, 30));
        tileMap.put("exitcave", new Tile(28, 31));
        tileMap.put("topleftcave", new Tile(24, 30));
        tileMap.put("toprightcave", new Tile(25, 30));
        tileMap.put("bottomleftcave", new Tile(24, 31));
        tileMap.put("bottomrightcave", new Tile(25, 31));
        tileMap.put("topedgecave", new Tile(27, 31));
        tileMap.put("leftedgecave", new Tile(26, 30));
        tileMap.put("rightedgecave", new Tile(27, 30));
        tileMap.put("bottomedgecave", new Tile(26, 31));
        tileMap.put("Crown", new Tile(11, 24));
        tileMap.put("bridge", new Tile(6, 5));
        tileMap.put("blueDoor", new Tile(0, 9));
        tileMap.put("blueOpenDoor", new Tile(2, 9));
        tileMap.put("Skull", new Tile(0, 15));
    }

    public  void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        try {
            context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                    x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
        } catch (NullPointerException e) {
            System.out.println("--");
        }
    }
}
