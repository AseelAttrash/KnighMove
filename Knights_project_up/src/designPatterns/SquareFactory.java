package designPatterns;

import model.BlockedSquare;
import model.IsForgetSquare;
import model.QueestionSquare;
import model.Square;

public class SquareFactory {

	public Square getype(String type, int[] corr, boolean isVisited, String color) {

		if (type.equals("blocked")) {
			return new BlockedSquare(corr, isVisited, color);
		}
		else if(type.equals("question")) {
			return new QueestionSquare(corr, isVisited, color);
		}
		else {
			return new IsForgetSquare(corr, isVisited, color);
		}
	}
}
