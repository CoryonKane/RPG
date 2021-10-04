package com.codecool.rpg.model.event;

import com.codecool.rpg.model.map.cell.Gate;
import com.codecool.rpg.util.state.StateLoader;
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
    private final StateLoader stateLoader = StateLoader.getInstance();

    @Override
    public void doEvent() {
//        stateLoader.loadMap(gate.getGoalMapName());
    }
}
