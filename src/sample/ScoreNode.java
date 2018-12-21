//      Tanish Jain 2017115       Raunak Srikant Mokhasi 2017085

package sample;

import java.io.Serializable;

/**
 * This is the class for a ScoreNode.
 * The ScoreNode is later stored in the leaderboard.
 * The ScoreNode consists of the Name,Score,Date and Time.
 */
public class ScoreNode implements Serializable {

    String Name;
    Integer Score;
    String Date;
    String Time;

    /**
     * This is the parameterized constructor of the Score Node.
     * @param nam It is the Name of the Player
     * @param scor It is the Score of the Player
     * @param dat It is the Date when the Player played the game.
     * @param tim It is the Time when the Player played the game.
     */
    ScoreNode(String nam, Integer scor, String dat, String tim){
        Name = nam;
        Score = scor;
        Date = dat;
        Time = tim;
    }

    /**
     *
     * @return It returns the Name of the Player
     */
    public String getName() {
        return Name;
    }

    /**
     * It sets the Name of a player
     * @param name It is the String of the Name.
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     *
     * @return It returns the Score of the Player
     */
    public Integer getScore() {
        return Score;
    }

    /**
     * This method sets the score of a player.
     * @param score It is the value of the score of the Player.
     */
    public void setScore(Integer score) {
        Score = score;
    }

    /**
     * This method returns the Date when the Player played the game.
     * @return It returns the value of the Date.
     */
    public String getDate() {
        return Date;
    }

    /**
     * This method sets the date when the player played the game.
     * @param date It is the value of the date.
     */
    public void setDate(String date) {
        Date = date;
    }

    /**
     *
     * @return It returns the Time when the Player played the game.
     */
    public String getTime() {
        return Time;
    }


    /**
     * This method sets the Time when the player played the game.
     * @param time It is the value of the time.
     */
    public void setTime(String time) {
        Time = time;
    }
}