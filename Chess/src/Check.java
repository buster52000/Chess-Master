package chess.take3.game;

import javax.swing.JOptionPane;

public class Check {

	public boolean check(Piece board[][], int color) {

		Piece king = null;
		boolean check = false;

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (board[i][j] != null)
					if (board[i][j].getType().equals("King")
							&& board[i][j].getColor() == color) {
						king = board[i][j];
					}

		if (king == null) {
			JOptionPane.showMessageDialog(null, "ERROR: King not found\n"
					+ "Program will exit.\n" + "error message: "
					+ (color == 1 ? "Black" : "White") + " "
					+ "king not found when checking for check.");
			System.exit(0);
		}

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (board[i][j] != null)
					if (board[i][j].checkValid(king.getX(), king.getY(), board))
						check = true;

		return check;
	}

	public boolean checkMate(Piece board[][], int color) {

		Piece king = null;
		Piece board2[][] = new Piece[8][8];
		boolean mate = true;

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (board[i][j] != null)
					if (board[i][j].getType().equals("King")
							&& board[i][j].getColor() == color) {
						king = board[i][j];
					}

		if (king == null) {
			JOptionPane.showMessageDialog(null, "ERROR: King not found\n"
					+ "Program will exit.\n" + "error message: "
					+ (color == 1 ? "Black" : "White") + " "
					+ "king not found when checking for check mate.");
			System.exit(0);
		}

		int x = king.getX();
		int y = king.getY();

		for (int i = 0; i < 8; i++) {
			int hX = x;
			int hY = y;
			switch (i) {
			case 0:
				hX = x + 1;
				break;
			case 1:
				hX = x - 1;
				break;
			case 2:
				hY = y + 1;
				break;
			case 3:
				hY = y - 1;
				break;
			case 4:
				hX = x + 1;
				hY = y + 1;
				break;
			case 5:
				hX = x - 1;
				hY = y + 1;
				break;
			case 6:
				hX = x + 1;
				hY = y - 1;
				break;
			case 7:
				hX = x - 1;
				hY = y - 1;
				break;
			}
			if (hX < 8 && hX >= 0 && hY < 8 && hY >= 0)
				if (king.checkValid(hX, hY, board)) {
					board2 = movePiece(board, x, y, hX, hY);
					if (!check(board2, color))
						mate = false;
				}
		}

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				for (int r = 0; r < 8; r++)
					for (int s = 0; s < 8; s++) {
						if (board[i][j] != null
								&& board[i][j].getColor() == color) {
							if (board[i][j].checkValid(r, s, board)) {
								board2 = movePiece(board, i, j, r, s);
								if (!check(board2, color)) {
									mate = false;
									H.puts("false i=" + i + " j=" + j + " r="
											+ r + " s=" + s);
								}
							}
						}
					}

		return mate;
	}

	public Piece[][] movePiece(Piece board[][], int sX, int sY, int eX, int eY) {
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

		board2[eX][eY] = board2[sX][sY];
		board2[sX][sY] = null;

		return board2;
	}

}
