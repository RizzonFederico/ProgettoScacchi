package Controller;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import Model.Board;
import View.TextArea;

public class Component extends JComponent {
	
	private static final long serialVersionUID = 1L;
	//Crea l'ogetto Board
	private Board board=new Board();
	//Crea una matrice 8x8 di bottoni
	protected JButton[][] buttons= new JButton [8][8];
	//Variabili per il salvataggio delle coordinate della casella di partenza
	private int x2=0;
	private int y2=0;
	//Variabile per il controllo della validità del turno
	private boolean ok=false;
	//Variabile per il controllo delle funzioni dei bottoni
	private boolean flag=false;
	//Crea l'ogetto TextArea per la stampa delle mosse
	private TextArea scrollPanel = new TextArea();
	// Immagini per la scacchiera e le pedine
	private final Image chessboard = new ImageIcon("img/chessboard.jpg").getImage();
	private final ImageIcon wp = new ImageIcon("img/White_Pawn.png");
	private final ImageIcon bp = new ImageIcon("img/Black_Pawn.png");
	private final ImageIcon wr = new ImageIcon("img/White_Rook.png");
	private final ImageIcon br = new ImageIcon("img/Black_Rook.png");
	private final ImageIcon wkn = new ImageIcon("img/White_Knight.png");
	private final ImageIcon bkn = new ImageIcon("img/Black_Knight.png");
	private final ImageIcon wb = new ImageIcon("img/White_Bishop.png");
	private final ImageIcon bb = new ImageIcon("img/Black_Bishop.png");
	private final ImageIcon wq = new ImageIcon("img/White_Queen.png");
	private final ImageIcon bq = new ImageIcon("img/Black_Queen.png");
	private final ImageIcon wk = new ImageIcon("img/White_King.png");
	private final ImageIcon bk = new ImageIcon("img/Black_King.png");

	@Override
	public Dimension getPreferredSize () {
		return new Dimension(512, 512);
	}
	
	// Costruttore che imposta la forma della griglia e imposta le funzioni dei bottoni
	public Component() {
		
		setLayout(new GridLayout(8, 8));
		
		int x;
		int y;
		
		for (x=0; x<8; x++) {
			for (y=0; y<8; y++) {
				add(buttons[x][y]=newButton(x, y));
			}
		}	
	}
	
	public TextArea getScrollPanel () {
		return scrollPanel;
	}
	
	// Imposta le informazioni sui bottoni e specifica l'azione da compiere quando vengono premuti
	protected JButton newButton(final int x, final int y) {
		final JButton button = new JButton ();
		button.setIcon(addIcon(x, y));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		// La prima pressione salva le coordinate della casella di partenza, la seconda effettua
		// lo scambio e aggiornamento delle immagini per corrispondere alla scacchiera
		button.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				if (board.getTile(x, y).getPlayer()==board.getTurn() || flag==true) {
					if (flag==false) {
						x2=x;
						y2=y;
						flag=true;
						board.getInfo(x, y);
					}
				
					else {
						ok=board.getInfo(x, y);
						if (ok==true) {
							swapButtons();
							AddText();
							ok=false; }
						button.setIcon(addIcon(x, y));
						x2=0;
						y2=0;
						flag=false;
					}
				}
			}
			
			private void swapButtons () {
			buttons[x][y].setIcon(buttons[x2][y2].getIcon());
			buttons[x2][y2].setIcon(null);
			}
			
			private void AddText () {
				scrollPanel.getTextArea().append(board.getText()+"\n");
			}
			
		});
		return button;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(chessboard==null)
			return;
		
		g.drawImage(chessboard, 0, 0, null);
	}
	
	private ImageIcon addIcon (int x, int y) {
		
		if(board.getTile(x, y).getPlayer()=='w') {
			if(board.getTile(x, y).getPieceType()=="pawn")
				return wp;
			else if(board.getTile(x, y).getPieceType()=="rook")
				return wr;
			else if(board.getTile(x, y).getPieceType()=="knight")
				return wkn;
			else if(board.getTile(x, y).getPieceType()=="bishop")
				return wb;
			else if(board.getTile(x, y).getPieceType()=="queen")
				return wq;
			else if(board.getTile(x, y).getPieceType()=="king")
				return wk;
			else return null;
		}
		
		else if(board.getTile(x, y).getPlayer()=='b') {
			if(board.getTile(x, y).getPieceType()=="pawn")
				return bp;
			else if(board.getTile(x, y).getPieceType()=="rook")
				return br;
			else if(board.getTile(x, y).getPieceType()=="knight")
				return bkn;
			else if(board.getTile(x, y).getPieceType()=="bishop")
				return bb;
			else if(board.getTile(x, y).getPieceType()=="queen")
				return bq;
			else if(board.getTile(x, y).getPieceType()=="king")
				return bk;
			else return null;
		}
		
		else return null;
			
	}
}