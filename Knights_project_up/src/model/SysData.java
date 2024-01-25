package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


//Sysdata main class of the game
public class SysData {
	//singleton
	private static SysData sysData = null;
    //fields of the class (databases)
	private HashSet<Game> games;
	private HashSet<Question>questions;
	//arraylist to deleted questions (questions the user had deleted)
	public ArrayList<Question>deletedQuesText;
	//arraylist to the deleted questions that has been resored
	public ArrayList<Question>addedAgain;
	public ArrayList<Question>jsonDelete;
	//constructor
	private SysData() {
		games = new HashSet<>();
		questions = new HashSet<>();
		deletedQuesText = new ArrayList<>();
		addedAgain = new ArrayList<>();
		jsonDelete = new ArrayList<>();
	}
	//gets and sets
	public HashSet<Game> getGames() {
		return games;
	}
	public void setGames(HashSet<Game> games) {
		this.games = games;
	}
	public HashSet<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(HashSet<Question> questions) {
		this.questions = questions;
	}
      //get instance
	public static SysData getInstance() { 
		if (sysData == null) {

			sysData = new SysData();
			return sysData;
		}
		return sysData;
	}

      //read from Json method  (Json contains the questions)
	public void ReadFromJson() throws IOException, ParseException {

		JSONParser parser = new JSONParser();
		FileInputStream fis = new FileInputStream("json/quetion.json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		Object obje = parser.parse(reader);
		JSONObject jo = (JSONObject) obje;
		JSONArray quesArray = (JSONArray) jo.get("questions");

		Iterator<JSONObject> QuestionIter = quesArray.iterator();
		while (QuestionIter.hasNext()) {

			JSONObject que = QuestionIter.next();
			String QuestionText = (String) que.get("question");
			JSONArray answeer = (JSONArray) que.get("answers");
			ArrayList<String> answers = new  ArrayList<String>();
			for (int i = 0; i < answeer.size(); i++) {
				String answercontent = (String) answeer.get(i);
				String annnns = new String(answercontent);
				answers.add(annnns);

			}
			int correctAnswerNum = Integer.valueOf(que.get("correct_ans").toString());

			String teams = (String) que.get("team");
			String levelDiffuclty = (String) que.get("level");
			//read and prints the json
			Question question = new Question(QuestionText, levelDiffuclty,
					correctAnswerNum, teams, answers.get(0),
					answers.get(1), answers.get(2), answers.get(3));
			questions.add(question);
		}
		questions.removeAll(deletedQuesText);
	}

       //write from Json method(Json contains the questions)
	public void writeToJson2(Question question) throws IOException, ParseException {


		JSONParser parser = new JSONParser();
		FileInputStream fis = new FileInputStream("json/quetion.json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		Object obje = parser.parse(reader);
		JSONObject jo = (JSONObject) obje;
		JSONArray quesArray = (JSONArray) jo.get("questions");


		JSONObject jsonObject = new JSONObject();
		//write question text
		jsonObject.put("question", question.getQuestionText());

		JSONArray arr = new JSONArray();
         //write answers
		arr.add(question.getAnswer1());
		arr.add(question.getAnswer2());
		arr.add(question.getAnswer3());
		arr.add(question.getAnswer4());
		jsonObject.put("answers",arr);
        //write correct answers
		jsonObject.put("correct_ans", Integer.toString(question.getCorrectAnswer()));
		jsonObject.put("level", question.getDiffuclty());
		jsonObject.put("team", question.getTeam());
		quesArray.add(jsonObject);


		JSONObject jsonObject2 = new JSONObject();

		jsonObject2.put("questions", quesArray); 

		try {
			FileWriter file = new FileWriter("json/quetion.json");
			file.write(jsonObject2.toJSONString());
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {//////////////////////////////////////////
			addedAgain.add(question);
			this.deletedQuesText.removeAll(addedAgain);
			SysData.getInstance().ReadFromJson();
		}



	}

    //remove from json method (Json contains the questions)
	public void RemoveFromJson(Question question) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileInputStream fis = new FileInputStream("json/quetion.json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		Object obje = parser.parse(reader);
		JSONObject jo = (JSONObject) obje;
		JSONArray quesArray = (JSONArray) jo.get("questions");
		Iterator<JSONObject> QuestionIter = quesArray.iterator();
	
		while(QuestionIter.hasNext()) {
			JSONObject jo2 = QuestionIter.next();
			String questionText = (String) jo2.get("question");
			if(questionText.equals(question.getQuestionText())) {
				QuestionIter.remove();
			}


		}
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("questions", quesArray);
		try {
			FileWriter file = new FileWriter("json/quetion.json");
			file.write(jsonObject2.toJSONString());
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			deletedQuesText.add(question);
			SysData.getInstance().writeToDeletedJson2(question);
			SysData.getInstance().ReadFromJson();
		}
		
	}
	
	
	  //read from deleted Json method   (Json contains the deleted questions)
		public void ReadFromDeletedJson() throws IOException, ParseException {


			JSONParser parser = new JSONParser();
			FileInputStream fis = new FileInputStream("json/q.json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			Object obje = parser.parse(reader);
			JSONObject jo = (JSONObject) obje;
			JSONArray quesArray = (JSONArray) jo.get("questions");

			Iterator<JSONObject> QuestionIter = quesArray.iterator();
			while (QuestionIter.hasNext()) {

				JSONObject que = QuestionIter.next();
				String AnswerText = (String) que.get("question");
				JSONArray answeer = (JSONArray) que.get("answers");
				ArrayList<String> answers = new  ArrayList<String>();
				for (int i = 0; i < answeer.size(); i++) {
					String answercontent = (String) answeer.get(i);
					String annnns = new String(answercontent);
					answers.add(annnns);

				}
				int correctAnswerNum = Integer.valueOf(que.get("correct_ans").toString());

				String teams = (String) que.get("team");
				String levelDiffuclty = (String) que.get("level");
				//read and prints the json
				Question question = new Question(AnswerText, levelDiffuclty,
						correctAnswerNum, teams, answers.get(0),
						answers.get(1), answers.get(2), answers.get(3));			
				        jsonDelete.add(question);
			}
			
		}
		
		 //remove from json method  (Json contains the deleted questions)
		public void RemoveFromDeletedJson(Question question) throws IOException, ParseException {
			JSONParser parser = new JSONParser();
			FileInputStream fis = new FileInputStream("json/q.json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			Object obje = parser.parse(reader);
			JSONObject jo = (JSONObject) obje;
			JSONArray quesArray = (JSONArray) jo.get("questions");
			Iterator<JSONObject> QuestionIter = quesArray.iterator();			
			while(QuestionIter.hasNext()) {
				JSONObject jo2 = QuestionIter.next();
				
				String questionText = (String) jo2.get("question");
				
				if(questionText.equals(question.getQuestionText())) {
					QuestionIter.remove();
				}


			}
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("questions", quesArray);
			
			try {
				FileWriter file = new FileWriter("json/q.json");
				file.write(jsonObject2.toJSONString());
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				SysData.getInstance().writeToJson2(question);
			}
			
		}
		
		   //write to Json method (Json contains the deleted questions)
		public void writeToDeletedJson2(Question question) throws IOException, ParseException {


			JSONParser parser = new JSONParser();
			FileInputStream fis = new FileInputStream("json/q.json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			Object obje = parser.parse(reader);
			JSONObject jo = (JSONObject) obje;
			JSONArray quesArray = (JSONArray) jo.get("questions");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("question", question.getQuestionText());

			JSONArray arr = new JSONArray();
			arr.add(question.getAnswer1());
			arr.add(question.getAnswer2());
			arr.add(question.getAnswer3());
			arr.add(question.getAnswer4());
			jsonObject.put("answers",arr);

			jsonObject.put("correct_ans", Integer.toString(question.getCorrectAnswer()));

			jsonObject.put("level", question.getDiffuclty());

			jsonObject.put("team", question.getTeam());

			quesArray.add(jsonObject);


			JSONObject jsonObject2 = new JSONObject();

			jsonObject2.put("questions", quesArray); 

			try {
				FileWriter file = new FileWriter("json/q.json");
				file.write(jsonObject2.toJSONString());
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {//////////////////////////////////////////
				jsonDelete.add(question);
			}
		}

		public void writeToJsonGames(Game g) throws IOException, ParseException {
			JSONParser parser = new JSONParser();
			FileInputStream fis = new FileInputStream("json/NewJson.json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			Object obje = parser.parse(reader);
			JSONObject jo = (JSONObject) obje;
			JSONArray gamesArray = (JSONArray) jo.get("games");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("PlayerName", g.getPlayer());
			jsonObject.put("round", Integer.toString(g.getRound()));
			LocalDate d =g.getGameDate();
			jsonObject.put("gameDate", (d.toString()));
			String str1 = new Boolean(g.isPrize()).toString();
			jsonObject.put("isPrize", (str1));
			jsonObject.put("pointsRound1", Integer.toString(g.getPointsRound1()));
			jsonObject.put("pointsRound2", Integer.toString(g.getPointsRound2()));
			jsonObject.put("pointsRound3", Integer.toString(g.getPointsRound3()));
			jsonObject.put("pointsRound4", Integer.toString(g.getPointsRound4()));
			gamesArray.add(jsonObject);
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("games", gamesArray); 
			try {
				FileWriter file = new FileWriter("json/NewJson.json");
				file.write(jsonObject2.toJSONString());
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//check if json Empty
		public   boolean ifJsonEmpty(BufferedReader reader) throws IOException {
			
		    String json = "";
		    String line;
		    while ((line = reader.readLine()) != null) {
		      json += line;
		    }
		    reader.close();

		    // Check if the JSON file is empty
		    if (json.length() == 0) {
		      System.out.println("The JSON file is empty");
		      return true;
		    } else {
		    	return false;
		    }
			 
		}
		public void ReadFromJsonGames() throws IOException, ParseException {
			
			try ( FileInputStream fis = new FileInputStream("json/NewJson.json"))
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		         if (ifJsonEmpty(reader)==true)
		         {
		        	 return;
		         }else {
		 			JSONParser parser2 = new JSONParser();
		 			FileInputStream fis2 = new FileInputStream("json/NewJson.json");
					BufferedReader reader2 = new BufferedReader(new InputStreamReader(fis2));
				Object obje = parser2.parse(reader2);
				
				JSONObject jo = (JSONObject) obje;
		
				JSONArray quesArray = (JSONArray) jo.get("games");

				Iterator<JSONObject> QuestionIter = quesArray.iterator();
				while (QuestionIter.hasNext()) {

					JSONObject que = QuestionIter.next();
					String PlayerName = (String) que.get("PlayerName");
					int round = Integer.valueOf(que.get("round").toString());
					int pointsRound1 = Integer.valueOf(que.get("pointsRound1").toString());
					int pointsRound2 = Integer.valueOf(que.get("pointsRound2").toString());
					int pointsRound3 = Integer.valueOf(que.get("pointsRound3").toString());
					int pointsRound4 = Integer.valueOf(que.get("pointsRound4").toString());
	                Boolean isPrize = Boolean.parseBoolean((String) que.get("isPrize"));
	                LocalDate localDate = LocalDate.parse((String) que.get("gameDate"));  	
	            	Game g = new Game(PlayerName,round,localDate,isPrize,pointsRound1,pointsRound2,pointsRound3,pointsRound4);
	            	SysData.getInstance().getGames().add(g);
			}}
			}catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

		}
}
