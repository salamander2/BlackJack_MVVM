package blackjack;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class View1 extends JFrame {

	//private GameViewModel gameViewModel;
	private JPanel mainPanel;

	static final int PANW = 1000;
	static final int PANH = 1000;

	//TODO: add GameViewModel to constructor
	public View1() {       
		setupJFrame();
	}

	void setupJFrame() {
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0,100,0));
		mainPanel.setPreferredSize(new Dimension(PANW, PANH));
		// set to no layout manager:  mainPanel.setLayout();

		this.setTitle("Blackjack");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.add(mainPanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}


}
