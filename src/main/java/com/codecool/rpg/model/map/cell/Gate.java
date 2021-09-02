package com.codecool.rpg.model.map.cell;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gate {
    private Cell cell;
    private String mapName;
    private int startX;
    private int startY;
}
