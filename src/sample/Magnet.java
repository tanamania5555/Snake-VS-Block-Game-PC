//      Tanish Jain 2017115       Raunak Srikant Mokhasi 2017085

package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * This is the class for the creation of a Magnet
 * It is Active for 10 seconds.
 */
public class Magnet {

    private Start startScreen;
    private Image magnetImgURL = new Image("/sample/magnetImage.png");
    private ImageView magnetImage = new ImageView(magnetImgURL);
    public int flag = 0;
    private static final double TimeLeft = 1000*10;      //in seconds = 30 seconds
    private static final int delta = 5;
    private static final int height = 30;
    private static final int width = 30;

    /**
     * This is the constructor for the Magnet.
     * It sets the Magnet Image and the size of the Magnet (side = 30)
     * @param s It is the Object of the Start Screen.
     */
    public Magnet(Start s) {
        startScreen = s;
        magnetImage.setCache(true);
        magnetImage.setFitHeight(height);
        magnetImage.setFitWidth(width);
    }

    /**
     *
     * @return It returns the image of the Magnet
     */
    public ImageView getImage(){
        return magnetImage;
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
     * @return It returns the X coordinate of the Magnet.
     */
    public double getLayoutX(){
        return (0+magnetImage.getX());
    }

    /**
     *
     * @return It returns the Y coordinate of the Magnet.
     */
    public double getLayoutY(){
        return (0+magnetImage.getY());
    }

    /**
     * It sets the X coordinate
     * @param x It is the value of the X coordinate.
     */
    public void setLayoutX(double x){
        magnetImage.setX(x);
    }

    /**
     * It sets the Y coordinate.
     * @param y It is the value of the Y coordinate.
     */
    public void setLayoutY(double y){
        magnetImage.setY(y);
    }

    /**
     *  This method is used to check whether the Snake grabs the magnet or not.
     *  It checks the difference in the X Coordinates of both the Snake and the Magnet.
     *  Based on that it determines whether collision takes place or not.
     *  If Collision takes place the Magnet attracts the points nearby the Snake's head.
     * @param snake It is the Main Snake of the Game.
     */
    public void isColliding(Snake snake){
        try{
            double d1 = getLayoutX() - snake.getSnakeBody().get(0).getLayoutX();
            if(d1<0) d1*=(-1);
            if(d1 <= 40){
                double d2 = getLayoutY() - snake.getSnakeBody().get(0).getLayoutY();
                if(d2<=40 && d2 >= 0){
                    magnetImage.setVisible(false);
                    flag = 1;
                    snake.setMagnetActive(true);
                    Timeline timeline7 = new Timeline(new KeyFrame(
                            Duration.millis(getTimeLeft()),
                            eventTime -> {
                                snake.setMagnetActive(true);
                            }));
                    timeline7.setCycleCount(1);
                    timeline7.play();
                    timeline7.setOnFinished(event -> {
                        snake.setMagnetActive(false);
                        startScreen.setVisibilityMagnetImg(false);
                    });
                }else{
                    setLayoutY(getLayoutY() + delta + (double)delta*snake.getLength()/250);
                }
            }else{
                setLayoutY(getLayoutY() + delta + (double)delta*snake.getLength()/250);
            }
        }catch(IndexOutOfBoundsException e){
            // do nothing
        }
    }
}