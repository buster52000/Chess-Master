

public class Knight extends Piece implements Cloneable {

	public Knight(int _locationX, int _locationY, int _color) {
		super(_locationX, _locationY, _color);
	}

	public String toString() {
		String pColor = color == 1 ? "black" : "white";
		return pColor + " Knight at coords (" + locationX + "," + locationY
				+ ")";
	}

	public boolean checkValid(int eX, int eY, Piece board[][]) {
		int x = locationX;
		int y = locationY;
		if (!(x == eX && y == eY)) 
			if (board[eX][eY] == null || color != board[eX][eY].color)
				if (eX != x && eY != y) 
					if (Math.abs(eX - x) + Math.abs(eY - y) == 3) 
						return true;

		return false;

	}

	public String getType() {
		return "Knight";
	}

	public int getValue() {
		return color == 0 ? 8 : 2;
	}

	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
