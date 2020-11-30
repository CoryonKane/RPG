package com.codecool.rpg.service;

import com.codecool.rpg.repository.DungeonMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DungeonMasterService {
    private final DungeonMasterRepository dmRepository;

    @Autowired
    public DungeonMasterService(DungeonMasterRepository dmRepository) {
        this.dmRepository = dmRepository;
    }
}
