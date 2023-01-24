
//package FinalProjects;
/*
 * Gillian Middleton attempts to make a game of solitaire
 * starting on October 28, 2020
 * finishing... never...
 */
import java.awt.Color;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Solitaire extends JFrame {

	public static void main(String[] args) {
		new Solitaire();
	}

	int winw = 1200;
	int winh = 900;
	BufferedImage imgback;

	Card currentCard = null;
	boolean mouseDrag = false;
	Point dragPt = null;

	ArrayList<Card> deck = new ArrayList<Card>();
	ArrayList<Card> discard = new ArrayList<Card>();

	ArrayList<Card> acedim = new ArrayList<Card>();
	ArrayList<Card> aceclub = new ArrayList<Card>();
	ArrayList<Card> aceheart = new ArrayList<Card>();
	ArrayList<Card> acespade = new ArrayList<Card>();

	ArrayList<Card> stack1 = new ArrayList<Card>();
	ArrayList<Card> stack2 = new ArrayList<Card>();
	ArrayList<Card> stack3 = new ArrayList<Card>();
	ArrayList<Card> stack4 = new ArrayList<Card>();
	ArrayList<Card> stack5 = new ArrayList<Card>();
	ArrayList<Card> stack6 = new ArrayList<Card>();
	ArrayList<Card> stack7 = new ArrayList<Card>();

	Rectangle deckR = new Rectangle(0,0,Card.CARDW, Card.CARDH);
	Rectangle discardR = new Rectangle(100,20,Card.CARDW, Card.CARDH);

	Rectangle stackadim = new Rectangle(730, 20, Card.CARDW, Card.CARDH);
	Rectangle stackaclub = new Rectangle(811, 20, Card.CARDW, Card.CARDH);
	Rectangle stackaheart = new Rectangle(892, 20, Card.CARDW, Card.CARDH);
	Rectangle stackaspade = new Rectangle(973, 20, Card.CARDW, Card.CARDH);

	Rectangle stack1R = new Rectangle(110,200,Card.CARDW, Card.CARDH);
	Rectangle stack2R = new Rectangle(191,200,Card.CARDW, Card.CARDH);
	Rectangle stack3R = new Rectangle(272,200,Card.CARDW, Card.CARDH);
	Rectangle stack4R = new Rectangle(353,200,Card.CARDW, Card.CARDH);
	Rectangle stack5R = new Rectangle(434,200,Card.CARDW, Card.CARDH);
	Rectangle stack6R = new Rectangle(515,200,Card.CARDW, Card.CARDH);
	Rectangle stack7R = new Rectangle(596,200,Card.CARDW, Card.CARDH);

	DrawingPanel panel = new DrawingPanel();

	BufferedImage backImg = null;

	Solitaire() {


		createCards();
		loadImages();

		deckR.translate(10,20);
		//System.out.println(deck);
		Collections.shuffle(deck);
		//System.out.println(deck);


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Solitaire");
		//this.setLayout(new GridLayout(2, 7, 10, 10)); don't need this anymore
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	void createCards() {
		for (int suit = 0; suit < 4; suit++) {
			for (int value = 0; value < 13; value++) {			
				Card card = new Card(value+1,suit); //ace=1; 2=2 yay
				deck.add(card);
			}
		}
	}


	void loadImages() {
		BufferedImage imgSprites = null;
		try {
			//			imgSprites = ImageIO.read(new File (Paths.get("").toAbsolutePath().toString() + "//" + "SolitaireCards.png"));
			imgSprites = ImageIO.read(new File ("SolitaireCards.png"));
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println("ERROR: no images loaded");
			System.exit(0);
		}

		//slice images and add to the correct card
		//this must be done before shuffling
		for (int i = 0; i < 52; i++) {
			BufferedImage img = imgSprites.getSubimage(i%13*Card.CARDW, i/13*Card.CARDH, Card.CARDW, Card.CARDH);
			deck.get(i).setImage(img);
		}
		backImg = imgSprites.getSubimage(0, 4*Card.CARDH, Card.CARDW, Card.CARDH);
	}

	//so far can only move from discard pile
	boolean cardPlacement(ArrayList<Card> removeStack) {
		if (currentCard == null) return false;

		//checks with ace piles
		if (stackadim.contains(dragPt)) {
			if(currentCard.getSuit()==Card.DIAMONDS) {
				//start with adding an ace
				if (acedim.size() == 0) {
					if (currentCard.getValue() == 1) {
						//addCurrentCard to diamond stack
						acedim.add(currentCard);
						removeStack.remove(currentCard);
						//currentCard = null;
						//return false;
					}
				}
				//at least one card is there
				int sz = acedim.size();
				if(currentCard.getValue() == sz+1) {
					acedim.add(currentCard);
					removeStack.remove(currentCard);
				}
			}
		}

		if (stackaclub.contains(dragPt)) {
			if(currentCard.getSuit()==Card.CLUBS) {
				if(aceclub.size() == 0) {
					if(currentCard.getValue() == 1) {
						aceclub.add(currentCard);
						removeStack.remove(currentCard);
					}
				}
				int sz = aceclub.size();
				if(currentCard.getValue() == sz+1) {
					aceclub.add(currentCard);
					removeStack.remove(currentCard);
				}
			}
		}

		if (stackaheart.contains(dragPt)) {
			if(currentCard.getSuit()==Card.HEARTS) {
				if(aceheart.size() == 0) {
					if(currentCard.getValue() == 1) {
						aceheart.add(currentCard);
						removeStack.remove(currentCard);
					}
				}
				int sz = aceheart.size();
				if(currentCard.getValue() == sz+1) {
					aceclub.add(currentCard);
					removeStack.remove(currentCard);
				}
			}
		}
		if (stackaspade.contains(dragPt)) {
			if(currentCard.getSuit()==Card.SPADES) {
				if(acespade.size() == 0) {
					if(currentCard.getValue() == 1) {
						acespade.add(currentCard);
						removeStack.remove(currentCard);
					}
				}
				int sz = acespade.size();
				if(currentCard.getValue() == sz+1) {
					acespade.add(currentCard);
					removeStack.remove(currentCard);
				}
			}
		}
		//checks with stacks
		if (stack1R.contains(dragPt)) {
			//if there's no card in the stack and the current card is a king place it
			if(stack1.size() == 0) {
				if (currentCard.getValue() == 13) {
				stack1.add(currentCard);
				removeStack.remove(currentCard);
				}
			}
			int sz = stack1.size();
			//checks to make sure the card being placed is 1 value less then the card placed
			if (currentCard.getValue() == 13-sz) {
				stack1.add(currentCard);
				removeStack.remove(currentCard);
			}
		}
		if (stack2R.contains(dragPt)) {
			if(stack2.size() == 0) {
				if(currentCard.getValue() == 13) {
			stack2.add(currentCard);
			removeStack.remove(currentCard);
				}
			}
			int sz = stack2.size();
			if (currentCard.getValue() == 13-sz) {
				stack2.add(currentCard);
				removeStack.remove(currentCard);
			}
		}
		if (stack3R.contains(dragPt)) {
			if(stack3.size()==0) {
				if(currentCard.getValue()==13) {
			stack3.add(currentCard);
			removeStack.remove(currentCard);
				}
			}
			int sz = stack3.size();
			if (currentCard.getValue() == 13-sz) {
				stack3.add(currentCard);
				removeStack.remove(currentCard);
			}
		}
		if (stack4R.contains(dragPt)) {
			if(stack4.size()==0) {
				if(currentCard.getValue()==13) {
			stack4.add(currentCard);
			removeStack.remove(currentCard);
				}
			}
			int sz = stack4.size();
			if (currentCard.getValue() == 13-sz) {
				stack4.add(currentCard);
				removeStack.remove(currentCard);
			}
		}
		if (stack5R.contains(dragPt)) {
			if(stack5.size()==0) {
				if(currentCard.getValue()==13) {
			stack5.add(currentCard);
			removeStack.remove(currentCard);
				}
			}
			int sz = stack5.size();
			if (currentCard.getValue() == 13-sz) {
				stack5.add(currentCard);
				removeStack.remove(currentCard);
			}
		}
		if (stack6R.contains(dragPt)) {
			if(stack6.size()==0) {
				if(currentCard.getValue()==13) {
			stack6.add(currentCard);
			removeStack.remove(currentCard);
				}
			}
			int sz = stack6.size();
			if (currentCard.getValue() == 13-sz) {
				stack6.add(currentCard);
				removeStack.remove(currentCard);
			}
		}
		if (stack7R.contains(dragPt)) {
			if(stack7.size()==0) {
				if(currentCard.getValue()==13) {
			stack7.add(currentCard);
			removeStack.remove(currentCard);
				}
			}
			int sz = stack7.size();
			if (currentCard.getValue() == 13-sz) {
				stack7.add(currentCard);
				removeStack.remove(currentCard);
			}
		}
		currentCard = null;
		return false;
	}


	private class DrawingPanel extends JPanel {
		DrawingPanel() {
			this.setBackground(Color.darkGray);
			this.setPreferredSize(new Dimension(winw,winh));
			//this action listener listens to the mouse
			MyMouseListener ml = new MyMouseListener();
			addMouseListener(ml);
			addMouseMotionListener(ml);
		}
		Font symbol = new Font("Arial", Font.PLAIN, 30);

		//game board
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(symbol);

			g.setColor(Color.RED);
			String heart = "" + '\u2665';
			g.drawString(heart, 920, 140);
			String diamond = "" + '\u2666';
			g.drawString(diamond, 760, 140);

			g.setColor(Color.BLACK);
			String club = "" + '\u2663';
			g.drawString(club, 840, 140);
			String spade = "" + '\u2660';
			g.drawString(spade, 1005, 140);

			//DEBUG : draw outline rectangle
			g.setColor(Color.lightGray);
			g.drawRect(stack1R.x, stack1R.y, stack1R.width, stack1R.height);
			g.drawRect(stack2R.x, stack2R.y, stack2R.width, stack2R.height);
			g.drawRect(stack3R.x, stack3R.y, stack3R.width, stack3R.height);
			g.drawRect(stack4R.x, stack4R.y, stack4R.width, stack4R.height);
			g.drawRect(stack5R.x, stack5R.y, stack5R.width, stack5R.height);
			g.drawRect(stack6R.x, stack6R.y, stack6R.width, stack6R.height);
			g.drawRect(stack7R.x, stack7R.y, stack7R.width, stack7R.height);

			g.drawRect(stackadim.x, stackadim.y, stackadim.width, stackadim.height);
			g.drawRect(stackaclub.x, stackaclub.y, stackaclub.width, stackaclub.height);
			g.drawRect(stackaheart.x, stackaheart.y, stackaheart.width, stackaheart.height);
			g.drawRect(stackaspade.x, stackaspade.y, stackaspade.width, stackaspade.height);

			//DEBUG: draw all cards
			/*
			int count = 0;
			for (int i = 0; i < deck.size(); i++) {
				Card c = deck.get(i);
				g.drawImage(c.image, i%13*Card.CARDW, i/13*Card.CARDH,Card.CARDW, Card.CARDH, null);	
				count+= Card.CARDW;	
			}
			 */
			//display deck:
			if (deck.size() > 0) {
				g.drawImage(backImg,deckR.x, deckR.y, deckR.width, deckR.height, null);
			}
			//display discard
			if (discard.size() > 0) {
				Card c = discard.get(discard.size()-1);
				g.drawImage(c.image, discardR.x, discardR.y, deckR.width, deckR.height, null);
			}


			//draw stack1
			drawStack(g, stack1, stack1R, true); //pass the name of the stack and if you want to use "pos"
			//draw stack2
			drawStack(g, stack2, stack2R, true);
			//draw stack3
			drawStack(g, stack3, stack3R, true);
			//draw stack4
			drawStack(g, stack4, stack4R, true);
			//draw stack5
			drawStack(g, stack5, stack5R, true);
			//draw stack6
			drawStack(g, stack6, stack6R, true);
			//draw stack7
			drawStack(g, stack7, stack7R, true);

			//diamond ace stack
			drawStack(g, acedim, stackadim, false);
			//club ace stack
			drawStack(g, aceclub, stackaclub, false);
			//heart ace stack
			drawStack(g, aceheart, stackaheart, false);
			//spade ace stack
			drawStack(g, acespade, stackaspade, false);

			//Point pos = new Point (0,0);
			//			for(Card c: stack1) {
			//				g.drawImage(c.image, stack1R.x + pos.x, stack1R.y + pos.y, deckR.width, deckR.height, null);
			//
			//				pos.x+=0;
			//				pos.y+=20;
			//			}

			//for (Card c : acedim) {
			//	g.drawImage(c.image, stackadim.x, stackadim.y, deckR.width, deckR.height, null);
			//}

			//this show the cad being dragged
			if (mouseDrag && currentCard != null) {
				g.drawImage(currentCard.image, dragPt.x, dragPt.y, deckR.width, deckR.height, null);
			}
		}
	}

	void drawStack(Graphics g, ArrayList<Card> stack, Rectangle r, boolean usePos) {
		Point pos = new Point (0,0);
		for(Card c: stack) {
			g.drawImage(c.image, r.x + pos.x, r.y + pos.y, deckR.width, deckR.height, null);
			if (usePos) {
				pos.x+=0;
				pos.y+=20;
			}
		}

	}


	/* NOTE: this takes cards off of the bottom of the deck. But it doesn't really matter.
	 * Everything else has to take cards off of the top */
	void discard() {
		//if you change the middle 'i' it changes the amount of cards dealt out to the discards pile
		for (int i = 0; i <1; i++) { 
			//System.out.println(deck.get(0));
			discard.add(deck.get(0));
			deck.remove(0);	
		}
		if(deck.size()==0) {
			for (int i = discard.size()-1; i >= 0; i--) {
				deck.add(discard.get(i));
				discard.remove(i);
			}
			Collections.shuffle(deck);
		}
	}

	class MyMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			Point p = e.getPoint();
			//click on deck: discard 3
			if(deckR.contains(p) && deck.size() > 0) { 
				discard();
			}			
			//click on discard
			if(discardR.contains(p) && discard.size() > 0) {				
				//testing
				int top = discard.size()-1;
				currentCard = discard.get(top);				
				//stack1.add(currentCard);
				//discard.remove(top);		
			}			
			panel.repaint();
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			mouseDrag=true;
			dragPt = e.getPoint();			
			if (deckR.contains(dragPt)) {
				mouseDrag=false;
				currentCard = null;
				return; // NOT DRAGGING FROM DECK
			}

			panel.repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (mouseDrag)  {
				cardPlacement(discard); //remove from discard
			}
			mouseDrag=false;
			panel.repaint();
		}
	}
}