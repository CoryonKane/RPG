package com.codecool.rpg.model.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Item implements Serializable {
    private ItemType itemType;
    private String name;
}
