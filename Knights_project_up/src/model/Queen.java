package model;

import java.util.ArrayList;

// queen class
public class Queen extends GameObject{
	private static int idCounter = 1;
//	private int queenID;
//	//the square the queen steps on it now
//	private Square onsquare;
//	public int getQueenID() {
//		return queenID;
//	}
//	public void setQueenID(int queenID) {
//		this.queenID = queenID;
//	}
//	public Square getOnsquare() {
//		return onsquare;
//	}
//	public void setOnsquare(Square onsquare) {
//		this.onsquare = onsquare;
//	}
	public Queen() {
		super();
	}
	
	
	public ArrayList<Point>getQueenPossibleMoves(int colQ, int rowQ) {
		ArrayList<Point>p = new ArrayList<>();
		// here in the 2 for loops we get the all possible moves of the queen
		for(int relevRow = 0; relevRow < 8; relevRow++) {

			for(int releveColumn = 0 ; releveColumn < 8; releveColumn++) {
				if (relevRow == rowQ || colQ == releveColumn || 
						Math.abs(relevRow - rowQ) == Math.abs(releveColumn - colQ)) {
//					queenPanes.add(this.panesMat[relevRow][releveColumn]);
					p.add(new Point(relevRow, releveColumn));
				}
			}
		}
		return p;
	}
	
	@Override
	public void MoveBias() {
		
	}
}	

