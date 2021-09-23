package com.codecool.rpg.util.state;

public class StateSaver {

    private static StateSaver instance;

    public static StateSaver getInstance() {
        if (instance == null) {
            instance = new StateSaver();
        }
        return instance;
    }

    private StateSaver() {}

    public void saveState() {

    }
}
