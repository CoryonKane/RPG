package com.codecool.rpg.util.state;

import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.actor.enemy.Enemy;
import com.codecool.rpg.model.actor.npc.NonPlayerCharacter;
import com.codecool.rpg.model.item.Inventory;
import com.codecool.rpg.model.map.GameMap;
import com.codecool.rpg.model.map.cell.Cell;
import com.codecool.rpg.model.map.cell.CellType;
import com.codecool.rpg.model.map.cell.Gate;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        String route = state.checkExistingSaveRoute(state.getSaveRoute()) ? state.getSaveRoute() : state.getTemplateRoute();
        System.out.println(route);
        String fileName = route + mapName;
        if (state.getMapCache().containsKey(fileName)) {
            return state.getMapCache().get(fileName);
        }

        GameMap map = prepareNewMap(fileName, mapName);

        state.getMapCache().put(mapName, map);

        String[] s = fileName.split("\\.");
        loadList(s[0] + "_enemies.ser", Enemy.class).forEach(map::addEnemy);
        loadList(s[0] + "_inventories.ser", Inventory.class).forEach(map::addInventory);
        loadList(s[0] + "_gates.ser", Gate.class).forEach(map::setGate);
        loadList(s[0] + "_npc.ser", NonPlayerCharacter.class).forEach(map::addNPC);
        loadPlayer(route + "player.ser");

        return map;
    }

    private GameMap prepareNewMap(String fileName, String mapName) {

        GameMap map = GameMap.builder()
                .map(new ArrayList<>())
                .enemies(new ArrayList<>())
                .name(mapName)
                .build();

        fillMap(fileName, map);

        return map;
    }

    private void fillMap(String fileName, GameMap map) {
        int rowCounter = 0;
        try (Stream<String> lines = Files.lines(Path.of(fileName))){
            List<String> listLines = lines.collect(Collectors.toList());
            for (String line : listLines) {
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
        } catch (IOException e) {
            e.printStackTrace();
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
            in.close();
            file.close();
        } catch(IOException | ClassNotFoundException ex) {
            System.out.println("No file found: " + fileName);
        }
        return list;
    }

    private void loadPlayer(String fileName) {
        PlayerCharacter playerCharacter;
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            playerCharacter = (PlayerCharacter) in.readObject();

            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No player found: " + fileName);
            playerCharacter = new PlayerCharacter();
            playerCharacter.newPlayer();
        }
        state.setPlayer(playerCharacter);
    }
}
