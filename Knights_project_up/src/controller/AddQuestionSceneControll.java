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
//Add Question Controller
public class AddQuestionSceneControll implements Initializable{
          //FIELDS TO FILL
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
    @FXML//fds
    private ComboBox<String> diff_combo;
    @FXML
    private Button addButton;
    @FXML
    private TextField corr_answerText;
    @FXML
    private Button backButton;
    @FXML
    private ComboBox<String> team_combo;
    
    //add method : after clicking add 
    @FXML
    boolean addQuestion(ActionEvent event) throws IOException, ParseException {
    	//check if all fields are not empty and filled by the user	
    	if(question_text.getText().length() == 0 || answer1_text.getText().length() == 0 ||
    				answer2_text.getText().length() == 0 || answer3_text.getText().length() == 0 ||
    				answer4_text.getText().length() == 0 || corr_answerText.getText().length() == 0) {
    		     //if there's one field empty the user gets a note : add is not applied!
    			view.Feedback.message("Error", "Please fill all the fileds");
    			return false;
    		}
    		//check if the combo box selected (filled)
    		if(diff_combo.getSelectionModel().getSelectedIndex() == -1 || team_combo.getSelectionModel().getSelectedIndex() == -1) {
    			view.Feedback.message("Error", "Please fill all the fileds");
    			return false;
    		}
    		//check if the correct answer field is numeric (not string)
    		double d=0;
    		try {
    			d=Double.valueOf(corr_answerText.getText());}
    			catch(Exception e ) {
        			view.Feedback.message("Error", "This field must be numeric");
                           return false;
    			}
    		//check if between 1 and 4
    		if(d!=1 && d!=2 && d!=3 && d!=4) {
    			view.Feedback.message("Error", "the correct answer must be between 1 and 4");
                return false;

    		}

    		//get the fields the user filled
    		String ques_text = question_text.getText();
    		String ans1_text = answer1_text.getText();
    		String ans2_text = answer2_text.getText();
    		String ans3_text = answer3_text.getText();
    		String ans4_text = answer4_text.getText();
    		String team = team_combo.getSelectionModel().getSelectedItem();
    		String diff = diff_combo.getSelectionModel().getSelectedItem();
      		String corr_ans = corr_answerText.getText();

    		//create new object (question) to add to our database
    		Question q = new Question(ques_text, diff, Integer.parseInt(corr_ans), team, 
    				ans1_text, ans2_text, ans3_text, ans4_text);
    		//write our question to json and add to question hashSet
    		SysData.getInstance().writeToJson2(q);
    		//clear fields
    		clearFields();
			view.Feedback2.message("Added", "Question has been added succesfully!");

    		return true;
    }
//back to question manage
    @FXML
    void goHome(ActionEvent event) {
    	try {
    		
			Parent root = FXMLLoader.load(getClass().getResource("/view/questionManage.fxml"));
			Scene scene = new Scene(root);
			MainMenu.mainS.setScene(scene);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
//initialize tha page
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		fillCombo();
		fillTeamCombo();
	}
	  //fill diffuclty comboBox
	private void fillCombo() {
		ObservableList<String> cd=FXCollections.observableArrayList();
		cd.add("easy");
		cd.add("medium");
		cd.add("hard");
		diff_combo.setItems(cd);
	}
	//Fill team comboBox
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
		team_combo.setItems(cd);

	}
	//clear fields
	private void clearFields() {
		question_text.clear();
		answer1_text.clear();
		answer2_text.clear();
		answer3_text.clear();
		answer4_text.clear();
		corr_answerText.clear();
		diff_combo.valueProperty().set(null);
		team_combo.valueProperty().set(null);
		
		}

}
