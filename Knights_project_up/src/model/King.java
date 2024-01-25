package model;

import java.util.ArrayList;

public class King extends GameObject{
	//king class
//	private static int idCounter = 1;
//	private int kingID;
//	//square of the king (on it now on the game)
//	private Square onSquare;
	private int speed;
	
//	public int getKingID() {
//		return kingID;
//	}
//	public void setKingID(int kingID) {
//		this.kingID = kingID;
//	}
//	public Square getOnSquare() {
//		return onSquare;
//	}
//	public void setOnSquare(Square onSquare) {
//		this.onSquare = onSquare;
//}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public King(Square onSquare, int speed) {
		super( onSquare);
		this.speed = speed;
	}

	public ArrayList<Point> getPossibleKingMoves(int rowK, int colK){
		ArrayList<Point> p = new ArrayList<>();
		
		if(rowK - 1>= 0 && colK - 1>= 0) {
			p.add(new Point(rowK-1, colK-1)); // x-1, y-1
		}
		
		if(rowK-1 >= 0) {
			p.add(new Point(rowK-1, colK)); // x-1, y
		}
		
		if(rowK - 1 >= 0 && colK <= 7) {
			p.add(new Point(rowK-1, colK+1)); // x-1, y+1
		}
		
		if(colK+1 <= 7) {
			p.add(new Point(rowK, colK+1));// x, y+1
		}
		
		if(rowK + 1 <= 7 && colK + 1 <= 7) {
			p.add(new Point(rowK+1, colK+1)); // x+1, y+1

		}
		
		if(rowK + 1 <= 7) {
			p.add(new Point(rowK+1, colK)); // x+1, y
		}
		
		if(rowK + 1 <= 7 && colK - 1 >= 0) {
			p.add(new Point(rowK+1, colK-1));; // x+1, y-1

		}
		
		if(colK - 1 >= 0) {
			p.add(new Point(rowK, colK-1)); // x, y-1
		}
		
		if(rowK == 0 && colK >= 1 && colK <= 6) {
			p.add(new Point(rowK + 7,  colK - 1));
			p.add(new Point(rowK + 7,  colK));
			p.add(new Point(rowK + 7,  colK + 1));
		}
		
		if(rowK == 7 && colK >= 1 && colK <= 6) {
			p.add(new Point(rowK - 7,  colK - 1));
			p.add(new Point(rowK - 7,  colK));
			p.add(new Point(rowK - 7,  colK + 1));
		}
		
		if(colK == 0 && rowK >=1 && colK <= 6) {
			p.add(new Point(rowK - 1,  colK +7));
			p.add(new Point(rowK  ,  colK +7));
			p.add(new Point(rowK + 1,  colK + 7));
		}
		
		if(colK == 7 && rowK >= 1 && rowK <= 6) {
			p.add(new Point(rowK - 1,  colK -7));
			p.add(new Point(rowK  ,  colK -7));
			p.add(new Point(rowK + 1,  colK - 7));
		}
		
		if(rowK == 0 && colK == 0) {
			p.add(new Point(7, 0));
			p.add(new Point(7, 1));
			p.add(new Point(0, 7));
			p.add(new Point(1, 7));
			p.add(new Point(7, 7));
		}
		
		if(rowK == 7 && colK == 7) {
			p.add(new Point(0, 0));
			p.add(new Point(0, 7));
			p.add(new Point(0, 6));
			p.add(new Point(6, 0));
			p.add(new Point(7, 0));
		}
		
		if(rowK == 0 && colK == 7) {
			p.add(new Point(0, 0));
			p.add(new Point(1, 0));
			p.add(new Point(7,0));
			p.add(new Point(7, 6));
			p.add(new Point(7, 7));
		}
		
		if(rowK == 7 && colK == 0) {
			p.add(new Point(0, 0));
			p.add(new Point(0, 1));
			p.add(new Point(0,7));
			p.add(new Point(6, 7));
			p.add(new Point(7, 7));
		}
//		
		return p;
		
	}
	public King() {
		super();
	}
	
	
	
}


// if(rowK == 0 && colK == 0) {
//	p.add(new Point(rowK + 7, colK + 7));
//}
//
//
//
//if(rowK == 0) {
//	p.add(new Point(rowK + 7, colK));
//}
//
//if(rowK == 7) {
//	p.add(new Point(rowK - 7, colK));
//}
//
//if(colK == 0) {
//	p.add(new Point(rowK, colK + 7));
//}
//
//if(colK == 7) {
//	p.add(new Point (rowK, colK - 7));
//}
//
