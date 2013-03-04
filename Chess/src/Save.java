

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Save {

	private Piece board[][];
	private int turn;
	private BufferedWriter write;
	@SuppressWarnings("unused")
	private File file, dir;

	public Save(Piece _board[][], int _turn) {
		board = _board;
		turn = _turn;
	}

	public void saveGame() {
		File dir = new File("saves");
		dir.mkdir();
		boolean notSaved = true;
		while (notSaved) {
			String name = JOptionPane
					.showInputDialog("Enter the name you want to save this game as...");
			if (name != null) {
				file = new File("saves/" + name + ".cmr");
				if (file.exists()) {
					int choice = JOptionPane
							.showConfirmDialog(null,
									"This file already exists.\nWould you like to overrwrite it?");
					if(choice == 0) {
						file.delete();
						writeFile();
						notSaved = false;
					} else if(choice == 2) {
						notSaved = false;
					}
				} else {
					notSaved = false;
					writeFile();
				}
			} else {
				notSaved = false;
			}
		}
	}

	private void writeFile() {
		try {
			write = new BufferedWriter(new FileWriter(file));
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int moves[][] = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				try {
					if (board[j][i] != null) {
						write.write(Integer.toString(board[j][i].getValue()));
						moves[j][i] = board[j][i].getMoves();
					} else {
						write.write("0");
						moves[j][i] = 0;
					}
					if (j < 7)
						write.write(",");
					if (j == 7 && i != 7)
						write.write(":");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			write.newLine();
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					write.write(Integer.toString(moves[i][j]));
					if (j < 7)
						write.write(",");
					if (j == 7 && i != 7)
						write.write(":");
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			write.newLine();
			write.write(Integer.toString(turn));
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Game saved");
	}
}
