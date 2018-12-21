//      Tanish Jain 2017115       Raunak Srikant Mokhasi 2017085

package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * This is the class used for Settings.
 * The Default Settings are - Difficulty = "Easy" and Colour = "Gold"
 */
public class Settings {

    private Main mainApp;
    private Parent settings;
    private Scene settingsScreen;
    private String difficulty = "EASY";
    private String colorSnake = "Gold";
    private Integer nmbrPlayers = 1;
    private static final ObservableList<String> difficultyLevelList = FXCollections.observableArrayList("EASY", "MEDIUM", "HARD");
    private static final ObservableList<String> colourSnakeList = FXCollections.observableArrayList("Red", "Green", "Blue", "Gold", "Mix");
    private static final ObservableList<Integer> nmbrPlayersList = FXCollections.observableArrayList(1);

    /**
     * This is the constructor of the Settings.
     * The Settings are displayed on an Anchor Pane.
     * Here the Settings that can be chosen are - Difficulty, Colour of Snake and Number of Players.
     * @param m It is an instance of the Main Class
     */
    public Settings(Main m){
        mainApp = m;
        settings = new AnchorPane();
        settings.setStyle("-fx-background-color: black");
        ((AnchorPane)settings).setPrefHeight(725.0);       ((AnchorPane)settings).setPrefWidth(1200.0);

        Label t1 = new Label("DIFFICULTY");
        Label t2 = new Label("COLOUR OF SNAKE");
        Label t3 = new Label("NUMBER OF PLAYERS");

        t1.setStyle("-fx-text-fill: #ff0022 ; -fx-font-weight: bold ; -fx-background-color: black ; -fx-font-size: 25");
        t2.setStyle("-fx-text-fill: #ff0022 ; -fx-font-weight: bold ; -fx-background-color: black ; -fx-font-size: 25");
        t3.setStyle("-fx-text-fill: #ff0022 ; -fx-font-weight: bold ; -fx-background-color: black ; -fx-font-size: 25");

        t1.setAlignment(Pos.CENTER);    t1.setPrefHeight(55.0);    t1.setPrefWidth(260.0);    t1.setLayoutX(155.0);    t1.setLayoutY(100.0);
        t2.setAlignment(Pos.CENTER);    t2.setPrefHeight(55.0);    t2.setPrefWidth(260.0);    t2.setLayoutX(155.0);    t2.setLayoutY(270.0);
        t3.setAlignment(Pos.CENTER);    t3.setPrefHeight(55.0);    t3.setPrefWidth(260.0);    t3.setLayoutX(155.0);    t3.setLayoutY(440.0);

        String image = Start.class.getResource("background7.jpg").toExternalForm();

        ChoiceBox<String> DifficultyLevel = new ChoiceBox();
        ChoiceBox<String> ColourSnake = new ChoiceBox();
        ChoiceBox<Integer> NmbrPlayers = new ChoiceBox();

        DifficultyLevel.getItems().addAll(difficultyLevelList);
        ColourSnake.getItems().addAll(colourSnakeList);
        NmbrPlayers.getItems().addAll(nmbrPlayersList);

        DifficultyLevel.setValue(difficulty);       DifficultyLevel.setPrefHeight(55.0);       DifficultyLevel.setPrefWidth(180.0);
        ColourSnake.setValue(colorSnake);           ColourSnake.setPrefHeight(55.0);           ColourSnake.setPrefWidth(180.0);
        NmbrPlayers.setValue(nmbrPlayers);          NmbrPlayers.setPrefHeight(55.0);           NmbrPlayers.setPrefWidth(180.0);

        DifficultyLevel.setLayoutX(700.0);      DifficultyLevel.setLayoutY(100.0);
        ColourSnake.setLayoutX(700.0);          ColourSnake.setLayoutY(270.0);
        NmbrPlayers.setLayoutX(700.0);          NmbrPlayers.setLayoutY(440.0);

        DifficultyLevel.setStyle("-fx-font-weight: bold; -fx-background-image: url('"+image+"'); -fx-alignment: center; -fx-font-size: 22; -fx-text-fill: black;");
        ColourSnake.setStyle("-fx-font-weight: bold; -fx-background-image: url('"+image+"'); -fx-alignment: center; -fx-font-size: 22; -fx-text-fill: black;");
        NmbrPlayers.setStyle("-fx-font-weight: bold; -fx-background-image: url('"+image+"'); -fx-alignment: center; -fx-font-size: 22; -fx-text-fill: black;");

        Button SettingsBackButton = new Button("BACK");
        SettingsBackButton.setPrefHeight(30.0);    SettingsBackButton.setPrefWidth(90.0);
        SettingsBackButton.setLayoutX(1020.0);      SettingsBackButton.setLayoutY(630.0);
        SettingsBackButton.setStyle("-fx-background-image: url('"+image+"'); -fx-font-size: 20; -fx-font-weight: bold");

        SettingsBackButton.setOnAction(e -> {
            setDifficulty(DifficultyLevel.getValue());
            setColorSnake(ColourSnake.getValue());
            setNmbrPlayers(Integer.parseInt(NmbrPlayers.getValue().toString()));
            mainApp.showHomeScreen();
        });

        ((AnchorPane) settings).getChildren().addAll(t1,t2,t3,DifficultyLevel,NmbrPlayers,ColourSnake,SettingsBackButton);
        settingsScreen = new Scene(settings, 1200, 725);
        settingsScreen.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.BACK_SPACE) {
                setDifficulty(DifficultyLevel.getValue());
                setColorSnake(ColourSnake.getValue());
                setNmbrPlayers(Integer.parseInt(NmbrPlayers.getValue().toString()));
                mainApp.showHomeScreen();
            }
        });
    }

    /**
     *
     * @return It returns the settings screen.
     */
    public Scene getSettingsScreen() {
        return settingsScreen;
    }

    /**
     *
     * @return It returns the Difficulty of the game.
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     *
     * @return It returns the Color of the snake.
     */
    public String getColorSnake() {
        return colorSnake;
    }

    /**
     *
     * @return It returns the Number of Players.
     */
    public int getNmbrPlayers() {
        return nmbrPlayers;
    }

    /**
     *
     * @param difficulty It sets the difficulty of the game. The options are Easy, Medium and Hard.
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     *
     * @param colorSnake It sets the Color of the Snake.
     */
    public void setColorSnake(String colorSnake) {
        this.colorSnake = colorSnake;
    }

    /**
     *
     * @param nmbrPlayers It sets the number of the Players.
     */
    public void setNmbrPlayers(Integer nmbrPlayers) {
        this.nmbrPlayers = nmbrPlayers;
    }
}