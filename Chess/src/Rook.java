
public class Rook extends Piece implements Cloneable{

	public Rook(int _locationX, int _locationY, int _color) {
		super(_locationX, _locationY, _color);
	}

	public String toString() {
		String pColor = color == 1 ? "black" : "white";
		return pColor + " Rook at coords (" + locationX + "," + locationY + ")";
	}

	public boolean checkValid(int eX, int eY, Piece board[][]) {
		int x = locationX;
		int y = locationY;
		if (!(x == eX && y == eY)) {
			if (board[eX][eY] == null || color != board[eX][eY].color) {
				if (x == eX || y == eY) {
					if (x == eX) {
						int bigger = Math.max(y, eY);
						int smaller = Math.min(y, eY);
						for(int i = smaller + 1; i < bigger; i++) {
							if(board[x][i] != null)
								return false;
						}
					} else {
						int bigger = Math.max(x, eX);
						int smaller = Math.min(x, eX);
						for(int i = smaller + 1; i < bigger; i++) {
							if(board[i][y] != null)
								return false;
						}
					}
				} else
					return false;
			} else
				return false;
		} else
			return false;

		return true;
	}

	public String getType() {
		return "Rook";
	}
	
	public int getValue() {
		return color == 0 ? 10 : 4;
	}
	
	protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
