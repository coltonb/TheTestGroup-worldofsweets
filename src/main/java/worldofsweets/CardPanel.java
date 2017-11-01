package worldofsweets;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardPanel extends JPanel {
    public int cardsRemaining = 60;	//how many cards are left in the deck, once this hits 0 need to shuffle
    public int cardsDiscarded = 0;		//how many cards have been drawn/played, once this hits 60 needs to reset to 0
	public int cardsPlayed = 0;		//how many cards have been played ALL GAME
    public Card[] drawnCards = new Card[60];	//All the cards played in the game so far. Need to resize if we go over 60
    public Deck cardDeck = new Deck();			//Deck of all the cards

	public CardPanel(){
		//do nothing
	}
	
    public CardPanel(WorldOfSweets game) {
        //Create Panel
		this.setLayout(new FlowLayout());
        
        //Create sub-panels
        JPanel drew = new JPanel();			//holds last card drawn
        JPanel discard = new JPanel();		//holds number of cards played so far
        JPanel deck = new JPanel();			//holds all cards remaining to be drawn
        this.add(drew);
        this.add(discard);
        this.add(deck);
        
		//Customize sub-panels.
        drew.setBackground(Color.black);
        drew.setPreferredSize(new Dimension(150,300));
        discard.setBackground(Color.blue);
        discard.setPreferredSize(new Dimension(150,300));
        deck.setBackground(Color.red);
        deck.setPreferredSize(new Dimension(150,300));
        
        //Builds the deck, 10 single per color, 2 double per color, 5 colors.
        cardDeck = new Deck();
		cardDeck.shuffle();
        
        //Create Buttons
        JButton discardPile = new JButton("Cards Discarded: " + cardsDiscarded);
        JButton cardDrawn = new JButton("Card Drawn: ");
        JButton card = new JButton("");
		
        JButton drawCard = new JButton("Draw Card: " + cardsRemaining);
        drawCard.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){	//handle all logic which follows the drawing of a card.
                if(cardsRemaining == 0){
                    cardDeck = new Deck();				//reshuffle the deck
					cardDeck.shuffle();
                    cardsRemaining = 60;
                    cardsDiscarded = 0;
					drawnCards = resizeDrawnCards();	//allow drawnCards to hold more cards
                }

				//Draw Card
				Card newCard = drawCard(cardDeck);
				
				//update drawnCards[]
				drawnCards[cardsPlayed] = newCard;
				
				//Update Information
				cardsPlayed = cardsPlayed + 1;
                cardsRemaining = cardsRemaining - 1;
                cardsDiscarded = cardsDiscarded + 1;
				
				//Update sub-panels
                drawCard.setText("Draw Card: " + cardsRemaining);
                discardPile.setText("Cards Discarded: " + cardsDiscarded);
                card.setText(newCard.getColor() + " " + newCard.getValue());
                
                //Send update to WorldOfSweets
                game.makeMove(newCard);
            }
            
        });	
        //add buttons to panel + draw
        deck.add(drawCard);
        discard.add(discardPile);
        drew.add(cardDrawn);
        drew.add(card);
		this.setVisible(true);
        
    }
    
	//Create the deck of cards to be used in the game
	//May not be used if buildingDeck is moved to Deck class
	//As it stands:
	//Red: 10 single + 2 Double
	//Blue: 10 single + 2 double
	//Yellow: 10 single + 2 double
	//Green: 10 single + 2 double
	//Orange: 10 single + 2 double
    public static Deck buildDeck(){
        Deck deck = new Deck();
        int i = 0;
        Card toAdd;
        while(i < 12){	//blue
            if(i > 9){
                toAdd = new Card("blue", 2);
            }else{
                toAdd = new Card("blue", 1);
            }
            deck.addCard(toAdd);
            i ++;
        }
        
        i = 0;
        while(i < 12){	//yellow
            if(i > 9){
                toAdd = new Card("yellow", 2);
            }else{
                toAdd = new Card("yellow", 1);
            }
            deck.addCard(toAdd);
            i ++;
        }
        
        i = 0;
        while(i < 12){	//green
            if(i > 9){
                toAdd = new Card("green", 2);
            }else{
                toAdd = new Card("green", 1);
            }
            deck.addCard(toAdd);
            i ++;
        }
        
        i = 0;
        while(i < 12){	//red
            if(i > 9){
                toAdd = new Card("red", 2);
            }else{
                toAdd = new Card("red", 1);
            }
            deck.addCard(toAdd);
            i ++;
        }
        
        i = 0;
        while(i < 12){	//orange
            if(i > 9){
                toAdd = new Card("orange", 2);
            }else{
                toAdd = new Card("orange", 1);
            }
            deck.addCard(toAdd);
            i ++;
        }
        deck.shuffle();
        return deck;
    }
    
    //External call this to get the last card played
    public Card getLastDrawn(){
        return drawnCards[cardsPlayed-1];
    }
	
	//External call this to get the last "x" amount of cards drawn
    public Card[] getLastDrawn(int x){
		Card[] toReturn = new Card[x];
		int i = 0;
		while (i < x){
			toReturn[i] = drawnCards[i];
			i = i + 1;
		}
		return toReturn;
	}
	
	//Draw a single card from the deck
    public static Card drawCard(Deck deckOfCards){
        Card result = deckOfCards.drawCard();
        return result;
    }
	
	//Draw "x" amount of cards from the deck
	public static Card[] drawCard(Deck deckOfCards, int x){
		Card[] requestedCards = new Card[x];
		int i = 0;
		while(i < x){
			requestedCards[i] = deckOfCards.drawCard();
			i = i + 1;
		}
		return requestedCards;
	}
	
	//double the size of drawnCards[] so that it can hold more history
	public Card[] resizeDrawnCards(){
		Card[] newDrawnCards = new Card[(drawnCards.length * 2)];
		int i = 0;
		while(i < drawnCards.length){
			newDrawnCards[i] = drawnCards[i];
			i = i + 1;
		}
		return newDrawnCards;
	}
    
}