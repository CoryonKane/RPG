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

        fillMap(scanner, map);

        state.getMapCache().put(mapName, map);

        String[] s = mapName.split("\\.");
        String route = checkExistingSave("") ? state.getSaveRoute() : state.getTemplateRoute();
        loadList(route + s[0] + "_enemies.ser", Enemy.class).forEach(map::addEnemy);
        loadList(route + s[0] + "_items.ser", Item.class).forEach(map::addItem);
        loadList(route + s[0] + "_gates.ser", Gate.class).forEach(map::setGate);
        loadList(route + s[0] + "_npc.ser", NonPlayerCharacter.class).forEach(map::addNPC);

        return map;
    }

    private void fillMap(Scanner scanner, GameMap map) {
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
    }

    public void loadNewActiveMap(String mapName) {
        state.setActiveMap(loadMap(mapName));
    }

    private <T> List<T> loadList(String fileName, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            list = (List<T>) in.readObject();
        } catch(IOException | ClassNotFoundException ex) {
            System.out.println("No npc file found: " + fileName);
//            ex.printStackTrace();
        }
        return list;
    }

    private boolean checkExistingSave(String route) {
        return false;
    }

    private void loadPlayer() {}
}
