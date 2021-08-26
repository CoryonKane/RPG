package com.codecool.rpg;

import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.map.Direction;
import com.codecool.rpg.model.map.GameMap;
import com.codecool.rpg.util.Draw;
import com.codecool.rpg.util.MapLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    private final Canvas canvas = new Canvas( //window size
            1000,
            1000);
    private GraphicsContext context = canvas.getGraphicsContext2D();
    private Timer timer = new Timer(true);
    private Draw draw = new Draw(canvas, context);
    private MapLoader mapLoader = new MapLoader();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add((new File("src/main/style/stylesheet.css")).toURI().toURL().toExternalForm());
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

        newGame();

    }

    private void onKeyPressed(KeyEvent keyEvent) {
    }

    private void onKeyReleased(KeyEvent keyEvent) {
    }

    private void refresh() {
        draw.refresh();
    }

    private void newGame () {
        PlayerCharacter player = PlayerCharacter.builder()
                .experience(0)
                .level(1)
                .build();
        player.setRow(4);
        player.setCol(4);
        player.setFacing(Direction.UP);
        player.fillTileNames();
        GameMap map = mapLoader.loadMap("/templates/start.txt", player);
        draw.setMap(map);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    refresh();
                });
            }
        }, 0, 600);
    }
}
