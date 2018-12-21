//      Tanish Jain 2017115       Raunak Srikant Mokhasi 2017085

package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;

/**
 *  This is the MAIN Class of the Application.
 */
public class Main extends Application implements Serializable{

    transient private Stage mainStage;
    transient private Parent home;
    transient private Scene homeScreen;
    transient private Help helpScreen;
              private LeaderBoard leaderBoardScreen;
              private Start startScreen;
    transient private Settings settingsScreen;
    transient private static final int easyBlocks = 5;
    transient private static final int mediumBlocks = 6;
    transient private static final int hardBlocks = 7;
    transient private int WindowHeight = 600;
    transient private int WindowWidth = 100*easyBlocks;
    transient private int BlockLength = easyBlocks;
              public int initialBallsSerialized = 0;
              public int initialScoreSerialized = 0;

    /**
     * This is the Start method of JavaFx application.
     * It handles all the GUI Components of the Application.
     * @param primaryStage It is the top level container.
     * All the Panes and other GUI components are inside this.
     */
    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        /*mainStage.setOnHiding(event -> {
            try {
                serialize(this);
            }catch(IOException e){
                System.out.println("IOException in serializing. line42");
            }
        });*/

        home = new GridPane();
        ((GridPane) home).setAlignment(Pos.CENTER);
        home.setStyle("-fx-background-color: black");
        home.prefWidth(1200);
        home.prefHeight(900);
        homeScreen = new Scene(home, 1200, 900);
        homeScreen.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.S || key.getCode() == KeyCode.R) {
                String dif = settingsScreen.getDifficulty();
                if(dif.equals("MEDIUM")) {
                    WindowWidth = 100*mediumBlocks;
                    BlockLength = mediumBlocks;
                }else if(dif.equals("HARD")) {
                    WindowWidth = 100*hardBlocks;
                    BlockLength = hardBlocks;
                }else{
                    System.out.println("No input found for Window Width.");
                    System.out.println("Difficulty = " + dif);
                }startScreen = new Start(this);
                showStartScreen();
            }else if(key.getCode() == KeyCode.H){
                showHelpScreen();
            }else if(key.getCode() == KeyCode.L){
                showLeaderBoardScreen();
            }else if(key.getCode() == KeyCode.SPACE){
                showSettingsScreen();
            }else if(key.getCode() == KeyCode.E  || key.getCode() == KeyCode.ESCAPE || key.getCode() == KeyCode.BACK_SPACE){
                //Main.getInstance().serialization();
                try {
                    serialize(this);
                }catch(IOException e){
                    //System.out.println("IOException in serializing. line107");
                }
                System.exit(0);
            }
        });

        Button StartButton = new Button("START");
        Button ResumeButton = new Button("RESUME");
        Button LeaderBoardButton = new Button("LEADERBOARD");
        Button HelpButton = new Button("HELP");
        Button SettingsButton = new Button("SETTINGS");
        Button ExitButton = new Button("EXIT");

        StartButton.setStyle("-fx-text-fill: white ; -fx-background-color: black ; -fx-font-weight: bold ; -fx-font-size: 30; -fx-border-radius: 20; -fx-border-color: white");
        ResumeButton.setStyle("-fx-text-fill: white ; -fx-background-color: black ; -fx-font-weight: bold ; -fx-font-size: 30; -fx-border-radius: 20; -fx-border-color: white");
        HelpButton.setStyle("-fx-text-fill: white ; -fx-background-color: black ; -fx-font-weight: bold ; -fx-font-size: 30; -fx-border-radius: 20; -fx-border-color: white");
        LeaderBoardButton.setStyle("-fx-text-fill: white ; -fx-background-color: black ; -fx-font-weight: bold ; -fx-font-size: 30; -fx-border-radius: 20; -fx-border-color: white");
        SettingsButton.setStyle("-fx-text-fill: white ; -fx-background-color: black ; -fx-font-weight: bold ; -fx-font-size: 30; -fx-border-radius: 20; -fx-border-color: white");
        ExitButton.setStyle("-fx-text-fill: white ; -fx-background-color: black ; -fx-font-weight: bold ; -fx-font-size: 30; -fx-border-radius: 20; -fx-border-color: white");

        StartButton.setOnAction((e -> {
            String dif = settingsScreen.getDifficulty();
            if(dif.equals("MEDIUM")) {
                WindowWidth = 100*mediumBlocks;
                BlockLength = mediumBlocks;
            }else if(dif.equals("HARD")) {
                WindowWidth = 100*hardBlocks;
                BlockLength = hardBlocks;
            }else{
                System.out.println("No input found for Window Width.");
                System.out.println("Difficulty = " + dif);
            }startScreen = new Start(this);
            showStartScreen();
        }));
        ResumeButton.setOnAction((e -> {
            String dif = settingsScreen.getDifficulty();
            if(dif.equals("MEDIUM")) {
                WindowWidth = 100*mediumBlocks;
                BlockLength = mediumBlocks;
            }else if(dif.equals("HARD")) {
                WindowWidth = 100*hardBlocks;
                BlockLength = hardBlocks;
            }else{
                System.out.println("No input found for Window Width.");
                System.out.println("Difficulty = " + dif);
            }startScreen = new Start(this);
            showStartScreen();
        }));
        LeaderBoardButton.setOnAction((e -> {
            showLeaderBoardScreen();
        }));
        HelpButton.setOnAction((e -> {
            showHelpScreen();
        }));
        SettingsButton.setOnAction((e -> {
            showSettingsScreen();
        }));
        ExitButton.setOnAction(e -> {
            //Main.getInstance().serialization();
            try {
                serialize(this);
            }catch(IOException ee){
                //System.out.println("IOException in serializing. line160");
            }
            System.exit(0);
        });

        StartButton.setPrefHeight(100.0);          StartButton.setPrefWidth(270.0);
        ResumeButton.setPrefHeight(100.0);         ResumeButton.setPrefWidth(270.0);
        HelpButton.setPrefHeight(100.0);           HelpButton.setPrefWidth(270.0);
        LeaderBoardButton.setPrefHeight(100.0);    LeaderBoardButton.setPrefWidth(270.0);
        SettingsButton.setPrefHeight(100.0);       SettingsButton.setPrefWidth(270.0);
        ExitButton.setPrefHeight(100.0);           ExitButton.setPrefWidth(270.0);

        Label label = new Label("SNAKE V/S BLOCK");
        label.setStyle("-fx-text-fill: yellow; -fx-font-weight: bold; -fx-font-size: 110;");
        label.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(StartButton, ResumeButton, HelpButton, LeaderBoardButton, SettingsButton, ExitButton);

        VBox vBoxMain = new VBox();
        vBoxMain.getChildren().addAll(label, vBox);
        ((GridPane) home).getChildren().add(vBoxMain);

        leaderBoardScreen = new LeaderBoard(this);
        settingsScreen = new Settings(this);
        helpScreen = new Help(this);

        try {
            Main m2 = deserialize();
            try {
                System.out.println(m2.getLeaderBoardScreen().getLeaderBoardList().getScoreNodes().size());
                for (int i = 0; i < m2.getLeaderBoardScreen().getLeaderBoardList().getScoreNodes().size(); i++) {
                    try {
                        getLeaderBoardScreen().getLeaderBoardList().addNode(m2.getLeaderBoardScreen().getLeaderBoardList().getScoreNodes().get(i));
                    }catch(Exception e){
                       // System.out.println(e.toString());
                    }
                }
            }catch(Exception e){
               // System.out.println(e.toString());
            }
            try {
                initialScoreSerialized = m2.getStartScreen().getScore();
              //  System.out.println("193 "+ initialScoreSerialized);
            }catch(NullPointerException e){
                //e.printStackTrace();
              //  System.out.println(e.toString());
            }
            try {
                initialBallsSerialized = m2.getStartScreen().getSnake().getLength();
             //   System.out.println("199 " + initialBallsSerialized);
            }catch(NullPointerException e){
             //   System.out.println("201 " + e.toString());
            }
        }catch(ClassNotFoundException e){
         //   System.out.println("ClassNotFoundException in deserializing. line91");
        }catch(IOException e){
         //   System.out.println("IOException in deserializing. line93");
        }

        mainStage.setTitle("Snake v/s Block");
        showHomeScreen();
        mainStage.show();
    }


    /**
     *
     * @return It returns the Start Screen.
     */
    public Start getStartScreen(){
        return startScreen;
    }

    /**
     *
     * @return It returns the Home Screen.
     */
    public Scene getHomeScreen(){
        return homeScreen;
    }

    /**
     *
     * @return It returns the Leaderboard Screen.
     */
    public LeaderBoard getLeaderBoardScreen(){
        return leaderBoardScreen;
    }

    /**
     *
     * @return It returns the Settings Screen.
     */
    public Settings getSettingsScreen(){
        return settingsScreen;
    }

    /**
     *
     * @return It returns the Help Screen.
     */
    public Help getHelpScreen(){
        return helpScreen;
    }

    /**
     *
     * @return It returns the Main Stage.
     */
    public Stage getMainStage(){
        return mainStage;
    }

    /**
     * It Creates the New Home Screen.
     */
    public void createNewHomeScreen(){
        startScreen = new Start(this);
    }

    /**
     * It is used to show the LeaderBoard Screen
     */
    public void showLeaderBoardScreen(){
        mainStage.setScene(leaderBoardScreen.getLeaderBoardScreen());
    }

    /**
     * It is used to show the Settings Screen
     */
    public void showSettingsScreen(){
        mainStage.setScene(settingsScreen.getSettingsScreen());
    }

    /**
     * It is used to show the Home Screen
     */
    public void showHomeScreen(){
        mainStage.setScene(homeScreen);
    }

    /**
     * It is used to show the Start Screen
     */
    public void showStartScreen(){
        mainStage.setScene(startScreen.getStartScreen());
    }

    /**
     * It is used to show the Help Screen
     */
    public void showHelpScreen(){
        mainStage.setScene(helpScreen.getHelpScreen());
    }

    /**
     * It is used to get Window Height Size
     * @return It returns the Window Height
     */
    public int getWindowHeight(){
        return WindowHeight;
    }

    /**
     * It is used to get Window Width Size
     * @return It returns the Window Width
     */
    public int getWindowWidth(){
        return WindowWidth;
    }

    /**
     * It is used to get Block length
     * @return It returns the Block Length
     */
    public int getBlockLength(){
        return BlockLength;
    }

    /**
     * This method is used to serialize (save) the state of the Game.
     * @param m It is the instance of the Main Class.
     * @throws IOException The method may throw an IOException
     */
    public static void serialize(Main m) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("out.txt"));
            try {
                out.writeObject(m);
            }catch(Exception e){
                e.printStackTrace();
            }
        } finally {
            out.close();
        }
    }

    /**
     * This method is used to deserialize (load) the game back from its saved condition.
     * @return It Returns an instance of the Main Class.
     * @throws IOException It may throw an IOException
     * @throws ClassNotFoundException It may throw a ClassNotFoundException
     */
    private static Main deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        Main m = null;
        try {
            in = new ObjectInputStream (new FileInputStream("out.txt"));
            m = (Main) in.readObject();
            return m;
        } finally {
            if(in != null)
                in.close();
        }
    }

    /**
     * This is the main method of the Application.
     * @param args It takes in Command Line Arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}