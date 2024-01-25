package model;

import java.sql.Date;
import java.time.LocalDate;
//game class
public class Game {
	//game class
	private static int idCounter = 1;
	private int GameID;
	private String PlayerName;
	private int round;
	private String name;
    private int totalPoints;
	private LocalDate gameDate;
    private boolean isPrize;
    private String Prize;
	private int pointsRound1;
	private int pointsRound2;
	private int pointsRound3;
	private int pointsRound4;
	public Game(String playerName, int round, LocalDate gameDate, boolean isPrize, int pointsRound1, int pointsRound2,
			int pointsRound3, int pointsRound4) {
		super();
		this.GameID = this.idCounter++;
		this.PlayerName = playerName;
		this.round = round;
		this.gameDate = gameDate;
		this.isPrize = isPrize;
		this.pointsRound1 = pointsRound1;
		this.pointsRound2 = pointsRound2;
		this.pointsRound3 = pointsRound3;
		this.pointsRound4 = pointsRound4;
		this.totalPoints = pointsRound1+pointsRound2+pointsRound3+pointsRound4;
		this.name=this.PlayerName;
		if(isPrize ==true) {
			this.Prize= "yes";
			
		}else this.Prize= "no";
	}
	public String getPrize() {
		return Prize;
	}
	public void setPrize(String prize) {
		Prize = prize;
	}
	public static int getIdCounter() {
		return idCounter;
	}
	public static void setIdCounter(int idCounter) {
		Game.idCounter = idCounter;
	}
	public int getGameID() {
		return GameID;
	}
	public void setGameID(int gameID) {
		GameID = gameID;
	}
	public int getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	public String getPlayer() {
		return PlayerName;
	}
	public String getPlayerName() {
		return PlayerName;
	}
	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = this.PlayerName;
	}
	public void setPlayer(String player) {
		this.PlayerName = player;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public LocalDate getGameDate() {
		return gameDate;
	}
	public void setGameDate(LocalDate gameDate) {
		this.gameDate = gameDate;
	}
	public boolean isPrize() {
		return isPrize;
	}
	public void setPrize(boolean isPrize) {
		this.isPrize = isPrize;
	}
	public int getPointsRound1() {
		return pointsRound1;
	}
	public void setPointsRound1(int pointsRound1) {
		this.pointsRound1 = pointsRound1;
	}
	public int getPointsRound2() {
		return pointsRound2;
	}
	public void setPointsRound2(int pointsRound2) {
		this.pointsRound2 = pointsRound2;
	}
	public int getPointsRound3() {
		return pointsRound3;
	}
	public void setPointsRound3(int pointsRound3) {
		this.pointsRound3 = pointsRound3;
	}
	public int getPointsRound4() {
		return pointsRound4;
	}
	public void setPointsRound4(int pointsRound4) {
		this.pointsRound4 = pointsRound4;
	}
	@Override
	public String toString() {
		return "Game [playerName=" + PlayerName + ", round=" + round + ", gameDate=" + gameDate + ", isPrize=" + isPrize
				+ ", pointsRound1=" + pointsRound1 + ", pointsRound2=" + pointsRound2 + ", pointsRound3=" + pointsRound3
				+ ", pointsRound4=" + pointsRound4 + "]";
	}


}