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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Question;
import model.SysData;
import view.MainMenu;
//QUESTION manage controller
public class questionManageControl implements Initializable{
//table and table columns
    @FXML
    private TableView<Question> questionTable;

    @FXML
    private TableColumn<Question, String> question_text;

    @FXML
    private TableColumn<Question, String> Answer1_text;

    @FXML
    private TableColumn<Question, String>  Answer2_text;
    
    @FXML
    private TableColumn<Question,String> Answer3_text;

    @FXML
    private TableColumn<Question, String>  Answer4_text;

    @FXML
    private TableColumn<Question, String>  correctAnswerNum;

    @FXML
    private TableColumn<Question, String> diffLevel;

    @FXML
    private TableColumn<Question, String> teamText;
      //buttons
    @FXML
    private Button addQButton;

    @FXML
    private Button UpdateQButton;

    @FXML
    private Button deleteQButton;

    @FXML
    private Button backButt;
         //order buttons
    @FXML
    private Button addQButton1;

    @FXML
    private Button addQButton11;
    
    @FXML
    private Button restoreBTN;
    
     //method that moves to the page behind (main page)
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
     //delete Question from table method
    @FXML
    void deleteQues(ActionEvent event) throws IOException, ParseException {
    	//check if the user selected to delete 
    	if(questionTable.getSelectionModel().getSelectedIndex() == -1) {
    		view.Feedback.message("Error", "Please select a question that you want to delete");
    		return;
    	}
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
    	//remove question from json
    	SysData.getInstance().RemoveFromJson(q);
		SysData.getInstance().getQuestions().removeAll(SysData.getInstance().deletedQuesText);
		fill();
    	questionTable.getItems().clear();
		fill();// fil
    	
    }
      //GO to add page method
    @FXML
    void goToAddQueSecene(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/AddQuestion.fxml"));
			Scene scene = new Scene(root);
			MainMenu.mainS.setScene(scene);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    //GO to add update method
    @FXML
    boolean goToUpdateScene(ActionEvent event) {
    	try {
    		if(questionTable.getSelectionModel().getSelectedIndex() == -1) {
    			view.Feedback.message("Error", "Please choose question that you want to update");
    			return false;
    		}
    		//question to update is question selected
    		UpdateQuestionSceneControll.questionToUp = questionTable.getSelectionModel().getSelectedItem();
			Parent root = FXMLLoader.load(getClass().getResource("/view/UpdateQuestion.fxml"));
			Scene scene = new Scene(root);
			MainMenu.mainS.setScene(scene);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
    }
   // order by diffuclty method
    @FXML
    void orderByDiff(ActionEvent event) { // order by the difficulty
    	ArrayList<Question> orderBydiff = new ArrayList<>(SysData.getInstance().getQuestions());
    	ArrayList<Question>ordered = new ArrayList<>();
    	//order
    	for(Question q : orderBydiff) {
    		if(q.getDiffuclty().equals("easy")) {
    			ordered.add(q);
    		}
    	}
    	for(Question q : orderBydiff) {
    		if(q.getDiffuclty().equals("medium")) {
    			ordered.add(q);
    		}
    	}
    		for(Question q : orderBydiff) {
        		if(q.getDiffuclty().equals("hard")) {
        			ordered.add(q);
        		}
    		}
    		//view on table
    		ObservableList<Question> dataQues = FXCollections.observableArrayList(ordered);
      		question_text.setCellValueFactory(new PropertyValueFactory<Question, String>("questionText"));
      		diffLevel.setCellValueFactory(new PropertyValueFactory<Question, String>("diffuclty"));
      		correctAnswerNum.setCellValueFactory(new PropertyValueFactory<Question, String>("correctAnswer"));
      		teamText.setCellValueFactory(new PropertyValueFactory<Question, String>("team"));
      		Answer1_text.setCellValueFactory(new PropertyValueFactory<Question, String>("answer1"));
      		Answer2_text.setCellValueFactory(new PropertyValueFactory<Question, String>("answer2"));
      		Answer3_text.setCellValueFactory(new PropertyValueFactory<Question, String>("answer3"));
      		Answer4_text.setCellValueFactory(new PropertyValueFactory<Question, String>("answer4"));
      		ObservableList<Question>temp =  FXCollections.observableArrayList(dataQues);
      		questionTable.setItems(temp);
      		
    	
    }

    @FXML
    void orderByQuestion(ActionEvent event) {
    	//order by question text alphapet
    	ArrayList<Question> orderByText = new ArrayList<>(SysData.getInstance().getQuestions());
    	Collections.sort(orderByText, new Comparator<Question>() {
            @Override
            public int compare(Question q1, Question q2) {
                String s1 = q1.getQuestionText();
                String s2 = q2.getQuestionText();
                return s1.compareToIgnoreCase(s2);
            }
    });
  		//view on table
		ObservableList<Question> dataQues = FXCollections.observableArrayList(orderByText);
  		question_text.setCellValueFactory(new PropertyValueFactory<Question, String>("questionText"));
  		diffLevel.setCellValueFactory(new PropertyValueFactory<Question, String>("diffuclty"));
  		correctAnswerNum.setCellValueFactory(new PropertyValueFactory<Question, String>("correctAnswer"));
  		teamText.setCellValueFactory(new PropertyValueFactory<Question, String>("team"));
  		Answer1_text.setCellValueFactory(new PropertyValueFactory<Question, String>("answer1"));
  		Answer2_text.setCellValueFactory(new PropertyValueFactory<Question, String>("answer2"));
  		Answer3_text.setCellValueFactory(new PropertyValueFactory<Question, String>("answer3"));
  		Answer4_text.setCellValueFactory(new PropertyValueFactory<Question, String>("answer4"));
  		ObservableList<Question>temp =  FXCollections.observableArrayList(dataQues);
  		questionTable.setItems(temp);
    	
    }
    
  //fill the table method
  	public void fill() {
  		ObservableList<Question> dataQues = FXCollections.observableArrayList(SysData.getInstance().getQuestions());
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			SysData.getInstance().ReadFromJson();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fill();
		questionTable.refresh();
		
	}
	
    @FXML
    void goToRestoreScene(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/RestoreQuesScene.fxml"));
			Scene scene = new Scene(root);
			MainMenu.mainS.setScene(scene);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
	
	


}
