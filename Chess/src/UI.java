package chess.take3.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class UI extends JFrame {

	private static Game game;
	private JFrame frame;
	private JPanel mainPanel, panel2[][], panel1[];
	private static JTextField textA;
	private static JButton button[][];
	private static ImageIcon img[];
	private squareChoose square[][];
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem save, quit, load, newGame, refresh;
	public static JMenuItem undo;

	public static final int SIZE_X = 756;
	public static final int SIZE_Y = 756;
	public static int coordX, coordY;
	public static boolean clicked;

	@SuppressWarnings("deprecation")
	public UI() {
		game = new Game();

		frame = new JFrame();
		mainPanel = new JPanel();
		panel2 = new JPanel[8][8];
		panel1 = new JPanel[8];
		button = new JButton[8][8];
		img = new ImageIcon[13];
		textA = new JTextField();
		square = new squareChoose[8][8];
		menuBar = new JMenuBar();
		menu = new JMenu("Game");
		save = new JMenuItem("Save Game");
		quit = new JMenuItem("Quit Game");
		undo = new JMenuItem("Undo Last Move");
		load = new JMenuItem("Load Game");
		newGame = new JMenuItem("New Game");
		refresh = new JMenuItem();
		coordX = 0;
		coordY = 0;
		clicked = false;

		menu.add(newGame);
		menu.add(save);
		menu.add(load);
		menu.add(undo);
		menu.add(quit);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		refresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));

		newGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				game.newGame();
				refreshBoard();
				for (int i = 0; i < 8; i++)
					for (int j = 0; j < 8; j++)
						button[i][j].setBorder(BorderFactory.createEmptyBorder(
								10, 10, 10, 10));
			}

		});

		save.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				save();
				refreshBoard();
			}

		});
		
		refresh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				refreshBoard();
			}

		});

		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.loadGame();
				refreshBoard();
			}
		});

		undo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				game.undoMove();
				refreshBoard();
				undo.setEnabled(false);
			}

		});

		quit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				quit();
			}

		});

		frame.addWindowListener(new WindowListener() {

			public void windowActivated(WindowEvent arg0) {

			}

			public void windowClosed(WindowEvent arg0) {

			}

			public void windowClosing(WindowEvent arg0) {
				quit();
			}

			public void windowDeactivated(WindowEvent arg0) {

			}

			public void windowDeiconified(WindowEvent arg0) {

			}

			public void windowIconified(WindowEvent arg0) {

			}

			public void windowOpened(WindowEvent arg0) {

			}

		});

		try {
			img[0] = new ImageIcon(ImageIO.read(new File("pics/blank.png")));
			img[1] = new ImageIcon(ImageIO.read(new File("pics/bPawn.png")));
			img[2] = new ImageIcon(ImageIO.read(new File("pics/bKnight.png")));
			img[3] = new ImageIcon(ImageIO.read(new File("pics/bBishop.png")));
			img[4] = new ImageIcon(ImageIO.read(new File("pics/bRook.png")));
			img[5] = new ImageIcon(ImageIO.read(new File("pics/bQueen.png")));
			img[6] = new ImageIcon(ImageIO.read(new File("pics/bKing.png")));
			img[7] = new ImageIcon(ImageIO.read(new File("pics/wPawn.png")));
			img[8] = new ImageIcon(ImageIO.read(new File("pics/wKnight.png")));
			img[9] = new ImageIcon(ImageIO.read(new File("pics/wBishop.png")));
			img[10] = new ImageIcon(ImageIO.read(new File("pics/wRook.png")));
			img[11] = new ImageIcon(ImageIO.read(new File("pics/wQueen.png")));
			img[12] = new ImageIcon(ImageIO.read(new File("pics/wKing.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			H.puts("Files not found program will exit.");
			System.exit(0);
		}

		frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		frame.setIconImage(img[2].getImage());

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		for (int i = 0; i < 8; i++) {
			panel1[i] = new JPanel();
			panel1[i].setLayout(new BoxLayout(panel1[i], BoxLayout.X_AXIS));
		}
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				panel2[i][j] = new JPanel();
				button[i][j] = new JButton();
				button[i][j].setBackground(null);
				button[i][j].setRolloverEnabled(false);
				button[i][j].setContentAreaFilled(false);
				button[i][j].setFocusable(false);
				button[i][j].setBorder(BorderFactory.createEmptyBorder(10, 10,
						10, 10));
				button[i][j].setIcon(img[0]);
				square[i][j] = new squareChoose(j, i);
				button[i][j].addActionListener(square[i][j]);
				panel2[i][j].add(button[i][j]);
				panel1[i].add(panel2[i][j]);
			}
			mainPanel.add(panel1[j]);
		}
		textA.setSize(SIZE_X, 15);
		textA.setBorder(BorderFactory.createLoweredBevelBorder());
		textA.setEditable(false);
		mainPanel.add(textA);
		frame.add(mainPanel);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 1) {
						panel2[i][j].setBackground(Color.darkGray);
					} else {
						panel2[i][j].setBackground(Color.white);
					}
				} else {
					if (j % 2 == 1) {
						panel2[i][j].setBackground(Color.white);
					} else {
						panel2[i][j].setBackground(Color.darkGray);
					}
				}
			}
		}
		frame.pack();
		frame.setResizable(false);
		frame.setTitle("Chess Master");
		frame.show();
	}

	public static void main(String[] args) {

		@SuppressWarnings("unused")
		UI ui = new UI();
		boolean newGame = true;
		while (newGame) {
			game.newGame();
			refreshBoard();
			boolean checkMate = false;
			boolean check = false;
			while (!checkMate) {
				boolean move;
				do {
					int startX, startY;
					for (int i = 0; i < 8; i++)
						for (int j = 0; j < 8; j++)
							button[i][j].setBorder(BorderFactory
									.createEmptyBorder(10, 10, 10, 10));
					do {
						while (!clicked) {
							try {
								Thread.sleep(125);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						startX = coordX;
						startY = coordY;
						clicked = false;

						H.puts("(" + startX + ", " + startY + ")");

						move = game.validStart(startX, startY);
						if (!move) {
							H.puts("Invalid Start Space");
							textA.setText("Invalid Start Space");
						} else
							textA.setText("");

					} while (!move);
					Game.enableChange = false;
					for (int i = 0; i < 8; i++)
						for (int j = 0; j < 8; j++)
							if (game.validEnd(startX, startY, i, j)) {
								button[j][i].setBorder(BorderFactory
										.createMatteBorder(10, 10, 10, 10,
												Color.lightGray));
							}
					Game.enableChange = true;

					while (!clicked) {
						try {
							Thread.sleep(125);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					int endX = coordX;
					int endY = coordY;
					clicked = false;

					H.puts("(" + endX + ", " + endY + ")");

					move = game.validEnd(startX, startY, endX, endY);
					if (move) {
						if (check) {
							if (!game.checkStillCheck(startX, startY, endX,
									endY)) {
								textA.setText("");
								game.move(startX, startY, endX, endY);
								check = false;
							} else {
								textA.setText("Invalid Move. Check!");
							}
						} else {
							textA.setText("");
							game.move(startX, startY, endX, endY);
						}
					} else {
						H.puts("Invalid Move");
						textA.setText("Invalid Move");
					}

				} while (!move);

				refreshBoard();

				check = game.checkCheck();
				if (check) {
					textA.setText("Check");
					checkMate = game.checkMate();
				}

			}
			int winner = game.getTurn() == 1 ? 0 : 1;
			JOptionPane.showMessageDialog(null, "Check Mate!\n"
					+ (winner == 1 ? "Black" : "White") + " Won!");

			int answer = JOptionPane.showOptionDialog(null,
					"Would you like to start a new game?", "New Game",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, null, null);
			if (answer == 1) {
				newGame = false;
				System.exit(0);
			}
		}
	}

	public static void refreshBoard() {

		Piece board[][] = game.getBoard();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null)
					button[j][i].setIcon(img[board[i][j].getValue()]);
				else
					button[j][i].setIcon(img[0]);
			}
		}

	}

	public void quit() {
		int n = JOptionPane.showConfirmDialog(frame,
				"Would you like to save your game before quitting?");
		if (n == 0) {
			save();
			System.exit(0);
		} else if (n == 1)
			System.exit(0);
	}

	public void save() {
		Save saveMe = new Save(game.getBoard(), game.getTurn());
		saveMe.saveGame();
	}

}
