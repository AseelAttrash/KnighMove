package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import model.SysData;
import view.MainMenu;


//Game main page controller
public class ControlMainMenu  implements Initializable{
      //buttons
    @FXML
    private Button startButt; 
    @FXML
    private Button GameRulesBtn;
    @FXML
    private Button hisButt; 

    @FXML
    private Button manageQuesButt;
    //method to go to the Game History Page
    @FXML
    void goToHistPage(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/gameHistory.fxml"));
			Scene scene = new Scene(root);
			MainMenu.mainS.setScene(scene);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
     //method to go to manage Page
    @FXML
    void goToManagePage(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/questionManage.fxml"));
			Scene scene = new Scene(root);
			MainMenu.mainS.setScene(scene);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    //method to Start the game
    @FXML
    void startGameFunc(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/PlayerName.fxml"));
			Scene scene = new Scene(root);
			MainMenu.mainS.setScene(scene);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			SysData.getInstance().ReadFromJson();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    @FXML
    void GoToRulesPage(ActionEvent event) {
    	try {
			Parent root =FXMLLoader.load(getClass().getResource("/view/GameRules.fxml"));
			Scene scene = new Scene(root);
			MainMenu.mainS.setScene(scene);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

}
