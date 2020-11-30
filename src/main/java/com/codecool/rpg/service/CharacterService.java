package com.codecool.rpg.service;

import com.codecool.rpg.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    private final CharacterRepository charRepository;

    @Autowired
    public CharacterService(CharacterRepository charRepository) {
        this.charRepository = charRepository;
    }
}
