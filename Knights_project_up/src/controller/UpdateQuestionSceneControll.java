package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Question;
import model.SysData;
import view.MainMenu;
//update Question Controller
public class UpdateQuestionSceneControll implements Initializable{ // update scene controller
	
	public static Question questionToUp = null;
    @FXML
    private TextField question_text;

    @FXML
    private TextField answer1_text;

    @FXML
    private TextField answer2_text;

    @FXML
    private TextField answer3_text;

    @FXML
    private TextField answer4_text;

    @FXML
    private ComboBox<String> diff_text;

    @FXML
    private Button updateButton;

    @FXML
    private TextField corrAns_text;

    @FXML
    private Button goBack;

    @FXML
    private ComboBox<String> team_id;
    //back to the manage Question page
    @FXML
    void goBackFunc(ActionEvent event) { // go back function to go back to the question manage scene
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/questionManage.fxml"));
			Scene scene = new Scene(root);
			MainMenu.mainS.setScene(scene);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
     //update QUestion Button
    @FXML
    void updateQuestion(ActionEvent event) throws IOException, ParseException {
    	//check if all fields are fillen
    	if(question_text.getText().length() == 0 || answer1_text.getText().length() == 0 || 
    			answer2_text.getText().length() == 0 || answer2_text.getText().length() == 0 ||
    			answer3_text.getText().length() == 0 || answer4_text.getText().length() == 0 ||
    			corrAns_text.getText().length() == 0 || team_id.getSelectionModel().getSelectedIndex() == -1 ||
    			diff_text.getSelectionModel().getSelectedIndex() == -1){
    			view.Feedback.message("Error","Please fill all the fields");
    	}

    	//updated Question
    	Question ques = new Question(question_text.getText(), diff_text.getSelectionModel().getSelectedItem(), 
    			Integer.parseInt(corrAns_text.getText()),team_id.getSelectionModel().getSelectedItem() ,answer1_text.getText(), answer2_text.getText(),
    			answer3_text.getText(), answer4_text.getText()); 	
    	SysData.getInstance().RemoveFromJson(questionToUp);
    	SysData.getInstance().writeToJson2(ques);
		view.Feedback2.message("Updated", "Question has been Updated succesfully!");
    	return;
    }
     //initialize the page
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//fill
		fillFields();
		fillCombo();
		fillTeamCombo();
	}
    //fill fields with the selected Question
	private void fillFields() {
		question_text.setText(questionToUp.getQuestionText());
		answer1_text.setText(questionToUp.getAnswer1());
		answer2_text.setText(questionToUp.getAnswer2());
		answer3_text.setText(questionToUp.getAnswer3());
		answer4_text.setText(questionToUp.getAnswer4());
		diff_text.setValue(questionToUp.getDiffuclty());
		team_id.setValue(questionToUp.getTeam());
		corrAns_text.setText(Integer.toString(questionToUp.getCorrectAnswer()));
	}
	//fill the combobox
	private void fillCombo() {
		ObservableList<String> cd=FXCollections.observableArrayList();
		cd.add("easy");
		cd.add("medium");
		cd.add("hard");
		diff_text.setItems(cd);
	}
	//fill the combobox
	private void fillTeamCombo() {
		ArrayList<String>temp = new ArrayList<>();
		temp.add("Girrafe");
		temp.add("Husky");
		temp.add("Spider");
		temp.add("Tiger");
		temp.add("Rabbit");
		temp.add("Jellyfish");
		temp.add("Zebra");
		temp.add("Panther");
		temp.add("Chimp");
		temp.add("Shark");
		temp.add("Panda");
		temp.add("Viper");
		temp.add("Sloth");
		temp.add("Scorpion");
		temp.add("Lion");
		temp.add("Hawk");
		temp.add("Wolf");
		temp.add("Python");	
		ObservableList<String> cd=FXCollections.observableArrayList();
		cd.addAll(temp);
		team_id.setItems(cd);
	}
}
