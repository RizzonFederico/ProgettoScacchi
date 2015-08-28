package Model;

public class Piece {
	
	private String piece;
	private char player;
	private int moves;

	// Costruttore delle pedine, contiene il tipo, colore e numero di mosse della pedina
	public Piece (String piece, char player, int moves) {
		
		this.piece=piece;
		this.player=player;
		this.moves=moves;
		
	}
	
	public String getPiece() {
		return piece;
	}

	public char getPlayer() {
		return player;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public void moveUp() {
		moves++;
	}
}