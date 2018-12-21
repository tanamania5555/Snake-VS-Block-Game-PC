//      Tanish Jain 2017115       Raunak Srikant Mokhasi 2017085

package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * It is the class used to create a Lightning Spark - DestroyBlock.
 * It destroys all the blocks on the screen.
 */
public class DestroyBlock {

    private Image destroyBlockImgURL = new Image("/sample/destroyBlock.png");
    private ImageView destroyBlockImage = new ImageView(destroyBlockImgURL);
    public int flag = 0;
    private final static int delta = 5;
    private final static int height = 30;
    private final static int width = 30;

    /**
     * It is the default (non-parameterized) constructor for the Block Destroyer.
     */
    public DestroyBlock() {
        destroyBlockImage.setCache(true);
        destroyBlockImage.setFitHeight(height);
        destroyBlockImage.setFitWidth(width);
    }

    /**
     *
     * @return It returns the image of the Block Destroyer (lightning in this case).
     */
    public ImageView getImage(){
        return destroyBlockImage;
    }

    /**
     *
     * @return It returns the X coordinate of the Block Destroyer.
     */
    public double getLayoutX(){
        return destroyBlockImage.getX();
    }

    /**
     *
     * @return It returns the Y coordinate of the Block Destroyer.
     */
    public double getLayoutY(){
        return destroyBlockImage.getY();
    }

    /**
     *
     * @param x It is the X Coordinate of the Block Destroyer.
     */
    public void setLayoutX(double x){
        destroyBlockImage.setX(x);
    }

    /**
     *
     * @param y It is the Y Coordinate of the Block Destroyer.
     */
    public void setLayoutY(double y){
        destroyBlockImage.setY(y);
    }

    /**
     * This method checks if the Block Destroyer collides with the Snake or not.
     * It does this by checking the difference between the x coordinates of the Snake body and the Block Destroyer.
     * @param snake It is the main snake of the game.
     */
    public void isColliding(Snake snake){
        try {
            double d1 = getLayoutX() - snake.getSnakeBody().get(0).getLayoutX();
            if (d1 < 0) d1 *= (-1);
            if (d1 <= 40) {
                double d2 = getLayoutY() - snake.getSnakeBody().get(0).getLayoutY();
                if (d2 <= 40 && d2 >= 0) {
                    destroyBlockImage.setVisible(false);
                    flag = 1;
                } else {
                    setLayoutY(getLayoutY() + delta + (double)delta*snake.getLength()/250);
                }
            } else {
                setLayoutY(getLayoutY() + delta + (double)delta*snake.getLength()/250);
            }
        }catch(IndexOutOfBoundsException e){
            // do nothing
        }
    }
}