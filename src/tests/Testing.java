package tests;
/* This class is for testing various objects */

import blackjack.*;

public class Testing {

	public static void main(String[] args) {
		Deck deck = new Deck();
		
		for (Card c: deck.deck) {
			System.out.println(c);
		}

	}

}
