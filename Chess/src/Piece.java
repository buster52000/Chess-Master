package chess.take3.game;

public class Piece {

	int locationX, locationY, color;
	int moves;
	
	public Piece(int _locationX, int _locationY, int _color) {
		color = _color;
		locationX = _locationX;
		locationY = _locationY;
		moves = 0;
	}
	
	public void moveTo(int x, int y) {
		locationX = x;
		locationY = y;
		moves++;
	}
	
	public boolean checkValid(int x, int y, Piece board[][]) {
		H.puts("ERROR: checkValid coords ("+locationX+","+locationY+") moving to ("+x+","+y+")");
		return true;
	}
	
	public String getType() {
		return "Piece";
	}
	
	public int getColor() {
		return color;
	}
	
	public int getValue() {
		return 0;
	}
	
	public int getX() {
		return locationX;
	}
	
	public int getY() {
		return locationY;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public void setMoves(int _moves) {
		moves = _moves;
	}
	
	public boolean getSpecial() {
		return false;
	}
	
	protected Object clone() throws CloneNotSupportedException {
        return super.clone();
	}
}
