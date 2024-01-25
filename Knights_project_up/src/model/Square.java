package model;

import java.util.Objects;

import designPatterns.SquareFactory;
//Square class
public abstract class Square {
	
	private static int idCounter = 1;
	private int squareID;
	private int [] corr = new int[2];
	private boolean isVisited;
	private String Color;
	public int getSquareID() {
		return squareID;
	}
	public void setSquareID(int squareID) {
		this.squareID = squareID;
	}
	public int[] getCorr() {
		return corr;
	}
	public void setCorr(int[] corr) {
		this.corr = corr;
	}

	public boolean isVisited() {
		return isVisited;
	}
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	@Override
	public int hashCode() {
		return Objects.hash(squareID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		return squareID == other.squareID;
	}
	public Square(int[] corr, boolean isVisited, String color) {
		super();
		this.squareID = idCounter++;
		this.corr = corr;
		this.isVisited = isVisited;
		Color = color;
	}


	public static void main(String[]args) {
		SquareFactory sf = new SquareFactory();
		
		Square s1 = sf.getype("blocked", null, false, null);
		
		System.out.println(s1.Color);
	}

}
