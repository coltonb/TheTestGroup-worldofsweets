package worldofsweets;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardPanel extends JPanel {
    public int cardsRemaining = -1;	//how many cards are left in the deck, once this hits 0 need to shuffle
    public int cardsDiscarded = -1;		//how many cards have been drawn/played, once this hits 60 needs to reset to 0
    public int cardsPlayed = -1;		//how many cards have been played ALL GAME
    public Card[] drawnCards = null;	//All the cards played in the game so far. Need to resize if we go over 60
    public Deck cardDeck = null;			//Deck of all the cards

    public CardPanel(){
        this(null);
    }

    public CardPanel(WorldOfSweets game) {
        cardsRemaining = 60;
        cardsDiscarded = 0;
        cardsPlayed = 0;
        drawnCards = new Card[60];
        cardDeck = new Deck();

        //Create Panel
        this.setLayout(new FlowLayout());

        //Create sub-panels
        JPanel drewContainer = new JPanel();
        JPanel deckContainer = new JPanel();

        drewContainer.setLayout(new BorderLayout());
        deckContainer.setLayout(new BorderLayout());

        JLabel drewLabel = new JLabel("Last Draw:");
        JLabel deckLabel = new JLabel("Deck:");

        drewContainer.add(drewLabel, BorderLayout.NORTH);
        deckContainer.add(deckLabel, BorderLayout.NORTH);

        JPanel drew = new JPanel();			//holds last card drawn
        drew.setBackground(Color.WHITE);
        drew.setLayout(new GridBagLayout());
        drew.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        drew.setPreferredSize(new Dimension(150,300));

        JPanel discard = new JPanel();		//holds number of cards played so far
        discard.setLayout(new GridBagLayout());
        discard.setPreferredSize(new Dimension(150,300));

        JPanel deck = new JPanel();			//holds all cards remaining to be drawn
        deck.setBackground(new Color(121, 85, 72)); // Brown
        deck.setLayout(new GridBagLayout());
        deck.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        deck.setPreferredSize(new Dimension(150,300));

        drewContainer.add(drew, BorderLayout.CENTER);
        deckContainer.add(deck, BorderLayout.CENTER);

        this.add(drewContainer);
        this.add(discard);
        this.add(deckContainer);

        //Customize sub-panels.

        //Builds the deck, 10 single per color, 2 double per color, 5 colors.
        cardDeck = new Deck("full");
        cardsRemaining = cardDeck.getSize();
        cardDeck.shuffle();

        //Create Buttons
        JLabel discardPile = new JLabel("Cards Discarded: " + cardsDiscarded);
        JLabel cardDrawn = new JLabel("");
        JLabel card = new JLabel("");
        card.setFont(new Font("Arial", Font.BOLD, 25));

        JButton drawCard = new JButton("Draw Card: " + cardsRemaining);
        drawCard.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){	//handle all logic which follows the drawing of a card.
                if(cardsRemaining == 0){
                    cardDeck.shuffle();
                    cardsRemaining = cardDeck.getSize();
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
                drew.setBackground(newCard.getColor().getAwt());
                if(!newCard.isSpecial())
                card.setText("x" + newCard.getValue());
                else
                {
                  card.setText(newCard.getName().toUpperCase());
                }

                //Send update to WorldOfSweets
                if (game != null)
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
