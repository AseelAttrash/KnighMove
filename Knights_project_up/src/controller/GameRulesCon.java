package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import view.MainMenu;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class GameRulesCon {

	 @FXML
	    private Button backButton;

	    @FXML
	    private TextArea txt; 

	    @FXML
	    private Text GRlbl;


//// return to main menu when pressing the back button
    @FXML
    void backHome(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
			Scene scene = new Scene(root);
			MainMenu.mainS.setScene(scene);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

    }

}
