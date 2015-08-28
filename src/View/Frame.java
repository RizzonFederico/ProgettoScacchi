package View;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

import Controller.Component;

public class Frame extends JFrame {
 
	private static final long serialVersionUID = 1L;
	private Component component=new Component();
	private JPanel centralPanel=new JPanel();
	
	public Frame () {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-400, dim.height/2-300);
		
		centralPanel.add(component);
		
		add(centralPanel, BorderLayout.CENTER);
		
		add(component.getScrollPanel().getScrollPanel(), BorderLayout.EAST);
		
		setIconImage(new ImageIcon("img/Black_Knight.png").getImage());

		pack();
		
	}
}