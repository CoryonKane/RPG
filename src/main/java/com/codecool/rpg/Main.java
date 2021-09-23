package com.codecool.rpg;

import com.codecool.rpg.model.actor.PlayerCharacter;
import com.codecool.rpg.model.map.GameMap;
import com.codecool.rpg.util.Draw;
import com.codecool.rpg.util.MapLoader;
import com.codecool.rpg.util.input.InputHandler;
import com.codecool.rpg.util.input.MovementController;
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

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application{

    private final Canvas canvas = new Canvas( //window size
            Toolkit.getDefaultToolkit().getScreenSize().width,
            Toolkit.getDefaultToolkit().getScreenSize().height);
    private final GraphicsContext context = canvas.getGraphicsContext2D();
    private final Timer timer = new Timer(true);
    private final Draw draw = Draw.getInstance();
    private final MapLoader mapLoader = MapLoader.getInstance();
    private InputHandler inputHandler;


    @Override
    public void start(Stage primaryStage) throws Exception {

        draw.setContext(context);
        draw.setCanvas(canvas);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);

        Scene scene = setUpStartScene(borderPane);

        setUpStage(primaryStage, scene);

        newGame();

    }

    private Scene setUpStartScene(BorderPane borderPane) throws MalformedURLException {
        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add((new File("src/main/style/stylesheet.css")).toURI().toURL().toExternalForm());
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);
        return scene;
    }

    private void setUpStage(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);

        primaryStage.getIcons().add(
                new Image("/images/icon.jpg")
        );
        primaryStage.setTitle("Bit Adventures");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        inputHandler.handleInput(keyEvent);
        draw.refresh();
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)) {
            exit();
        }
    }

    private void refresh() {
        draw.refresh();
    }

    private void newGame () {
        PlayerCharacter.newPlayer();
        GameMap map = mapLoader.loadMap("/templates/start.txt");
        draw.setMap(map);
        MovementController movementController = MovementController.getInstance();
        movementController.setMap(map);
        inputHandler = movementController;

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
