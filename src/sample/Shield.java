//      Tanish Jain 2017115       Raunak Srikant Mokhasi 2017085

package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * This is the class for the creation of a Shield.
 * It is Active for 10 seconds.
 */
public class Shield {

    private Start startScreen;
    private Image shieldImgURL = new Image("/sample/shieldImage.png");
    private ImageView shieldImage = new ImageView(shieldImgURL);
    public int flag = 0;
    private static final double TimeLeft = 1000*10;
    private static final int delta = 5;
    private static final int height = 30;
    private static final int width = 30;

    /**
     * This is the constructor for the Shield.
     * It sets the Shield Image and the size of the Shield (side = 30)
     * @param s It is the Object of the Start Screen.
     */
    public Shield(Start s) {
        startScreen = s;
        shieldImage.setCache(true);
        shieldImage.setFitHeight(height);
        shieldImage.setFitWidth(width);
    }

    /**
     *
     * @return It returns the image of the Shield
     */
    public ImageView getImage(){
        return shieldImage;
    }

    /**
     *
     * @return It returns the time left in seconds.
     */
    public double getTimeLeft() {
        return TimeLeft;
    }

    /**
     *
     * @return It returns the X coordinate of the Shield.
     */
    public double getLayoutX(){
        return (0+shieldImage.getX());
    }

    /**
     *
     * @return It returns the Y coordinate of the Shield.
     */
    public double getLayoutY(){
        return (0+shieldImage.getY());
    }

    /**
     * It sets the X coordinate
     * @param x It is the value of the X coordinate.
     */
    public void setLayoutX(double x){
        shieldImage.setX(x);
    }

    /**
     * It sets the Y coordinate.
     * @param y It is the value of the Y coordinate.
     */
    public void setLayoutY(double y){
        shieldImage.setY(y);
    }

    /**
     *  This method is used to check whether the Snake grabs the Shield or not.
     *  It checks the difference in the X Coordinates of both the Snake and the Shield.
     *  Based on that it determines whether collision takes place or not.
     *  If Collision takes place the Shield protects the snake from all the blocks that it collides with.
     * @param snake It is the Main Snake of the Game.
     */
    public void isColliding(Snake snake){
        try {
            double d1 = getLayoutX() - snake.getSnakeBody().get(0).getLayoutX();
            if (d1 < 0) d1 *= (-1);
            if (d1 <= 40) {
                double d2 = snake.getSnakeBody().get(0).getLayoutY() - getLayoutY();
                if (d2 <= 40 && d2 >= 0) {
                    shieldImage.setVisible(false);
                    flag = 1;
                    snake.setShieldActive(true);
                    Timeline timeline8 = new Timeline(new KeyFrame(
                            Duration.millis(getTimeLeft()),
                            eventTime -> {
                                snake.setShieldActive(true);
                            }));
                    timeline8.setCycleCount(1);
                    timeline8.play();
                    timeline8.setOnFinished(event -> {
                        snake.setShieldActive(false);
                        startScreen.setVisibilityShieldImg(false);
                    });
                } else {
                    setLayoutY(getLayoutY() + delta + (double) delta*snake.getLength()/250);
                }
            } else {
                setLayoutY(getLayoutY() + delta + (double)delta*snake.getLength()/250);
            }
        }catch(IndexOutOfBoundsException e){
            //do nothing
        }
    }
}