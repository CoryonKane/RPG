package com.codecool.rpg.repository;

import com.codecool.rpg.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}
