package com.codecool.rpg.util.state;

import com.codecool.rpg.model.actor.enemy.Enemy;
import com.codecool.rpg.model.actor.npc.NonPlayerCharacter;
import com.codecool.rpg.model.item.Item;
import com.codecool.rpg.model.map.GameMap;
import com.codecool.rpg.model.map.cell.Cell;
import com.codecool.rpg.model.map.cell.CellType;
import com.codecool.rpg.model.map.cell.Gate;

import java.io.*;
import java.util.*;

public class StateLoader {
    private final GameState state = GameState.getInstance();
    private static StateLoader instance;

    public static StateLoader getInstance() {
        if (instance == null) {
            instance = new StateLoader();
        }
        return instance;
    }

    private StateLoader() {}

    public GameMap loadMap(String mapName) {
        System.out.println(mapName);
        if (state.getMapCache().containsKey(mapName)) {
            return state.getMapCache().get(mapName);
        }

        InputStream is = StateLoader.class.getResourceAsStream(mapName);

        Scanner scanner = new Scanner(is);

        GameMap map = GameMap.builder()
                .map(new ArrayList<>())
                .items(new ArrayList<>())
                .enemies(new ArrayList<>())
                .name(mapName)
                .player(state.getPlayer())
                .build();

        int rowCounter = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Cell> row = new ArrayList<>();
            int colCounter = 0;
            for (String mapCharacter : line.split("")) {
                Cell cell = Cell.builder()
                        .cellType(Arrays.stream(CellType.values()).filter(v -> mapCharacter.equals(v.getTileCharacter())).findFirst().orElse(null))
                        .gameMap(map)
                        .row(rowCounter)
                        .col(colCounter)
                        .build();
                row.add(cell);
                colCounter++;
            }
            map.addRow(row);
            if (row.size() > map.getMaxWidth()) {
                map.setMaxWidth(row.size());
            }
            rowCounter++;
        }

        state.getMapCache().put(mapName, map);

        String[] s = mapName.split("\\.");
        String resources = state.getResources();
        loadEnemies(resources + s[0] + "_enemies.ser", map);
        loadItems(resources + s[0] + "_items.ser", map);
        loadGates(resources + s[0] + "_gates.ser", map);
        loadNPCs(resources + s[0] + "_npc.ser", map);

        return map;
    }

    public void loadNewActiveMap(String mapName) {
        state.setActiveMap(loadMap(mapName));
    }

    private void loadEnemies(String mapName, GameMap map) {
        List<Enemy> list;
        try {
            FileInputStream file = new FileInputStream(mapName);
            ObjectInputStream in = new ObjectInputStream(file);

            list = (List<Enemy>) in.readObject();

            in.close();
            file.close();
        } catch(IOException | ClassNotFoundException ex) {
            System.out.println("No enemy file found: " + mapName);
//            ex.printStackTrace();
            return;
        }
        list.forEach(map::addEnemy);
    }

    private void loadItems(String mapName, GameMap map) {
        List<Item> list;
        try {
            FileInputStream file = new FileInputStream(mapName);
            ObjectInputStream in = new ObjectInputStream(file);

            list = (List<Item>) in.readObject();

            in.close();
            file.close();
        } catch(IOException | ClassNotFoundException ex) {
            System.out.println("No item file found: " + mapName);
//            ex.printStackTrace();
            return;
        }
        list.forEach(map::addItem);
    }

    private void loadGates(String mapName, GameMap map) {
        List<Gate> list;
        try {
            FileInputStream file = new FileInputStream(mapName);
            ObjectInputStream in = new ObjectInputStream(file);

            list = (List<Gate>) in.readObject();

            in.close();
            file.close();
        } catch(IOException | ClassNotFoundException ex) {
            System.out.println("No gate file found: " + mapName);
//            ex.printStackTrace();
            return;
        }
        list.forEach(map::setGate);
    }

    private void loadNPCs(String mapName, GameMap map) {
        List<NonPlayerCharacter> list;
        try {
            FileInputStream file = new FileInputStream(mapName);
            ObjectInputStream in = new ObjectInputStream(file);

            list = (List<NonPlayerCharacter>) in.readObject();

            in.close();
            file.close();
        } catch(IOException | ClassNotFoundException ex) {
            System.out.println("No npc file found: " + mapName);
//            ex.printStackTrace();
            return;
        }
        list.forEach(map::addNPC);
    }
}
