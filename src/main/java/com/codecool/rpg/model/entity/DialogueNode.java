package com.codecool.rpg.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DialogueNode {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String pictureUrl;
    @OneToMany
    private Map<DialogueResponse, DialogueNode> responses;
}
