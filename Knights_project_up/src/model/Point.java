package model;

public class Point {
	
	@Override
	public String toString() {
		return "Point [row=" + row + ", column=" + column + "]";
	}
	private int row;
	private int column;
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public Point(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	
	
}
