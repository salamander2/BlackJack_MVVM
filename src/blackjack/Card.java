package blackjack;

//import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Card{

	//String cards[] = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",};
	private int suit;
	private int face;
	
//	Rectangle location;
//	int x;
//	int y;
	
	BufferedImage image = null;
	
	//assigning values so I can see if stuff works
	static final int RED = 12;
	static final int BLACK = 13;

	/*
	private enum Suit{
		SPADES(0), HEARTS(1), CLUBS(2), DIAMONDS(3);
		char c;
		Suit(int n) {
			switch(n) {
			case 0:  c = 'S'; break;
			case 1:  c = 'H'; break;
			case 2:  c = 'C'; break;
			case 3:  c = 'D'; break;
			}
		}
		
		char getSuit() {
			return c;
		}
	}
	*/
	
	static final int SPADES = 0;
	static final int HEARTS = 1;
	static final int CLUBS = 2;
	static final int DIAMONDS = 3;
	
	//for image size.
	static final int CARDW = 71; //pixels
	static final int CARDH = 96;
	
	//constructor
	Card(int suit, int value) {
		this.suit = suit;
		this.face = value;
//		location = new Rectangle(x+(71*value),y,CARDW, CARDH);
//		location = new Rectangle(0,0,CARDW, CARDH);
	}
	
	int getSuit() {
		return  suit;
	}
	
	int getValue() {
		return face < 10 ? face:10;
	}
	
	int getColour() {
		if (getSuit() == HEARTS || getSuit() == DIAMONDS) {
			return RED;
		} else {
			return BLACK;
		}
	}

	@Override
	public String toString() {
		return "Suit=" + suit + " Face=" + face;
	}
}