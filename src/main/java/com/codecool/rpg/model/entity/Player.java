package com.codecool.rpg.model.entity;

import com.codecool.rpg.model.entity.DungeonMaster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String name;
    private String password;
    private String email;
    private String profilePicture;
    @OneToOne(cascade = CascadeType.ALL)
    private DungeonMaster dungeonMaster;
}
