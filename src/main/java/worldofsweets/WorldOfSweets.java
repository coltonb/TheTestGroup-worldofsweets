package worldofsweets;

import java.awt.*;
import java.io.*;
import java.security.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WorldOfSweets {

    public static final int W_WIDTH = 600;
    public static final int W_HEIGHT = 750;

    public static final int TILE_WIDTH = 10;
    public static final int TILE_HEIGHT = 7;

    public int numPlayers = -1;
    public int currentPlayer = -1;
    public Player[] players = null;

    private boolean strategicMode = false;

    private GameFrame frame = null;
    private BoardPanel boardPanel = null;
    private CardPanel cardPanel = null;

    private boolean paused = false;

    public Random rng = null;

    private Board board = null;
    private static final java.awt.Color REDAWT    =
        new java.awt.Color(231, 76, 60);
    private static final java.awt.Color YELLOWAWT =
        new java.awt.Color(241, 196, 15);
    private static final java.awt.Color BLUEAWT   =
        new java.awt.Color(52, 152, 219);
    private static final java.awt.Color GREENAWT  =
        new java.awt.Color(46, 204, 113);
    private static final java.awt.Color ORANGEAWT =
        new java.awt.Color(230, 126, 34);
    private static final java.awt.Color WHITEAWT =
        new java.awt.Color(255,255,255);


    private static final java.awt.Color FINISHAWT =
        new java.awt.Color(255,213,79);


    private static final java.awt.Color BUBBLEGUMAWT =
        new java.awt.Color(244,143,177);
    private static final java.awt.Color CHOCOLATEAWT =
        new java.awt.Color(141, 110, 99);
    private static final java.awt.Color LABOONROOMAWT =
        new java.awt.Color(255, 209, 128);
    private static final java.awt.Color CANDYCORNAWT =
        new java.awt.Color(206, 147, 216);


    public static enum Color {
        RED(REDAWT),
        YELLOW(YELLOWAWT),
        BLUE(BLUEAWT),
        GREEN(GREENAWT),
        ORANGE(ORANGEAWT),
        GOTOBUBBLEGUM(BUBBLEGUMAWT),
        GOTOCANDYCORN(CANDYCORNAWT),
        GOTOICECREAM(WHITEAWT),
        GOTOLABOONROOM(LABOONROOMAWT),
        GOTOCHOCOLATE(CHOCOLATEAWT),
        SKIP(WHITEAWT),
      //  START(WHITEAWT),
        FINISH(FINISHAWT);

        private final java.awt.Color AWTCOLOR;

        Color(java.awt.Color c) {
            this.AWTCOLOR = c;
        }

        public java.awt.Color getAwt() {
            return AWTCOLOR;
        }
    }

    public WorldOfSweets() {
        //On Startup, ask if new or load
        String desiredChoice = promptLoadOrNew();

        if(desiredChoice.equalsIgnoreCase("load a previous game.") ){
            //if Player Selected Load Game
            //Get the save file they want to load from
            boolean resultWorldOfSweets = false;
            boolean resultCardPanel = false;
            JFileChooser jfc = new JFileChooser(".\\");
            int returnValue = jfc.showOpenDialog(null);
            String selectedFilePath = "";
            if(returnValue == JFileChooser.APPROVE_OPTION){
                File selectedFile = jfc.getSelectedFile();
                selectedFilePath = selectedFile.getAbsolutePath();
            }

            //read in contents of save file
            String toWorldOfSweets = "";
            String toCardPanel = "";
            try{
                FileReader fileReader = new FileReader(selectedFilePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                toWorldOfSweets = bufferedReader.readLine();
                toCardPanel = bufferedReader.readLine();

                bufferedReader.close();
            }catch(IOException ioe){
                System.out.println("ERROR: IOException");
                System.exit(0);
            }

            //Load
            load(toWorldOfSweets);

            //establish frames
            board = new Board();
            board.initializePlayers(players);

            frame = new GameFrame(players);
            boardPanel = new BoardPanel(board);
            cardPanel = new CardPanel(this, toCardPanel, board);

            frame.add(boardPanel, BorderLayout.PAGE_START);
            frame.add(cardPanel, BorderLayout.CENTER);

            //movePlayers to their correct positions on the board.
            int i = 0;
            while(i < numPlayers ){
                board.movePlayer(players[i], players[i].getIndex() );
                i = i + 1;
            }

            frame.setVisible(true);

            //Prompt player
            promptPlayerTurn(players[currentPlayer]);

        }else if(desiredChoice.equalsIgnoreCase("start a new game!") ){
            //Ask if they'd like to play strategic mode

            strategicMode = promptStrategic();
            //If Player Selected New Game
            numPlayers = promptNumPlayers();

            players = new Player[numPlayers];
            currentPlayer = 0;
            String[] types = promptPlayerTypes(numPlayers);
            String[] names = promptPlayerNames(numPlayers);

            for (int i = 0; i < players.length; i++) {
                players[i] = new Player();
                players[i].setType(types[i]);
                players[i].setName(names[i]);
                if (names[i].length() == 3) {
                    if (names[i].substring(0, 3).equalsIgnoreCase("dad")){
                    players[i].dad = true;
                    }
            }
            }

            board = new Board();
            board.addPlayers(players);

            frame = new GameFrame(players);
            boardPanel = new BoardPanel(board);
            cardPanel = new CardPanel(this, "", board);

            frame.add(boardPanel, BorderLayout.PAGE_START);
            frame.add(cardPanel, BorderLayout.CENTER);

            frame.setVisible(true);
            //prompt player
            promptPlayerTurn(players[currentPlayer]);
        }else{
            System.out.println("ERROR: CHOICE SHOULDN'T BE POSSIBLE");
            System.exit(0);
        }

    }

    //Alerts player that it is their turn
    private void promptPlayerTurn(Player player) {
        cardPanel.update();
      if(player.getType().equals("Human"))
      {
        JOptionPane.showMessageDialog(
            null,
            player.getName() + ", it's your turn!",
            "Player Confirmation",
            JOptionPane.INFORMATION_MESSAGE);
      }
      if(player.getType().equals("AI"))
      {
        JOptionPane.showMessageDialog(
            null,
            player.getName() + " is taking its turn!",
            "Player Confirmation",
            JOptionPane.INFORMATION_MESSAGE);
        makeAIMove();
      }
    }

    /*
     * Displays a dialog with a dropdown menu to ask for the number of players.
     */
    private int promptNumPlayers() {
        Object[] options = {1, 2, 3, 4};
        int numPlr = (int) JOptionPane.showInputDialog(
            null,
            "How many people are playing?",
            "Welcome!",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            1);

        players = new Player[numPlr];

        for (int i = 0; i < numPlr; i++) {
            players[i] = new Player();
        }

        return numPlr;
    }

    /*
     * Prompts numPlr players for their names
     * Returns an array of names given a number of players.
     */
    private String[] promptPlayerNames(int numPlr) {
        String[] names = new String[numPlr];

        for (int i = 0; i < numPlr; i++) {
            names[i] = (String) JOptionPane.showInputDialog(
                null,
                "What is Player " + (i + 1) + "'s name?",
                "Name Entry",
                JOptionPane.PLAIN_MESSAGE);

            // Default name to Player i+1 should they not provide one
            if (names[i].length() == 0) names[i] = "Player " + (i + 1);
            for (int j = 0; j < i; j++) {
                if (names[i].equals(names[j])) {
                    names[i] = names[i] + "*";
                }
            }
        }
        return names;
    }
    /*
    * Prompts numPlr players for whether they are Human or AI
    * Returns an array of types given a number of players.
    */
    private String[] promptPlayerTypes(int numPlr)
    {
      String[] playerTypes = new String[numPlr];
      Object[] types = {"Human","AI"};
      Object[] makeHuman = {"Human"};
      playerTypes[0] = (String) JOptionPane.showInputDialog(
        null,
        "Is Player 1 Human or AI?",
        null,
        JOptionPane.PLAIN_MESSAGE,
        null,
        makeHuman,
        "Human");
      for(int i = 1; i < numPlr; i++)
      {
      playerTypes[i] = (String) JOptionPane.showInputDialog(
          null,
          "Is Player " + (i + 1) + " Human or AI?",
          null,
          JOptionPane.PLAIN_MESSAGE,
          null,
          types,
          "Human");
      }
      return playerTypes;
    }
    /*
    * At the startup of the game, ask the player whether they would like
    * to load a previous save or start a new game.
    */
    private String promptLoadOrNew(){
        Object[] options = {"Start A New Game!", "Load A Previous Game."};
        String result = (String) JOptionPane.showInputDialog(
            null,
            "What would you like to do?",
            "Welcome!",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            1);
        return result;

    }

    private boolean promptStrategic(){
        Object[] options = {"Normal", "Strategic"};
        String result = (String) JOptionPane.showInputDialog(
            null,
            "What gamemode would you like to play?",
            "Gamemode Selection",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            1);

        if (result == null) {
            return false;
        }

        if (result.equals("Normal")) {
            return false;
        } else {
            return true;
        }
    }

    public void makeMove(Card cardDrawn){
        // move player
        board.movePlayer(players[currentPlayer], cardDrawn);

        // update board
        boardPanel.drawBoard(board);

        if(players[currentPlayer].getType().equals("AI"))
        {
          JOptionPane.showMessageDialog(
              null,
              players[currentPlayer].getName() + " drew a card.",
              "Player Confirmation",
              JOptionPane.INFORMATION_MESSAGE);
        }

        // Check for winners here, do something about it
        if (players[currentPlayer] == board.checkWinner()){
          System.out.println("There is a winner!");
          JOptionPane.showMessageDialog(
              null,
              players[currentPlayer].getName() + " wins!",
              "Winner!",
              JOptionPane.INFORMATION_MESSAGE);

              if (JOptionPane.showConfirmDialog(null, "Play Again?", "",
                  JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                  frame.setVisible(false); //you can't see me!
                  frame.dispose();
                  new WorldOfSweets();
              }else {
                  System.exit(0);
              }
        }


        //update current player
        currentPlayer = (currentPlayer + 1) % players.length;

        //prompt player
        promptPlayerTurn(players[currentPlayer]);
        cardPanel.update();
    }
    public void makeAIMove()
    {
      int choice = 1;
      rng = new Random();
      rng.setSeed(System.nanoTime());
      if(strategicMode&&players[currentPlayer].getNumBoomerangs()!=0&&numPlayers!=1)
      choice = rng.nextInt(5);
      if(choice!=4)
      {
      cardPanel.drawACard(null);
      }
      else
      {
        Player target = null;
        while(target==null)
        {
        int rnum = rng.nextInt(4)%numPlayers;
        if(rnum!=currentPlayer)
        {
          target = players[rnum];
        }
        }
        cardPanel.getBoomerangPanel().throwBoomerang(target);
      }
    }
    public void makeBoomerangMove(Card cardDrawn, Player target){
        // move player
        board.movePlayerBackwards(target, cardDrawn);
        // update board
        boardPanel.drawBoard(board);

        if(players[currentPlayer].getType().equals("AI"))
        {
          JOptionPane.showMessageDialog(
              null,
              players[currentPlayer].getName() + " threw a boomerang at "+target.getName(),
              "Player Confirmation",
              JOptionPane.INFORMATION_MESSAGE);
        }
        //update current player
        currentPlayer = (currentPlayer + 1) % players.length;

        //prompt player
        promptPlayerTurn(players[currentPlayer]);
        cardPanel.update();
    }

    /*
    * Converts all volatile data in WorldOfSweets to a single String to be
    * printed to a file. Adheres to the following format:
    * currentPlayer " " numPlayers " " players[]
    */
    public String save(){
        StringBuilder saveString = new StringBuilder("");
        //currentPlayer, numPlayer, players[],
        saveString.append(strategicMode + " ");
        saveString.append(currentPlayer + " ");
        saveString.append(numPlayers + " ");

        int i = 0;
        while(i < numPlayers){
            saveString.append(playerToString(players[i]) + " ");
            i = i + 1;
        }

		//encrypt saveString
		String result = encryptSave(saveString.toString());

        return result;
    }

    /*
    * Initializes this class using the String recieved from a save file.
    */
    public void load(String loadString){
        try{
            String[] split = loadString.split("\\ ");

			//Before we do anything else, attempt to verify the checksum
			if(verifySave(split) == false){
				System.exit(0);
			}

			//Initialize fields
            strategicMode = Boolean.parseBoolean(split[0]);
            currentPlayer = Integer.parseInt(split[1]);
            numPlayers = Integer.parseInt(split[2]);
            players = new Player[numPlayers];

            int i = 0;
            int j = 3;	//index to start reading in player information
            while(i < numPlayers){
                players[i] = stringToPlayer(split[j],split[j+1], split[j+2], split[j+3] );
                j = j + 4;
                i = i + 1;
            }
        }catch(Exception e){
            System.out.println("ERROR: Sorry, but could not load a game from that file");
            System.exit(0);
        }


    }

    /*
    * Converts a Player object into a String object for use in
    * saving volatile data to a file.
    * "playername index".
    */
    public String playerToString(Player player){
        StringBuilder toReturn = new StringBuilder("");
        String playerName = player.getName();
        playerName = playerName.replace(" ", "_");
        toReturn.append(playerName + " "+player.getType()+ " " + player.getIndex() + " " + player.getNumBoomerangs());

        return toReturn.toString();
    }

    /*
    * Converts a String object into a Player object. For use in reading in
    * previously saved volatile data from a save file.
    */
    public Player stringToPlayer(String playerName, String playerType, String playerIndex, String numBoomerangs){
        playerName = playerName.replace("_", " ");
        Player toReturn = new Player(playerName);
        toReturn.setType(playerType);
        toReturn.setIndex(Integer.parseInt(playerIndex));
        toReturn.setNumBoomerangs(Integer.parseInt(numBoomerangs));
        toReturn.dad = playerName.substring(0, 3).equalsIgnoreCase("dad");
        return toReturn;
    }

    public void pause() {
        paused = true;
    }

    public void unpause() {
        paused = false;
    }

    public boolean isPaused() {
        return paused;
    }

    public static void main(String[] args) {
        new WorldOfSweets();
    }

	//Encrypts the string about to be saved by adding a checksum to it
	public static String encryptSave(String toSave){
		byte[] checksum = null;
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			checksum = md.digest(toSave.getBytes());	//maybe something other than getBytes()?
			StringBuilder sb = new StringBuilder("");
			for(int j = 0; j < checksum.length; j++){
				sb.append(Integer.toString((checksum[j] & 0xff) + 0x100, 16).substring(1));
			}
			toSave = toSave + sb.toString();
		}catch(NoSuchAlgorithmException nsae){
			System.out.println("ERROR: Could not encrypt save file");
			System.exit(0);
		}
		return toSave;
	}

	//Verifies that the checksum in the loaded file holds true for the save file
	public static boolean verifySave(String[] toCheckAgainst){
		int h = 0;
		byte[] checksum = null;
		StringBuilder toVerify = new StringBuilder("");

		while(h < toCheckAgainst.length-1){
			toVerify.append(toCheckAgainst[h] + " ");
			h = h + 1;
		}

		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			checksum = md.digest(toVerify.toString().getBytes());
		}catch(NoSuchAlgorithmException nsae){
			System.out.println("OOPS!");
			System.exit(0);
		}

		StringBuilder sb = new StringBuilder("");
		for(int g = 0; g < checksum.length; g++){
			sb.append(Integer.toString((checksum[g] & 0xff) + 0x100, 16).substring(1));
		}

		if(sb.toString().equals(toCheckAgainst[toCheckAgainst.length-1]) ){
			System.out.println("Save File verified with checksum " + sb.toString());
			return true;
		}else{
			System.out.println("ERROR: Save File has been tampered with " + sb.toString());
			return false;
		}


	}


	public boolean isStrategic() {
        return strategicMode;
    }


    public Player[] getPlayers() {
        return players;
    }

	public Player getCurrentPlayer() {
        return players[currentPlayer];
    }
}
