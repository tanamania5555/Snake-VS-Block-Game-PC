//      Tanish Jain 2017115       Raunak Srikant Mokhasi 2017085

package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is the class for the creation of the Snake.
 * The Snake has a particular Length and Color.
 * The Snake Body is an Array of Balls (which are created using Circles).
 */
public class Snake {

    private Main mainApp;
    private String color;
    private Start startScreen;
    private ArrayList<Ball> snakeBody = new ArrayList<>();
    private int length = 0;
    private static final int initialBalls = 5;
    private boolean magnetActive = false;
    private boolean shieldActive = false;
    private boolean destroyBlockActive = false;

    /**
     * This is the Constructor of the Snake.
     * It sets the size of the snake equal to the number of initial balls (5 in our case).
     * @param m It is the object of the Main Class.
     * @param start It is the object of the Start Class.
     * @param col It is the Value of the Colour of the Snake.
     */
    public Snake(Main m, Start start, String col){
        mainApp = m;
        startScreen = start;
        color = col;
        addBall();
        snakeBody.get(0).setLayoutX((double)mainApp.getWindowWidth()/2);
        for(int l = 1; l<initialBalls; l++){
            addBall();
        }snakeBody.get(0).setWeight(length);
    }

    /**
     *
     * @return It returns the Boolean Value of whether Magnet is Active or not.
     */
    public boolean isMagnetActive() {
        return magnetActive;
    }

    /**
     *
     * @return It returns the Boolean Value of whether Shield is Active or not.
     */
    public boolean isShieldActive() {
        return shieldActive;
    }


    /**
     *
     * @return It returns the Boolean Value of whether DestroyBlock is Active or not.
     */
    public boolean isDestroyBlockActive() {
        return destroyBlockActive;
    }

    /**
     * It sets the Active State of the Magnet.
     * @param magnetActive It is the boolean value which decides active state of Magnet.
     */
    public void setMagnetActive(boolean magnetActive) {
        this.magnetActive = magnetActive;
    }

    /**
     * It sets the Active State of the Shield.
     * @param shieldActive It is the boolean value which decides active state of Shield.
     */
    public void setShieldActive(boolean shieldActive){
        this.shieldActive = shieldActive;
    }

    /**
     * It sets the Active State of the DestroyBlock.
     * @param destroyBlockActive It is the boolean value which decides active state of destroyBlock.
     */
    public void setDestroyBlockActive(boolean destroyBlockActive) {
        this.destroyBlockActive = destroyBlockActive;
    }

    /**
     *
     * @return It returns the color of the snake.
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @return It returns the body of the snake.
     */
    public ArrayList<Ball> getSnakeBody(){
        return snakeBody;
    }


    /**
     *
     * @return It returns the length of the snake.
     */
    public int getLength(){
        return length;
    }

    /**
     * This method checks if the Snake is colliding with any wall.
     * It checks this by calculating the difference in the X coordinates of the Wall and the Snake.
     * @return It returns the boolean value of whether collision takes place or not
     */
    public boolean isCollidingWall(){
        try {
            double dist;
            for (int i = 0; i < startScreen.getWalls().size(); i++) {
                /*dist = startScreen.getWalls().get(i).getLayoutX() - getSnakeBody().get(0).getLayoutX();
                if (dist < 0) dist *= (-1);
                if (dist <= getSnakeBody().get(0).getCircle().getRadius() + 2) {
                    double dist2 = getSnakeBody().get(0).getLayoutY() - startScreen.getWalls().get(i).getLayoutY();
                    if (dist2 >= 0 && dist2 <= startScreen.getWalls().get(i).getLength()) {
                        return true;
                    }
                }*/
                /*Shape intersecting = Shape.intersect(getSnakeBody().get(0).getCircle(),startScreen.getWalls().get(i).getRect());
                if(intersecting.getBoundsInLocal().getWidth() == -1){
                    return true;
                }*/
                if(isCollidingWall(startScreen.getWalls().get(i))){
                    return true;
                }
            }return false;
        }catch(IndexOutOfBoundsException e){
            return false;
        }
    }


    /**
     * This method checks if the Snake is colliding with a particular wall.
     *  It checks this by calculating the difference in the X coordinates of the Wall and the Snake.
     * @param w It takes in the object of a particular Wall
     * @return It returns the boolean value of whether collision takes place or not
     */
    public boolean isCollidingWall(Wall w){
        try {
            double dist = w.getLayoutX() - getSnakeBody().get(0).getLayoutX();
            if (dist < 0) dist *= (-1);
            if (dist <= getSnakeBody().get(0).getCircle().getRadius() + w.getRect().getWidth()) {
                double dist2 = getSnakeBody().get(0).getLayoutY() - w.getLayoutY();
                if (dist2 >= 0 && dist2 <= w.getLength()) {
                    return true;
                }
            }return false;
        }catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    /**
     * It is used to move the Snake left.
     * This method changes the X coordinate of the Snake.
     */
    public void snakeMoveLeft(){
        for(Ball ball: snakeBody){
            if ( ball.getLayoutX() >= 10) {
                Timeline timeline4 = new Timeline(new KeyFrame(
                    Duration.millis(5),
                    eventTime -> {
                        if(!isCollidingWall())
                            ball.setLayoutX(ball.getLayoutX() - 1);
                        for(int i = 1; i<snakeBody.size(); i++){
                            try {
                                if (snakeBody.get(i).getLayoutX() != snakeBody.get(0).getLayoutX()) {
                                    snakeBody.get(i).setLayoutX(snakeBody.get(0).getLayoutX());
                                }
                            }catch(IndexOutOfBoundsException e){
                                //do nothing
                            }
                        }
                    }));
                timeline4.setCycleCount(10);
                timeline4.play();
                timeline4.setOnFinished(event -> {
                    return;
                });
            }
        }
    }

    /**
     * It is used to move the Snake right.
     * This method changes the X coordinate of the Snake.
     */
    public void snakeMoveRight(){
        for(Ball ball: snakeBody){
            if(ball.getLayoutX() <= (mainApp.getWindowWidth() - 2*ball.getCircle().getRadius() - 10)) {
                Timeline timeline5 = new Timeline(new KeyFrame(
                        Duration.millis(5),
                        eventTime -> {
                            if(!isCollidingWall())
                                ball.setLayoutX(ball.getLayoutX() + 1);
                            for(int i = 1; i<snakeBody.size(); i++){
                                try {
                                    if (snakeBody.get(i).getLayoutX() != snakeBody.get(0).getLayoutX()) {
                                        snakeBody.get(i).setLayoutX(snakeBody.get(0).getLayoutX());
                                    }
                                }catch(IndexOutOfBoundsException e){
                                    //do nothing
                                }
                            }
                        }));
                timeline5.setCycleCount(10);
                timeline5.play();
                timeline5.setOnFinished(event -> {
                    return;
                });
            }
        }
    }

    /**
     * This method is used to delete a Ball from the snake.
     */
    public void delBall(){
        if(length >= 1) {
            startScreen.incScore();
            if(!isShieldActive() && !isDestroyBlockActive()) {
                snakeBody.get(snakeBody.size() - 1).getSPane().setVisible(false);
                length -= 1;
                snakeBody.get(0).setWeight(length);
                snakeBody.remove(snakeBody.size() - 1);
            }
        }
    }

    /**
     * This method is used to add a ball to the Snake.
     * @param b It is the point Ball.
     */
    public void addBall(Ball b){
        snakeBody.add(b);
        length += 1;
        ((AnchorPane)startScreen.getStart()).getChildren().add(b.getSPane());
        b.setLayoutX(snakeBody.get(0).getLayoutX());
        b.setLayoutY(mainApp.getWindowHeight() - 250 + b.getCircle().getRadius()*2*snakeBody.size());
        snakeBody.get(0).setWeight(length);
    }

    /**
     * This method adds the initial balls to the snake of a specified colour.
     */
    public void addBall(){
        Ball b = new Ball();
        if(color.equals("Red")) {
            b.getCircle().setFill(Color.RED);
        }else if(color.equals("Green")) {
            b.getCircle().setFill(Color.GREEN);
        }else if(color.equals("Blue")) {
            b.getCircle().setFill(Color.BLUE);
        }else if(color.equals("Mix")) {
            Random r = new Random();
            int j = r.nextInt(3);
            if(j == 0) b.getCircle().setFill(Color.RED);
            else if(j == 1) b.getCircle().setFill(Color.GREEN);
            else if(j == 2) b.getCircle().setFill(Color.BLUE);
        }addBall(b);
    }
}