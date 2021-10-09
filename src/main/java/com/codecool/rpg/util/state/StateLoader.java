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
        state.checkExistingSaveRoute();
        String route = state.isHasSave() ? state.getSaveRoute() : state.getTemplateRoute();
        String fileName = route + mapName;
        if (state.getMapCache().containsKey(fileName)) {
            return state.getMapCache().get(fileName);
        }

        GameMap map = prepareNewMap(fileName, mapName);

        state.getMapCache().put(mapName, map);

        String[] s = fileName.split("\\.");
        if (!state.isHasSave()) {
            loadTemplateList(s[0] + "_enemies.ser", Enemy.class).forEach(map::addEnemy);
            loadTemplateList(s[0] + "_inventories.ser", Inventory.class).forEach(map::addInventory);
            loadTemplateList(s[0] + "_gates.ser", Gate.class).forEach(map::setGate);
            loadTemplateList(s[0] + "_npcs.ser", NonPlayerCharacter.class).forEach(map::addNPC);
            loadTemplatePlayer(route + "player.ser");
        } else {
            loadSavedList(s[0] + "_enemies.ser", Enemy.class).forEach(map::addEnemy);
            loadSavedList(s[0] + "_inventories.ser", Inventory.class).forEach(map::addInventory);
            loadSavedList(s[0] + "_gates.ser", Gate.class).forEach(map::setGate);
            loadSavedList(s[0] + "_npcs.ser", NonPlayerCharacter.class).forEach(map::addNPC);
            loadSavedPlayer(route + "player.ser");
        }
        return map;
    }

    private GameMap prepareNewMap(String fileName, String mapName) {

        GameMap map = GameMap.builder()
                .map(new ArrayList<>())
                .enemies(new ArrayList<>())
                .name(mapName)
                .build();

        prepareFillingMap(fileName, map);

        return map;
    }

    private void prepareFillingMap(String fileName, GameMap map) {
        int rowCounter = 0;
        if (!state.isHasSave()) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(fileName))))) {
                fillMap(map, rowCounter, br);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
                fillMap(map, rowCounter, br);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillMap(GameMap map, int rowCounter, BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
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

    private <T> List<T> loadTemplateList(String fileName, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        try (InputStream is = getClass().getResourceAsStream(fileName);
             ObjectInputStream in = new ObjectInputStream(is)){
            list = (List<T>) in.readObject();
        } catch(IOException | ClassNotFoundException | NullPointerException ex) {
            System.out.println("No file found: " + fileName);
        }
        return list != null ? list : new ArrayList<>();
    }

    private <T> List<T> loadSavedList(String fileName, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        try (InputStream is = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(is)){
            list = (List<T>) in.readObject();
        } catch(IOException | ClassNotFoundException | NullPointerException ex) {
            System.out.println("No file found: " + fileName);
        }
        return list != null ? list : new ArrayList<>();
    }

    private void loadTemplatePlayer(String fileName) {
        PlayerCharacter playerCharacter;
        try (InputStream file = getClass().getResourceAsStream(fileName);
             ObjectInputStream in = new ObjectInputStream(file)){ ;

            playerCharacter = (PlayerCharacter) in.readObject();
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            System.out.println("No player found: " + fileName);
            playerCharacter = new PlayerCharacter();
            playerCharacter.newPlayer();
        }
        state.setPlayer(playerCharacter);
    }

    private void loadSavedPlayer(String fileName) {
        PlayerCharacter playerCharacter;
        try (InputStream file = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(file)){ ;

            playerCharacter = (PlayerCharacter) in.readObject();
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            System.out.println("No player found: " + fileName);
            playerCharacter = new PlayerCharacter();
            playerCharacter.newPlayer();
        }
        state.setPlayer(playerCharacter);
    }
}
