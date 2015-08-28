import java.awt.EventQueue;

import javax.swing.JFrame;

import View.Frame;

public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new Frame();
				frame.setTitle("Scacchi");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
		
	}
}