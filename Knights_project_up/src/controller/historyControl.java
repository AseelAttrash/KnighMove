package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Game;
import model.Question;
import model.SysData;
import view.MainMenu;

public class historyControl implements Initializable{ // history scene controllerrs
     //table and table columns

	 @FXML
	    private Button OrderByDate;

	    @FXML
	    private Button OrderByName;

	    @FXML
	    private Button ShowtopThree;

	    @FXML
	    private Button backButton;
	    @FXML
	    private TableView<Game> historyTable;
	    @FXML
	    private TableColumn<Game, String> gameDate;

	    @FXML
	    private TableColumn<Game, String> gamePrize;

	    @FXML
	    private TableColumn<Game, String> gameRound;
	    @FXML
	    private TableColumn<Game, String> PlayerName;

	    @FXML
	    private TableColumn<Game, String> pointsround1;

	    @FXML
	    private TableColumn<Game, String> pointsround2;

	    @FXML
	    private TableColumn<Game, String> pointsround3;

	    @FXML
	    private TableColumn<Game, String> pointsround4;
	    @FXML
	    void OrderByDateAction(ActionEvent event) {
	    	ArrayList<Game> orderByDate = new ArrayList<>(SysData.getInstance().getGames());
	 
	        Collections.sort(orderByDate, new Comparator<Game>() {
	            @Override
	            public int compare(Game e1, Game e2) {
	              return e1.getGameDate().compareTo(e2.getGameDate());
	            }
	          });	  
	        ObservableList<Game> datagames = FXCollections.observableArrayList(orderByDate);
	    	gameRound.setCellValueFactory(new PropertyValueFactory<Game, String>("round"));
	    	gameDate.setCellValueFactory(new PropertyValueFactory<Game, String>("gameDate"));
	    	gamePrize.setCellValueFactory(new PropertyValueFactory<Game, String>("isPrize"));
	    	pointsround1.setCellValueFactory(new PropertyValueFactory<Game, String>("pointsRound1"));
	    	pointsround2.setCellValueFactory(new PropertyValueFactory<Game, String>("pointsRound2"));
	    	pointsround3.setCellValueFactory(new PropertyValueFactory<Game, String>("pointsRound3"));
	    	pointsround4.setCellValueFactory(new PropertyValueFactory<Game, String>("pointsRound4"));
	    	PlayerName.setCellValueFactory(new PropertyValueFactory<Game, String>("name"));
	    	HashSet<Game> set = new HashSet<>();
	    	set.addAll(datagames);
	    	ObservableList<Game> dataGame2 =  FXCollections.observableArrayList(set);
	    	historyTable.setItems(dataGame2);
	    }

	    @FXML
	    void OrderByNameAction(ActionEvent event) {
	    	//order by question text alphapet
	    	ArrayList<Game> orderByText = new ArrayList<>(SysData.getInstance().getGames());
	    	Collections.sort(orderByText, new Comparator<Game>() {
	            @Override
	            public int compare(Game q1, Game q2) {
	                String s1 = q1.getName();
	                String s2 = q2.getName();
	                return s1.compareToIgnoreCase(s2);
	            }
	    });
	  		//view on table
			ObservableList<Game> dataQues = FXCollections.observableArrayList(orderByText);
			gameRound.setCellValueFactory(new PropertyValueFactory<Game, String>("round"));
			gameDate.setCellValueFactory(new PropertyValueFactory<Game, String>("gameDate"));
			gamePrize.setCellValueFactory(new PropertyValueFactory<Game, String>("isPrize"));
			pointsround1.setCellValueFactory(new PropertyValueFactory<Game, String>("pointsRound1"));
			pointsround2.setCellValueFactory(new PropertyValueFactory<Game, String>("pointsRound2"));
			pointsround3.setCellValueFactory(new PropertyValueFactory<Game, String>("pointsRound3"));
			pointsround4.setCellValueFactory(new PropertyValueFactory<Game, String>("pointsRound4"));
			PlayerName.setCellValueFactory(new PropertyValueFactory<Game, String>("PlayerName"));
	  		ObservableList<Game>temp =  FXCollections.observableArrayList(dataQues);
	  		historyTable.setItems(temp);
	    }

	    @FXML
	    void ShowtopThreeAction(ActionEvent event) {
	       	ObservableList<Game> datagames = FXCollections.observableArrayList(SysData.getInstance().getGames());
	    	SysData.getInstance().getGames().removeAll(datagames);
	       	try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/TopThree.fxml"));
				Scene scene = new Scene(root);
				MainMenu.mainS.setScene(scene);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    }
    //back to the main page
    @FXML
    void backHome(ActionEvent event) { //a
    	ObservableList<Game> datagames = FXCollections.observableArrayList(SysData.getInstance().getGames());
    	SysData.getInstance().getGames().removeAll(datagames);
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
			Scene scene = new Scene(root);
			MainMenu.mainS.setScene(scene);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
  	public void fill() {
	ObservableList<Game> datagames = FXCollections.observableArrayList(SysData.getInstance().getGames());
	gameRound.setCellValueFactory(new PropertyValueFactory<Game, String>("round"));
	gameDate.setCellValueFactory(new PropertyValueFactory<Game, String>("gameDate"));
	gamePrize.setCellValueFactory(new PropertyValueFactory<Game, String>("Prize"));
	pointsround1.setCellValueFactory(new PropertyValueFactory<Game, String>("pointsRound1"));
	pointsround2.setCellValueFactory(new PropertyValueFactory<Game, String>("pointsRound2"));
	pointsround3.setCellValueFactory(new PropertyValueFactory<Game, String>("pointsRound3"));
	pointsround4.setCellValueFactory(new PropertyValueFactory<Game, String>("pointsRound4"));
	PlayerName.setCellValueFactory(new PropertyValueFactory<Game, String>("PlayerName"));

	HashSet<Game> set = new HashSet<>();
	set.addAll(datagames);
	ObservableList<Game> dataGame2 =  FXCollections.observableArrayList(set);
	historyTable.setItems(dataGame2);
  	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
		    SysData.getInstance().ReadFromJsonGames();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		fill();
		historyTable.refresh();
		
	}
}
