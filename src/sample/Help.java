//      Tanish Jain 2017115       Raunak Srikant Mokhasi 2017085

package sample;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * This is Class used for the Help Menu.
 * It shows the user all the instructions required to play the game.
 */
public class Help {

    private Main mainApp;
    private Parent help;
    private Scene helpScreen;

    /**
     * This is the Constructor of the Help Class.
     * It shows all the instructions in a numbered form.
     * @param main It is the Object of the Main Class.
     */
    public Help(Main main){
        mainApp = main;
        help = new AnchorPane();
        help.setStyle("-fx-background-color: black");
        ((AnchorPane)help).setPrefHeight(725.0);       ((AnchorPane)help).setPrefWidth(1200.0);

        String image = Start.class.getResource("background7.jpg").toExternalForm();
        Button HelpBackButton = new Button("BACK");
        HelpBackButton.setPrefHeight(30.0);    HelpBackButton.setPrefWidth(90.0);
        HelpBackButton.setLayoutX(1020.0);      HelpBackButton.setLayoutY(630.0);
        HelpBackButton.setStyle("-fx-background-image: url('"+image+"'); -fx-font-size: 20; -fx-font-weight: bold");

        Label helpInstructions = new Label();
        helpInstructions.setPrefHeight(570);    helpInstructions.setPrefWidth(1150);
        helpInstructions.setLayoutX(20);        helpInstructions.setLayoutY(20);
        helpInstructions.setStyle("-fx-border-radius: 90; -fx-border-color: yellow; -fx-font-size: 25; -fx-font-weight: bold; -fx-text-fill: #00FF3C");
        helpInstructions.setAlignment(Pos.CENTER);
        String str = new String("1.  Use '->' or 'D' keys to go right." + "\n" +
                                        "2.  Use '<-' or 'A' keys to go left." + "\n" +
                                        "3.  Collect Balls to increase the number of balls in the snake." + "\n" +
                                        "4.  Collect Magnets to attract balls towards the snake but only for a limited time." + "\n" +
                                        "5.  Collect Shields to shield the snake and destroy all the blocks in the way \n     without decreasing the number of balls in the snake." + "\n" +
                                        "6.  Collect lighting to destroy all the blocks on the screen at once." + "\n" +
                                        "7.  Break blocks to increase the score." + "\n \n" +
                                        "TRY TO GET A HIGH SCORE!!" + "\n \n \n" +
                                        "Made by   :   Tanish Jain | Raunak Mokhasi");
        helpInstructions.setText(str);     ////////////////////////////////////////////////////////////////////

        HelpBackButton.setOnAction(e -> {
            mainApp.showHomeScreen();
        });

        ((AnchorPane) help).getChildren().addAll(helpInstructions, HelpBackButton);

        helpScreen = new Scene(help, 1200, 725);
        helpScreen.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.BACK_SPACE) {
                mainApp.showHomeScreen();
            }
        });
    }

    /**
     * @return This method returns the Help Screen.
     */
    public Scene getHelpScreen(){
        return helpScreen;
    }
}