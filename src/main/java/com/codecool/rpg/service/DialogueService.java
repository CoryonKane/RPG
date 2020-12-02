package com.codecool.rpg.service;

import com.codecool.rpg.repository.DialogueNodeRepository;
import com.codecool.rpg.repository.DialogueResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DialogueService {
    private final DialogueNodeRepository nodeRepository;
    private final DialogueResponseRepository responseRepository;

    @Autowired
    public DialogueService(DialogueNodeRepository nodeRepository, DialogueResponseRepository responseRepository) {
        this.nodeRepository = nodeRepository;
        this.responseRepository = responseRepository;
    }
}
