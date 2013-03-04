
public class Bishop extends Piece implements Cloneable{

	public Bishop(int _locationX, int _locationY, int _color) {
		super(_locationX, _locationY, _color);
	}

	public String toString() {
		String pColor = color == 1 ? "black" : "white";
		return pColor + " Bishop at coords (" + locationX + "," + locationY
				+ ")";
	}

	public boolean checkValid(int eX, int eY, Piece board[][]) {
		int x = locationX;
		int y = locationY;
		if (!(eX == x && eY == y)) {
			if (board[eX][eY] == null || color != board[eX][eY].color) {
				if (Math.abs(eX - x) == Math.abs(eY - y)) {
					boolean helper = true;
					int bigX = Math.max(eX, x);
					int smallX = Math.min(eX, x);
					int smallY = Math.min(eY, y);
					if ((y-eY)/(x-eX) == 1) {
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
			}
		}
		return false;
	}

	public String getType() {
		return "Bishop";
	}

	public int getValue() {
		return color == 0 ? 9 : 3;
	}
	
	protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
