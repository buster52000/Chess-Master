package chess.take3.game;

public class King extends Piece implements Cloneable {

	private Piece[] specialUndo = new Piece[2];
	
	public King(int _locationX, int _locationY, int _color) {
		super(_locationX, _locationY, _color);
	}

	public String toString() {
		String pColor = color == 1 ? "black" : "white";
		return pColor + " King at coords (" + locationX + "," + locationY + ")";
	}

	public boolean checkValid(int eX, int eY, Piece board[][]) {
		int x = locationX;
		int y = locationY;
		Piece board2[][] = new Piece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null) {
					try {
						board2[i][j] = (Piece) board[i][j].clone();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if (!(eX == x && eY == y)) {
			if (board[eX][eY] == null || color != board[eX][eY].color) {
				if ((x == eX + 1 && y == eY)
						|| (x == eX - 1 && y == eY)
						|| (y == eY + 1 && x == eX)
						|| (y == eY - 1 && x == eX)
						|| (x == eX + 1 && y == eY + 1)
						|| (x == eX - 1 && y == eY - 1)
						|| (x == eX + 1 && y == eY - 1)
						|| (x == eX - 1 && y == eY + 1)
						|| (y == eY && x == eX + 3 && moves == 0 && x - 4 >= 0
								&& board[x - 4][y] != null && board[x - 4][y]
								.getMoves() == 0)
						|| (y == eY && x == eX - 2 && moves == 0 && x + 3 < 8
								&& board[x + 3][y] != null && board[x + 3][y]
								.getMoves() == 0)) {
					if (y == eY && x == eX + 3 && moves == 0 && x - 4 >= 0
							&& board[x - 4][y] != null
							&& board[x - 4][y].getMoves() == 0) {
						if (Game.enableChange) {
							board[x - 2][y] = board[x - 4][y];
							board[x - 4][y] = null;
						}
					}
					if (y == eY && x == eX - 2 && moves == 0 && x + 3 < 8
							&& board[x + 3][y] != null
							&& board[x + 3][y].getMoves() == 0) {
						if (Game.enableChange) {
							board[x + 1][y] = board[x + 3][y];
							board[x + 3][y] = null;
						}

					}
					board2[eX][eY] = board2[x][y];
					board2[x][y] = null;
					board2[eX][eY].moveTo(eX, eY);
					Check check = new Check();
					if (!check.check(board2, color))
						return true;
				}
			}
		}

		return false;
	}

	public String getType() {
		return "King";
	}

	public int getValue() {
		return color == 0 ? 12 : 6;
	}

	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
