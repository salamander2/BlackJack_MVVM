# Organizational Structure
## Files and Model, View, View Model

✏️ I don't understand the difference between MVVM and MVC model. The VM just takes the place of the Controller. 
In spite of reading a number of websites, I don't see a difference in how it actually works.

:one:  Shoud there be **interfaces** for Model, View, and ViewModel, or should these be **abstract classes** that are extended?
2️⃣

### Structure

    implements            implements               implements
    View                  ViewModel                 Model
    
    View1   ----\                              /
                 \-----   BlackJack ----------/    DATA (see below)    
    View2   ----/                             \

The View1 and View2 classes are just to demonstrate how different views can be connected to a ViewModel. 
View1 will just display all of the cards. 
View2 will display cards according to Blackjack positions.

**DATA** 

🔴 Class Deck
 * contains an arraylist of 52 cards.
 * also has the backimage of card
 * is repsonsible for loading image sheet
 * has size of image sprite
 * ❓ when a card is dealt or discarded, where does it go? It's removed from the arraylist.

🔴 Class Card
 * contains the suit and face of each card (both of these are integers)
 * calculates value of card (ie. JQK = 10). Ace is 1.
 * contains correct image associated with each card
 * ❓ I'm using BufferedImage because I'm more used to drawing on JPanel, but most people use JLabels which mean that we need to change the BufferedImage to ImageIcon
 * it is possible for this class to also return R/B to indicate whether it's a red or black card.
 * it is possible for it to return a char (SHCD) to indicate the suit, or even a string to indicate this.
 * I could use an enum to do this. Is it worth it ❓

🔴 Class Player
--to be created still

🔴 Class Dealer

-- to be created still  
