//      Tanish Jain 2017115       Raunak Srikant Mokhasi 2017085

package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * This class is used for the creation of the POINT Balls.
 * It is made using circles (and contained in a stack pane) and each Ball has a given weight assigned to it.
 * The snake eats these Point Balls and increases in size exactly as per the weight of the Ball.
 */
public class Ball {

    private Circle circle = new Circle();
    private StackPane sPane = new StackPane();
    private Text weightText = new Text();
    public int flag = 0;
    public int flag2 = 0;
    private static final int delta = 5;
    private static final double radius = 12.5;
    private static final int magnetRange = 100;

    /**
     * This is a default (non-parameterized) constructor for the creation of a point Ball.
     * The Ball is made of a Circle which is placed in a Pane twice its radius size.
     */

    public Ball(){

        circle.setRadius(radius);
        sPane.setPrefHeight(2*radius);
        sPane.setPrefWidth(2*radius);
        sPane.setStyle("-fx-border-radius: 100; -fx-border-color: red");
        circle.setFill(Color.GOLD);
        sPane.getChildren().addAll(circle, weightText);
    }

    /**
     *
     * @return It returns the Circle created.
     */
    public Circle getCircle(){
        return circle;
    }

    /**
     *
     * @return It returns the Pane created.
     */
    public StackPane getSPane(){
        return sPane;
    }

    /**
     *
     * @param i It is the value of the Weight to be assigned to the Point Ball.
     */
    public void setWeight(int i){
        weightText.setText(String.valueOf(i));
    }

    /**
     *
     * @return It returns the X coordinate of the Layout.
     */
    public double getLayoutX(){
        return (0+sPane.getLayoutX());
    }

    /**
     *
     * @return It returns the Y coordinate of the Layout.
     */
    public double getLayoutY(){
        return (0+sPane.getLayoutY());
    }

    /**
     *
     * @param x It is the value of the X Coordinate of the Layout.
     */
    public void setLayoutX(double x){
        sPane.setLayoutX(x);
    }

    /**
     *
     * @param y It is the value of the Y Coordinate of the Layout.
     */
    public void setLayoutY(double y){
        sPane.setLayoutY(y);
    }

    /**
     *  This method is used to check whether the Point Ball collides with the snake or not.
     *  It checks the difference in the X Coordinates of both the Snake and the Point Block.
     *  Based on that it determines whether collision takes place or not.
     *  If Collision takes place the Point Ball is eaten by the snake and the snake increases in size equal to the weight of the Point Ball.
     * @param snake It is the Main Snake of the Game.
     */
    public void isColliding(Snake snake){
        try {
            if(sPane.isVisible() && flag2 == 0) {
                flag2 = 1;
                double d1 = getLayoutX() - snake.getSnakeBody().get(0).getLayoutX();
                double d2 = getLayoutY() - snake.getSnakeBody().get(0).getLayoutY();
                if (d1 < 0) d1 *= (-1);
                int dist = 20;
                if (snake.isMagnetActive()) dist = magnetRange;
                if (d1 <= dist) {
                    if (d2 <= dist && d2 >= 0) {
                        if (!snake.isMagnetActive()) {
                            for (int i = 0; i < Integer.parseInt(weightText.getText()); i++) {
                                snake.addBall();
                            }
                            sPane.setVisible(false);
                            flag = 1;
                        } else {
                            Timeline timeline6 = new Timeline(new KeyFrame(
                                    Duration.millis(10),
                                    eventTime -> {
                                        if (snake.getSnakeBody().get(0).getLayoutX() > getLayoutX()) {
                                            setLayoutX(getLayoutX() + 5);
                                        } else {
                                            setLayoutX(getLayoutX() - 5);
                                        }
                                        if (snake.getSnakeBody().get(0).getLayoutY() > getLayoutY()) {
                                            setLayoutY(getLayoutY() + 5);
                                        } else {
                                            setLayoutY(getLayoutY() - 5);
                                        }
                                    }));
                            timeline6.setCycleCount(20);
                            timeline6.play();
                            timeline6.setOnFinished(event -> {
                                for (int i = 0; i < Integer.parseInt(weightText.getText()); i++) {
                                    snake.addBall();
                                }
                                sPane.setVisible(false);
                                flag = 1;
                                return;
                            });
                        }
                    } else {
                        setLayoutY(getLayoutY() + delta + (double) delta * snake.getLength() / 250);
                        flag2 = 0;
                    }
                } else {
                    setLayoutY(getLayoutY() + delta + (double) delta * snake.getLength() / 250);
                    flag2 = 0;
                }
            }
        }catch(IndexOutOfBoundsException e){
            //do nothing
        }
    }
}