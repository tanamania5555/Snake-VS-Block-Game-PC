//      Tanish Jain 2017115       Raunak Srikant Mokhasi 2017085

package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This is the leaderboard class used to save the results of the players.
 * It shows all the details of the players in tabular form.
 * It can be sorted with respect to any property specified.
 */
public class LeaderBoard implements Serializable {

    transient private Main mainApp;
    transient private Parent leaderBoard;
    transient private Scene leaderBoardScreen;
    transient private TableView leaderBoardTable;
    //private ArrayList<ScoreNode> ScoreNodes = new ArrayList<>();
    private leaderBoardMini leaderBoardList = new leaderBoardMini(this);

    /**
     * This is the constructor of the Leaderboard class.
     * It uses an Anchor Pane to store all the components.
     * The leaderboard consists of a table which can be sorted on the basis of any given property.
     * @param m It is the instance of the Main Class
     */
    public LeaderBoard(Main m){
        mainApp = m;

        String image = Start.class.getResource("background7.jpg").toExternalForm();
        String image2 = Start.class.getResource("background8.jpg").toExternalForm();

        leaderBoard = new AnchorPane();
        ((AnchorPane)leaderBoard).setPrefHeight(725.0);
        ((AnchorPane)leaderBoard).setPrefWidth(1200.0);
        leaderBoard.setStyle("-fx-background-image : url('"+image2+"');");

        Button LeaderBoardBackButton = new Button("BACK");
        LeaderBoardBackButton.setPrefHeight(30.0);     LeaderBoardBackButton.setPrefWidth(90.0);
        LeaderBoardBackButton.setLayoutX(1020.0);      LeaderBoardBackButton.setLayoutY(630.0);
        LeaderBoardBackButton.setStyle("-fx-background-image: url('"+image+"'); -fx-font-size: 20; -fx-font-weight: bold");

        LeaderBoardBackButton.setOnAction(e ->
            mainApp.showHomeScreen()
        );

        leaderBoardTable = new TableView();
        leaderBoardTable.setLayoutX(60.0);         leaderBoardTable.setLayoutY(60.0);
        leaderBoardTable.prefHeight(700); leaderBoardTable.prefWidth(1200.0);
        leaderBoardTable.setStyle("-fx-font-weight: bold; -fx-font-color: black; -fx-cell-size: 50px; -fx-font-size: 20; -fx-background-image : url('" + image + "');");

        TableColumn<ScoreNode, String> Name = new TableColumn("Username");
        TableColumn<ScoreNode, Integer> Score = new TableColumn("Score");
        TableColumn<ScoreNode, String> Date = new TableColumn("Date");
        TableColumn<ScoreNode, String> Time = new TableColumn("Time");

        Name.setPrefWidth(420.0);
        Score.setPrefWidth(210.0);
        Date.setPrefWidth(210.0);
        Time.setPrefWidth(210.0);

        Name.setStyle("-fx-background-image : url('" + image + "');");
        Score.setStyle("-fx-background-image : url('" + image + "');");
        Time.setStyle("-fx-background-image : url('" + image + "');");
        Date.setStyle("-fx-background-image : url('" + image + "');");

        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Score.setCellValueFactory(new PropertyValueFactory<>("Score"));
        Time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));

        leaderBoardTable.getColumns().addAll(Name,Score,Date,Time);

        /*leaderBoardList.addNode(new ScoreNode("Narendra Modi",100, "02/11/18", "02:00"));
        leaderBoardList.addNode("Rahul Gandhi", 40, "01/11/18", "12:31");
        leaderBoardList.addNode(new ScoreNode("Narendra Modi",100, "02/11/18", "02:00"));
        leaderBoardList.addNode("Rahul Gandhi", 40, "01/11/18", "12:31");
        leaderBoardList.addNode(new ScoreNode("Narendra Modi",100, "02/11/18", "02:00"));
        leaderBoardList.addNode("Rahul Gandhi", 40, "01/11/18", "12:31");
        leaderBoardList.addNode(new ScoreNode("Narendra Modi",100, "02/11/18", "02:00"));
        leaderBoardList.addNode("Rahul Gandhi", 40, "01/11/18", "12:31");*/

        ((AnchorPane) leaderBoard).getChildren().addAll(leaderBoardTable,LeaderBoardBackButton);
        leaderBoardScreen = new Scene(leaderBoard, 1200, 725);
        leaderBoardScreen.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.BACK_SPACE) {
                mainApp.showHomeScreen();
            }
        });
    }

    /**
     * @return This method returns the Score Nodes.
     */
    public leaderBoardMini getLeaderBoardList() {
        return leaderBoardList;
    }

    /**
     * @return This method returns the Leaderboard Screen.
     */
    public Scene getLeaderBoardScreen(){
        return leaderBoardScreen;
    }

    /**
     *
     * @return This returns the leaderBoard Table.
     */
    public TableView getLeaderBoardTable() {
        return leaderBoardTable;
    }


}