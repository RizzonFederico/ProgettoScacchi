package Model;

public class Board {
	
	// Matrice di oggetti Tile per formare la schacchiera di dimensione 8x8
	private Tile[][] board=new Tile[8][8];
	// Casella di partenza durante una mossa; all'inizio contiene la pedina da muovere, alla fine rimane vuota
	private Tile from=null;
	// Casella di arrivo durante una mossa; l'oggetto Piece contenuto viene sostituito con quello di from
	private Tile to=null;
	// Oggetti utilizzati principalmente per la stampa delle mosse nel pannello laterale
	private Tile lastMoveFrom=null;
	private Tile lastMoveTo=null;
	// Variabile per il controllo della validità della mossa
	private boolean ok=false;
	// Variabili usate principalmente per la stampa delle mosse a video
	private boolean isCheckmate=false;
	private boolean isDraw=false;
	// Variabile che permette l'alternanza dei turni dei due giocatori 
	private char turn='w';
	// Contatore che aumenta per ogni mossa senza cattura
	private int lastCapture=0;
	// Matrice di oggetti Tile che memorizza la posizione delle pedine
	private Tile[][] database={{new Tile(0,0,"rook",'b', 0), new Tile(0,1,"knight",'b', 0), new Tile(0,2,"bishop",'b', 0), new Tile(0,3,"queen",'b', 0), new Tile(0,4,"king",'b', 0), new Tile(0,5,"bishop",'b', 0), new Tile(0,6,"knight",'b', 0), new Tile(0,7,"rook",'b', 0), new Tile(1,0,"pawn",'b', 0), new Tile(1,1,"pawn",'b', 0), new Tile(1,2,"pawn",'b', 0), new Tile(1,3,"pawn",'b', 0), new Tile(1,4,"pawn",'b', 0), new Tile(1,5,"pawn",'b', 0), new Tile(1,6,"pawn",'b', 0), new Tile(1,7,"pawn",'b', 0)},
								 {new Tile(7,0,"rook",'w', 0), new Tile(7,1,"knight",'w', 0), new Tile(7,2,"bishop",'w', 0), new Tile(7,3,"queen",'w', 0), new Tile(7,4,"king",'w', 0), new Tile(7,5,"bishop",'w', 0), new Tile(7,6,"knight",'w', 0), new Tile(7,7,"rook",'w', 0), new Tile(6,0,"pawn",'w', 0), new Tile(6,1,"pawn",'w', 0), new Tile(6,2,"pawn",'w', 0), new Tile(6,3,"pawn",'w', 0), new Tile(6,4,"pawn",'w', 0), new Tile(6,5,"pawn",'w', 0), new Tile(6,6,"pawn",'w', 0), new Tile(6,7,"pawn",'w', 0)}};
	
	// Costruttore che mette le pedine nella posizione iniziale
	public Board () {
		
		int x;
		int y;
		
		for(x=0; x<8; x++) {
			
				for(y=0; y<8; y++) {
					
					if (x==0) {
						if(y==0) board[x][y]=database[0][0];
						if(y==1) board[x][y]=database[0][1];
						if(y==2) board[x][y]=database[0][2];
						if(y==3) board[x][y]=database[0][3];
						if(y==4) board[x][y]=database[0][4];
						if(y==5) board[x][y]=database[0][5];
						if(y==6) board[x][y]=database[0][6];
						if(y==7) board[x][y]=database[0][7];
					}
					
					if (x==1)
						board[x][y]=database[0][y+8];
					
					if (x>1 && x<6)
						board[x][y]=new Tile(x,y,"empty", 'e', 0);
					
					if (x==6)
						board[x][y]=database[1][y+8];
					
					if (x==7) {
						if(y==0) board[x][y]=database[1][0];
						if(y==1) board[x][y]=database[1][1];
						if(y==2) board[x][y]=database[1][2];
						if(y==3) board[x][y]=database[1][3];
						if(y==4) board[x][y]=database[1][4];
						if(y==5) board[x][y]=database[1][5];
						if(y==6) board[x][y]=database[1][6];
						if(y==7) board[x][y]=database[1][7];
					}
				}
				y=0;	
			}
	}

	// Costruttore per la creazione di varie situazioni speciali a fini di test
	public Board (int i) {
		if (i==0) {
			for (int x=0; x<8; x++)
				for(int y=0; y<8; y++)
					board[x][y]=new Tile(x,y,"empty", 'e', 0);
			board[0][4]=database[0][4];
			board[7][4]=database[1][4];
			board[0][2]=database[0][2];
			for(int y=0; y<16; y++)
				if (y!=2 && y!=4)
					database[0][y]=new Tile(0,0,"empty", 'e', 0);
			for(int y=0; y<16; y++)
				if (y!=4)
					database[1][y]=new Tile(0,0,"empty", 'e', 0);
			turn='w';
		}
		else if (i==1) {
			for (int x=0; x<8; x++)
				for(int y=0; y<8; y++)
					board[x][y]=new Tile(x,y,"empty", 'e', 0);
			board[0][4]=database[0][4];
			board[7][4]=database[1][4];
			board[0][2]=database[0][1];
			for(int y=0; y<16; y++)
				if (y!=1 && y!=4)
					database[0][y]=new Tile(0,0,"empty", 'e', 0);
			for(int y=0; y<16; y++)
				if (y!=4)
					database[1][y]=new Tile(0,0,"empty", 'e', 0);
			turn='w';
		}
		else if (i==2) {
			for (int x=0; x<8; x++)
				for(int y=0; y<8; y++)
					board[x][y]=new Tile(x,y,"empty", 'e', 0);
			board[0][4]=database[0][4];
			board[7][7]=database[1][4]=new Tile(7,7,"king", 'w', 0);
			board[6][0]=database[0][3]=new Tile(6,0,"queen", 'b', 0);
			board[0][6]=database[0][8]=new Tile(0,6,"queen", 'b', 0);
				for(int y=0; y<16; y++)
					if (y!=3 && y!=4 && y!=8)
						database[0][y]=new Tile(0,0,"empty", 'e', 0);
				for(int y=0; y<16; y++)
					if (y!=4)
						database[1][y]=new Tile(0,0,"empty", 'e', 0);
				turn='w';
		}
		else if (i==3) {
			for (int x=0; x<8; x++)
				for(int y=0; y<8; y++)
					board[x][y]=new Tile(x,y,"empty", 'e', 0);
			board[1][0]=database[0][8]=new Tile(1,0,"pawn",'b',0);
			board[2][1]=database[1][8]=new Tile(2,1,"pawn",'w',0);
			board[5][5]=database[0][0]=new Tile(5,5,"rook",'b',0);
			board[3][5]=database[1][9]=new Tile(3,5,"pawn",'w',0);
			board[1][5]=database[0][3]=new Tile(1,5,"king",'b',0);
			board[5][3]=database[0][2]=new Tile(5,3,"bishop",'b',0);
			board[4][2]=database[0][1]=new Tile(4,2,"knight",'b',0);
			for(int y=0; y<16; y++)
				if (y!=0 && y!=1 && y!=2 && y!=3 && y!=8)
					database[0][y]=new Tile(0,0,"empty", 'e', 0);
			for(int y=0; y<16; y++)
				if (y!=8 && y!=9)
					database[1][y]=new Tile(0,0,"empty", 'e', 0);
			turn='b';
		}
		else if (i==4) {
			for (int x=0; x<8; x++)
				for(int y=0; y<8; y++)
					board[x][y]=new Tile(x,y,"empty", 'e', 0);
			board[0][0]=database[1][4]=new Tile(0,0,"king",'w',0);
			board[1][1]=database[0][3]=new Tile(1,1,"queen",'b',0);
			board[2][2]=database[0][4]=new Tile(2,2,"king",'b',0);
			for(int y=0; y<16; y++)
				if (y!=3 && y!=4)
					database[0][y]=new Tile(0,0,"empty", 'e', 0);
			for(int y=0; y<16; y++)
				if (y!=4)
					database[1][y]=new Tile(0,0,"empty", 'e', 0);
			turn='w';
			from=new Tile(1,5,"empty",'e',0);
			to=new Tile(1,1,"queen",'b',0);
			lastMoveTo=new Tile(to.getRow(),to.getColumn(),to.getPieceType(),to.getPlayer(),to.getMoves());
		}
	}

	public Tile getTile (int x, int y) {
		return board[x][y];
	}
	
	public char getTurn() {
		return turn;
	}
	
	// Comunica le coordinate dei bottoni premuti alla board e riferisce se la mossa è stata compiuta o meno
	public boolean getInfo (int x, int y) {
		boolean ok;
		
		if(from==null) {
			from=board[x][y];
			return false;
		}
		else {
			to=board[x][y];
			ok=move(turn);
			from=null;
			to=null;
			return ok;
		}
	}
	
	// Scrive la stringa da stampare nel pannello laterale
	public String getText () {
		String s;
		
		char[] column = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
		
		String from=column[lastMoveFrom.getColumn()]+Integer.toString(8-lastMoveFrom.getRow());
		String to=column[lastMoveTo.getColumn()]+Integer.toString(8-lastMoveTo.getRow());
		
		s=lastMoveTo.getPlayer()+" "+lastMoveTo.getPieceType()+" "+from+"->"+to;
		
		if (isCheckmate==true)
			s=s+" Checkmate!";
		
		if (isDraw==true)
			s=s+" Draw!";
		
		return s;
	}
	
	
	// Controlla ed eventualmente esegue la mossa ed eventuali situazioni di scacco o pareggio
	private boolean move(char player) {
	
		if (from.getPlayer()!=player)
			return false;
		
		else if (isCheckmate==false && isDraw==false) {
		
			if(from.getPieceType()=="pawn" && check())
				ok=movePawn(from, to, false);

			else if(from.getPieceType()=="knight"  && check())	
				ok=moveKnight(from.getPlayer(), from, to, false);
				
			else if(from.getPieceType()=="rook"  && check())
				ok=moveRook(from.getPlayer(), from, to, false);
			
			else if(from.getPieceType()=="bishop"  && check())
				ok=moveBishop(from.getPlayer(), from, to, false);
			
			else if(from.getPieceType()=="queen"  && check()) {	
				
				if (to.getRow()==from.getRow() || to.getColumn()==from.getColumn())
					ok=moveRook(from.getPlayer(), from, to, false);
				
				else
					ok=moveBishop(from.getPlayer(), from, to, false);
			}
			
			else if(from.getPieceType()=="king" && check())
				ok=moveKing(from.getPlayer(), from, to, false);
			
			else ok=false;
			
			if(ok==true) {
				lastMoveTo=new Tile(to.getRow(),to.getColumn(),to.getPieceType(),to.getPlayer(),to.getMoves());
				lastMoveFrom=new Tile(from.getRow(),from.getColumn(),from.getPieceType(),from.getPlayer(),from.getMoves());
				if (turn=='w')
					turn='b';
				else
					turn='w';
				if (checkmate()==true) {
					isCheckmate=true;
				}
				else if (draw()==true) {
					isDraw=true;
				}
				return true;
			}
			
			return false;
		}
		
		return false;		
	}
	
	// Sposta la pedina da from a to su Board e modifica Database di conseguanza
	private void swap(Tile from, Tile to, boolean countDraw) {
		
		// Se sta per essere mangiata una pedina o mosso un pedone il contatore viene azzerato
		if (countDraw) {
			if (to.getPlayer()!='e' || from.getPieceType()=="pawn")
				lastCapture=0;
			else
				lastCapture++;
		}
		
		if(to.getPlayer()=='b') {
			for(int x=0; x<16; x++)
				if(database[0][x].getRow()==to.getRow() && database[0][x].getColumn()==to.getColumn())
					database[0][x]=new Tile(-2,-2,"empty",'e',0);
		}
		else if (to.getPlayer()=='w') {
			for(int x=0; x<16; x++)
				if(database[1][x].getRow()==to.getRow() && database[1][x].getColumn()==to.getColumn())
					database[1][x]=new Tile(-2,-2,"empty",'e',0);
		}
		
		to.setPiece(from.getPiece());
		
		if(turn=='b') {
			for(int x=0; x<16; x++)
				if(database[0][x].getRow()==from.getRow() && database[0][x].getColumn()==from.getColumn())
					database[0][x]=to;
		}
		else {
			for(int x=0; x<16; x++)
				if(database[1][x].getRow()==from.getRow() && database[1][x].getColumn()==from.getColumn())
					database[1][x]=to;
		}
		
		from.setPiece(new Piece("empty", 'e', 0));
	}
	
	// Controlla se la mossa metterebbe in pericolo di scacco il proprio re
	private boolean check () {
	
		boolean res=false;
	
		if(from.getPieceType()=="pawn")
			ok=movePawn(from, to, true);

		else if(from.getPieceType()=="knight")
			ok=moveKnight(from.getPlayer(), from, to, true);
		
		else if(from.getPieceType()=="rook")
			ok=moveRook(from.getPlayer(), from, to, true);
	
		else if(from.getPieceType()=="bishop")
			ok=moveBishop(from.getPlayer(), from, to, true);
	
		else if(from.getPieceType()=="queen") {	
			if (to.getRow()==from.getRow() || to.getColumn()==from.getColumn())
				ok=moveRook(from.getPlayer(), from, to, true);
			else
				ok=moveBishop(from.getPlayer(), from, to, true);
		}
	
		else if(from.getPieceType()=="king")
			ok=moveKing(from.getPlayer(), from, to, true);
	
		else
			ok=false;
	
		if (ok==true) {
	
			int king;
			int x1=-1, y1=-1, x2=-1, y2=-1;
			Tile tempFrom=new Tile(from.getRow(),from.getColumn(),from.getPieceType(),from.getPlayer(),from.getMoves());
			Tile tempTo=new Tile(to.getRow(),to.getColumn(),to.getPieceType(),to.getPlayer(),to.getMoves());
	
			for(int y=0; y<2; y++)
				for(int x=0; x<16; x++)
					if(database[y][x].getRow()==from.getRow() && database[y][x].getColumn()==from.getColumn()) {
						x1=x;
						y1=y;
					}
	
			for(int y=0; y<2; y++)
				for(int x=0; x<16; x++)
					if(database[y][x].getRow()==to.getRow() && database[y][x].getColumn()==to.getColumn()) {
						x2=x;
						y2=y;
					}			
	
			if(turn=='b')
				king=0;
			else
				king=1;
	
			swap (from, to, false);
	
			res=control(database[king][4], king);
	
			from=tempFrom;
			to=tempTo;
			board[from.getRow()][from.getColumn()]=tempFrom;
			board[to.getRow()][to.getColumn()]=tempTo;
			if (x1!=-1 || y1!=-1)
				database[y1][x1]=tempFrom;
			if (x2!=-1 || y2!=-1)
				database[y2][x2]=tempTo;
	
		}
	
		return res;
	}

	public boolean testMovePawn (char adv, int xFrom, int yFrom, int xTo, int yTo, boolean test) {
		Tile from=board[xFrom][yFrom];
		Tile to=board[xTo][yTo];
		return movePawn (from, to, test);
	}
	
	// Contiene le regole riguardo ai movimenti del pedone
	private boolean movePawn (Tile from, Tile to, boolean test) {
		
		boolean ok;
		char adv;
	
		if (turn=='b')
			adv='w';
		else
			adv='b';
		
		
			if (to.getRow()==from.getRow()-1 && from.getPlayer()=='w' && to.getColumn()==from.getColumn() && to.getPlayer()=='e') {
				if (!test) {
					from.getPiece().moveUp();
					swap(from, to, true);
				}
				ok=true;
			}
			else if (to.getRow()==from.getRow()+1 && from.getPlayer()=='b' && to.getColumn()==from.getColumn() && to.getPlayer()=='e') {
				if (!test) {
					from.getPiece().moveUp();
					swap(from, to, true);
				}
				ok=true;
			}
			else if (to.getRow()==from.getRow()-2 && from.getPlayer()=='w' && to.getColumn()==from.getColumn() && to.getPlayer()=='e' && from.getMoves()==0) {
				if (!test) {
					from.getPiece().moveUp();
					swap(from, to, true);
				}
				ok=true;
			}
			else if (to.getRow()==from.getRow()+2 && from.getPlayer()=='b' && to.getColumn()==from.getColumn() && to.getPlayer()=='e' && from.getMoves()==0) {
				if (!test) {
					from.getPiece().moveUp();
					swap(from, to, true);
				}
				ok=true;
			}
			else if (to.getRow()==from.getRow()-1 && from.getPlayer()=='w' && (to.getColumn()==from.getColumn()+1 || to.getColumn()==from.getColumn()-1) && to.getPlayer()==adv) {
				if (!test) {
					from.getPiece().moveUp();
					swap(from, to, true);
				}
				ok=true;
			}
			else if (to.getRow()==from.getRow()+1 && from.getPlayer()=='b' && (to.getColumn()==from.getColumn()+1 || to.getColumn()==from.getColumn()-1) && to.getPlayer()==adv) {
				if (!test) {
					from.getPiece().moveUp();
					swap(from, to, true);
				}
				ok=true;
			}
			
		else {
			if (!test) System.out.println("Mossa non valida");
			ok=false;
		}
		
		// se un pedone ha raggiunto il limite opposto della scacchiera viene promosso a regina
		if ((to.getPlayer()=='b' && to.getRow()==7) || (to.getPlayer()=='w' && to.getRow()==0))
			board[to.getRow()][to.getColumn()].setPiece(new Piece("queen", to.getPlayer(), to.getMoves()));
		
		return ok;
		
	}

	public boolean testMoveRook (char player, int xFrom, int yFrom, int xTo, int yTo, boolean test) {
		Tile from=board[xFrom][yFrom];
		Tile to=board[xTo][yTo];
		return moveRook (player, from, to, test);
	}
	
	// Contiene le regole riguardo ai movimenti della torre
	private boolean moveRook (char player, Tile from, Tile to, boolean test) {
		
		int i;
		boolean ok;
		
		if (to.getRow()==from.getRow() && to.getColumn()>from.getColumn()) {
			ok=true;
			for(i=from.getColumn()+1; i<to.getColumn(); i++) {
				if(board[from.getRow()][i].getPlayer()!='e')
					ok=false;
			}
			if (ok==true && to.getPlayer()!=player) {
				if (!test) {
					swap(from, to, true);
				}
			}
			else {
				if (!test) System.out.println("Mossa non valida");
				ok=false;
			}
		}
		
		else if (to.getRow()==from.getRow() && to.getColumn()<from.getColumn()) {
			ok=true;
			for(i=from.getColumn()-1; i>to.getColumn(); i--) {
				if(board[from.getRow()][i].getPlayer()!='e')
					ok=false;
			}
			if (ok==true && to.getPlayer()!=player) {
				if (!test) {
					swap(from, to, true);
				}
			}
			else {
				if (!test) System.out.println("Mossa non valida");
				ok=false;
			}
		}
		
		else if (to.getColumn()==from.getColumn() && to.getRow()>from.getRow()) {
			ok=true;
			for(i=from.getRow()+1; i<to.getRow(); i++) {
				if(board[i][from.getColumn()].getPlayer()!='e')
					ok=false;
			}
			if (ok==true && to.getPlayer()!=player) {
				if (!test) {
					swap(from, to, true);
				}
			}
			else {
				if (!test) System.out.println("Mossa non valida");
				ok=false;
			}
		}
		
		else if (to.getColumn()==from.getColumn() && to.getRow()<from.getRow()) {
			ok=true;
			for(i=from.getRow()-1; i>to.getRow(); i--) {
				if(board[i][from.getColumn()].getPlayer()!='e')
					ok=false;
			}
			if (ok==true && to.getPlayer()!=player) {
				if (!test) {
					swap(from, to, true);
				}
			}
			else {
				if (!test) System.out.println("Mossa non valida");
				ok=false;
			}
		}
		
		else {
			if (!test) System.out.println("Mossa non valida");
			ok=false;}
		
		return ok;
	}
	
	public boolean testMoveKnight (char player, int xFrom, int yFrom, int xTo, int yTo, boolean test) {
		Tile from=board[xFrom][yFrom];
		Tile to=board[xTo][yTo];
		return moveKnight (player, from, to, test);
	}
	
	// Contiene le regole riguardo ai movimenti del cavallo
	private boolean moveKnight(char player, Tile from, Tile to, boolean test) {
		
		boolean ok;
		
		if((to.getRow()==from.getRow()+1 || to.getRow()==from.getRow()-1) && (to.getColumn()==from.getColumn()+2 || to.getColumn()==from.getColumn()-2) && to.getPlayer()!=player) {
			if (!test) {
				swap(from, to, true);
			}
				ok=true;
		}
		else if((to.getRow()==from.getRow()+2 || to.getRow()==from.getRow()-2) && (to.getColumn()==from.getColumn()+1 || to.getColumn()==from.getColumn()-1) && to.getPlayer()!=player) {
			if (!test) {
				swap(from, to, true);
			}
				ok=true;
		}
		else {
			if (!test) System.out.println("Mossa non valida");
			ok=false;
		}
		
		return ok;
	}
	
	public boolean testMoveBishop (char player, int xFrom, int yFrom, int xTo, int yTo, boolean test) {
		Tile from=board[xFrom][yFrom];
		Tile to=board[xTo][yTo];
		return moveBishop (player, from, to, test);
	}
	
	// Contiene le regole riguardo ai movimenti dell'alfiere
	private boolean moveBishop(char player, Tile from, Tile to, boolean test) {
	
		int i;
		boolean ok;
		
		if(to.getRow()>from.getRow() && to.getColumn()>from.getColumn()) {
			ok=true;
			for(i=1; from.getRow()+i<to.getRow() && from.getRow()+i<8 && from.getColumn()+i<8 && ok==true; i++) {
				if (board[from.getRow()+i][from.getColumn()+i].getPlayer()!='e')
					ok=false;
			}
			if (ok==true && to.getPlayer()!=player && to.getRow()==from.getRow()+i && to.getColumn()==from.getColumn()+i) {
				if (!test) {
					swap(from, to, true);
				}
			}
			else {
				if (!test) System.out.println("Mossa non valida");
				ok=false;}
		}
		else if(to.getRow()>from.getRow() && to.getColumn()<from.getColumn()) {
			ok=true;
			for(i=1; from.getRow()+i<to.getRow() && from.getRow()+i<8 && from.getColumn()-i>-1 && ok==true; i++) {
				if (board[from.getRow()+i][from.getColumn()-i].getPlayer()!='e')
					ok=false;
			}
			if (ok==true && to.getPlayer()!=player && to.getRow()==from.getRow()+i && to.getColumn()==from.getColumn()-i) {
				if (!test) {
					swap(from, to, true);
				}
			}	
			else {
				if (!test) System.out.println("Mossa non valida");
				ok=false;}
		}
		else if(to.getRow()<from.getRow() && to.getColumn()>from.getColumn()) {
			ok=true;
			for(i=1; from.getRow()-i>to.getRow() && from.getRow()-i>-1 && from.getColumn()+i<8 && ok==true; i++) {
				if (board[from.getRow()-i][from.getColumn()+i].getPlayer()!='e')
					ok=false;
			}
			if (ok==true && to.getPlayer()!=player && to.getRow()==from.getRow()-i && to.getColumn()==from.getColumn()+i) {
				if (!test) {
					swap(from, to, true);
				}
			}	
			else {
				if (!test) System.out.println("Mossa non valida");
				ok=false;}
		}
		else if(to.getRow()<from.getRow() && to.getColumn()<from.getColumn()) {
			ok=true;
			for(i=1; from.getRow()-i>to.getRow() && from.getRow()-i>-1 && from.getColumn()-i>-1 && ok==true; i++) {
				if (board[from.getRow()-i][from.getColumn()-i].getPlayer()!='e')
					ok=false;
			}
			if (ok==true && to.getPlayer()!=player && to.getRow()==from.getRow()-i && to.getColumn()==from.getColumn()-i) {
				if (!test) {
					swap(from, to, true);
				}
			}	
			else {
				if (!test) System.out.println("Mossa non valida");
				ok=false;}
		}
		else {
			if (!test) System.out.println("Mossa non valida");
			ok=false;}
		
		return ok;

	}
	
	public boolean testMoveKing (char player, int xFrom, int yFrom, int xTo, int yTo, boolean test) {
		Tile from=board[xFrom][yFrom];
		Tile to=board[xTo][yTo];
		return moveKing (player, from, to, test);
	}
	
	// Contiene le regole riguardo ai movimenti del re
	private boolean moveKing (char player, Tile from, Tile to, boolean test) {
		
		boolean ok;
		
		if((to.getRow()<=from.getRow()+1 && to.getRow()>=from.getRow()-1) && (to.getColumn()<=from.getColumn()+1 && to.getColumn()>=from.getColumn()-1) && to.getPlayer()!=player && from!=to) {
			if (!test) {
				swap(from, to, true);
			}
				ok=true;
		}
		else {
			if (!test) System.out.println("Mossa non valida");
			ok=false;}
		
		return ok;
		
	}
	
	// Controlla se c'è una pedina dell'avversario che tiene il re sotto scacco
	private boolean control(Tile to, int king) {
		
		int row, i;
		boolean unsafe=false;
		
		board[database[king][4].getRow()][database[king][4].getColumn()]=new Tile(0,0,"empty",'e',0);
		
		if (turn=='b')
			row=1;
		else
			row=0;
		
		for(i=0; i<16; i++) {

			if(database[row][i].getPieceType()=="pawn")
				unsafe=movePawn(database[row][i], to, true);

			else if(database[row][i].getPieceType()=="knight")	
				unsafe=moveKnight(database[row][i].getPlayer(), database[row][i], to, true);
				
			else if(database[row][i].getPieceType()=="rook")
				unsafe=moveRook(database[row][i].getPlayer(), database[row][i], to, true);
			
			else if(database[row][i].getPieceType()=="bishop")
				unsafe=moveBishop(database[row][i].getPlayer(), database[row][i], to, true);
			
			else if(database[row][i].getPieceType()=="queen") {	
				if (to.getRow()==database[row][i].getRow() || to.getColumn()==database[row][i].getColumn())
					unsafe=moveRook(database[row][i].getPlayer(), database[row][i], to, true);
				else
					unsafe=moveBishop(database[row][i].getPlayer(), database[row][i], to, true);
			}
			
			else if(database[row][i].getPieceType()=="king")
				unsafe=moveKing(database[row][i].getPlayer(), database[row][i], to, true);

			else unsafe=false;
			
			if (unsafe==true) {
				board[database[king][4].getRow()][database[king][4].getColumn()]=database[king][4];
				return false;
			}
		}
		
		board[database[king][4].getRow()][database[king][4].getColumn()]=database[king][4];
		return true;
	}
	
	public boolean testCheckmate() {
		return checkmate();
	}
	
	// Controlla se è avvenuto lo scacco matto
	private boolean checkmate() {
		
	int king, row, column;
	Tile tempTo=new Tile(to.getRow(),to.getColumn(),to.getPieceType(),to.getPlayer(),to.getMoves());

	
		if(turn=='b')
			king=0;
		else
			king=1;
		
		// ciclo che controlla se il re può muoversi in una posizione sicura
		if (control(database[king][4], king)==false) {
			for (int y=0; y<3; y++) {
				for (int x=0; x<3; x++) {
					if ((x+database[king][4].getRow()-1>-1 && x+database[king][4].getRow()-1<8) && (y+database[king][4].getColumn()-1>-1 && y+database[king][4].getColumn()-1<8)) {
						from=database[king][4];
						to=board[x+database[king][4].getRow()-1][y+database[king][4].getColumn()-1];
						if (check()==true)
							return false;
					}
				}
			}
		}
		else
			return false;
	
		// Ciclo che controlla se una pedina del giocatore può mangiare la minaccia
		for (int x=0; x<16; x++) {
			if (database[king][x].getPieceType()!="empty") {
				from=database[king][x];
				to=lastMoveTo;
				if (check()==true) {
					lastMoveTo=tempTo;
					return false;
				}
			}
		}
		
		lastMoveTo=tempTo;
		row=database[king][4].getRow()-lastMoveTo.getRow();
		column=database[king][4].getColumn()-lastMoveTo.getColumn();
		
		// Serie di cicli che controlla se una pedina del giocatore può piazzarsi tra il re e la minaccia
		if(row==0 && column>0 && (lastMoveTo.getPieceType()=="rook" || lastMoveTo.getPieceType()=="queen")) {
			for (int y=0; y<column; y++) { 
				for (int x=0; x<16; x++) {
					if (database[king][x].getPieceType()!="empty") {
						from=database[king][x];
						to=board[lastMoveTo.getRow()][lastMoveTo.getColumn()+y];
						if (check()==true)
							return false;
					}
				}
			}
		}
			
		else if(row==0 && column<0 && (lastMoveTo.getPieceType()=="rook" || lastMoveTo.getPieceType()=="queen")) {
			for (int y=0; y<(-column); y++) { 
				for (int x=0; x<16; x++) {
					if (database[king][x].getPieceType()!="empty") {
						from=database[king][x];
						to=board[lastMoveTo.getRow()][lastMoveTo.getColumn()-y];
						if (check()==true)
							return false;
					}
				}
			}
		}
		
		else if(row>0 && column==0 && (lastMoveTo.getPieceType()=="rook" || lastMoveTo.getPieceType()=="queen")) {
			for (int y=0; y<row; y++) { 
				for (int x=0; x<16; x++) {
					if (database[king][x].getPieceType()!="empty") {
						from=database[king][x];
						to=board[lastMoveTo.getRow()+y][lastMoveTo.getColumn()];
						if (check()==true)
							return false;
					}
				}
			}
		}
			
		else if(row<0 && column==0 && (lastMoveTo.getPieceType()=="rook" || lastMoveTo.getPieceType()=="queen")) {
			for (int y=0; y<(-row); y++) { 
				for (int x=0; x<16; x++) {
					if (database[king][x].getPieceType()!="empty") {
						from=database[king][x];
						to=board[lastMoveTo.getRow()-y][lastMoveTo.getColumn()];
						if (check()==true)
							return false;
					}
				}
			}
		}
		
		else if(row>0 && column>0 && (lastMoveTo.getPieceType()=="bishop" || lastMoveTo.getPieceType()=="queen")) {
			for (int y=0; y<row; y++) { 
				for (int x=0; x<16; x++) {
					if (database[king][x].getPieceType()!="empty") {
						from=database[king][x];
						to=board[lastMoveTo.getRow()+y][lastMoveTo.getColumn()+y];
						if (check()==true)
							return false;
					}
				}
			}
		}
		
		else if(row>0 && column<0 && (lastMoveTo.getPieceType()=="bishop" || lastMoveTo.getPieceType()=="queen")) {
			for (int y=0; y<row; y++) { 
				for (int x=0; x<16; x++) {
					if (database[king][x].getPieceType()!="empty") {
						from=database[king][x];
						to=board[lastMoveTo.getRow()+y][lastMoveTo.getColumn()-y];
						if (check()==true)
							return false;
					}
				}
			}
		}
		
		else if(row<0 && column>0 && (lastMoveTo.getPieceType()=="bishop" || lastMoveTo.getPieceType()=="queen")) {
			for (int y=0; y<column; y++) { 
				for (int x=0; x<16; x++) {
					if (database[king][x].getPieceType()!="empty") {
						from=database[king][x];
						to=board[lastMoveTo.getRow()-y][lastMoveTo.getColumn()+y];
						if (check()==true)
							return false;
					}
				}
			}
		}
		
		else if(row<0 && column<0 && (lastMoveTo.getPieceType()=="bishop" || lastMoveTo.getPieceType()=="queen")) {
			for (int y=0; y<(-column); y++) { 
				for (int x=0; x<16; x++) {
					if (database[king][x].getPieceType()!="empty") {
						from=database[king][x];
						to=board[lastMoveTo.getRow()-y][lastMoveTo.getColumn()-y];
						if (check()==true)
							return false;
					}
				}
			}
		}
		
	return true;	
	}
	
	public boolean testDraw () {
		return draw();
	}
	
	// Controlla se la partita ha raggiunto una situazione di parità
	private boolean draw() {
		
		boolean b=true;
		boolean w=true;
		boolean b2=true;
		boolean w2=true;
		boolean isSafe=false;
		int king;
		
		if(turn=='b')
			king=0;
		else
			king=1;
		
		// Controlla se c'è una pedina che possa eseguire una mossa che non metta il re in pericolo
		if (control(database[king][4], king)==true) {
			for (int i=0; i<16; i++) {
				for (int y=0; y<8; y++) {
					for (int x=0; x<8; x++) {
						if (database[king][i].getPieceType()!="empty") {
							from=database[king][i];
							to=board[x][y];
							if (check()==true)
								isSafe=true;
						}
					}
				}
			}
		}
		else return false;
		
		if (isSafe==false) return true;
		
		if (lastCapture==100)
			return true;
		
		//Se le uniche pedine rimaste sono i re, dichiara il pareggio
		for (int i=0; i<16; i++) {
			if (database[0][i].getPieceType()!="empty" && database[0][i].getPieceType()!="king")
				b=false;
		}
		for (int i=0; i<16; i++) {
			if (database[1][i].getPieceType()!="empty" && database[1][i].getPieceType()!="king")
				w=false;		
		}
		if (b && w)
			return true;
		
		//Se le uniche pedine rimaste sono i re e un cavallo, dichiara il pareggio
		for (int i=0; i<16; i++) {
			if (database[0][i].getPieceType()!="empty" && database[0][i].getPieceType()!="king" && database[0][i].getPieceType()!="knight")
				b2=false;
		}
		for (int i=0; i<16; i++) {
			if (database[1][i].getPieceType()!="empty" && database[1][i].getPieceType()!="king" && database[0][i].getPieceType()!="knight")
				w2=false;
		}
		if ((((database[0][1].getPieceType()=="knight"  && database[0][6].getPieceType()=="empty") || (database[0][1].getPieceType()=="empty"  && database[0][6].getPieceType()=="knight")) && w && b2) || (((database[1][1].getPieceType()=="knight"  && database[1][6].getPieceType()=="empty") || (database[1][1].getPieceType()=="empty"  && database[1][6].getPieceType()=="knight")) && b && w2))
			return true;
		
		b2=true;
		w2=true;
		
		//Se le uniche pedine rimaste sono i re e un alfiere, dichiara il pareggio
		for (int i=0; i<16; i++) {
			if (database[0][i].getPieceType()!="empty" && database[0][i].getPieceType()!="king" && database[0][i].getPieceType()!="bishop")
				b2=false;
		}
		for (int i=0; i<16; i++) {
			if (database[1][i].getPieceType()!="empty" && database[1][i].getPieceType()!="king" && database[0][i].getPieceType()!="bishop")
				w2=false;
		}
		if ((((database[0][2].getPieceType()=="bishop"  && database[0][5].getPieceType()=="empty") || (database[0][2].getPieceType()=="empty"  && database[0][5].getPieceType()=="bishop")) && w && b2) || (((database[1][2].getPieceType()=="bishop"  && database[1][5].getPieceType()=="empty") || (database[1][2].getPieceType()=="empty"  && database[1][5].getPieceType()=="bishop")) && b && w2))
			return true;
		
		b=true;
		w=true;
		
		//Se le uniche pedine rimaste sono il re e un alfiere su una casella dello stesso colore per entrambi i giocatori, dichiara il pareggio
		for (int i=0; i<16; i++) {
			if (database[0][i].getPieceType()!="empty" && database[0][i].getPieceType()!="king" && database[0][i].getPieceType()!="bishop")
				b=false;		
		}
		for (int i=0; i<16; i++) {
			if (database[1][i].getPieceType()!="empty" && database[1][i].getPieceType()!="king" && database[1][i].getPieceType()!="bishop")
				w=false;		
		}
		if ((database[0][2].getPieceType()=="bishop"  && database[0][5].getPieceType()=="empty" && database[1][2].getPieceType()=="empty" && database[1][5].getPieceType()=="bishop") || (database[0][2].getPieceType()=="empty" && database[0][5].getPieceType()=="bishop" && database[1][2].getPieceType()=="bishop" && database[1][5].getPieceType()=="empty") && b && w)
			return true;	
		
		return false;
	}
}