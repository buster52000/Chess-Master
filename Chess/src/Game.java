public class Game {

	private Piece board[][] = new Piece[8][8];
	public static int turn, turns, timePlayed;
	private Piece[][] undo;
	public static boolean undoPRBool;
	public static Piece undoPR;
	public static boolean enableChange, enablePawnChange;
	public static int[] undoPRLoc;

	public Game() {

		turn = 0;
		turns = 0;
		undo = new Piece[8][8];
		undoPR = new Piece(0, 0, 0);
		undoPRLoc = new int[4];
		undoPRBool = false;
		timePlayed = 0;

	}

	public boolean validStart(int x, int y) {

		if (board[x][y] != null) {
			if (turn == board[x][y].color) {
				return true;
			}
		}

		return false;

	}

	public boolean validEnd(int startX, int startY, int endX, int endY) {

		boolean temp = board[startX][startY].checkValid(endX, endY, board);

		if (Game.enablePawnChange)
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					if (board[i][j] != null
							&& board[i][j].getType().equals("Pawn"))
						board[i][j].special = false;

		return temp;

	}

	public void move(int startX, int startY, int endX, int endY) {

		turns++;
		UI.undo.setEnabled(true);
		try {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					if (board[i][j] != null)
						undo[i][j] = (Piece) board[i][j].clone();
					else
						undo[i][j] = null;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		H.puts(board[startX][startY].toString());
		board[startX][startY].moveTo(endX, endY);
		board[endX][endY] = board[startX][startY];
		board[startX][startY] = null;
		turn = turn == 0 ? 1 : 0;

	}

	public void loadGame() {
		boolean exist = false;
		boolean cancel = false;
		while (!exist && !cancel) {
			Load load = new Load();
			load.loadGame();
			exist = load.getExists();
			cancel = load.getCancel();
			if (exist && !load.getCancel()) {
				board = load.getBoard();
				turn = load.getTurn();
			}
		}
	}

	public void undoMove() {
		turns--;
		turn = turn == 0 ? 1 : 0;
		Piece undoPR = Game.undoPR;
		int[] undoPRLoc = Game.undoPRLoc;
		boolean undoPRBool = Game.undoPRBool;
		try {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					if (undo[i][j] != null)
						board[i][j] = (Piece) undo[i][j].clone();
					else
						board[i][j] = null;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		if (undoPRBool) {
			try {
				board[undoPRLoc[0]][undoPRLoc[1]] = (Piece) undoPR.clone();
				if (board[undoPRLoc[2]][undoPRLoc[3]] != null
						&& board[undoPRLoc[2]][undoPRLoc[3]].getType().equals(
								"Rook"))
					board[undoPRLoc[2]][undoPRLoc[3]] = null;
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		undoPRBool = false;
		UI.refreshBoard();
	}

	public boolean checkStillCheck(int sX, int sY, int eX, int eY) {
		Check check = new Check();
		Piece board2[][];
		board2 = check.movePiece(board, sX, sY, eX, eY);
		return check.check(board2, turn == 0 ? 1 : 0);
	}

	public boolean checkCheck() {
		Check check = new Check();
		return check.check(board, turn);
	}

	public boolean checkMate() {
		Check check = new Check();
		boolean mate = check.checkMate(board, turn);
		return mate;
	}

	public void newGame() {
		Load load = new Load();
		load.newGame();
		board = load.getBoard();
		turn = load.getTurn();
	}

	public void addTime() {
		timePlayed += 125;
	}

	public Piece[][] getBoard() {
		return board;
	}

	public int getTurn() {
		return turn;
	}

}
