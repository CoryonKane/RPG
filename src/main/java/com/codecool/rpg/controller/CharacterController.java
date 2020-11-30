package com.codecool.rpg.controller;

import com.codecool.rpg.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("character/")
public class CharacterController {
    private final CharacterService charService;

    @Autowired
    public CharacterController(CharacterService charService) {
        this.charService = charService;
    }
}
