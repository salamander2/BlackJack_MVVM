package blackjack;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

//FIXME:  make sure that this works.  Also update the comments above each method (JavaDoc?)

public class Deck implements Model {

	//The deck can be an array (in which case we need to know where the cards are
	//or an arraylist.
	//Card[] deck = new Card[52];
	public ArrayList<Card> deck = new ArrayList<Card>();

	private BufferedImage spriteSheet;
	BufferedImage cardBack;
	//Size of each card image
	static final int CARDW = 71; //pixels
	static final int CARDH = 96;
		

	@Override
	public void initialize() {
		shuffle();	
	}

	public Deck(){
		spriteSheet = loadImage("SolitaireCards.png");
		createDeck();		
	}

	/* Deck must be created in the following order for the images to match up:
	 * Spades:   A-K
	 * Hearts:   A-K
	 * Clubs:    A-K
	 * Diamonds: A-K
	 */
	void createDeck() {
		deck.clear();
		for (int suit = 0; suit < 4; suit++) {
			for (int value = 1; value <= 13; value++) {
				deck.add(new Card(suit,value));
			}
		}	
		sliceAndSetImages();
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	Card deal() {
		Card c = deck.get(0);
		deck.remove(0);
		return c;
	}

	static BufferedImage loadImage(String filename) {
		BufferedImage image = null;

		// Filename must be relative (no / at beginning) 
		InputStream inputStr = Deck.class.getClassLoader().getResourceAsStream(filename);
		//Method 3. InputStream using ImageIO.read
		if (inputStr != null) {
			try {
				image = ImageIO.read(inputStr);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "An image failed to load: " + filename , "FATAL ERROR", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "An image failed to load: " + filename , "FATAL ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return image;
	}
	/* Slice images and add to the correct card
	 * This must be done before shuffling
	 */
	private void sliceAndSetImages() {
		if (spriteSheet == null || spriteSheet.getWidth() == 0) {
			JOptionPane.showMessageDialog(null, "Spritesheet not found", "FATAL ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		for (int i = 0; i < 52; i++) {
			BufferedImage img = spriteSheet.getSubimage(i%13*CARDW, i/13*CARDH, CARDW, CARDH);
			deck.get(i).image = img;
		}
		cardBack = spriteSheet.getSubimage(0, 4*CARDH, CARDW, CARDH);
	}

}
