

public class Queen extends Piece implements Cloneable {

	public Queen(int _locationX, int _locationY, int _color) {
		super(_locationX, _locationY, _color);
	}

	public String toString() {
		String pColor = color == 1 ? "black" : "white";
		return pColor + " Queen at coords (" + locationX + "," + locationY
				+ ")";
	}

	public boolean checkValid(int eX, int eY, Piece board[][]) {
		int x = locationX;
		int y = locationY;
		if (!(x == eX && y == eY)) {
			if (board[eX][eY] == null || color != board[eX][eY].color) {
				if (x == eX || y == eY) {
					boolean helper = true;
					if (x == eX) {
						int bigger = Math.max(y, eY);
						int smaller = Math.min(y, eY);
						for (int i = smaller + 1; i < bigger; i++) {
							if (board[x][i] != null)
								helper = false;
						}
					} else {
						int bigger = Math.max(x, eX);
						int smaller = Math.min(x, eX);
						for (int i = smaller + 1; i < bigger; i++) {
							if (board[i][y] != null)
								helper = false;
						}
					}
					return helper;
				} else if (Math.abs(eX - x) == Math.abs(eY - y)) {
					boolean helper = true;
					int bigX = Math.max(eX, x);
					int smallX = Math.min(eX, x);
					int smallY = Math.min(eY, y);
					if ((y - eY) / (x - eX) == 1) {
						int i = smallX;
						int j = smallY;
						while (i < bigX - 1) {
							i++;
							j++;
							if (board[i][j] != null)
								helper = false;
						}
					} else {
						int i = bigX;
						int j = smallY;
						while (i > smallX + 1) {
							i--;
							j++;
							if (board[i][j] != null)
								helper = false;
						}
					}
					return helper;
				}
			} else
				return false;
		} else
			return false;
		return false;
	}

	public String getType() {
		return "Queen";
	}

	public int getValue() {
		return color == 0 ? 11 : 5;
	}

	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
