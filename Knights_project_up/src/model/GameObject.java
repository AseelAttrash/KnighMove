package model;

public abstract class  GameObject { //https://en.wikipedia.org/wiki/Template_method_pattern
	private static int idCounter = 1;
	private int obectID;
	private Square onSquare;
	
	public int getObectID() {
		return obectID;
	}
	public void setObectID(int obectID) {
		this.obectID = obectID;
	}
	//square of the king (on it now on the game)
	public Square getOnSquare() {
		return onSquare;
	}
	public void setOnSquare(Square onSquare) {
		this.onSquare = onSquare;
	}

	
	public void MoveForward() { // some basic code that the Object will use and add more details
		
	}
	
	public void MoveBias() { // some basic code that the Object will use and add more details
		
	}
	public GameObject(Square onSquare) {
		super();
		this.obectID = idCounter++;
		this.onSquare = onSquare;
	}
	
	public GameObject() {
		super();
		this.obectID = idCounter++;
	}
	
	
}
