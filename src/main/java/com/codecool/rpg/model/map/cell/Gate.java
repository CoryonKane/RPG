package com.codecool.rpg.model.map.cell;

import com.codecool.rpg.model.event.Event;
import com.codecool.rpg.model.event.TransferEvent;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gate extends Cell implements Serializable {
    private int goalCol;
    private int goalRow;
    private String goalMapName;

    @Override
    public Event arriveOn() {
        return TransferEvent.builder().gate(this).build();
    }
}
