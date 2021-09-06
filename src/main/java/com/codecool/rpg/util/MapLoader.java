package com.codecool.rpg.util;

import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.map.GameMap;
import com.codecool.rpg.model.map.cell.Cell;
import com.codecool.rpg.model.map.cell.CellType;

import java.io.InputStream;
import java.util.*;

public class MapLoader {
    private final Map<String, GameMap> mapCache = new HashMap<>();
    private static MapLoader instance;

    public static MapLoader getInstance() {
        if (instance == null) {
            instance = new MapLoader();
        }
        return instance;
    }

    private MapLoader() {}

    public GameMap loadMap(String mapName, PlayerCharacter player) {
        if (mapCache.containsKey(mapName)) {
            return mapCache.get(mapName);
        }

        InputStream is = MapLoader.class.getResourceAsStream(mapName);

        Scanner scanner = new Scanner(is);

        GameMap map = GameMap.builder()
                .map(new ArrayList<>())
                .items(new ArrayList<>())
                .enemies(new ArrayList<>())
                .name(mapName)
                .player(player)
                .build();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Cell> row = new ArrayList<>();
            for (String mapCharacter : line.split("")) {
                Cell cell = Cell.builder()
                        .cellType(Arrays.stream(CellType.values()).filter(v -> mapCharacter.equals(v.getTileCharacter())).findFirst().orElse(null))
                        .gameMap(map)
                        .build();
                row.add(cell);
            }
            map.addRow(row);
            if (row.size() > map.getMaxWidth()) {
                map.setMaxWidth(row.size());
            }
        }

        mapCache.put(mapName, map);

        return map;
    }
}
