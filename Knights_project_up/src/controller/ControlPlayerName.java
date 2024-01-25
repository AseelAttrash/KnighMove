package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.parser.ParseException;

import model.SysData;
import view.MainMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControlPlayerName implements Initializable {

	@FXML
	private TextField nicknameField;
	@FXML 
	private Label messageLabel;
	@FXML
	private Button closeNameWindow;
	@FXML
	private Button startAfterNickname;


	public void startAfterNickname(ActionEvent e)
	{
		try {
			if(nicknameField.getText().isEmpty() == true)
			{
				messageLabel.setText("Please enter your nickname");
			}
			else 
			{
				ControlBoard.playName = nicknameField.getText();
		    	try {
					Parent root = FXMLLoader.load(getClass().getResource("/view/Board.fxml"));
					Scene scene = new Scene(root);
					MainMenu.mainS.setScene(scene);

				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			
			}
			
		}
			catch(Exception e1)
			{
				System.out.println("Cannot load window");
			}
	}
	public void closeNameWindowOnAction(ActionEvent e)
	{
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
			Scene scene = new Scene(root);
			MainMenu.mainS.setScene(scene);

		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
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
}
