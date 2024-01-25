package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.ResourceBundle;

import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Game;
import model.SysData;
import view.MainMenu;
//showTopThreeController
public class showTopThreeController implements Initializable{
	//the 3 top Players  
	@FXML
	    private Text Player1;

	    @FXML
	    private Text Player2;

	    @FXML
	    private Text Player3;
	    @FXML
	    private Button backButton;
         //back to gameHistory Page
	    @FXML
	    void goBack(ActionEvent event) {
	       	ObservableList<Game> datagames = FXCollections.observableArrayList(SysData.getInstance().getGames());
	    	SysData.getInstance().getGames().removeAll(datagames);
	       	try {
					Parent root = FXMLLoader.load(getClass().getResource("/view/gameHistory.fxml"));
					Scene scene = new Scene(root);
					MainMenu.mainS.setScene(scene);

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
	    }
	    //initialize function
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			try {
			    SysData.getInstance().ReadFromJsonGames();
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
	    ArrayList<Game> ordered = new ArrayList<>(SysData.getInstance().getGames());
	   	 
	        Collections.sort(ordered, new Comparator<Game>() {
	           @Override
	           public int compare(Game e1, Game e2) {
	             return e2.getTotalPoints() - (e1.getTotalPoints());
	           }
	         });	
	        //set the top 3 players
	        Player3.setText(ordered.get(2).getName());
	        Player2.setText(ordered.get(1).getName());
	        Player1.setText(ordered.get(0).getName());

		}

}