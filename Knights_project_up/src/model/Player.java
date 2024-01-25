package model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;
//player Class :
public class Player {
	private static int idCounter = 1;
	private int playerID;
	private String name;
	// a stack that saves the steps of the player (used on forget square)
	// the values will be the last points the player lost/won
	private Stack<Integer>steps;
	@Override
	public String toString() {
		return "Player [playerID=" + playerID + ", name=" + name + "]";
	}
	public Player(String name) {
		super();
		this.playerID = idCounter++;
		this.name = name;
	}
	@Override
	public int hashCode() {
		return Objects.hash(playerID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return playerID == other.playerID;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Stack<Integer> getSteps() {
		return steps;
	}
	public void setSteps(Stack<Integer> steps) {
		this.steps = steps;
	}

	public ArrayList<Point> possibleMovesLevel2up(int col, int row){

		ArrayList<Point> p = new ArrayList<>();
		// 2 bias 1 straight
		if(row - 3 >= 0 && col - 2 >= 0) { // x-3, y-2
			p.add(new Point(row-3, col-2));
		}

		if(row - 2 >= 0 && col - 3 >= 0) {// x-2, y-3
			p.add(new Point(row-2, col-3));
		}

		if(row - 2 >= 0 && col - 1 >= 0) {// x-2, y-1
			p.add(new Point(row-2, col-1));
		}

		if(row - 1 >= 0 && col - 2 >= 0) { // x-1, y-2
			p.add(new Point(row-1, col-2));

		}

		if(row - 3 >= 0 && col + 2 <= 7) {// x-3, y+2
			p.add(new Point(row-3, col+2));

		}

		if(row - 2 >= 0 && col + 3 <= 7) { // x-2, y+3
			p.add(new Point(row-2, col+3));
		}


		if(row - 1 >= 0 && col + 2 <= 7) {// x-2, y+2
			p.add(new Point(row-1, col+2));
		}

		if(row - 2 >= 0 && col + 1 <= 7) {// x-2, y+1
			p.add(new Point(row-2, col+1));
		}

		if(row + 1 <=7 && col + 2 <= 7) {// x+1, y+2
			p.add(new Point(row+1, col+2));
		}

		if(row + 2 <=  7 && col +3 <= 7) { // X+2, y+3
			p.add(new Point(row+2, col+3));
		}

		if(row + 3 <= 7 && col + 2 <= 7) {// x+3, y+2
			p.add(new Point(row+3, col+2));
		}

		if(row + 2 <= 7 && col + 1 <= 7) {// x+2, y+1
			p.add(new Point(row+2, col+1));
		}

		if(row + 1 <= 7 && col - 2 >= 0) {// x+1, y-2
			p.add(new Point(row+1, col-2));
		}

		if(row + 2 <= 7 && col - 1 >= 0) {// x+2, y-1
			p.add(new Point(row+2, col-1));
		}

		if(row + 3 <= 7 && col - 2 >= 0) {// x+3, y-2
			p.add(new Point(row+3, col-2));
		}

		if(row + 2 <= 7 && col - 3 >= 0) {// x+2, y-3
			p.add(new Point(row+2, col-3));
		}
		//=======================================

		// 1 bias 2 straight
		if(row - 3 >= 0 && col - 1 >= 0) { // x-3, y-1
			p.add(new Point(row-3, col-1));
		}

		if(row - 3 >= 0 && col + 1 <= 7) { // x-3, y+1
			p.add(new Point(row-3, col+1));
		}

		if(row - 1 >= 0 && col - 3 >= 0) { // x-1, y-3
			p.add(new Point(row-1, col-3));
		}

		if(row - 1 >= 0 && col - 1 >= 0) {
			p.add(new Point(row-1, col-1));// x-1, y-1
		}

		if(row - 1 >= 0 && col + 1 <= 7) {
			p.add(new Point(row-1, col+1)); // x-1, y+1
		}

		if(row - 1 >= 0 && col + 3 <= 7) {
			p.add(new Point(row-1, col+3)); // x-1, y+3
		}

		if(row + 1 <= 7 && col - 3 >= 0) {
			p.add(new Point(row+1, col-3)); // x+1, y-3
		}

		if(row + 1 <= 7 && col - 1 >= 0) {
			p.add(new Point(row+1, col-1)); // x+1, y-1
		}

		if(row + 1 <= 7 && col + 1 <= 7) {
			p.add(new Point(row+1, col+1)); // x+1, y+1
		}

		if(row + 1 <= 7 && col + 2 <= 7) {
			p.add(new Point(row+1, col+2));// x+1, y+2
		}

		if(row + 1 <= 7 && col + 3 <= 7) {
			p.add(new Point(row+1, col+3)); // x+1, y+3

		}

		if(row + 3 <= 7 && col - 1 >= 0) { // x+3, y-1
			p.add(new Point(row+3, col-1));
		}

		if(row + 3 <= 7 && col + 1 <= 7) { // x+3, y+1
			p.add(new Point(row+3, col+1));
		}


		if(row >= 1 && row <=6 && col == 0) {
			p.add(new Point(row -1,  col+6));
			p.add(new Point(row +1,  col+6));
		}

		if(col == 1 && row >= 1 && row <=6) {
			p.add(new Point(row -1, col +6));
			p.add(new Point(row +1, col +6));

		}

		if(col == 6 && row >= 1 && row <=6) {
			p.add(new Point(row -1, col - 6));
			p.add(new Point(row +1, col - 6));
		}

		if(col == 7 && row >= 1 && row <=6) {
			p.add(new Point(row -1, col - 6));
			p.add(new Point(row +1, col - 6));
		}

		if(row == 0 && col >= 1 & col <= 6) {
			p.add(new Point(row +6,  col - 1));
			p.add(new Point(row +6, col +1));
		}

		if(row == 0 && col >= 1 & col <= 6) {
			p.add(new Point(row +6,  col - 1));
			p.add(new Point(row +6, col +1));
		}

		if(row == 0 && col >= 1 & col <= 6) {
			p.add(new Point(row +6,  col - 1));
			p.add(new Point(row +6, col +1));
		}
		if(row == 7 && col >= 1 && col <= 6) {
			p.add(new Point(row - 6,  col - 1));
			p.add(new Point(row - 6, col +1));
		}

		if(row == 6 && col >= 1 && col <= 6) {
			p.add(new Point(row - 6,  col - 1));
			p.add(new Point(row - 6, col +1));
		}
		
		if(row  == 0 && col == 0) {
			p.add(new Point(7,7));
			p.add(new Point(row + 6, col + 1));
			p.add(new Point(row + 1, col + 6));
		}
		
		if(row == 7 && col == 7) {
			p.add(new Point(0,0));
			p.add(new Point(row - 1, col -6));
			p.add(new Point(row - 6, col -1));
		}
		
		if(row == 0 && col == 7) {
			p.add(new Point(7,0));
			p.add(new Point(row + 1, col -6));
			p.add(new Point(row + 6, col -1));
		}
		
		if(row == 7 && col == 0) {
			p.add(new Point(0,7));
			p.add(new Point(row - 1, col + 6));
			p.add(new Point(row - 6, col + 1));

		}
		
		return p;


	}

	public ArrayList<Point> possibleMovesLevel1(int row, int col){

		ArrayList<Point> p = new ArrayList<>();
		for(int relevRow = 0; relevRow < 8; relevRow++) {

			for(int releveColumn = 0 ; releveColumn < 8; releveColumn++) {

				if ((Math.abs(relevRow - row) == 2 && Math.abs(releveColumn - col) == 1) || 
						(Math.abs(relevRow - row) == 1 && Math.abs(releveColumn - col) == 2)) {
					// the knight can move to this square
					//						tempPanes.add(this.panesMat[relevRow][releveColumn]);
					p.add(new Point(relevRow,releveColumn));
				}
			}

		}

		//		if(row == 0 && col == 0) {
		//			p.add(new Point(row + 7, col + 7));
		//		}
		//		
		//		if(row == 0) {
		//			p.add(new Point(row + 7, col));
		//		}
		//		
		//		if(row == 7) {
		//			p.add(new Point(row - 7, col));
		//		}
		//		
		//		if(col == 0) {
		//			p.add(new Point(row, col + 7));
		//		}
		//		
		//		if(col == 7) {
		//			p.add(new Point (row, col - 7));
		//		}


		if(row >= 1 && row <=6 && col == 0) {
			p.add(new Point(row -1,  col+6));
			p.add(new Point(row +1,  col+6));
		}

		if(col == 1 && row >= 1 && row <=6) {
			p.add(new Point(row -1, col +6));
			p.add(new Point(row +1, col +6));

		}

		if(col == 6 && row >= 1 && row <=6) {
			p.add(new Point(row -1, col - 6));
			p.add(new Point(row +1, col - 6));
		}

		if(col == 7 && row >= 1 && row <=6) {
			p.add(new Point(row -1, col - 6));
			p.add(new Point(row +1, col - 6));
		}

		if(row == 0 && col >= 1 & col <= 6) {
			p.add(new Point(row +6,  col - 1));
			p.add(new Point(row +6, col +1));
		}

		if(row == 0 && col >= 1 & col <= 6) {
			p.add(new Point(row +6,  col - 1));
			p.add(new Point(row +6, col +1));
		}

		if(row == 0 && col >= 1 & col <= 6) {
			p.add(new Point(row +6,  col - 1));
			p.add(new Point(row +6, col +1));
		}
		if(row == 7 && col >= 1 && col <= 6) {
			p.add(new Point(row - 6,  col - 1));
			p.add(new Point(row - 6, col +1));
		}

		if(row == 6 && col >= 1 && col <= 6) {
			p.add(new Point(row - 6,  col - 1));
			p.add(new Point(row - 6, col +1));
		}
		
		if(row  == 0 && col == 0) {
			p.add(new Point(7,7));
			p.add(new Point(row + 6, col + 1));
			p.add(new Point(row + 1, col + 6));
		}
		
		if(row == 7 && col == 7) {
			p.add(new Point(0,0));
			p.add(new Point(row - 1, col -6));
			p.add(new Point(row - 6, col -1));
		}
		
		if(row == 0 && col == 7) {
			p.add(new Point(7,0));
			p.add(new Point(row + 1, col -6));
			p.add(new Point(row + 6, col -1));
		}
		
		if(row == 7 && col == 0) {
			p.add(new Point(0,7));
			p.add(new Point(row - 1, col + 6));
			p.add(new Point(row - 6, col + 1));

		}
		
		return p;

	}

}