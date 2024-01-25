package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

//import application.Position;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;
import model.King;
import model.Player;
import model.Point;
import model.Queen;
import model.Question;
import model.Square;
import model.SysData;
import view.Feedback;
import view.Feedback2;
import view.MainMenu;

public class ControlBoard implements Initializable{

	public static String playName = new String();

	//	yournode.setStyle("-fx-background-color: #" + enteredByUser);

	Player player;
	King king;
	Queen queen;
	Game game;


	public static int timeLeft = 10;

	public static int points = 0;

	ArrayList<Pane>panesClicked = new ArrayList<>();
	ArrayList<Pane>panes = new ArrayList<>();
	ObservableList<Pane> paness = FXCollections.observableArrayList();// all our panes
	static ArrayList<Pane>panesPanes = new ArrayList<>();
	static ArrayList<Pane>tempPanes = new ArrayList<>(); // the panes of the horse
	static LinkedHashSet<Pane>visitedPanes = new LinkedHashSet<>(); // the visited panes
	static HashSet<Pane>queenPanes = new HashSet<>(); // the queen panes
	static HashSet<Pane>kingPanes = new HashSet<>();
	static String whoPlays = "horse"; // whoPlays the queen or the horse
	HashSet<String>twoSteps = new HashSet<>(); // this is hashset to check if its the horse played
	static List<Pane> tempList = new ArrayList<>(); // Arraylist to handle the random jump panes
	static List<Pane> questionPanes = new ArrayList<>();
	static HashMap<Pane, String> questionColor = new HashMap<>(); // question panes 
	static Timeline timeline = new Timeline(); // the timeline of level 1 
	static Pane lastUsedQuesPane = null; // to check  what is the last pane that the hoese was on
	static ArrayList<Pane> blockedPanes = new ArrayList<>();
	static int[] pointsPerLevel = new int[4];

	static int row = 0;
	static int col = 0;

	static ArrayList<Integer> pointsToRestore = new ArrayList<>();
	static ArrayList<Object> forgotPanesNow = new ArrayList<>();


	Pane[][] panesMat = new Pane[8][8]; // the board

	@FXML
	private Text alertMan;

	@FXML
	private TextField alertForUser;

	@FXML
	private Text AlertText;

	@FXML
	private Text playerNameLabel;

	@FXML
	private Text levelText;

	@FXML
	private Text timer;

	@FXML
	private TextField scoreText;

	@FXML
	private Button gameRulesBtn;

	@FXML
	private GridPane gridPane;

	@FXML
	private Pane pane00;

	@FXML
	private Text horseCHESS;

	@FXML
	private Pane pane10;

	@FXML
	private Pane pane20;

	@FXML
	private Pane pane30;

	@FXML
	private Pane pane40;

	@FXML
	private Pane pane50;

	@FXML
	private Pane pane60;

	@FXML
	private Pane pane70;

	@FXML
	private Pane pane01;

	@FXML
	private Pane pane11;

	@FXML
	private Pane pane21;

	@FXML
	private Pane pane31;

	@FXML
	private Pane pane41;

	@FXML
	private Pane pane51;

	@FXML
	private Pane pane61;

	@FXML
	private Pane pane71;

	@FXML
	private Pane pane02;

	@FXML
	private Pane pane12;

	@FXML
	private Pane pane22;

	@FXML
	private Pane pane32;

	@FXML
	private Pane pane42;

	@FXML
	private Pane pane52;

	@FXML
	private Pane pane62;

	@FXML
	private Pane pane72;

	@FXML
	private Pane pane03;

	@FXML
	private Pane pane13;

	@FXML
	private Pane pane23;

	@FXML
	private Pane pane33;

	@FXML
	private Pane pane43;

	@FXML
	private Pane pane53;

	@FXML
	private Pane pane63;

	@FXML
	private Pane pane73;

	@FXML
	private Pane pane04;

	@FXML
	private Pane pane14;

	@FXML
	private Pane pane24;

	@FXML
	private Pane pane34;

	@FXML
	private Pane pane44;

	@FXML
	private Pane pane54;

	@FXML
	private Pane pane64;

	@FXML
	private Pane pane74;

	@FXML
	private Pane pane75;

	@FXML
	private Pane pane65;

	@FXML
	private Pane pane55;

	@FXML
	private Pane pane45;

	@FXML
	private Pane pane35;

	@FXML
	private Pane pane25;

	@FXML
	private Pane pane15;

	@FXML
	private Pane pane05;

	@FXML
	private Pane pane06;

	@FXML
	private Pane pane16;

	@FXML
	private Pane pane26;

	@FXML
	private Pane pane36;

	@FXML
	private Pane pane46;

	@FXML
	private Pane pane56;

	@FXML
	private Pane pane66;

	@FXML
	private Pane pane76;

	@FXML
	private Pane pane07;

	@FXML
	private Text queenChess;

	@FXML
	private Pane pane17;

	@FXML
	private Pane pane27;

	@FXML
	private Pane pane37;

	@FXML
	private Pane pane47;

	@FXML
	private Pane pane57;

	@FXML
	private Pane pane67;

	@FXML
	private Pane pane77;

	@FXML
	private Pane gameRulesPane;

	@FXML
	private Button closeGameRulesBtn;

	@FXML
	private Button exitButton;

	@FXML
	private Button startBTN;

	@FXML
	private Text kingCHESS;







	@FXML
	void closeGameRulesOnAction(ActionEvent event) { // this function is to close the game rules scene
		gameRulesPane.setVisible(false);

	}

	@FXML
	void exitButton(ActionEvent event) { // this function is to let the user go back to home screen
		timelineLevel1.stop();
		timelineLevel1.getKeyFrames().clear();
		timeLineLevel2.stop();
		timeLineLevel2.getKeyFrames().clear();
		horseTimeLineLevel3.stop();
		horseTimeLineLevel3.getKeyFrames().clear();
		this.restartBoeard();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));

			Scene scene = new Scene(root);
			MainMenu.mainS.setScene(scene);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	@FXML
	void gameRulesBtnOnAction(ActionEvent event) {// this function is to let the 
		gameRulesPane.setVisible(true);
	}




	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub



		clicks = 1;
		playerNameLabel.setText(playName);// set the name of the player in the textField
		initPanes(); // helper method

		// creating a pane matrix to handle the game with
		panesMat[0][0] = pane00;
		panesMat[0][1] = pane10;
		panesMat[0][2] = pane20;
		panesMat[0][3] = pane30;
		panesMat[0][4] = pane40;
		panesMat[0][5] = pane50;
		panesMat[0][6] = pane60;
		panesMat[0][7] = pane70;

		panesMat[1][0] = pane01;
		panesMat[1][1] = pane11;
		panesMat[1][2] = pane21;
		panesMat[1][3] = pane31;
		panesMat[1][4] = pane41;
		panesMat[1][5] = pane51;
		panesMat[1][6] = pane61;
		panesMat[1][7] = pane71;

		panesMat[2][0] = pane02;
		panesMat[2][1] = pane12;
		panesMat[2][2] = pane22;
		panesMat[2][3] = pane32;
		panesMat[2][4] = pane42;
		panesMat[2][5] = pane52;
		panesMat[2][6] = pane62;
		panesMat[2][7] = pane72;

		panesMat[3][0] = pane03;
		panesMat[3][1] = pane13;
		panesMat[3][2] = pane23;
		panesMat[3][3] = pane33;
		panesMat[3][4] = pane43;
		panesMat[3][5] = pane53;
		panesMat[3][6] = pane63;
		panesMat[3][7] = pane73;

		panesMat[4][0] = pane04;
		panesMat[4][1] = pane14;
		panesMat[4][2] = pane24;
		panesMat[4][3] = pane34;
		panesMat[4][4] = pane44;
		panesMat[4][5] = pane54;
		panesMat[4][6] = pane64;
		panesMat[4][7] = pane74;

		panesMat[5][0] = pane05;
		panesMat[5][1] = pane15;
		panesMat[5][2] = pane25;
		panesMat[5][3] = pane35;
		panesMat[5][4] = pane45;
		panesMat[5][5] = pane55;
		panesMat[5][6] = pane65;
		panesMat[5][7] = pane75;

		panesMat[6][0] = pane06;
		panesMat[6][1] = pane16;
		panesMat[6][2] = pane26;
		panesMat[6][3] = pane36;
		panesMat[6][4] = pane46;
		panesMat[6][5] = pane56;
		panesMat[6][6] = pane66;
		panesMat[6][7] = pane76;

		panesMat[7][0] = pane07;
		panesMat[7][1] = pane17;
		panesMat[7][2] = pane27;
		panesMat[7][3] = pane37;
		panesMat[7][4] = pane47;
		panesMat[7][5] = pane57;
		panesMat[7][6] = pane67;
		panesMat[7][7] = pane77;

		whoPlays = "horse";
		points = 0;
		this.scoreText.setText(Integer.toString(points));
		twoSteps.clear();
		tempList.clear();
		questionPanes.clear();
		panesPanes.addAll(panes);
		queenPanes.clear();
		tempPanes.clear();
		visitedPanes.clear();
		twoSteps.clear();
		questionColor.clear();
		lastUsedQuesPane = null;
		panesPanes.clear();
		panesPanes.addAll(panes);
		pointsToRestore.clear();
		pointsToRestore.clear();
		forgotPanesNow.clear();
		blockedPanes.clear();
		panesPanes.addAll(panes);
		visitedPanes.clear();
		col = 0;
		row =0;
		restartBoeard();
	}

	private void initPanes() {// for the game using it save all the panes in arraylist
		// TODO Auto-generated method stub
		panes.add(pane00);
		panes.add(pane10);
		panes.add(pane20);
		panes.add(pane30);
		panes.add(pane40);
		panes.add(pane50);
		panes.add(pane60);
		panes.add(pane70);
		panes.add(pane01);
		panes.add(pane11);
		panes.add(pane12);
		panes.add(pane13);
		panes.add(pane14);
		panes.add(pane15);
		panes.add(pane16);
		panes.add(pane17);
		panes.add(pane20);
		panes.add(pane21);
		panes.add(pane22);
		panes.add(pane23);
		panes.add(pane24);
		panes.add(pane25);
		panes.add(pane26);
		panes.add(pane27);
		panes.add(pane30);
		panes.add(pane31);
		panes.add(pane32);
		panes.add(pane33);
		panes.add(pane34);
		panes.add(pane35);
		panes.add(pane36);
		panes.add(pane37);
		panes.add(pane40);
		panes.add(pane41);
		panes.add(pane42);
		panes.add(pane43);
		panes.add(pane44);
		panes.add(pane45);
		panes.add(pane46);
		panes.add(pane47);
		panes.add(pane50);
		panes.add(pane51);
		panes.add(pane52);
		panes.add(pane53);
		panes.add(pane54);
		panes.add(pane55);
		panes.add(pane56);
		panes.add(pane57);
		panes.add(pane27);
		panes.add(pane60);
		panes.add(pane61);
		panes.add(pane62);
		panes.add(pane63);//s
		panes.add(pane64);
		panes.add(pane65);
		panes.add(pane66);
		panes.add(pane67);
		panes.add(pane27);
		panes.add(pane70);
		panes.add(pane71);
		panes.add(pane72);
		panes.add(pane73);
		panes.add(pane74);
		panes.add(pane75);
		panes.add(pane76);
		panes.add(pane77);

	}








	static Timeline timelineLevel1 = new Timeline(); // static timeline for level1 to handle the game 
	static boolean isDone = false;
	void level1() {
		timeLeft = 60;
		visitedPanes.add(pane00);
		levelText.setText("1");



		// we choose three random panes to make them the random jum panes
		Collections.shuffle(panesPanes); 
		//System.out.println(panesPanes);
		// Get the first 3 elements as a sublist
		panesPanes.remove(pane00);
		List<Pane>forgotPnaes =  new ArrayList<>();
		forgotPnaes.addAll(panesPanes.subList(0, 3));
		forgotPnaes.add(pane21);
		panesPanes.removeAll(forgotPnaes);
		Collections.shuffle(panesPanes);
		//=======================
		/***

		Here i used a arrayList too handle the question panes i used arrayList "t" becuase i dont want
		the the random jum pane be a question pane

		 ***/
		ArrayList<Pane> t = new ArrayList<>();
		t.addAll(panesPanes.subList(0, 3));
		panesPanes.removeAll(t);
		questionPanes.addAll(t);

		panesPanes.removeAll(visitedPanes);
		questionPanes.get(0).setStyle("-fx-background-color: #" + "f3f3f3");
		questionPanes.get(1).setStyle("-fx-background-color: #" + "e3f301");
		questionPanes.get(2).setStyle("-fx-background-color: #" + "be0000");


		// i also used hashMap to check the color of the pane and then when the horse on a pane then
		// i will get the value of the pane and add question to it depends on his color.
		questionColor.put(questionPanes.get(0), "white");
		questionColor.put(questionPanes.get(1), "yellow");
		questionColor.put(questionPanes.get(2), "red");


		// est3ml panesPanes 3shan tzed kmn so2al b3d ma el 7san w2f 3la kman so2al!!


		KeyFrame keyF = new KeyFrame(Duration.seconds(1), e->{ //here we handle the game

			tempList.addAll(forgotPnaes); // the random jump panes saved in arraylist
			forgotPnaes.clear();
			timer.setText(Integer.toString(timeLeft--));
			visitedPanes.add((Pane) this.horseCHESS.getParent());
			this.horseCHESS.getParent().setStyle("-fx-background-color: #" + "818181"); // change the color of the pane that the horse on it
			Pane tempPane = (Pane)this.horseCHESS.getParent();

			/***
			 This helps us to know who should play now i.e. whose turn now
			 assume that the horse was on pane00 and he moved to pane01 so the hashset size will be 2 
			 and this tell us that the queen now should play
			 ***/

				
						if(twoSteps.size() >= 2 && window.isShowing() == false) { 
							twoSteps.clear();
							whoPlays = "queen";
						}
			// =============================



			// here i get the horse current place by the pane id
			Integer row = 0;
			Integer col =0;

			String id = tempPane.getId();
			twoSteps.add(id);
			char coll = id.charAt(4); // 3amod
			char roww = id.charAt(5); // sater
			twoSteps.add(id);

			col = Character.getNumericValue(coll);
			row = Character.getNumericValue(roww);

			// ===========================
			panesPanes.removeAll(visitedPanes);

			if(whoPlays == "horse") { // the horse turn 

				if(tempPanes.size() > 0) { // This makes sure that the horse cannot go anywhere on the board
					stopPaneWord(tempPanes);
					tempPanes.clear();
				}



				/***
				 here i get all the possible moves of the horse
				 ***/
				ArrayList<Point> p = new ArrayList<>();

				p.addAll(this.player.possibleMovesLevel1(row, col));

				for(Point p1: p) {
					tempPanes.add(this.panesMat[p1.getRow()][p1.getColumn()]);
				}
				// ==========================

				letPaneWork(tempPanes); // it's a funtion that let the user click on the panes that are from the possible moves of the horse


				// here in the 3 if's i check if the horse on a question pane so if yes a display a relevt question for him
				if(questionColor.containsKey(this.horseCHESS.getParent())) {
					if(questionColor.get(this.horseCHESS.getParent()).equals("white")) {
						this.alertUser("you were on a easy pane question");
						questionColor.put((Pane)this.horseCHESS.getParent(), "Null");
						questionPanes.add((Pane)this.horseCHESS.getParent());
						if(window.isShowing() == false) {
							displayQuestion("white");
							lastUsedQuesPane = (Pane) this.horseCHESS.getParent();
							displayNewHardPane("easy");
						}
					}

					if(questionColor.get(this.horseCHESS.getParent()).equals("yellow")) {
						this.alertUser("you were on a medium pane question");
						questionColor.put((Pane)this.horseCHESS.getParent(), "Null");
						questionPanes.add((Pane)this.horseCHESS.getParent());
						if(window.isShowing() == false) {
							displayQuestion("yellow");
							lastUsedQuesPane = (Pane) this.horseCHESS.getParent();
							displayNewHardPane("medium");
						}

					}

					if(questionColor.get(this.horseCHESS.getParent()).equals("red")) {
						this.alertUser("you were on a hard pane question");
						questionColor.put((Pane)this.horseCHESS.getParent(), "Null");
						questionPanes.add((Pane)this.horseCHESS.getParent());
						if(window.isShowing() == false) {
							displayQuestion("red");		
							lastUsedQuesPane = (Pane) this.horseCHESS.getParent();
							displayNewHardPane("hard");
						}

					}
				}

				if(this.horseCHESS.getParent().equals(this.queenChess.getParent())) { // here if the queen and the horse on the same panes
					pointsPerLevel[0] = points;
					stopPaneWord(tempPanes);// here we stop the user from pressing on the boeard
					timelineLevel1.stop();
					Feedback.message("Game Over", "You lost the game, try again later");
					timelineLevel1.pause();
					whoPlays="horse";
					this.restartBoeard();

					Game g = new Game(playerNameLabel.getText(),1,LocalDate.now(),false,points,0,0,0);
					try {
						SysData.getInstance().writeToJsonGames(g);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					restartBoeard();
					try {
						Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
						Scene scene = new Scene(root);
						MainMenu.mainS.setScene(scene);


					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();

					}

				}



				if(tempList.contains(this.horseCHESS.getParent())) { // here i check if the horse on random jump pane if yes
					// i choose a random pane and move the horse to it
					this.alertUser("your were on random jump pane");
					whoPlays = "horse";
					Pane par = (Pane) this.horseCHESS.getParent();
					Pane paneSelected = randomPane(tempList);

					Collections.shuffle(panesPanes);
					tempList.add(panesPanes.get(0));
					if(paneSelected != null) {
						paneSelected.getChildren().add(this.horseCHESS);
						twoSteps.add(paneSelected.getId());

					}
					tempList.remove(par);
					this.alertMan.setText("");

				}

			}


			else if(whoPlays == "queen") { // here we handle the queen moves
				if(queenPanes.size() > 0) {
					queenPanes.clear();
				}
				twoSteps.clear();
				if(tempPanes.size() > 0) { // this make sure that the user can move the horse while the queen plays
					stopPaneWord(tempPanes);
					tempPanes.clear();
				}
				Pane queenPane = (Pane)this.queenChess.getParent(); // getting the queen pane that she on it



				// the same as the horses
				int rowQ = 0;
				int colQ =0;

				String idQ = queenPane.getId();
				char collQ = idQ.charAt(4); // 3amod
				char rowwQ = idQ.charAt(5); // sater


				colQ = Character.getNumericValue(collQ);
				rowQ = Character.getNumericValue(rowwQ);


				// here in the 2 for loops we get the all possible moves of the queen
				for(int relevRow = 0; relevRow < 8; relevRow++) {

					for(int releveColumn = 0 ; releveColumn < 8; releveColumn++) {
						if (relevRow == rowQ || colQ == releveColumn || 
								Math.abs(relevRow - rowQ) == Math.abs(releveColumn - colQ)) {
							queenPanes.add(this.panesMat[relevRow][releveColumn]);
						}
					}
				}

				Pane p = getCloseToHorse(queenPanes, tempPane); // i built a function to find the pane that is the closest to the horse

				if(p.getChildren().contains(this.queenChess) == false) { // moving the queen to the closest pane to the horse
					p.getChildren().add(this.queenChess);
				}

				whoPlays = "horse";// now it's the horse turn

			}			
		});
		timelineLevel1.getKeyFrames().add(keyF);
		timelineLevel1.setCycleCount(60);
		timelineLevel1.play();
		timelineLevel1.setOnFinished(ev->{
			Feedback2.message("Level 1 Done", "you finished level 1 with "+ points +" points");
			timelineLevel1.stop();

			if(points >= 15) { // if the player win so the game will freeze and level 2 will start after 5 seconds
				pointsPerLevel[0] = points;
				this.restartBoeard();
				Feedback2.message("Warning", "level 2 will start after 5 seconds please close the window and wait");
				KeyFrame k = new KeyFrame(Duration.seconds(1), e->{

				});
				Timeline ttt = new Timeline();
				ttt.setCycleCount(5);
				ttt.getKeyFrames().add(k);
				ttt.play();
				ttt.setOnFinished(e->{
					this.alertUser("level 2 started");
					level2();
				});

			}else if(points < 15){
				pointsPerLevel[0] = points;
				stopPaneWord(tempPanes);
				timelineLevel1.stop();
				Feedback.message("Game Over", "You lost the game, try again later");
				timelineLevel1.pause();
				whoPlays="horse";
				this.restartBoeard();
				Game g = new Game(playerNameLabel.getText(),1,LocalDate.now(),false,points,0,0,0);
				try {
					SysData.getInstance().writeToJsonGames(g);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				restartBoeard();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
					Scene scene = new Scene(root);
					MainMenu.mainS.setScene(scene);


				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();

				}

			}

		});



	}

	void alertUser(String message) { // this function is used to alert the user about special things that happened in his game

		KeyFrame k = new KeyFrame(Duration.seconds(0.70), e->{
			this.alertMan.setText(message);
		});

		Timeline temp = new Timeline();
		temp.setCycleCount(5);
		temp.getKeyFrames().add(k);
		temp.play();
		temp.setOnFinished(e->{
			this.alertMan.setText("");
		});
	}


	/***
	 * 
	 * @param diff: the question diffculty
	 * the function display a new question on the board  with respect to the diff parameter
	 */
	void displayNewHardPane(String diff) { 
		panesPanes.removeAll(questionPanes);
		panesPanes.remove(lastUsedQuesPane);

		Collections.shuffle(panesPanes);

		Pane p = panesPanes.get(0);

		if(diff.equals("hard")) {
			panesPanes.addAll(questionPanes);

			questionColor.put(p, "red");

			questionPanes.add(p);

			p.setStyle("-fx-background-color: #" + "be0000");
		}
		else if (diff.equals("medium")) {
			panesPanes.addAll(questionPanes);

			questionColor.put(p, "yellow");

			questionPanes.add(p);

			p.setStyle("-fx-background-color: #" + "e3f301");
		}
		else if (diff.equals("easy")) {
			panesPanes.addAll(questionPanes);

			questionColor.put(p, "white");

			questionPanes.add(p);

			p.setStyle("-fx-background-color: #" + "f3f3f3");
		}
	}






	double CalcDistance(Point p1, Point p2) { // get two points and return the distance between them 

		double distance = Math.sqrt(Math.pow(p1.getRow() - p2.getRow(), 2) 
				+ Math.pow(p1.getColumn() - p2.getColumn(), 2));

		return distance;
	}


	Pane randomPane(List<Pane> forgotPanes) { // display a new random pane on the board

		Collections.shuffle(panes);
		if(forgotPanes.contains(panes.get(0)) == false) {
			return panes.get(0);
		}
		return null;
	}



	/***
	 * 
	 * @param queenPanes: the queen possible moves 
	 * @param HorsePane: the horse current place
	 * @return the closest pane between the queen/king to the horse
	 */
	Pane getCloseToHorse(HashSet<Pane>queenPanes, Pane HorsePane) {
		HashMap<Pane, Double> distance = new HashMap<>();
		int horseRow = Character.getNumericValue(HorsePane.getId().charAt(5));
		int horseColu =  Character.getNumericValue(HorsePane.getId().charAt(4));

		Point pH = new Point(horseRow, horseColu);
		for(Pane queenP : queenPanes) {

			int queenRow = Character.getNumericValue(queenP.getId().charAt(5));
			int queenColu = Character.getNumericValue(queenP.getId().charAt(4));

			Point pQ = new Point (queenRow, queenColu);

			distance.put(queenP, CalcDistance(pH, pQ));
		}

		HashMap<Pane, Double> temp = sortByValue(distance);

		Set<Pane> keys = temp.keySet();
		Pane firstKey = keys.iterator().next();

		return firstKey;

	}

	/***
	 * 
	 * @param map: a hashmap that contains the possible moves of the king/queen and
	 * @return: a sorted hashmap by it's values (the distance between the object (king,queen) to the horse)
	 */
	private HashMap<Pane, Double> sortByValue(HashMap<Pane, Double>map){ 
		List<Map.Entry<Pane, Double>> list = new ArrayList<>(map.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<Pane, Double>>() {
			@Override
			public int compare(Map.Entry<Pane, Double> o1, Map.Entry<Pane, Double> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		HashMap<Pane, Double> sortedMap = new LinkedHashMap<>();
		for (Entry<Pane, Double> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}



	static Timeline timeLineLevel2 = new Timeline();
	void level2() {// level 2 function


		timeLeft = 60; // the timeLeft if for the timer
		Timeline timeline = new Timeline(); 
		visitedPanes.add(pane00); // adding the horse initial pane to the visited panes
		levelText.setText("2");

		// here we take a initial 3 random panes to be a question panes
		Collections.shuffle(panesPanes);
		ArrayList<Pane> t = new ArrayList<>();
		t.addAll(panesPanes.subList(0, 3));
		panesPanes.removeAll(t);

		questionPanes.addAll(t);

		panesPanes.removeAll(visitedPanes);
		questionPanes.get(0).setStyle("-fx-background-color: #" + "f3f3f3");
		questionPanes.get(1).setStyle("-fx-background-color: #" + "e3f301");
		questionPanes.get(2).setStyle("-fx-background-color: #" + "be0000");


		// i also used hashMap to check the color of the pane and then when the horse on a pane then
		// i will get the value of the pane and add question to it depends on his color.
		questionColor.put(questionPanes.get(0), "white");
		questionColor.put(questionPanes.get(1), "yellow");
		questionColor.put(questionPanes.get(2), "red");
		//==========================================================

		getForgotPanes();

		KeyFrame game = new KeyFrame(Duration.seconds(1),e->{ // here where the game handled
			timer.setText(Integer.toString(timeLeft--));
			visitedPanes.add((Pane) this.horseCHESS.getParent());
			this.horseCHESS.getParent().setStyle("-fx-background-color: #" + "818181"); // change the color of the pane that the horse on it
			Pane tempPane = (Pane)this.horseCHESS.getParent();


			if(tempPanes.size() > 0) { // to make sure that there is no extra panes for the player to press on
				stopPaneWord(tempPanes);
				tempPanes.clear();
			}

			if(twoSteps.size()>=2 && window.isShowing() == false) {
				tempPanes.clear();
				whoPlays = "queen";
			}

			// getting the col and row of the horse
			String id = tempPane.getId();
			twoSteps.add(id);
			char coll = id.charAt(4); 
			char roww = id.charAt(5); 
			twoSteps.add(id);


			col = Character.getNumericValue(coll);
			row = Character.getNumericValue(roww);

			System.out.println("forgotPanes= "+  forgotPanesNow);

			System.out.println("row= "+ row);
			System.out.println("col= "+ col);
			System.out.println();
			checkQues();// displaying question on the boeard

			if(this.horseCHESS.getParent().equals(this.queenChess.getParent())) { // here if the queen and the horse on the same panes
				pointsPerLevel[1] = points;
				stopPaneWord(tempPanes);// stop the user from playing on the boeard 
				timeLineLevel2.stop();// finish the game
				Feedback.message("Game Over", "You lost the game, try again later");
				timeLineLevel2.pause();
				whoPlays="horse";


				// go to the mainMenu scene
				Game g = new Game(playerNameLabel.getText(),1,LocalDate.now(),false,pointsPerLevel[0],points,0,0);
				try {
					SysData.getInstance().writeToJsonGames(g);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				restartBoeard();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
					Scene scene = new Scene(root);
					MainMenu.mainS.setScene(scene);


				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();

				}
				this.restartBoeard();

			}

			/***
			 * here we used a used a two arraylists that helped me to delete the 3 last steps of the player
			 * i multiply the point he gain by -1 
			 */
			if((forgotPanesNow.contains((Pane)this.horseCHESS.getParent())) && (questionColor.get((Pane)this.horseCHESS.getParent()) == "Null" || !questionColor.containsKey((Pane)this.horseCHESS.getParent()))){

				this.alertUser("you were on forgot pane that forgot your three last steps"); // alert to the user that he is on  forget pane
				forgotPanesNow.remove((Pane)this.horseCHESS.getParent()); // remove the current forget pane to add  a new one
				Collections.shuffle(panesPanes);// shuffle that panesPanes arrayList to get a random pane s
				forgotPanesNow.add(panesPanes.get(0));//here we got the random pane

				if(visitedPanes.size() >= 3 && pointsToRestore.size() >= 3) { // if the player has more than 3 stes so we forget them and delete them and delete the last three panes from the visitedPanes 
					int x1 = (-1) * pointsToRestore.get(pointsToRestore.size() - 1);
					int x2 =  (-1) * pointsToRestore.get(pointsToRestore.size() - 2);
					int x3 =  (-1) * pointsToRestore.get(pointsToRestore.size() - 3);

					points = points + x1 + x2 + x3;
					this.scoreText.setText(Integer.toString(points));
					if(pointsToRestore.size() > 0) {
						pointsToRestore.remove(pointsToRestore.size() - 1);
					}
					if(pointsToRestore.size() > 0) {
						pointsToRestore.remove(pointsToRestore.size() - 1);
					}
					if(pointsToRestore.size() > 0) {
						pointsToRestore.remove(pointsToRestore.size() - 1);
					}

					ArrayList<Pane> temp = new ArrayList<>();

					temp.addAll(visitedPanes);

					Pane p1 = temp.get(temp.size()-1);
					Pane p2 = temp.get(temp.size()-2);
					Pane p3 = temp.get(temp.size()-3);

					temp.remove(p1); 
					temp.remove(p2); 
					temp.remove(p3);

					restoreColor(p1);
					restoreColor(p2);
					restoreColor(p3);
				}
			}

			if(whoPlays == "horse") { // horse playing
				HorseTurnLevel2();
			}

			if(whoPlays == "queen") {//queen playing
				queenTurnLevel2();
			}

		});

		timeLineLevel2.getKeyFrames().add(game);
		timeLineLevel2.setCycleCount(60);
		timeLineLevel2.play();
		timeLineLevel2.setOnFinished(ev->{
			Feedback.message("Level 1 Done", "you finished level 1 with "+ points +" points");
			timeline.stop();
			//the same as other levels
			if(points >= 15) {
				pointsPerLevel[1] = points;
				this.restartBoeard();
				Feedback2.message("Warning", "level 3 will start after 5 seconds please close the window and wait");
				KeyFrame k = new KeyFrame(Duration.seconds(1), e->{

				});
				Timeline ttt = new Timeline();
				ttt.setCycleCount(5);
				ttt.getKeyFrames().add(k);
				ttt.play();
				ttt.setOnFinished(e->{
					this.alertUser("level 3 started");
					level3();
				});
			}

			if(points < 15) {
				pointsPerLevel[1] = points;
				stopPaneWord(tempPanes);
				timeLineLevel2.stop();
				Feedback.message("Game Over", "You lost the game, try again later");
				timeLineLevel2.pause();
				whoPlays="horse";

				Game g = new Game(playerNameLabel.getText(),1,LocalDate.now(),false,pointsPerLevel[0],points,0,0);
				try {
					SysData.getInstance().writeToJsonGames(g);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				restartBoeard();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
					Scene scene = new Scene(root);
					MainMenu.mainS.setScene(scene);


				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();

				}
				this.restartBoeard();

			}
		});

	}

	void restoreColor(Pane p) {// when the player on forgot pane so we change restore the last three panes color from 
		// grey to the original color

		visitedPanes.remove(p);

		int row =Character.getNumericValue(p.getId().charAt(5));
		int col = Character.getNumericValue(p.getId().charAt(4));

		if(row % 2 == 0) {
			if(col % 2 == 0) {
				panesMat[row][col].setStyle("-fx-background-color: #" + "EAD1A8");
			}else {
				panesMat[row][col].setStyle("-fx-background-color: #" + "D19556");

			}
		}else if (row % 2 != 0) {

			if(col % 2 == 0) {
				panesMat[row][col].setStyle("-fx-background-color: #" + "D19556");

			}else {
				panesMat[row][col].setStyle("-fx-background-color: #" + "EAD1A8");

			}
		}

	}


	void getForgotPanes() {// here we get a three forgot panes to put them on the board
		Collections.shuffle(panesPanes);

		Pane p1 = panesPanes.get(0);
		Pane p2 = panesPanes.get(1);
		Pane p3 = panesPanes.get(2);

		panesPanes.remove(0);
		panesPanes.remove(1);
		panesPanes.remove(2);

		forgotPanesNow.add(p1);
		forgotPanesNow.add(p2);
		forgotPanesNow.add(p3);
	}

	void queenTurnLevel2(){// how the queen moves the same as level 1 
		if(queenPanes.size() > 0) {
			queenPanes.clear();
		}
		twoSteps.clear();
		if(tempPanes.size() > 0) { // this make sure that the user can move the horse while the queen plays
			stopPaneWord(tempPanes);
			tempPanes.clear();
		}
		Pane queenPane = (Pane)this.queenChess.getParent(); // getting the queen pane that she on it



		// the same as the horses
		int rowQ = 0;
		int colQ =0;

		String idQ = queenPane.getId();
		char collQ = idQ.charAt(4); // 3amod
		char rowwQ = idQ.charAt(5); // sater

		Pane tempPane = (Pane)this.horseCHESS.getParent();

		colQ = Character.getNumericValue(collQ);
		rowQ = Character.getNumericValue(rowwQ);




		ArrayList<Point> points = new ArrayList<>();

		points.addAll(this.queen.getQueenPossibleMoves(colQ, rowQ));

		for(Point po : points) {
			queenPanes.add(this.panesMat[po.getRow()][po.getColumn()]);
		}



		Pane p = getCloseToHorse(queenPanes, tempPane); // i built a function to find the pane that is the closest to the horse


		if(p.getChildren().contains(this.queenChess) == false) { // moving the queen to the closest pane to the horse
			p.getChildren().add(this.queenChess);
		}

		whoPlays = "horse";// now it's the horse turn

	}


	void HorseTurnLevel2() {
		if(tempPanes.size() > 0 ) {// to remoev the last turn player moves
			this.stopPaneWord(tempPanes);
			tempPanes.clear();
		}


		ArrayList<Point> p = new ArrayList<>(); // arrayList to save the player possible moves


		p.addAll(this.player.possibleMovesLevel2up(col, row)); //Getting the horse possible moves from the player object

		for(Point po : p) {
			tempPanes.add(this.panesMat[po.getRow()][po.getColumn()]);
		}

		if(isLevel4 == true) {
			tempPanes.removeAll(blockedPanes);
		}
		letPaneWork(tempPanes);



	}






	static Timeline k1 = new Timeline();
	static Timeline horseTimeLineLevel3 = new Timeline();

	void level3() { // level 3 game 
		timeLeft = 60;
		this.kingCHESS.setOpacity(1); // display the king on the beard
		this.queenChess.setOpacity(0); // undisplay the queen on the boeard
		this.levelText.setText("3");


		/***
		 * gettting three initial random panes for the questions
		 */
		panesPanes.remove(pane00);
		Collections.shuffle(panesPanes);
		ArrayList<Pane> t = new ArrayList<>();
		t.addAll(panesPanes.subList(0, 3));
		panesPanes.removeAll(t);

		questionPanes.addAll(t);

		panesPanes.removeAll(visitedPanes);
		questionPanes.get(0).setStyle("-fx-background-color: #" + "f3f3f3");
		questionPanes.get(1).setStyle("-fx-background-color: #" + "e3f301");
		questionPanes.get(2).setStyle("-fx-background-color: #" + "be0000");

		// i also used hashMap to check the color of the pane and then when the horse on a pane then
		// i will get the value of the pane and add question to it depends on his color.
		questionColor.put(questionPanes.get(0), "white");
		questionColor.put(questionPanes.get(1), "yellow");
		questionColor.put(questionPanes.get(2), "red");

		// getting 2 random random jump panes and 2 random forgot panes

		Collections.shuffle(panesPanes);

		Pane p1 = panesPanes.get(0);
		Pane p2 = panesPanes.get(1);

		Pane p3 = panesPanes.get(3);
		Pane p4 = panesPanes.get(4);

		tempList.add(p1);
		tempList.add(p2);

		forgotPanesNow.add(p3);
		forgotPanesNow.add(p4);
		forgotPanesNow.add(pane21);

		KeyFrame horseKey = new KeyFrame(Duration.seconds(1), e->{ // here we handle the game

			this.horseCHESS.getParent().setStyle("-fx-background-color: #" + "818181");

			if(tempList.contains((Pane)this.horseCHESS.getParent())) {// if the horse on a random jump ane do the same as level2
				whoPlays = "horse";
				Pane par = (Pane) this.horseCHESS.getParent();
				Pane paneSelected = randomPane(tempList);

				Collections.shuffle(panesPanes);
				tempList.add(panesPanes.get(0));
				if(paneSelected != null) {
					paneSelected.getChildren().add(this.horseCHESS);
					twoSteps.add(paneSelected.getId());

				}
				tempList.remove(par);

			}

			// the same as other levels here we handle if the player on a forgot pane 
			if((forgotPanesNow.contains((Pane)this.horseCHESS.getParent())) && (questionColor.get((Pane)this.horseCHESS.getParent()) == "Null" || !questionColor.containsKey((Pane)this.horseCHESS.getParent()))){
				this.alertUser("you were on forgot pane so your last 3 steps will be deleted");
				forgotPanesNow.remove((Pane)this.horseCHESS.getParent());
				Collections.shuffle(panesPanes);
				forgotPanesNow.add(panesPanes.get(0));

				if(visitedPanes.size() >= 3 && pointsToRestore.size() >= 3) {
					int x1 = (-1) * pointsToRestore.get(pointsToRestore.size() - 1);
					int x2 =  (-1) * pointsToRestore.get(pointsToRestore.size() - 2);
					int x3 =  (-1) * pointsToRestore.get(pointsToRestore.size() - 3);

					points = points + x1 + x2 + x3;
					this.scoreText.setText(Integer.toString(points));
					if(pointsToRestore.size() > 0) {
						pointsToRestore.remove(pointsToRestore.size() - 1);
					}
					if(pointsToRestore.size() > 0) {
						pointsToRestore.remove(pointsToRestore.size() - 1);
					}
					if(pointsToRestore.size() > 0) {
						pointsToRestore.remove(pointsToRestore.size() - 1);
					}

					ArrayList<Pane> temp = new ArrayList<>();

					temp.addAll(visitedPanes);

					Pane p11 = temp.get(temp.size()-1);
					Pane p22 = temp.get(temp.size()-2);
					Pane p33 = temp.get(temp.size()-3);

					temp.remove(p11); 
					temp.remove(p22); 
					temp.remove(p33);

					restoreColor(p11);
					restoreColor(p22);
					restoreColor(p33);
				}
			}



			if(this.horseCHESS.getParent().equals(this.kingCHESS.getParent())) { // if the player lose
				/***
				 * we sto the game for the horse and we stop the king from moving too and then we go to the home screen 
				 * and restart everythinh
				 */
				pointsPerLevel[3] = points;
				stopPaneWord(tempPanes);
				horseTimeLineLevel3.stop();
				Feedback.message("Game Over", "You lost the game, try again later");
				horseTimeLineLevel3.pause();
				k1.stop();
				whoPlays="horse";
				Game g = new Game(playerNameLabel.getText(),1,LocalDate.now(),false,pointsPerLevel[0],pointsPerLevel[1],points,0);
				try {
					SysData.getInstance().writeToJsonGames(g);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				restartBoeard();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
					Scene scene = new Scene(root);
					MainMenu.mainS.setScene(scene);

				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();

				}
				this.restartBoeard();
			}

			timeLeft--;
			timer.setText(Integer.toString(timeLeft));

			if(timeLeft == 50 || timeLeft == 40 || timeLeft == 30 || timeLeft == 20 || timeLeft == 10) {// increase the king speed
				k1.setRate(k1.getRate() + 0.000005);
			}
			visitedPanes.add((Pane) this.horseCHESS.getParent());// to paint the pane that the horse visited already
			this.horseCHESS.getParent().setStyle("-fx-background-color: #" + "818181");


			if(tempPanes.size() > 0 ) {// making sure that the player cant press everywhere on the boeard
				this.stopPaneWord(tempPanes);
				tempPanes.clear();
			}


			/***
			 * getting the horse row and col
			 */
			String id = this.horseCHESS.getParent().getId();
			twoSteps.add(id);
			char coll = id.charAt(4); // 3amod
			char roww = id.charAt(5); // sater
			twoSteps.add(id);


			col = Character.getNumericValue(coll);
			row = Character.getNumericValue(roww);

			HorseTurnLevel2();// the horse function to provide the possible moves to the player
			checkQues();// handle the questions and add new pane question if the horse on a question pane

		});
		horseTimeLineLevel3.setCycleCount(60);
		horseTimeLineLevel3.getKeyFrames().add(horseKey);
		horseTimeLineLevel3.play();
		horseTimeLineLevel3.setOnFinished(e->{

			// the same as before
			if(points >= 15) {
				pointsPerLevel[1] = points;
				this.restartBoeard();
				Feedback2.message("Warning", "level 4 will start after 5 seconds please close the window and wait");
				KeyFrame k = new KeyFrame(Duration.seconds(1), ev->{

				});
				Timeline ttt = new Timeline();
				ttt.setCycleCount(5);
				ttt.getKeyFrames().add(k);
				ttt.play();
				ttt.setOnFinished(ev->{
					this.alertUser("level 4 started");
					level4();
				});
			}

			if(points < 15) {
				pointsPerLevel[3] = points;
				stopPaneWord(tempPanes);
				horseTimeLineLevel3.stop();
				Feedback.message("Game Over", "You lost the game, try again later");
				horseTimeLineLevel3.pause();
				k1.stop();
				whoPlays="horse";

				Game g = new Game(playerNameLabel.getText(),1,LocalDate.now(),false,pointsPerLevel[0],pointsPerLevel[1],points,0);
				try {
					SysData.getInstance().writeToJsonGames(g);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				restartBoeard();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
					Scene scene = new Scene(root);
					MainMenu.mainS.setScene(scene);

				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();

				}
				this.restartBoeard();
			}
		});


		KeyFrame kingF1 = new KeyFrame(Duration.seconds(1), e->{

			if(window.isShowing() == false) {
				kingMoves(); // get the moves of the king
			}

		});

		// the king timeline 
		k1.setCycleCount(Timeline.INDEFINITE);
		k1.getKeyFrames().add(kingF1);
		k1.play();



	}

	//=================================LEVEL 4========================================
	static Timeline horseLevel4TIme = new Timeline();
	static boolean isLevel4 = false;
	void level4() {
		k1.getKeyFrames().clear();
		this.kingCHESS.setOpacity(1);
		this.queenChess.setOpacity(0);
		this.levelText.setText("4");
		timeLeft = 60;
		isLevel4 = true;

		// here we choose a 8 blocked panes that the player and the king can't be on it 
		Collections.shuffle(panesPanes);
		panesPanes.remove(pane00);
		panesPanes.remove(pane70);
		blockedPanes.addAll(panesPanes.subList(0, 8));
		panesPanes.removeAll(blockedPanes);
		colorBlockedPanes();

		Collections.shuffle(panesPanes);
		ArrayList<Pane> t = new ArrayList<>();
		t.addAll(panesPanes.subList(0, 3));
		panesPanes.removeAll(t);

		questionPanes.addAll(t);

		panesPanes.removeAll(visitedPanes);
		questionPanes.get(0).setStyle("-fx-background-color: #" + "f3f3f3");
		questionPanes.get(1).setStyle("-fx-background-color: #" + "e3f301");
		questionPanes.get(2).setStyle("-fx-background-color: #" + "be0000");


		// i also used hashMap to check the color of the pane and then when the horse on a pane then
		// i will get the value of the pane and add question to it depends on his color.
		questionColor.put(questionPanes.get(0), "white");
		questionColor.put(questionPanes.get(1), "yellow");
		questionColor.put(questionPanes.get(2), "red");

		KeyFrame kingF4 = new KeyFrame(Duration.seconds(1), e->{ // same as before

			if(window.isShowing() == false) {
				kingMoves();
			}
		});


		KeyFrame horseLevel4 = new KeyFrame(Duration.seconds(1), e->{// the same as level3

			this.horseCHESS.getParent().setStyle("-fx-background-color: #" + "818181");
			timer.setText(Integer.toString(timeLeft--));
			String id = this.horseCHESS.getParent().getId();
			twoSteps.add(id);
			char coll = id.charAt(4); 
			char roww = id.charAt(5);
			twoSteps.add(id);
			visitedPanes.add(	(Pane)this.horseCHESS.getParent());

			col = Character.getNumericValue(coll);
			row = Character.getNumericValue(roww);
			if(this.horseCHESS.getParent().equals(this.kingCHESS.getParent())) {
				k1.stop();
				pointsPerLevel[3] = points;
				stopPaneWord(tempPanes);
				horseLevel4TIme.stop();
				Feedback.message("Game Over", "You lost the game, try again later");
				horseLevel4TIme.pause();
				whoPlays="horse";

				Game g = new Game(playerNameLabel.getText(),1,LocalDate.now(),false,pointsPerLevel[0],pointsPerLevel[1],pointsPerLevel[2],points);
				try {
					SysData.getInstance().writeToJsonGames(g);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				restartBoeard();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
					Scene scene = new Scene(root);
					MainMenu.mainS.setScene(scene);


				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();

				}
				this.restartBoeard();
			}

			HorseTurnLevel2();
			checkQues();

		});

		horseLevel4TIme.setOnFinished(e->{
			k1.stop();

			if(points < 15) {
				Feedback2.message("LOSER", "You lose the game!!!");

				Game g = new Game(playerNameLabel.getText(),1,LocalDate.now(),false,pointsPerLevel[0],pointsPerLevel[1],pointsPerLevel[2],points);
				try {
					SysData.getInstance().writeToJsonGames(g);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				restartBoeard();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
					Scene scene = new Scene(root);
					MainMenu.mainS.setScene(scene);


				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();

				}
				this.restartBoeard();
				return;
			}

			if(pointsPerLevel[0] +pointsPerLevel[1]+pointsPerLevel[2]+pointsPerLevel[3] > 200) {
				Feedback2.message("WINNER", "You win the game and you win a prise hero!!!!");
				Game g = new Game(playerNameLabel.getText(),1,LocalDate.now(),true,pointsPerLevel[0],pointsPerLevel[1],pointsPerLevel[2],points);
				try {
					SysData.getInstance().writeToJsonGames(g);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				restartBoeard();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
					Scene scene = new Scene(root);
					MainMenu.mainS.setScene(scene);


				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();

				}
				this.restartBoeard();
				return;
			}
			if(points >= 15) {
				Feedback2.message("WINNER", "You win the game!!!");

				Game g = new Game(playerNameLabel.getText(),1,LocalDate.now(),false,pointsPerLevel[0],pointsPerLevel[1],pointsPerLevel[2],points);
				try {
					SysData.getInstance().writeToJsonGames(g);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				restartBoeard();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
					Scene scene = new Scene(root);
					MainMenu.mainS.setScene(scene);


				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();

				}
				this.restartBoeard();
			}

		});
		horseLevel4TIme.setCycleCount(60);
		horseLevel4TIme.getKeyFrames().add(horseLevel4);
		horseLevel4TIme.play();

		k1.setCycleCount(Timeline.INDEFINITE);
		k1.getKeyFrames().add(kingF4);
		k1.play();


	}

	void colorBlockedPanes() { // function to give a color to the blocked panes
		for(Pane p : blockedPanes) {
			p.setStyle("-fx-background-color: #" + "880f0f");

		}
	}

	void kingMoves() { // function to provide the king possible moves using the King object
		// the same as the horses
		int rowK = 0;
		int colK =0;

		String idK = this.kingCHESS.getParent().getId();
		char collK = idK.charAt(4); // 3amod
		char rowwK = idK.charAt(5); // sater

		Pane tempPane = (Pane)this.horseCHESS.getParent();

		colK = Character.getNumericValue(collK);
		rowK = Character.getNumericValue(rowwK);

		kingPanes.clear();

		ArrayList<Point> points= new ArrayList<>();
		points.addAll(this.king.getPossibleKingMoves(rowK, colK));

		for(Point po : points) {
			kingPanes.add(this.panesMat[po.getRow()][po.getColumn()]);
		}

		if(isLevel4 == true) {
			kingPanes.removeAll(blockedPanes);
		}

		Pane p = getCloseToHorse(kingPanes, tempPane); // i built a function to find the pane that is the closest to the horse


		if(p.getChildren().contains(this.kingCHESS) == false) { // moving the king to the closest pane to the horse
			p.getChildren().add(this.kingCHESS);
		}
	}

	static int clicks = 1;

	/***
	 * 
	 * @param event: when the user clicks on the start button the game start from level 1
	 * @throws InterruptedException
	 */
	@FXML
	void startGameFunc(ActionEvent event) throws InterruptedException { 
		player = new Player(playName);
		king = new King();
		queen = new Queen();
		if(clicks <= 1) {
			level1();
		}
		clicks++;
	}


	/***
	 * 
	 * @param panes: ArrayList of panes of the player that this function will set on action this panes so the user 
	 * can click on them and action will happen
	 */
	void letPaneWork(ArrayList<Pane>panes) {
		for(Pane p : panes) {
			p.setOnMouseClicked((t)->{
				p.getChildren().add(this.horseCHESS);
				if(visitedPanes.contains(p)) {
					pointsToRestore.add(-1);
					points--;
				}
				else {
					points++;
					pointsToRestore.add(1);
				}
				this.scoreText.setText(Integer.toString(points));

			});

		}
	}

	/***
	 * 
	 * @param panes: this funtion get a arraylist of panes and set on action off so the user can not click on any pane i
	 * in this arraylist
	 */
	void stopPaneWord(ArrayList<Pane>panes) {
		for(Pane p : panes) {
			p.setOnMouseClicked((t)->{
			});

		}

	}

	void restartBoeard() { // restart the beard to it's initial state with respect to the level
		int counter = 0;
		for(int i = 0; i < 8; i++) {

			for(int j = 0; j < 8; j++) {

				if(i % 2 == 0) {
					if(j % 2 == 0) {
						panesMat[i][j].setStyle("-fx-background-color: #" + "EAD1A8");
					}else {
						panesMat[i][j].setStyle("-fx-background-color: #" + "D19556");
					}
				}
				else if(i % 2 != 0) {
					if(j % 2 == 0) {
						panesMat[i][j].setStyle("-fx-background-color: #" + "D19556");
					}
					else {
						panesMat[i][j].setStyle("-fx-background-color: #" + "EAD1A8");
					}
				}

			}
		}

		if(this.horseCHESS.getParent().equals(pane00) == false)
			pane00.getChildren().add(this.horseCHESS);

		if(this.queenChess.getParent().equals(pane70) == false)
			pane70.getChildren().add(this.queenChess);
		if(this.kingCHESS.getParent().equals(pane70) == false)
			pane70.getChildren().add(this.kingCHESS);


		visitedPanes.clear();

		timer.setText("60");

		whoPlays = "horse";

		points = 0;
		this.scoreText.setText(Integer.toString(points));
		twoSteps.clear();
		tempList.clear();
		questionPanes.clear();
		panesPanes.addAll(panes);
		queenPanes.clear();
		tempPanes.clear();
		visitedPanes.clear();
		twoSteps.clear();
		questionColor.clear();
		lastUsedQuesPane = null;
		panesPanes.clear();
		panesPanes.addAll(panes);
		pointsToRestore.clear();
		pointsToRestore.clear();
		forgotPanesNow.clear();
		blockedPanes.clear();
		timelineLevel1.stop();
		timelineLevel1.getKeyFrames().clear();
		timeLineLevel2.stop();
		timeLineLevel2.getKeyFrames().clear();
		horseTimeLineLevel3.stop();
		horseTimeLineLevel3.getKeyFrames().clear();
		row = 0;
		col = 0;


	}

	void checkQues() { // the same as level1
		if(questionColor.containsKey(this.horseCHESS.getParent())) {
			if(questionColor.get(this.horseCHESS.getParent()).equals("white")) {
				this.alertUser("you were on easy question pane");
				questionColor.put((Pane)this.horseCHESS.getParent(), "Null");
				questionPanes.add((Pane)this.horseCHESS.getParent());
				if(window.isShowing() == false) {
					displayQuestion("white");
					lastUsedQuesPane = (Pane) this.horseCHESS.getParent();
					displayNewHardPane("easy");
				}
				whoPlays = "horse";

			}
		}

		if(questionColor.containsKey(this.horseCHESS.getParent())) {
			if(questionColor.get(this.horseCHESS.getParent()).equals("yellow")) {
				this.alertUser("you were on medium question pane");
				questionColor.put((Pane)this.horseCHESS.getParent(), "Null");
				questionPanes.add((Pane)this.horseCHESS.getParent());
				if(window.isShowing() == false) {
					displayQuestion("yellow");
					lastUsedQuesPane = (Pane) this.horseCHESS.getParent();
					displayNewHardPane("medium");
				}
				whoPlays = "horse";

			}
		}

		if(questionColor.containsKey(this.horseCHESS.getParent())) {
			if(questionColor.get(this.horseCHESS.getParent()).equals("red")) {
				this.alertUser("you were on hard question pane");
				questionColor.put((Pane)this.horseCHESS.getParent(), "Null");
				questionPanes.add((Pane)this.horseCHESS.getParent());
				if(window.isShowing() == false) {
					displayQuestion("red");		
					lastUsedQuesPane = (Pane) this.horseCHESS.getParent();
					displayNewHardPane("hard");
				}

				whoPlays = "horse";


			}
		}//s
	}


	static Stage window = new Stage();
	static ArrayList<Question> usedQues = new ArrayList<>();	

	/***
	 * 
	 * @param color: this function get a color as parameter and display a window with a question with respect to the color 
	 * and the difficulty
	 * @return true or false
	 */
	boolean displayQuestion(String color) {

		ArrayList<Question> ques = new ArrayList<>(SysData.getInstance().getQuestions());

		Question q2 = null;
		if(color == "white") {
			q2 = getEasyQuestion();
		}
		else if(color == "yellow") {
			q2 = getMediumQuestion();
		}
		else {
			q2 = getHardQuestion();
		}

		Question q = new Question(q2.getQuestionText(), q2.getDiffuclty(), q2.getCorrectAnswer(),
				q2.getTeam(), q2.getAnswer1(), q2.getAnswer2(), q2.getAnswer3(), q2.getAnswer4());

		boolean isCor = false;


		//window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Try answer the question");
		window.setMinWidth(500);
		window.setMinHeight(200);

		Label question = new Label("Question:");
		Text questionText = new Text(q.getQuestionText());

		RadioButton a1 = new RadioButton(q.getAnswer1());
		RadioButton a2 = new RadioButton(q.getAnswer2());
		RadioButton a3 = new RadioButton(q.getAnswer3());
		RadioButton a4 = new RadioButton(q.getAnswer4());

		ToggleGroup tg = new ToggleGroup(); 
		a1.setToggleGroup(tg);
		a2.setToggleGroup(tg);
		a3.setToggleGroup(tg);
		a4.setToggleGroup(tg);
		Button sub = new Button("Submit");
		sub.setOnAction(e->{
			whoPlays = "horse";
			if(!a1.isSelected() && !a2.isSelected() &&  !a3.isSelected() &&  !a4.isSelected()) {
				Feedback.message("Error", "Please answer the question");
				return;
			}

			if(q.getDiffuclty().equals("easy")) {
				if(q.getCorrectAnswer() == 1 && a1.isSelected()) {
					pointsToRestore.add(1);
					points++;
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}
				else if(q.getCorrectAnswer() == 1 && !a1.isSelected()) {
					pointsToRestore.add(-2);
					points = points - 2;
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}
				if(q.getCorrectAnswer() == 2 && a2.isSelected()) {
					pointsToRestore.add(1);
					points++;
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}
				else if(q.getCorrectAnswer() == 2 && !a2.isSelected()) {
					points = points - 2;
					pointsToRestore.add(-2);
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}

				if(q.getCorrectAnswer() == 3 && a3.isSelected()) {
					pointsToRestore.add(1);
					points++;
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}
				else if(q.getCorrectAnswer() == 3 && !a3.isSelected()) {
					points = points - 2;
					pointsToRestore.add(-2);
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}
				if(q.getCorrectAnswer() == 4 && a4.isSelected()) {
					points++;
					this.scoreText.setText(Integer.toString(points));
					pointsToRestore.add(1);
					window.close();
				}
				else if(q.getCorrectAnswer() == 4 && !a4.isSelected()) {
					points = points - 2;
					this.scoreText.setText(Integer.toString(points));
					pointsToRestore.add(-2);
					window.close();
				}

			}
			if(q.getDiffuclty().equals("medium")) {
				if(q.getCorrectAnswer() == 1 && a1.isSelected()) {
					points = points + 2;
					this.scoreText.setText(Integer.toString(points));
					pointsToRestore.add(2);

					window.close();
				}
				else if(q.getCorrectAnswer() == 1 && !a1.isSelected()) {
					points = points - 3;
					this.scoreText.setText(Integer.toString(points));
					pointsToRestore.add(-3);
					window.close();
				}
				if(q.getCorrectAnswer() == 2 && a2.isSelected()) {
					points = points + 2;
					this.scoreText.setText(Integer.toString(points));
					pointsToRestore.add(2);
					window.close();
				}
				else if(q.getCorrectAnswer() == 2 && !a2.isSelected()) {
					points = points - 3;
					this.scoreText.setText(Integer.toString(points));
					pointsToRestore.add(-3);
					window.close();
				}

				if(q.getCorrectAnswer() == 3 && a3.isSelected()) {
					points = points + 2;
					this.scoreText.setText(Integer.toString(points));
					pointsToRestore.add(2);
					window.close();
				}
				else if(q.getCorrectAnswer() == 3 && !a3.isSelected()) {
					points = points - 3;
					this.scoreText.setText(Integer.toString(points));
					pointsToRestore.add(-3);
					window.close();
				}
				if(q.getCorrectAnswer() == 4 && a4.isSelected()) {
					points = points + 2;
					this.scoreText.setText(Integer.toString(points));
					pointsToRestore.add(2);
					window.close();
				}
				else if(q.getCorrectAnswer() == 4 && !a4.isSelected()) {
					points = points - 3;
					pointsToRestore.add(-3);
					this.scoreText.setText(Integer.toString(points));
					window.close();

				}

			}

			if(q.getDiffuclty().equals("hard")) {
				if(q.getCorrectAnswer() == 1 && a1.isSelected()) {
					points = points + 3;
					pointsToRestore.add(3);
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}
				else if(q.getCorrectAnswer() == 1 && !a1.isSelected()) {
					points = points - 4;
					pointsToRestore.add(-4);
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}
				if(q.getCorrectAnswer() == 2 && a2.isSelected()) {
					points = points + 3;
					pointsToRestore.add(3);
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}
				else if(q.getCorrectAnswer() == 2 && !a2.isSelected()) {
					points = points - 4;
					pointsToRestore.add(-4);
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}

				if(q.getCorrectAnswer() == 3 && a3.isSelected()) {
					points = points + 3;
					pointsToRestore.add(3);
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}
				else if(q.getCorrectAnswer() == 3 && !a3.isSelected()) {
					points = points - 4;
					pointsToRestore.add(-4);
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}
				if(q.getCorrectAnswer() == 4 && a4.isSelected()) {
					points = points + 3;
					pointsToRestore.add(3);
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}
				else if(q.getCorrectAnswer() == 4 && !a4.isSelected()) {
					points = points - 4;
					pointsToRestore.add(-4);
					this.scoreText.setText(Integer.toString(points));
					window.close();
				}

			}

		});

		VBox vbox = new VBox();

		vbox.getChildren().addAll(question, questionText,a1, a2, a3, a4, sub);

		Scene scene = new Scene(vbox, 50, 40);
		window.setScene(scene);
		window.show();

		return false;
	}

	// three function to get a questions from the SysData
	Question getEasyQuestion() {
		ArrayList<Question>ques = new ArrayList<>(SysData.getInstance().getQuestions());
		ArrayList<Question>easy = new ArrayList<>();


		for(Question q : ques) {
			System.out.println(q.getDiffuclty());
			System.out.println(usedQues.contains(q));
			if(q.getDiffuclty().equals("easy")) {
				easy.add(q);
			}
		}
		System.out.println(easy);
		easy.removeAll(usedQues);
		Collections.shuffle(easy);
		usedQues.add(easy.get(0));
		return easy.get(0);

	}


	Question getMediumQuestion() {

		ArrayList<Question>ques = new ArrayList<>(SysData.getInstance().getQuestions());
		ArrayList<Question>medium = new ArrayList<>();


		for(Question q : ques) {
			System.out.println(q.getDiffuclty());
			System.out.println(usedQues.contains(q));
			if(q.getDiffuclty().equals("medium")) {
				medium.add(q);
			}
		}
		System.out.println(medium);
		medium.removeAll(usedQues);
		Collections.shuffle(medium);
		usedQues.add(medium.get(0));
		return medium.get(0);

	}


	Question getHardQuestion() {
		ArrayList<Question>ques = new ArrayList<>(SysData.getInstance().getQuestions());
		ArrayList<Question>hard = new ArrayList<>();



		System.out.println("UsedQues= " + usedQues.size());


		for(Question q : ques) {
			System.out.println(q.getDiffuclty());
			System.out.println(usedQues.contains(q));
			if(q.getDiffuclty().equals("hard")) {
				hard.add(q);
			}
		}
		System.out.println(hard);
		hard.removeAll(usedQues);
		Collections.shuffle(hard);
		usedQues.add(hard.get(0));
		return hard.get(0);

	}



}

