package worldofsweets;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardPanel extends JPanel {
    private BoardPanel board;
    private GameFrame game;
    int cardsRemaining = 60;
    int cardsDiscarded = 0;
    Card lastCard;
    Deck cardDeck = new Deck();

    public CardPanel(GameFrame gf, BoardPanel bp) {
        game = gf;
        board = bp;
        this.setLayout(new FlowLayout());
        
        
        //sub-panels
        JPanel drew = new JPanel();
        JPanel discard = new JPanel();
        JPanel deck = new JPanel();
        this.add(drew);
        this.add(discard);
        this.add(deck);
        
        drew.setBackground(Color.black);
        drew.setPreferredSize(new Dimension(150,300));
        discard.setBackground(Color.blue);
        discard.setPreferredSize(new Dimension(150,300));
        deck.setBackground(Color.red);
        deck.setPreferredSize(new Dimension(150,300));
        
        //build deck 10 reg, 2 double, 5 colors
        cardDeck = buildDeck();
        
        //Buttons
        JButton discardPile = new JButton("Cards Discarded: " + cardsDiscarded);
        
        JButton cardDrawn = new JButton("Card Drawn: ");
        JButton card = new JButton("");
        
        JButton drawCard = new JButton("Draw Card: " + cardsRemaining);
        drawCard.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(cardsRemaining == 0){
                    cardDeck = buildDeck();
                    cardsRemaining = 60;
                    cardsDiscarded = 0;
                }

                cardsRemaining = cardsRemaining - 1;
                cardsDiscarded = cardsDiscarded + 1;
                drawCard.setText("Draw Card: " + cardsRemaining);
                discardPile.setText("Cards Discarded: " + cardsDiscarded);
                Card newCard = drawCard(cardDeck);
                lastCard = newCard;
                card.setText(newCard.getColor() + " " + newCard.getValue());
                
                //send update to gameFrame
                gf.makeMove(drawCard());
                
            }
            
        });	
        
        //add buttons to panel
        deck.add(drawCard);
        discard.add(discardPile);
        drew.add(cardDrawn);
        drew.add(card);
        
    }
    
    public Deck buildDeck(){
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
    
    //Externall call this to get the last card drawn
    public Card drawCard(){
        return lastCard;
    }
    
    public Card drawCard(Deck deckOfCards){
        Card result = deckOfCards.drawCard();
        return result;
    }
    
}