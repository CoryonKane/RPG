package com.codecool.rpg.model.actor.npc;

import com.codecool.rpg.model.actor.Actor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public abstract class NonPlayerCharacter extends Actor implements Serializable {
}
