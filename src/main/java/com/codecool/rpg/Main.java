package com.codecool.rpg;

import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.map.Direction;
import com.codecool.rpg.model.map.GameMap;
import com.codecool.rpg.util.Draw;
import com.codecool.rpg.util.MapLoader;
import com.codecool.rpg.util.MovementController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application{

    private final Canvas canvas = new Canvas( //window size
            Toolkit.getDefaultToolkit().getScreenSize().width,
            Toolkit.getDefaultToolkit().getScreenSize().height);
    private final GraphicsContext context = canvas.getGraphicsContext2D();
    private final Timer timer = new Timer(true);
    private Draw draw;
    private MapLoader mapLoader;
    private MovementController movementController;


    @Override
    public void init() {
        ConfigurableApplicationContext applicationContext = new SpringApplication(RpgApplication.class).run();
        draw = applicationContext.getBean(Draw.class);
        mapLoader = applicationContext.getBean(MapLoader.class);
        movementController = applicationContext.getBean(MovementController.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        draw.setContext(context);
        draw.setCanvas(canvas);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add((new File("src/main/style/stylesheet.css")).toURI().toURL().toExternalForm());
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.getIcons().add(
                new Image("/images/icon.jpg")
        );
        primaryStage.setTitle("Bit Adventures");
        primaryStage.setFullScreen(true);
        primaryStage.show();

        newGame();

    }

    private void onKeyPressed(KeyEvent keyEvent) {
        movementController.kexPressed(keyEvent, draw.getMap());
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }
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

    private void exit() {
        System.exit(0);
    }
}
