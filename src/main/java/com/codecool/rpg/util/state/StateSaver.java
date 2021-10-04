package com.codecool.rpg.util.state;

import java.io.*;

public class StateSaver {

    private final GameState state = GameState.getInstance();
    private static StateSaver instance;

    public static StateSaver getInstance() {
        if (instance == null) {
            instance = new StateSaver();
        }
        return instance;
    }

    private StateSaver() {}

    public void saveState() {
        if (state.getSaveName() == null) {
            return;
        }
        String route = state.getSaveRoute();
        createSaveFolder(route);
        saveMaps(route);
        savePlayer(route);
        saveEnemies(route);
        saveNpcs(route);
        saveGates(route);
        saveInventories(route);
    }

    private void saveMaps(String route) {
        StringBuilder sb = new StringBuilder();
        state.getMapCache().forEach((name, map) -> map.getMap().forEach(row -> {
            row.forEach(cell -> sb.append(cell.getCellType().getTileCharacter()));
            sb.append(System.lineSeparator());
        }));
        writeMap(sb.toString(), route + state.getActiveMap().getName());
    }

    private void savePlayer(String route) {
        writeSerializableObject(state.getPlayer(), route + "player.ser");
    }

    private void saveEnemies(String route) {
        writeSerializableObject(state.getActiveMap().getEnemies(), route + state.getActiveMap().getName().split("\\.")[0] + "_enemies.ser");
    }

    private void saveNpcs(String route) {
        writeSerializableObject(state.getActiveMap().getNpcList(), route + state.getActiveMap().getName().split("\\.")[0] + "_npcs.ser");
    }

    private void saveGates(String route) {
        writeSerializableObject(state.getActiveMap().getGates(), route + state.getActiveMap().getName().split("\\.")[0] + "_gates.ser");
    }

    private void saveInventories(String route) {
        writeSerializableObject(state.getActiveMap().getInventories(), route + state.getActiveMap().getName().split("\\.")[0] + "_inventories.ser");
    }

    private void writeMap(String s, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write(s);

            System.out.println("Map serialized");

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private void writeSerializableObject(Object o, String fileName) {
        try(
                FileOutputStream file = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(file)
        ){
            oos.writeObject(o);
            System.out.println("Object serialized");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createSaveFolder(String route) {
        new File(route).mkdir();
    }
}
