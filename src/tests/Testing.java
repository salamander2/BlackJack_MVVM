package tests;
/* This class is for testing various objects */

import blackjack.*;

public class Testing {

	public static void main(String[] args) {
		createAndPrintDeck();
	}

	
	static void createAndPrintDeck() {
		Deck deck = new Deck();
		
		//verify that shuffle works
//		deck.shuffle();

		for (Card c: deck.deck) {
			System.out.println(c);
		}

	}
	
	

}
