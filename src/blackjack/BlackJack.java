package blackjack;

public class BlackJack {

	/* This will be the main class that runs everything */
	
	public static void main(String[] args) {
		
		Deck deck = new Deck();
		View1 view = new View1();
		view.displayAllCards(deck.deck, deck.cardBack);
	}

}
