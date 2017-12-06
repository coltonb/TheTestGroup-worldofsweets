package worldofsweets;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.io.*;
import java.security.*;
import javax.swing.*;


public class CardPanel extends JPanel {
    public int cardsRemaining = -1;		//how many cards are left in the deck, once this hits 0 need to shuffle
    public int cardsDiscarded = -1;		//how many cards have been drawn/played, once this hits 70 needs to reset to 0
    public int cardsPlayed = -1;		//how many cards have been played ALL GAME
    public Card[] drawnCards = null;	//All the cards played in the game so far. Need to resize if we go over 60
    public Deck cardDeck = null;			//Deck of all the cards
    private BufferedImage image;
    private JPanel drew = null;
    public GameTimer gameTimer = null;  //A timer for how long the game has been played
    private BoomerangPanel boomerangPanel = null;
    private WorldOfSweets game = null;
    private int skipCount = 5;
    private JButton cardButton = null;
    private JLabel card = null;
    private JLabel discardPile = null;
    private Board board = null;

    public CardPanel(){
        this(null,"",null);
    }

    /*
    *For use in testing non-static methods
    */
    public CardPanel(String test){
        this(null,"",null);
        if(test.equalsIgnoreCase("test")){
            cardsRemaining = 1;
            cardsDiscarded = 0;
            cardsPlayed = 0;
            drawnCards = new Card[70];
            cardDeck = new Deck("empty");
            cardDeck.addCard(Card.Type.RED);
        }

    }

    /*
    *New Game With Save File if Specified
    */
    public CardPanel(WorldOfSweets game, String save, Board board) {
        this.game = game;
        this.board = board;
        if(save.equals("")){
            cardsDiscarded = 0;
            cardsPlayed = 0;
            drawnCards = new Card[70];
            cardDeck = new Deck("full");
            cardsRemaining = cardDeck.getSize();
            gameTimer = new GameTimer(game);
            boomerangPanel = new BoomerangPanel(game, this);
            boomerangPanel.update(game.getCurrentPlayer().getNumBoomerangs());
            cardDeck.shuffle();
        }else{
            load(save);
        }

        //Create Panel
        this.setLayout(new FlowLayout());

        //Create sub-panels
        JPanel drewContainer = new JPanel();
        JPanel discardContainer = new JPanel();
        JPanel deckContainer = new JPanel();

        drewContainer.setLayout(new BorderLayout());
        discardContainer.setLayout(new BorderLayout());
        deckContainer.setLayout(new BorderLayout());

        JLabel drewLabel = new JLabel("Last Draw:");
        JLabel deckLabel = new JLabel("Deck:");

        drewContainer.add(drewLabel, BorderLayout.NORTH);
        deckContainer.add(deckLabel, BorderLayout.NORTH);

        drew = new JPanel(){		//holds last card drawn
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
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
        discardContainer.add(discard, BorderLayout.CENTER);
        deckContainer.add(deck, BorderLayout.CENTER);

        this.add(drewContainer);
        this.add(discardContainer);
        this.add(deckContainer);

        //Create Buttons
        discardPile = new JLabel("Cards Discarded: " + cardsDiscarded);
        JLabel cardDrawn = new JLabel("");
        card = new JLabel("");
        card.setFont(new Font("Arial", Font.BOLD, 25));

        //Save Button
        JButton saveButton = new JButton("Save Game");
        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get Save File Location
                game.pause();
                JFileChooser jfc = new JFileChooser(".\\");
                int returnValue = jfc.showSaveDialog(null);
                String selectedFilePath = "";
                if(returnValue == JFileChooser.APPROVE_OPTION){
                    File selectedFile = jfc.getSelectedFile();
                    selectedFilePath = selectedFile.getAbsolutePath();
                }
                try{
                    FileWriter fileWriter = new FileWriter(selectedFilePath);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write to Save File
                    String saveWorldOfSweets = game.save();
                    String saveCardPanel = save();
                    bufferedWriter.write(saveWorldOfSweets);
                    bufferedWriter.newLine();
                    bufferedWriter.write(saveCardPanel);
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                    game.unpause();
                }catch(IOException ioe){
                    System.out.println("ERROR: Sorry, could not write to designated save file");
                    game.unpause();
                }

            }
        });

        //Draw Card Button
        cardButton = new JButton("Draw Card: " + cardsRemaining);
        cardButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){	//handle all logic which follows the drawing of a card.
                if(game.getCurrentPlayer().getType().equals("Human"))
                drawACard(null);
            }
        });

        //add buttons to panel + draw
        deck.add(cardButton);

        discardPile.setHorizontalAlignment(JLabel.CENTER);
        saveButton.setHorizontalAlignment(JButton.CENTER);
        gameTimer.setHorizontalAlignment(JLabel.CENTER);

        //disabled for now
        //discardContainer.add(discardPile, BorderLayout.PAGE_START);
        discardContainer.add(saveButton, BorderLayout.CENTER);
        //discard.add(loadButton, BorderLayout.CENTER);
        discardContainer.add(gameTimer, BorderLayout.PAGE_START);

        if (game.isStrategic()) {
            discardContainer.add(boomerangPanel, BorderLayout.PAGE_END);
        }

        drew.add(cardDrawn);
        drew.add(card);
        this.setVisible(true);

    }

    public void drawACard(Player target) {
        if(cardsRemaining == 0)
        {
            cardDeck = new Deck("full");
            cardDeck.shuffle();
            cardsRemaining = cardDeck.getSize();
            cardsDiscarded = 0;
            drawnCards = resizeDrawnCards();	//allow drawnCards to hold more cards
        }

        if(game.getCurrentPlayer().dad)
        {
          int worst_index = board.findWorstCard(game.getCurrentPlayer(),new Deck(cardDeck));
          cardDeck.swap(worst_index,cardDeck.getNextIndex());
        }
        //Draw Card
        Card newCard = drawCard(cardDeck);

        //update drawnCards[]
        drawnCards[cardsPlayed] = newCard;

        //Update Information
        cardsPlayed = cardsPlayed + 1;
        cardsRemaining = cardsRemaining - 1;
        cardsDiscarded = cardsDiscarded + 1;

        String fname = "";
        Color color = newCard.getColor().getAwt();
        if (color == WorldOfSweets.Color.GOTOBUBBLEGUM.getAwt()){
            fname = "src/main/resources/images/snowcone.png";
        }
        else if (color == WorldOfSweets.Color.GOTOCANDYCORN.getAwt()){
            fname = "src/main/resources/images/lolipop.png";
        }
        else if (color == WorldOfSweets.Color.GOTOICECREAM.getAwt()){
            fname = "src/main/resources/images/icecream.png";
        }
        else if (color == WorldOfSweets.Color.GOTOLABOONROOM.getAwt()){
            fname = "src/main/resources/images/cookie.png";
        }
        else if (color == WorldOfSweets.Color.GOTOCHOCOLATE.getAwt()){
            fname = "src/main/resources/images/chocolate.png";
        }
        else if (color == WorldOfSweets.Color.FINISH.getAwt()){
            fname = "src/main/resources/images/grandma.png";
        }
        else if (color == WorldOfSweets.Color.RED.getAwt()){
            fname = "src/main/resources/images/redtile.jpg";
        }
        else if (color == WorldOfSweets.Color.BLUE.getAwt()){
            fname = "src/main/resources/images/bluetile.jpg";
        }
        else if (color == WorldOfSweets.Color.GREEN.getAwt()){
            fname = "src/main/resources/images/greentile.jpg";
        }
        else if (color == WorldOfSweets.Color.YELLOW.getAwt()){
            fname = "src/main/resources/images/yellowtile.jpg";
        }
        else if (color == WorldOfSweets.Color.ORANGE.getAwt()){
            fname = "src/main/resources/images/orangetile.jpg";
        }
        if(newCard.getType() == Card.Type.SKIP){
            fname = "src/main/resources/images/skiptile.jpg";
        }

        try {
            image = ImageIO.read(new File(fname));
            drew.repaint();
            drew.revalidate();
        } catch (IOException ex) {
            // drew.repaint();
            drew.revalidate();

            drew.setBackground(newCard.getColor().getAwt());
        }
        //Update sub-panels
        cardButton.setText("Draw Card: " + cardsRemaining);
        discardPile.setText("Cards Discarded: " + cardsDiscarded);
        // drew.setBackground(newCard.getColor().getAwt());
        if(!newCard.isSpecial())
            card.setText("x" + newCard.getValue());
        else
        {
            card.setText(newCard.getName().toUpperCase());
        }

        //Send update to WorldOfSweets
        if (game != null) {
            if (target == null){
                game.makeMove(newCard);
            } else {
                game.makeBoomerangMove(newCard, target);
            }
        }
    }

    /*
    *External call this to get the last card played
    */
    public Card getLastDrawn(){
        return drawnCards[cardsPlayed-1];
    }

    /*
    *External call this to get the last "x" amount of cards drawn
    */
    public Card[] getLastDrawn(int x){
        Card[] toReturn = new Card[x];
        int i = 0;
        while (i < x){
            toReturn[i] = drawnCards[i];
            i = i + 1;
        }
        return toReturn;
    }

    /*
    *Draw a single card from the deck
    */
    public static Card drawCard(Deck deckOfCards){
        Card result = deckOfCards.drawCard();
        return result;
    }

    public static Card drawSpecificCard(Deck deckOfCards, Card.Type t){
        Card result = deckOfCards.drawSpecificCard(t);
        return result;
    }

    /*
    *Draw "x" amount of cards from the deck
    */
    public static Card[] drawCard(Deck deckOfCards, int x){
        Card[] requestedCards = new Card[x];
        int i = 0;
        while(i < x){
            requestedCards[i] = deckOfCards.drawCard();
            i = i + 1;
        }
        return requestedCards;
    }

    /*
    *double the size of drawnCards[] so that it can hold more history
    */
    public Card[] resizeDrawnCards(){
        Card[] newDrawnCards = new Card[(drawnCards.length * 2)];
        int i = 0;
        while(i < drawnCards.length){
            newDrawnCards[i] = drawnCards[i];
            i = i + 1;
        }
        return newDrawnCards;
    }

    /*
    *Convert all volatile data in this class to a single String and then
    *return that String to be written to file in following format
    *int numDiscarded, int numRemaining, cardsRemainingInDeck[], int numPlayedTotal, everyCardPlayedSoFar[]
    */
    public String save(){
        //create string with all cards remaining in deck
        StringBuilder remainingInDeck = new StringBuilder("");

        String timeString = gameTimer.save();

        int i = 0;
        while(i < cardsRemaining){
            Card current = cardDeck.drawCard();
            String toAppend = cardToString(current);
            remainingInDeck.append(toAppend + " ");
            i = i + 1;
        }

        //create string with all cards played so far
        StringBuilder allPlayed = new StringBuilder("");
        i = 0;
        while(i < cardsPlayed){
            Card current = drawnCards[i];
            String toAppend = cardToString(current);
            allPlayed.append(toAppend + " ");
            i = i + 1;
        }

        //compile unencrypted save string
        String saveString = timeString + " " + cardsDiscarded + " " + cardsRemaining + " " + remainingInDeck.toString() + cardsPlayed + " " + allPlayed.toString();


		//encrypt save string [add a SHA-256 digested checksum to end of string]
		saveString = game.encryptSave(saveString);

        return saveString;
    }

    /*
    * Accepts a String which contains all the volatile data of the previous
    * game parsed from an existing save file.
    */
    public void load(String loadString){
        try{
            String[] split = loadString.split("\\ ");

			//before we do anything else, attempt to verify the hashed checksum
			if(game.verifySave(split) == false){
				System.exit(0);
			}

			//initiate fields
            gameTimer = new GameTimer(game);
            gameTimer.load(split[0]);
            boomerangPanel = new BoomerangPanel(game, this);
            boomerangPanel.update(game.getCurrentPlayer().getNumBoomerangs());
            cardsDiscarded = Integer.parseInt(split[1]);
            cardsRemaining = Integer.parseInt(split[2]);
            cardDeck = new Deck("empty");

            //Fill up cardDeck
            int j = 3;	//index in split
            int i = 0;
            while(i < cardsRemaining){
                String typeName = split[j];
                Card newCard = stringToCard(typeName);
                cardDeck.addCard(newCard.getType());

                j = j + 1;
                i = i + 1;
            }

            cardsPlayed = Integer.parseInt(split[j]);
            drawnCards = new Card[70];

            //Fill up drawnCards
            j = j + 1;
            i = 0;
            while(i < cardsPlayed){
                String typeName = split[j];
                Card newCard = stringToCard(typeName);
                drawnCards[i] = newCard;
                j = j + 1;
                i = i + 1;
            }
        }catch(Exception e){
            System.out.println("ERROR: Sorry, we could not load a game from that file");
            e.printStackTrace();
            System.exit(0);
        }

    }

    /*
    * Accepts a String argument, replaces all of its
    * _ characters with white spaces so that it can be
    * matched with its approriate enum type.
    */
    public static Card stringToCard(String toConvert){
        Card toReturn = null;
        toConvert = toConvert.replace('_', ' ');
        if(toConvert.equalsIgnoreCase("red")){
            toReturn =  new Card(Card.Type.RED);

        }else if(toConvert.equalsIgnoreCase("yellow")){
            toReturn = new Card(Card.Type.YELLOW);

        }else if(toConvert.equalsIgnoreCase("blue")){
            toReturn = new Card(Card.Type.BLUE);

        }else if(toConvert.equalsIgnoreCase("green")){
            toReturn = new Card(Card.Type.GREEN);

        }else if(toConvert.equalsIgnoreCase("orange")){
            toReturn = new Card(Card.Type.ORANGE);

        }else if(toConvert.equalsIgnoreCase("double red")){
            toReturn = new Card(Card.Type.DOUBLERED);

        }else if(toConvert.equalsIgnoreCase("double yellow")){
            toReturn = new Card(Card.Type.DOUBLEYELLOW);

        }else if(toConvert.equalsIgnoreCase("double blue")){
            toReturn = new Card(Card.Type.DOUBLEBLUE);

        }else if(toConvert.equalsIgnoreCase("double green")){
            toReturn = new Card(Card.Type.DOUBLEGREEN);

        }else if(toConvert.equalsIgnoreCase("double orange")){
            toReturn = new Card(Card.Type.DOUBLEORANGE);

        }else if(toConvert.equalsIgnoreCase("skip")){
            toReturn = new Card(Card.Type.SKIP);

        }else if(toConvert.equalsIgnoreCase("chocolate")){
            toReturn = new Card(Card.Type.GOTOCHOCOLATE);

        }else if(toConvert.equalsIgnoreCase("snowcone")){
            toReturn = new Card(Card.Type.GOTOBUBBLEGUM);

        }else if(toConvert.equalsIgnoreCase("lolipop")){
            toReturn = new Card(Card.Type.GOTOCANDYCORN);

        }else if(toConvert.equalsIgnoreCase("ice cream")){
            toReturn = new Card(Card.Type.GOTOICECREAM);

        }else if(toConvert.equalsIgnoreCase("cookie")){
            toReturn = new Card(Card.Type.GOTOLABOONROOM);

        }

        return toReturn;
    }

    /*
    * Accepts a Card argument and returns its enum Type with
    * all white spaces replaced with _ for easier save/load parsing
    */
    public static String cardToString(Card toConvert){
        String toReturn = toConvert.getName();
        toReturn = toReturn.replace(' ', '_');
        return toReturn;
    }

    public void update() {
        if (boomerangPanel != null) {
            boomerangPanel.update(game.getCurrentPlayer().getNumBoomerangs());
        }
    }

    public BoomerangPanel getBoomerangPanel()
    {
      return boomerangPanel;
    }
}
