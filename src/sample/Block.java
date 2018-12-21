//      Tanish Jain 2017115       Raunak Srikant Mokhasi 2017085

package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This is the class for the creation of a Block.
 * Each block is assigned a particular weight randomly.
 * A Block reduced the Snakes points by how much ever weight is assigned to it.
 */
public class Block {

    private Rectangle rect = new Rectangle();
    private StackPane sPane = new StackPane();
    private Text weightText = new Text();
    private Start startClass;

    /**
     * This is the default (non-parameterized constructor of the Block.
     * Each block is a square of side 96 and is placed in a Stack Pane of side 100.
     * The weight of the block is written inside its body.
     * @param s It is an instance of the Start Screen of the Game. It is required mainly to show the Collision process.
     */
    public Block(Start s) {
        startClass = s;
        rect.setHeight(96);
        rect.setWidth(96);
        sPane.setPrefHeight(100);
        sPane.setPrefWidth(100);
        sPane.setStyle("-fx-border-width: 2; -fx-border-radius: 1000; -fx-border-color: black; -fx-background-color: black;");
        weightText.setStyle("-fx-font-weight: bold; -fx-font-size: 30");
        sPane.getChildren().addAll(rect, weightText);
    }

    /**
     *
     * @return It returns the Rectangle used in the creation of the block.
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     *
     * @return It returns the Pane used in the creation of the block.
     */
    public StackPane getSPane(){
        return sPane;
    }

    /**
     *
     * @return It returns the weight of the block.
     */
    public int getWeight(){
        return Integer.parseInt(weightText.getText());
    }

    /**
     * This method sets the weight of the Block and also assigns a Colour to it Randomly.
     * It makes sure that the weight of the Block is within certain bounds.
     * @param newWeightt The value of the weight of the Block.
     */
    public void setWeight(int newWeightt){
        int newWt;
        int newWeight = newWeightt;
        if(newWeight > 1000) newWeight = 1000;

        if(newWeight <= 100) newWt = 101;
        else if(newWeight <= 500) newWt = 501;
        else newWt = 1001;

        weightText.setText(String.valueOf(newWeight));
        double greenCalc = 1 - (((double)newWeight)/ newWt);
        int blueRand = ThreadLocalRandom.current().nextInt(1, 30);
        double blueCalc = (((double) (blueRand))/ (101));
        rect.setFill(Color.color(1,greenCalc,blueCalc));
    }

    /**
     *
     * @return It returns the X Coordinate of the block.
     */
    public double getLayoutX(){
        return (0+sPane.getLayoutX());
    }

    /**
     *
     * @return It returns the Y Coordinate of the block.
     */
    public double getLayoutY(){
        return (0+sPane.getLayoutY());
    }

    /**
     * @param x It is the value of the X Coordinate of the Block.
     */
    public void setLayoutX(double x){
        sPane.setLayoutX(x);
    }

    /**
     * @param y It is the value of the Y Coordinate of the Block.
     */
    public void setLayoutY(double y){
        sPane.setLayoutY(y);
    }

    /**
     *  This method is used to check whether the Block collides with the snake or not.
     *  It checks the difference in the X Coordinates of both the Snake and the Block.
     *  Based on that it determines whether collision takes place or not.
     *  If Collision takes place the Block reduces the size of the snake with the weight assigned to it.
     *  If the weight of the block is higher than the snake's weight then the snake dies.
     * @param snake It is the Main Snake of the Game.
     */
    public void isColliding(Snake snake){
        try{
            if(sPane.isVisible()) {
                int wt = getWeight();
                if (wt <= 5) {
                    startClass.pause();
                    for (int i = 0; i < wt; i++) {
                        setWeight(getWeight() - 1);
                        snake.delBall();
                    }
                    sPane.setVisible(false);
                    startClass.play();
                } else {
                    startClass.pause();
                    Timeline timeline3 = new Timeline();
                    timeline3.getKeyFrames().add(new KeyFrame(
                            Duration.millis(1+200/wt),
                            event -> {
                                setWeight(getWeight() - 1);
                                snake.delBall();
                            }));
                    timeline3.setCycleCount(wt);
                    timeline3.play();
                    timeline3.setOnFinished(event -> {
                        sPane.setVisible(false);
                        startClass.play();
                    });
                }if(snake.getLength() == 0){
                    startClass.stop();
                }
            }
        }catch(IndexOutOfBoundsException e){
            //do nothing
        }
    }
}