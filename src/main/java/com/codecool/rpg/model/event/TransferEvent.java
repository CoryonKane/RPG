package com.codecool.rpg.model.event;

import com.codecool.rpg.model.map.GameMap;
import com.codecool.rpg.model.map.cell.Gate;
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

    @Override
    public void doEvent(GameMap map) {

    }
}
