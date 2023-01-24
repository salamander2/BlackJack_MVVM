package blackjack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
	
	/* This is a mess. It is just for testing.
	 * We're using JLabels to put the images on the JPanel
	 * which means that the buffered images have to be changed to ImageIcons.
	 */
	void displayAllCards(ArrayList<Card> deck, BufferedImage cardBack) {
		//DEBUG: draw all cards
		
		JLabel label = new JLabel();
		label.setIcon( new ImageIcon( deck.get(0).image) );
		mainPanel.add(label);
		
		label = new JLabel();
		label.setIcon( new ImageIcon( cardBack ));
		mainPanel.add(label);
		/*
		 * int count = 0; for (int i = 0; i < deck.size(); i++) { 
		 * Card c = deck.get(i);
		 * g.drawImage(c.image, i%13*Card.CARDW, i/13*Card.CARDH,Card.CARDW, Card.CARDH,
		 * null); count+= Card.CARDW; 
		 * }
		 * 
		 * //display deck: 
		 * if (deck.size() > 0) {
		 * g.drawImage(backImg,deckR.x, deckR.y,
		 * deckR.width, deckR.height, null); 
		 * }
		 */
	}


}
