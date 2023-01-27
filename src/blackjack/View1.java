package blackjack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;
/***************************************************
 * 
 * This View displays all the cards in the deck
 *
 ***************************************************/
public class View1 extends JFrame {

	//private GameViewModel gameViewModel;
	private JPanel mainPanel;

	static final int PANW = 1200;
	static final int PANH = 1000;

	//TODO: add GameViewModel to constructor?
	public View1() {       
		setupJFrame();
	}

	void setupJFrame() {
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0,100,0));
		mainPanel.setPreferredSize(new Dimension(PANW, PANH));
		mainPanel.setLayout(null);

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
		int w = Deck.CARDW;
		int h = Deck.CARDH;


		JLabel label = new JLabel();
		label.setIcon( new ImageIcon( cardBack ));
		label.setBounds(10,10,w,h);
		mainPanel.add(label);

		int i=0;
		for (Card c: deck) {
			label = new JLabel();
			label.setIcon( new ImageIcon(c.image) );
			label.setBounds(10+(i%13)*(w+10), h+20+(h+10)*(i/13), w,h);
			mainPanel.add(label);			
			i++;
		}
		//one of the two following will get the cards to actually display, otherwise some are not drawn
		this.repaint();
		//this.pack();
	}
}
