package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Game;
import model.SysData;


public class MainMenu extends Application{
	public static Stage mainS=null;
	
	public static void main(String[] args) throws IOException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				try {
					primaryStage.initStyle(StageStyle.DECORATED);
					Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
					
					Scene scene = new Scene(root);
					mainS=primaryStage;
					primaryStage.setScene(scene);
					primaryStage.setResizable(false);
					primaryStage.show();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
//				public Game(String playerName, int round, LocalDate gameDate, boolean isPrize, int pointsRound1, int pointsRound2,
//						int pointsRound3, int pointsRound4) 
//	Game g = new Game("top",4,LocalDate.now(),true,50,50,50,50);
//	SysData.getInstance().writeToJsonGames(g);
	}
}


