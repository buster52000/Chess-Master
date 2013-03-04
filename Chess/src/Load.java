

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Load {

	private File file;
	private BufferedReader reader;
	private int turn;
	private Piece board[][];
	private boolean exists;
	private boolean cancel;

	public Load() {
		exists = true;
		board = new Piece[8][8];
	}
	
	public void newGame() {
		file = new File("conf/newGame.cfg");
		if(!file.exists()) {
			JOptionPane.showMessageDialog(null, "Config File Does Not Exist./nProgram Will Exit.");
			System.exit(0);
		} else {
			loadFile();
		}
	}
	
	public void loadGame() {
		String name = JOptionPane
				.showInputDialog("Enter the name of the file.");
		if (name != null) {
			file = new File("saves/" + name + ".cmr");
			if (!file.exists()) {
				exists = false;
				JOptionPane.showMessageDialog(null, "File Does Not Exist");
			} else {
				loadFile();
			}
		} else
			cancel = true;
	}

	private void loadFile() {
		String line1, line2;
		try {
			reader = new BufferedReader(new FileReader(file));
			line1 = reader.readLine();
			line2 = reader.readLine();
			turn = Integer.parseInt(reader.readLine());
			reader.close();
			String helper1[] = line1.split(":");
			String helper2[];
			String helper3[][] = new String[8][8];
			for (int i = 0; i < 8; i++) {
				helper2 = helper1[i].split(",");
				for (int j = 0; j < 8; j++) {
					helper3[j][i] = helper2[j];
				}
			}

			String moves1[] = line2.split(":");
			String moves2[];
			String moves3[][] = new String[8][8];
			for (int i = 0; i < 8; i++) {
				moves2 = moves1[i].split(",");
				for (int j = 0; j < 8; j++) {
					moves3[j][i] = moves2[j];
				}
			}

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					switch (Integer.parseInt(helper3[i][j])) {
					case 1:
						board[i][j] = new Pawn(i, j, 1);
						break;
					case 2:
						board[i][j] = new Knight(i, j, 1);
						break;
					case 3:
						board[i][j] = new Bishop(i, j, 1);
						break;
					case 4:
						board[i][j] = new Rook(i, j, 1);
						break;
					case 5:
						board[i][j] = new Queen(i, j, 1);
						break;
					case 6:
						board[i][j] = new King(i, j, 1);
						break;
					case 7:
						board[i][j] = new Pawn(i, j, 0);
						break;
					case 8:
						board[i][j] = new Knight(i, j, 0);
						break;
					case 9:
						board[i][j] = new Bishop(i, j, 0);
						break;
					case 10:
						board[i][j] = new Rook(i, j, 0);
						break;
					case 11:
						board[i][j] = new Queen(i, j, 0);
						break;
					case 12:
						board[i][j] = new King(i, j, 0);
						break;
					}
					if (board[i][j] != null)
						board[i][j].setMoves(Integer
								.parseInt(moves3[i][j]));
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Piece[][] getBoard() {
		return board;
	}

	public int getTurn() {
		return turn;
	}

	public boolean getExists() {
		return exists;
	}

	public boolean getCancel() {
		return cancel;
	}
}
