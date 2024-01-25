package model;

import java.net.URL;
import java.util.ArrayList;
//question class
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public  class  Question implements Comparable<Question>{
	
	private static int idCounter = 1;
	private int questionID;
	private String questionText;
	private String diffuclty;
	private int correctAnswer;
	// points to add and points to delete will be update depends on the diffuclty
	// will build a function to that on the next itertation when we start to develop the game
	private int pointsToDelelete = 0;
	private int pointsToAdd = 0;
	private String team;
	//answers of the Question
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;

	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getDiffuclty() {
		return diffuclty;
	}
	public void setDiffuclty(String diffuclty) {
		this.diffuclty = diffuclty;
	}
	public int getPointsToDelelete() {
		return pointsToDelelete;
	}
	public void setPointsToDelelete() {

		if(this.diffuclty == "easy") {
			this.pointsToDelelete = 2;
		}
		if(this.diffuclty == "medium") {
			this.pointsToDelelete = 3;
		}
		if(this.diffuclty == "hard") {
			this.pointsToDelelete = 4;
		}
	}
	public int getPointsToAdd() {
		return pointsToAdd;
	}
	public void setPointsToAdd() {
		
		if(this.diffuclty == "easy") {
			this.pointsToAdd = 1;
		}
		if(this.diffuclty == "medium") {
			this.pointsToAdd = 2;
		}
		if(this.diffuclty == "hard") {
			this.pointsToAdd = 3;
		}
	}

	


@Override
	public int hashCode() {
		return Objects.hash(questionText);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		return Objects.equals(questionText, other.questionText);
	}
public Question(String questionText, String diffuclty, int correctAnswer
					, String team, String answer1, String answer2, String answer3, String answer4) {
		super();
		this.questionID = idCounter++;
		this.questionText = questionText;
		this.diffuclty = diffuclty;
		this.correctAnswer = correctAnswer;
		this.setPointsToDelelete();
		this.setPointsToAdd();
		this.team = team;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
	}

public Question(String questionText) {
	this.questionText = questionText;
}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	@Override
	public String toString() {
		return this.getDiffuclty() + " " + this.getQuestionText();
	}
@Override
public int compareTo(Question o) {
	return this.questionText.compareToIgnoreCase(o.getQuestionText());
	
}

	

}
