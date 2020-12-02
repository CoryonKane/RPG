package com.codecool.rpg.repository;

import com.codecool.rpg.model.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
