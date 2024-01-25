package controller;

import java.io.IOException;
import java.net.URL;
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
import model.Question;
import model.SysData;
import view.MainMenu;

public class RestoreQuesSceneControll implements Initializable{
    //table
    @FXML
    private TableView<Question> questionTable;
     //table columns
    @FXML
    private TableColumn<Question, String> question_text;

    @FXML
    private TableColumn<Question, String> Answer1_text;

    @FXML
    private TableColumn<Question, String> Answer2_text;

    @FXML
    private TableColumn<Question, String> Answer3_text;

    @FXML
    private TableColumn<Question, String> Answer4_text;

    @FXML
    private TableColumn<Question, String> correctAnswerNum;

    @FXML
    private TableColumn<Question, String> diffLevel;

    @FXML
    private TableColumn<Question, String> teamText;
       //buttons    
    @FXML
    private Button backButton;

    @FXML
    private Button restorebutton;
         //initialize
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			SysData.getInstance().ReadFromDeletedJson();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fill();
		questionTable.refresh();
		
	}
     //fill the table
	private void fill() {
		// TODO Auto-generated method stub
		ObservableList<Question> dataQues = FXCollections.observableArrayList(SysData.getInstance().jsonDelete);
  		question_text.setCellValueFactory(new PropertyValueFactory<Question, String>("questionText"));
  		diffLevel.setCellValueFactory(new PropertyValueFactory<Question, String>("diffuclty"));
  		correctAnswerNum.setCellValueFactory(new PropertyValueFactory<Question, String>("correctAnswer"));
  		teamText.setCellValueFactory(new PropertyValueFactory<Question, String>("team"));
  		Answer1_text.setCellValueFactory(new PropertyValueFactory<Question, String>("answer1"));
  		Answer2_text.setCellValueFactory(new PropertyValueFactory<Question, String>("answer2"));
  		Answer3_text.setCellValueFactory(new PropertyValueFactory<Question, String>("answer3"));
  		Answer4_text.setCellValueFactory(new PropertyValueFactory<Question, String>("answer4"));
  		HashSet<Question> set = new HashSet<>();
  		set.addAll(dataQues);
  		ObservableList<Question>dataQues2 =  FXCollections.observableArrayList(set);
  		questionTable.setItems(dataQues2);
	}
	//back to manage questions button
	 @FXML
	    void goBack(ActionEvent event) {
		 try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/questionManage.fxml"));
				Scene scene = new Scene(root);
				MainMenu.mainS.setScene(scene);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    }
        //restore question button
	    @FXML
	    void restoreQuesFunc(ActionEvent event) throws IOException, ParseException {
	    	//check if the user selected a question to restore 
	    	if(questionTable.getSelectionModel().getSelectedIndex() == -1) {
	    		view.Feedback.message("Error", "Please select a question that you want to restore");
	    		return;
	    	}
	    	//get the question selected fields
	    	String question_t = questionTable.getSelectionModel().getSelectedItem().getQuestionText();
    		String ans1_text = questionTable.getSelectionModel().getSelectedItem().getAnswer1();
    		String ans2_text = questionTable.getSelectionModel().getSelectedItem().getAnswer2();
    		String ans3_text = questionTable.getSelectionModel().getSelectedItem().getAnswer3();
    		String ans4_text = questionTable.getSelectionModel().getSelectedItem().getAnswer4();
    		String team = questionTable.getSelectionModel().getSelectedItem().getTeam();
    		String diff = questionTable.getSelectionModel().getSelectedItem().getDiffuclty();
      		Integer corr_ans = questionTable.getSelectionModel().getSelectedItem().getCorrectAnswer();
	    	Question q = new Question(question_t, diff, corr_ans, team, ans1_text,
	    			ans2_text, ans3_text, ans4_text);
	    	//remove question from deleted question json
	    	SysData.getInstance().RemoveFromDeletedJson(q);
			SysData.getInstance().jsonDelete.removeAll(SysData.getInstance().getQuestions());
			fill();
	    	questionTable.getItems().clear();
			fill();// fil
	    }

}
