package com.codecool.rpg.controller;

import com.codecool.rpg.service.DialogueService;
import com.codecool.rpg.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("story/")
public class StoryController {
    private final StoryService storyService;
    private final DialogueService dialogueService;

    @Autowired
    public StoryController(StoryService storyService, DialogueService dialogueService) {
        this.storyService = storyService;
        this.dialogueService = dialogueService;
    }
}
