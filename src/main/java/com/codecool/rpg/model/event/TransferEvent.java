package com.codecool.rpg.model.event;

import com.codecool.rpg.model.map.cell.Gate;
import com.codecool.rpg.util.MapLoader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferEvent implements Event{

    private Gate gate;
    private final MapLoader mapLoader = MapLoader.getInstance();

    @Override
    public void doEvent() {
        mapLoader.loadMap(gate.getGoalMapName()); // TODO: 2021. 09. 06.
    }
}
