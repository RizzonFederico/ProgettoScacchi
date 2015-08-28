package Model;

// Costruttore delle caselle, contiene le coordinate e il pezzo presente
public class Tile {
	
	private int x;
	private int y;
	private Piece piece;
	
	public Tile(int x, int y, String piece, char player, int moves) {
		this.x=x;
		this.y=y;
		this.piece=new Piece(piece, player, moves);
	}
	
	public int getRow () {
		return x;
	}

	public int getColumn () {
		return y;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece=piece;
	}
	
	public String getPieceType() {
		return piece.getPiece();
	}
	
	public char getPlayer () {
		return piece.getPlayer();
	}
	
	public int getMoves () {
		return piece.getMoves();
	}
}
