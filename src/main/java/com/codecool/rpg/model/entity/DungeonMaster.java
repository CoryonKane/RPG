package com.codecool.rpg.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DungeonMaster {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Player player;
    @ElementCollection
    @Builder.Default
    private Map<Player, Integer> rating = new HashMap<>();
    @OneToMany(cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Story> stories = new HashSet<>();

    public void addRating (Player player, Integer rating) {
        if (!this.rating.containsKey(player)) {
            this.rating.put(player, rating);
        }
    }

    public void deleteRating (Player player) {
        this.rating.remove(player);
    }

    public void addStory (Story story) {
        this.stories.add(story);
    }

    public void removeStory (Story story) {
        this.stories.remove(story);
    }
}
