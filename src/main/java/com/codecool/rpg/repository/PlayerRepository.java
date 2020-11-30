package com.codecool.rpg.repository;

import com.codecool.rpg.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
