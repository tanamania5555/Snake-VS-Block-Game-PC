package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * It is the Class used for creating Mini Leaderboard, Used during Serialization.
 */
public class leaderBoardMini implements Serializable {

    private LeaderBoard mainLeader;
    private ArrayList<ScoreNode> ScoreNodes = new ArrayList<>();

    /**
     * It is the Constructor of the Mini Leaderboard.
     * @param l It is the instance of the Leaderboard.
     */
    public leaderBoardMini(LeaderBoard l){
        mainLeader = l;
    }

    /**
     *
     * @return It returns ArrayList of Score Nodes.
     */
    public ArrayList<ScoreNode> getScoreNodes() {
        return ScoreNodes;
    }

    /**
     * This method is used to add a score node of a Player with a Name, Score, Date and Time.
     * @param nam It is the Name of the Player.
     * @param scor It is the Score of the Player.
     * @param dat It is the Date when the Player played the game.
     * @param tim It is the Time when the Player played the game.
     */
    public void addNode(String nam, Integer scor, String dat, String tim){
        ScoreNode s = new ScoreNode(nam,scor,dat,tim);
        addNode(s);
    }

    /**
     * This method is directly used to add a ScoreNode to the leaderboard.
     * @param n It is the ScoreNode of a particular player.
     */
    public void addNode(ScoreNode n){
        ScoreNodes.add(n);
        //ScoreNodes.sort(Comparator.comparingInt(ScoreNode::getScore));
        Collections.sort(ScoreNodes, (node1, node2) ->
                node2.getScore() - node1.getScore());

        ArrayList<ScoreNode> a = new ArrayList<>();
        ObservableList<ScoreNode> obsListEmpty = FXCollections.observableList(a);
        ObservableList<ScoreNode> obsList = FXCollections.observableList(getScoreNodes());
        mainLeader.getLeaderBoardTable().setItems(obsListEmpty);
        mainLeader.getLeaderBoardTable().getItems().addAll(obsList);
    }
}