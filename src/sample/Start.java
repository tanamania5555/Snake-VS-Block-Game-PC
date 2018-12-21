//      Tanish Jain 2017115       Raunak Srikant Mokhasi 2017085

package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This is the class that is mainly involved with the GUI aspect and functioning of the Game.
 * It contains all the animations and also ensures smooth and proper functioning of the game.
 */
public class Start implements Serializable {

    transient private Main mainApp;
    transient private Scene startScreen;
    transient private Parent start;
    transient private Snake snake;
    transient private Timeline timeline;
    transient private Timeline timeline2;
    transient private Pane endPane;
    transient private Pane PausePane;
    transient private Label scoreLabelEndPane;
    transient private Label scoreLabel;
    transient private HBox hBox1;
    transient private HBox hBox2;
    transient private ArrayList<Block> Blocks = new ArrayList<>();
    transient private ArrayList<Block> Blocks2 = new ArrayList<>();
    transient private ArrayList<Ball> Balls = new ArrayList<>();
    transient private ArrayList<Magnet> Magnets = new ArrayList<>();
    transient private ArrayList<Shield> Shields = new ArrayList<>();
    transient private ArrayList<DestroyBlock> DestroyBlocks = new ArrayList<>();
    transient private ArrayList<Wall> Walls = new ArrayList<>();
    transient private Image magnetImgURL = new Image("/sample/magnetImage.png");
    transient private Image destroyBlockImgURL = new Image("/sample/destroyBlock.png");
    transient private Image shieldImgURL = new Image("/sample/shieldImage.png");
    transient private ImageView magnetImage = new ImageView(magnetImgURL);
    transient private ImageView destroyBlockImage = new ImageView(destroyBlockImgURL);
    transient private ImageView shieldImage = new ImageView(shieldImgURL);
    transient private static final int delta = 5;
              private              int Score = 0;

    /**
     * This is the Constructor of the Start Class and is used to create all GUI Components and place them on one screen.
     * It also ensures smooth and proper functioning of the game.
     * @param main This is an instance of the Main Class.
     */
    public Start(Main main) {
        Random r = new Random();
        mainApp = main;

        start = new AnchorPane();
        ((AnchorPane)start).setPrefSize(mainApp.getWindowWidth(),mainApp.getWindowHeight());
        start.setStyle("-fx-background-color: black;");

        startScreen = new Scene(start, mainApp.getWindowWidth(), mainApp.getWindowHeight());
        startScreen.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) {
                snake.snakeMoveLeft();
            }else if(key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT){
                snake.snakeMoveRight();
            }else if(key.getCode() == KeyCode.BACK_SPACE){
                stop();
            }
        });

        String col = mainApp.getSettingsScreen().getColorSnake();
        snake = new Snake(mainApp, this, col);

        scoreLabel = new Label(" " + Score);
        scoreLabel.setPrefWidth(200);
        scoreLabel.setPrefHeight(25);
        scoreLabel.setStyle("-fx-text-fill : white; -fx-font-size : 20; -fx-font-weight: bold");

        String image = Start.class.getResource("background3.jpg").toExternalForm();
        String image2 = Start.class.getResource("background4.jpg").toExternalForm();

        PausePane = new AnchorPane();
        PausePane.setStyle("-fx-border-radius: 18; -fx-background-image: url('" + image2 + "')");
        //PausePane.setStyle("-fx-border-radius: 18; -fx-background-color: black");
        PausePane.setPrefWidth(325);    PausePane.setPrefHeight(240);
        PausePane.setLayoutX(((AnchorPane) start).getWidth()/2  -  162.5);
        PausePane.setLayoutY(((AnchorPane) start).getHeight()/2  -  120);

        Image pauseImgURL = new Image("/sample/pauseImage.png");
        ImageView pauseImage = new ImageView(pauseImgURL);
        pauseImage.setFitHeight(40);     pauseImage.setFitWidth(40);
        pauseImage.setX(((AnchorPane) start).getWidth()-40);
        pauseImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            timeline.pause();
            try {
                ((AnchorPane) start).getChildren().add(PausePane);
            }catch(IllegalArgumentException e){
                //do nothing
            }
        });

        shieldImage.setFitHeight(20);          shieldImage.setFitWidth(20);
        magnetImage.setFitHeight(20);          magnetImage.setFitWidth(20);
        destroyBlockImage.setFitHeight(20);    destroyBlockImage.setFitWidth(20);

        shieldImage.setVisible(false);
        magnetImage.setVisible(false);
        destroyBlockImage.setVisible(false);

        shieldImage.setX(((AnchorPane) start).getWidth() - 70);
        magnetImage.setX(((AnchorPane) start).getWidth() - 100);
        destroyBlockImage.setX(((AnchorPane) start).getWidth() - 130);

        ((AnchorPane) start).getChildren().addAll(magnetImage,shieldImage,destroyBlockImage);


        endPane = new AnchorPane();
        endPane.setStyle("-fx-border-radius: 18; -fx-background-image: url('" + image2 + "')");
        endPane.setPrefWidth(350);    endPane.setPrefHeight(245);
        endPane.setLayoutX(((AnchorPane) start).getWidth()/2  -  175);
        endPane.setLayoutY(((AnchorPane) start).getHeight()/2  -  122.5);

        TextField name = new TextField();
        name.setEditable(false);
        name.setText("ENTER NAME");
        name.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            name.setEditable(true);
            name.setText("");
                });
        name.setPrefHeight(40);     name.setPrefWidth(250);
        name.setLayoutX(50);        name.setLayoutY(40);

        scoreLabelEndPane = new Label();
        scoreLabelEndPane.setText("" + Score);
        scoreLabelEndPane.setPrefHeight(40);    scoreLabelEndPane.setPrefWidth(250);
        scoreLabelEndPane.setLayoutX(50);       scoreLabelEndPane.setLayoutY(120);

        Button OK = new Button("OK");
        OK.setPrefHeight(25);  OK.setPrefWidth(150);
        OK.setLayoutX(100);    OK.setLayoutY(190);

        OK.setAlignment(Pos.CENTER);
        scoreLabelEndPane.setAlignment(Pos.CENTER);
        name.setAlignment(Pos.CENTER);

        OK.setStyle("-fx-background-image: url('" + image + "')");
        scoreLabelEndPane.setStyle("-fx-background-image: url('" + image + "')");
        name.setStyle("-fx-background-image: url('" + image + "')");

        endPane.getChildren().addAll(name,scoreLabelEndPane,OK);

        timeline2 = new Timeline(new KeyFrame(
                Duration.millis(10),
                eventTime -> {
                    int iiii = 1111;
                }));
        timeline2.setCycleCount(Animation.INDEFINITE);


        OK.setOnAction(e -> {
            timeline2.stop();
            mainApp.showHomeScreen();
            String namee = name.getText();
            String ts = LocalDateTime.now().toString();  //YYYY-MM-DDTHH:MM:SS.(MS)(MS)
            String time = ts.substring(11,13) + ":" + ts.substring(14,16);
            String date = ts.substring(8,10) + "/" + ts.substring(5,7) + "/" + ts.substring(2,4);
            ScoreNode s = new ScoreNode(namee,Score,date,time);
            mainApp.getLeaderBoardScreen().getLeaderBoardList().addNode(s);
        });

        endPane.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ENTER || key.getCode() == KeyCode.E) {
                timeline2.stop();
                mainApp.showHomeScreen();
                String namee = name.getText();
                String ts = LocalDateTime.now().toString();  //YYYY-MM-DDTHH:MM:SS.(MS)(MS)
                String time = ts.substring(11,13) + ":" + ts.substring(14,16);
                String date = ts.substring(8,10) + "/" + ts.substring(5,7) + "/" + ts.substring(2,4);
                ScoreNode s = new ScoreNode(namee,Score,date,time);
                mainApp.getLeaderBoardScreen().getLeaderBoardList().addNode(s);
            }
        });

        Button mainMenuButton = new Button("Main Menu");
        Button resumeButton = new Button("Resume");
        Button restartButton = new Button("Restart");

        mainMenuButton.setPrefWidth(175);    mainMenuButton.setPrefHeight(40);      mainMenuButton.setAlignment(Pos.CENTER);
        resumeButton.setPrefWidth(175);      resumeButton.setPrefHeight(40);        resumeButton.setAlignment(Pos.CENTER);
        restartButton.setPrefWidth(175);     restartButton.setPrefHeight(40);       restartButton.setAlignment(Pos.CENTER);

        mainMenuButton.setLayoutX(75);       mainMenuButton.setLayoutY(30);
        resumeButton.setLayoutX(75);         resumeButton.setLayoutY(100);
        restartButton.setLayoutX(75);        restartButton.setLayoutY(170);

        mainMenuButton.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-image: url('" + image + "')");
        resumeButton.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-image: url('" + image + "')");
        restartButton.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-image: url('" + image + "')");

        mainMenuButton.setOnAction(e -> {
            mainApp.showHomeScreen();
        });
        resumeButton.setOnAction(e -> {
            timeline.play();
            ((AnchorPane) start).getChildren().remove(PausePane);
        });
        restartButton.setOnAction(e -> {
            mainApp.showHomeScreen();
            mainApp.createNewHomeScreen();
            mainApp.showStartScreen();
        });

        PausePane.getChildren().addAll(mainMenuButton, resumeButton, restartButton);

        for(int i = 0; i<mainApp.getBlockLength(); i++){
            Block b = new Block(this);
            b.setWeight(1);
            b.setLayoutX(100*i);
            Blocks.add(b);

            Block b2 = new Block(this);
            b2.setWeight(1);
            b2.setLayoutX(100*i);
            Blocks2.add(b2);
        }

        hBox1 = new HBox();   hBox1.setLayoutY(-200);   hBox1.setPrefHeight(Blocks.get(0).getRect().getHeight());    hBox1.setPrefWidth(mainApp.getWindowWidth());
        hBox2 = new HBox();   hBox2.setLayoutY(-700);   hBox2.setPrefHeight(Blocks2.get(0).getRect().getHeight());   hBox2.setPrefWidth(mainApp.getWindowWidth());

        for(int i = 0; i<mainApp.getBlockLength(); i++){
            hBox1.getChildren().add(Blocks.get(i).getSPane());
            hBox2.getChildren().add(Blocks2.get(i).getSPane());
        }

        ((AnchorPane)start).getChildren().addAll(hBox1,hBox2);

        for(int i = 0; i<mainApp.getBlockLength(); i++){
            Ball b = new Ball();

            if(col.equals("Red")) b.getCircle().setFill(Color.RED);
            else if(col.equals("Green")) b.getCircle().setFill(Color.GREEN);
            else if(col.equals("Blue")) b.getCircle().setFill(Color.BLUE);

            if(r.nextInt(2) == 0){
                b.setWeight(1);
            }else{
                b.setWeight(2);
            }b.setLayoutX(50 + 100*i);
            b.setLayoutY(100);
            Balls.add(b);
            b.setWeight(1);
            ((AnchorPane) start).getChildren().add(b.getSPane());
        }

        ((AnchorPane) start).getChildren().addAll(scoreLabel, pauseImage);

        if(mainApp.initialBallsSerialized != 0){
            while(snake.getLength() < mainApp.initialBallsSerialized){
                snake.addBall();
            }while(snake.getLength() > mainApp.initialBallsSerialized){
                snake.delBall();
            }
        }
        if(mainApp.initialBallsSerialized != 0 && mainApp.initialScoreSerialized != 0){
            Score = 0+mainApp.initialScoreSerialized;
        }

        timeline = new Timeline(new KeyFrame(
            Duration.millis(20),
            eventTime -> {
                try {
                    mainApp.serialize(mainApp);
                }catch(IOException e){
                    System.out.println("IOException in serializing. line42");
                }
                if(snake.getLength() == 0){
                    stop();
                }

                for(int i = Balls.size()-1; i>=0; i--){
                    Balls.get(i).isColliding(snake);
                    if(Balls.get(i).flag == 1)
                        Balls.remove(i);
                    else if(Balls.get(i).getLayoutY() >= mainApp.getWindowHeight()){
                        Balls.get(i).getSPane().setVisible(false);
                        Balls.remove(Balls.get(i));
                    }
                }
                for(int i = Magnets.size()-1; i>=0; i--){
                    Magnets.get(i).isColliding(snake);
                    if(Magnets.get(i).flag == 1) {
                        Magnets.remove(i);
                        setVisibilityMagnetImg(true);
                    }else if(Magnets.get(i).getLayoutY() >= mainApp.getWindowHeight()){
                        Magnets.get(i).getImage().setVisible(false);
                        Magnets.remove(i);
                    }
                }
                for(int i = Shields.size()-1; i>=0; i--){
                    Shields.get(i).isColliding(snake);
                    if(Shields.get(i).flag == 1) {
                        Shields.remove(i);
                        setVisibilityShieldImg(true);
                    }else if(Shields.get(i).getLayoutY() >= mainApp.getWindowHeight()){
                        Shields.get(i).getImage().setVisible(false);
                        Shields.remove(i);
                    }
                }snake.setDestroyBlockActive(false);
                destroyBlockImage.setVisible(false);
                for(int i = DestroyBlocks.size()-1; i>=0; i--){
                    DestroyBlocks.get(i).isColliding(snake);
                    if(DestroyBlocks.get(i).flag == 1) {
                        snake.setDestroyBlockActive(true);
                        DestroyBlocks.remove(i);
                        setVisibilityDestroyBlockImg(true);
                        if(hBox1.getLayoutY() >= 0){
                            for(int ii = 0; ii<Blocks.size(); ii++){
                                Blocks.get(ii).isColliding(snake);
                            }
                        }if(hBox2.getLayoutY() >= 0){
                            for(int ii = 0; ii<Blocks2.size(); ii++){
                                Blocks2.get(ii).isColliding(snake);
                            }
                        }
                    }else if(DestroyBlocks.get(i).getLayoutY() >= mainApp.getWindowHeight()){
                        DestroyBlocks.get(i).getImage().setVisible(false);
                        DestroyBlocks.remove(i);
                    }
                }

                if(Balls.size()<2){
                    for(int i = 0; i<5; i++) {
                        Ball b = new Ball();
                        int newX = r.nextInt((int) ((AnchorPane) start).getWidth() - (int) b.getCircle().getRadius());
                        if(newX < b.getCircle().getRadius()){
                            newX = (int)b.getCircle().getRadius();
                        }
                        b.setLayoutX(newX);
                        b.setLayoutY((-1)*r.nextInt((int) ((AnchorPane) start).getHeight()));
                        b.setWeight(r.nextInt(9)+1);

                        if (col.equals("Red")) b.getCircle().setFill(Color.RED);
                        else if (col.equals("Green")) b.getCircle().setFill(Color.GREEN);
                        else if (col.equals("Blue")) b.getCircle().setFill(Color.BLUE);

                        Balls.add(b);
                        ((AnchorPane) start).getChildren().add(b.getSPane());
                    }
                }
                if(Magnets.size()<1 && Shields.size()<1 && DestroyBlocks.size()<1 && r.nextInt(500)==1) {
                    int i = r.nextInt(3);
                    if (i == 0) {
                        Magnet m = new Magnet(this);
                        int ii = r.nextInt((int) ((AnchorPane) start).getWidth());
                        if((int)((AnchorPane) start).getWidth() - ii < 30)
                            ii = (int)((AnchorPane) start).getWidth() - 30;
                        m.setLayoutX(ii);
                        m.setLayoutY((-1) * r.nextInt((int) ((AnchorPane) start).getHeight()));
                        Magnets.add(m);
                        ((AnchorPane) start).getChildren().add(m.getImage());
                    } else if (i == 1) {
                        Shield s = new Shield(this);
                        int ii = r.nextInt((int) ((AnchorPane) start).getWidth());
                        if((int)((AnchorPane) start).getWidth() - ii < 30)
                            ii = (int)((AnchorPane) start).getWidth() - 30;
                        s.setLayoutX(ii);
                        s.setLayoutY((-1) * r.nextInt((int) ((AnchorPane) start).getHeight()));
                        Shields.add(s);
                        ((AnchorPane) start).getChildren().add(s.getImage());
                    } else if (i == 2) {
                        DestroyBlock d = new DestroyBlock();
                        int ii = r.nextInt((int) ((AnchorPane) start).getWidth());
                        if((int)((AnchorPane) start).getWidth() - ii < 30)
                            ii = (int)((AnchorPane) start).getWidth() - 30;
                        d.setLayoutX(ii);
                        d.setLayoutY((-1) * r.nextInt((int) ((AnchorPane) start).getHeight()));
                        DestroyBlocks.add(d);
                        ((AnchorPane) start).getChildren().add(d.getImage());
                    }
                }
                if(Walls.size()<2){
                    if(hBox1.getLayoutY() < -300) {
                        Wall w = new Wall();
                        w.setLayoutX((double)r.nextInt(mainApp.getWindowWidth()) + 1);
                        w.setLength(r.nextInt(100) + 100);
                        w.setLayoutY((double)hBox1.getLayoutY() + 100);
                        ((AnchorPane) start).getChildren().add(w.getRect());
                        Walls.add(w);
                    }
                    if(hBox2.getLayoutY() < -300) {
                        Wall w2 = new Wall();
                        w2.setLayoutX(r.nextInt(mainApp.getWindowWidth()) + 1);
                        w2.setLength(r.nextInt(100) + 100);
                        w2.setLayoutY(hBox2.getLayoutY() + 100);
                        ((AnchorPane) start).getChildren().add(w2.getRect());
                        Walls.add(w2);
                    }
                }

                for(int i = 0; i<Walls.size(); i++){
                    Walls.get(i).setLayoutY(Walls.get(i).getLayoutY() + delta + delta*snake.getLength()/250);
                    if(snake.isCollidingWall(Walls.get(i))){
                        if(Walls.get(i).getRect().getLayoutX() > mainApp.getWindowWidth()/2) {
                            snake.snakeMoveLeft();
                            snake.snakeMoveLeft();
                        }else{
                            snake.snakeMoveRight();
                            snake.snakeMoveRight();
                        }
                    }
                }

                if(hBox1.getLayoutY() >= -150 && hBox1.getLayoutY() <= -100) {
                    int le = snake.getLength();
                    if (le == 0) le += 1;
                    if(r.nextInt(3) == 1){
                        for (int i = 0; i < Blocks.size(); i++) {
                            Blocks.get(i).getSPane().setVisible(true);
                            Blocks.get(i).setWeight(r.nextInt(le) + 1);
                        }
                    }else {
                        int f1 = 0;
                        for (int i = 0; i < Blocks.size(); i++) {
                            if (r.nextInt(2) == 1) {
                                Blocks.get(i).getSPane().setVisible(true);
                                Blocks.get(i).setWeight(r.nextInt(le) + 1);
                                f1 += 1;
                            } else {
                                Blocks.get(i).getSPane().setVisible(false);
                            }
                        }
                        if (f1 == 0) {
                            int b1 = r.nextInt(5);
                            int b2 = r.nextInt(5);
                            while (b2 == b1) {
                                b2 = r.nextInt(5);
                            }
                            Blocks.get(b1).getSPane().setVisible(true);
                            Blocks.get(b2).getSPane().setVisible(true);
                        } else if (f1 == 1) {
                            int b1 = r.nextInt(5);
                            while (Blocks.get(b1).getSPane().isVisible()) {
                                b1 = r.nextInt(5);
                            }
                            Blocks.get(b1).getSPane().setVisible(true);
                        }
                    }
                }
                if(hBox2.getLayoutY() >= -150 && hBox2.getLayoutY() <= -100){
                    int le2 = snake.getLength();
                    if(le2 == 0) le2+=1;
                    if(r.nextInt(3)== 1) {
                        for (int i = 0; i < Blocks2.size(); i++) {
                            Blocks2.get(i).getSPane().setVisible(true);
                            Blocks2.get(i).setWeight(r.nextInt(le2) + 1);
                        }
                    }else{
                        int f2 = 0;
                        for (int i = 0; i < Blocks2.size(); i++) {
                            if (r.nextInt(2) == 1) {
                                Blocks2.get(i).getSPane().setVisible(true);
                                Blocks2.get(i).setWeight(r.nextInt(le2) + 1);
                                f2 += 1;
                            } else {
                                Blocks2.get(i).getSPane().setVisible(false);
                            }
                        }
                        if (f2 == 0) {
                            int b3 = r.nextInt(5);
                            int b4 = r.nextInt(5);
                            while (b4 == b3) {
                                b4 = r.nextInt(5);
                            }
                            Blocks2.get(b3).getSPane().setVisible(true);
                            Blocks2.get(b4).getSPane().setVisible(true);
                        } else if (f2 == 1) {
                            int b3 = r.nextInt(5);
                            while (Blocks2.get(b3).getSPane().isVisible()) {
                                b3 = r.nextInt(5);
                            }
                            Blocks2.get(b3).getSPane().setVisible(true);
                        }
                    }
                }


                if(!isCollidingWithBlock1()){
                    hBox1.setLayoutY(hBox1.getLayoutY() + delta + delta*snake.getLength()/250);
                } else {
                    int index = 1+(int)snake.getSnakeBody().get(0).getLayoutX()/100;
                    Blocks.get(index-1).isColliding(snake);
                    hBox1.setLayoutY(hBox1.getLayoutY() + delta + delta*snake.getLength()/250);
                }
                if(!isCollidingWithBlock2()){
                    hBox2.setLayoutY(hBox2.getLayoutY() + delta + delta*snake.getLength()/250);
                } else {
                    int index = 1+(int)snake.getSnakeBody().get(0).getLayoutX()/100;
                    Blocks2.get(index-1).isColliding(snake);
                    hBox2.setLayoutY(hBox2.getLayoutY() + delta + delta*snake.getLength()/250);
                }


                if(hBox1.getLayoutY() > mainApp.getWindowHeight()){
                    hBox1.setLayoutY(hBox2.getLayoutY() - 500);
                }
                if(hBox2.getLayoutY() > mainApp.getWindowHeight()){
                    hBox2.setLayoutY(hBox1.getLayoutY() - 500);
                }
                for(int i = Walls.size()-1; i>=0; i--){
                    if(Walls.get(i).getLayoutY()>mainApp.getWindowHeight()){
                        Walls.get(i).getRect().setVisible(false);
                        Walls.remove(i);
                    }
                }
            }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public Snake getSnake() {
        return snake;
    }

    public ArrayList<Wall> getWalls(){
        return Walls;
    }

    /**
     *
     * @return It returns the Start Screen Scene object.
     */
    public Scene getStartScreen() {
        return startScreen;
    }

    /**
     *
     * @return It returns the Start object.
     */
    public Parent getStart() {
        return start;
    }

    /**
     * This method is used to set the score.
     * @param newScore This is the new value of the score.
     */
    public void setScore(int newScore){
        Score = newScore;
        scoreLabel.setText(" " + Score);
    }

    /**
     *
     * @return It is used to get the current value of the Score.
     */
    public int getScore(){
        return Score;
    }

    /**
     * This method used to increase the score by 1.
     */
    public void incScore(){
        Score += 1;
        scoreLabel.setText(" " + Score);
    }

    /**
     * This method is used to pause the game.
     */
    public void pause(){
        timeline.pause();
    }

    /**
     * This method is used to resume the game.
     */
    public void play(){
        timeline.play();
    }

    /**
     * This method is used to stop the game and display the score.
     */
    public void stop(){
        scoreLabelEndPane.setText("SCORE : " + Score);
        try {
            ((AnchorPane) start).getChildren().add(endPane);
        }catch(IllegalArgumentException e){
            //do nothing
        }
        timeline2.play();
        timeline.stop();
    }


    /**
     * It sets visibility of the Magnet
     * @param visibility It is the boolean value of whether the visibility is set or not.
     */
    public void setVisibilityMagnetImg(boolean visibility){
        magnetImage.setVisible(visibility);
    }

    /**
     * It sets visibility of the Shield
     * @param visibility It is the boolean value of whether the visibility is set or not.
     */
    public void setVisibilityShieldImg(boolean visibility){
        shieldImage.setVisible(visibility);
    }

    /**
     * It sets visibility of the DestroyBlock
     * @param visibility It is the boolean value of whether the visibility is set or not.
     */
    public void setVisibilityDestroyBlockImg(boolean visibility){
        destroyBlockImage.setVisible(visibility);
    }

    /**
     * @return It returns a boolean value of whether the snake is colliding with hBox1 (collection of blocks) or not.
     */
    public boolean isCollidingWithBlock1(){
        try{
            double d = snake.getSnakeBody().get(0).getLayoutY() - hBox1.getLayoutY();
            double length = Blocks.get(0).getSPane().getHeight();

            if(d > 0 && d <= length+5) return true;
            else return false;
        }catch(IndexOutOfBoundsException e){
            // do nothing
            return false;
        }
    }

    /**
     * @return It returns a boolean value of whether the snake is colliding with hBox2 (collection of blocks) or not.
     */
    public boolean isCollidingWithBlock2(){
        try{
            double d = snake.getSnakeBody().get(0).getLayoutY() - hBox2.getLayoutY();
            double length = Blocks2.get(0).getSPane().getHeight();

            if(d > 0 && d <= length+5) return true;
            else return false;
        }catch(IndexOutOfBoundsException e){
            // do nothing
            return false;
        }
    }
}