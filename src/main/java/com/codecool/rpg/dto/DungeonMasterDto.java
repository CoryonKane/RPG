package com.codecool.rpg.dto;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DungeonMasterDto {
    private Long id;
    private Long playerId;
    private String userName;
    private String profilePicture;
    @Singular
    private Set<Long> storyIds;
    private double rating;
}
