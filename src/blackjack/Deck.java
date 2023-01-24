package blackjack;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//FIXME:  make sure that this works.  Also update the comments above each method (JavaDoc?)

public class Deck implements Model {

	//The deck can be an array (in which case we need to know where the cards are
	//or an arraylist.
	//Card[] deck = new Card[52];
	public ArrayList<Card> deck = new ArrayList<Card>();
	
	private BufferedImage spriteSheet;
	BufferedImage cardBack;
	
	@Override
	public void initialize() {
		shuffle();	
	}

	public Deck(){
		//loadImage("SolitaireCards.png");
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
		//sliceAndSetImages();
	}
	
	void shuffle() {
		
	}
	
	Card deal() {
		Card c = deck.get(0);
		deck.remove(0);
		return c;
	}
	
	static BufferedImage loadImage(String filename) {
		BufferedImage image = null;
		try {
			//images = ImageIO.read(new File (Paths.get("").toAbsolutePath().toString() + "//" + "SolitaireCards.png"));
			image = ImageIO.read(new File (filename) );
		} catch (IOException e) {			
			e.printStackTrace();
			//TODO: change this to JOptionPane
			System.out.println("FATAL ERROR: file not found (" + filename + ")");
			System.exit(0);
		}
		return image;
	}
	/* Slice images and add to the correct card
	 * This must be done before shuffling
	 */
	private void sliceAndSetImages() {
		if (spriteSheet == null || spriteSheet.getWidth() == 0) {
			System.out.println("FATAL ERROR: spritesheet not found");
			System.exit(0);
		}
		
		for (int i = 0; i < 52; i++) {
			BufferedImage img = spriteSheet.getSubimage(i%13*Card.CARDW, i/13*Card.CARDH, Card.CARDW, Card.CARDH);
			deck.get(i).image = img;
		}
		cardBack = spriteSheet.getSubimage(0, 4*Card.CARDH, Card.CARDW, Card.CARDH);
	}

}
