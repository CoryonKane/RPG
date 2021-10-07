package com.codecool.rpg.util.factory;

public class CanvasFactory {
    private static CanvasFactory instance;

    public static CanvasFactory getInstance() {
        if (instance == null) {
            instance = new CanvasFactory();
        }
        return instance;
    }

    private CanvasFactory() {}
}
