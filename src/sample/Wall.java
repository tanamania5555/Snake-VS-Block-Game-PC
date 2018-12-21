//      Tanish Jain 2017115       Raunak Srikant Mokhasi 2017085

package sample;

import javafx.scene.shape.Rectangle;

/**
 * This is the class for generating Walls.
 * A wall blocks the snake and doesnt allow it to go through it.
 * Every wall consists of a random length and width = 5.
 */
public class Wall {

    private double length;
    private Rectangle rect = new Rectangle();
    private static final int width = 5;

    /**
     * This is the Constructor of the Wall.
     * It sets the width of the Wall and also the style.
     */
    public Wall() {
        rect.setWidth(width);
        rect.setStyle("-fx-background-color: white; -fx-fill : white");
    }

    /**
     *
     * @return This method returns the length of the Wall.
     */
    public double getLength() {
        return length;
    }

    /**
     * This method sets the length of the Wall.
     * @param length It is the length of the Wall.
     */
    public void setLength(double length) {
        if(length <= 400)
            this.length = length;
        rect.setHeight(length);
    }

    /**
     *
     * @return It returns the X coordinate of the wall.
     */
    public double getLayoutX(){
        return rect.getLayoutX();
    }

    /**
     *
     * @return It returns the Y coordinate of the wall.
     */
    public double getLayoutY(){
        return rect.getLayoutY();
    }

    /**
     * This method sets the X coordinate of the wall.
     * @param x It is the value of the X coordinate.
     */
    public void setLayoutX(double x){
        rect.setLayoutX(x);
    }

    /**
     * This method sets the Y coordinate of the wall.
     * @param y It is the value of the Y coordinate.
     */
    public void setLayoutY(double y){
        rect.setLayoutY(y);
    }

    /**
     *
     * @return This method returns the Rectangle used to create the wall.
     */
    public Rectangle getRect() {
        return rect;
    }
}