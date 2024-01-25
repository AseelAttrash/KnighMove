package JUnit;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import controller.ControlBoard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.Player;
import model.Point;
import model.Question;
import model.SysData;

public class tests {



	//create a new question and write it to json and check if it has been added to sysData
    @Test
    public void testAddingNewQuestion() throws IOException, ParseException {

        Question question = new Question("A software component is an architectural entity that.", "Medium", 4, "Lion", "encapsulates a subset of the system’s functionality and/or data", "restricts access to that subset via an explicitly defined interface", "has explicitly defined dependencies on its required execution context", "all the answers are correct"); 
        SysData.getInstance().writeToJson2(question);
        assertTrue(SysData.getInstance().getQuestions().contains(question));

 

    }

	 

	    
	    // remove a question from sysData and checks if it actually has been removed from the class
	    @Test
	    public void testRemovingQuestion() throws IOException, ParseException {
	        Question question = new Question("A software component is an architectural entity that.", "Medium", 4, "Lion", "encapsulates a subset of the system’s functionality and/or data", "restricts access to that subset via an explicitly defined interface", "has explicitly defined dependencies on its required execution context", "all the answers are correct"); 
	        SysData.getInstance().RemoveFromJson(question);
	        SysData.getInstance().ReadFromJson();
	        assertTrue(!SysData.getInstance().getQuestions().contains(question));

	 

	    }

	 

	    // test if the hashSet question that is in sysData isn't empty
	    @Test
	   public void testQuestionsNotNull() throws IOException, ParseException {
	        SysData.getInstance().ReadFromJson();
	       SysData.getInstance().getQuestions();
	        assertNotNull("Questions", SysData.getInstance().getQuestions());

	   }

	 

	    @Test
	    //test if the ifJsonEmpty method is working correctly and returning false when the input is a non empty json file
	    public void testIfJsonEmpty() throws IOException  {
	      // Test with a non-empty JSON file
	      FileInputStream fis = new FileInputStream("json/q.json");
	      BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
	      assertFalse(SysData.getInstance().ifJsonEmpty(reader));

	      
	    }
	  
	// test if the games hashSet that contains the games in class SysData is not null

	    @Test
	    public void testGamesNotNull() throws IOException, ParseException {
            SysData.getInstance().ReadFromJsonGames();
	        SysData.getInstance().getGames();
	        assertNotNull("Games", SysData.getInstance().getGames());
	    	ObservableList<Game> datagames = FXCollections.observableArrayList(SysData.getInstance().getGames());
	    	SysData.getInstance().getGames().removeAll(datagames);
	    }
	    

}