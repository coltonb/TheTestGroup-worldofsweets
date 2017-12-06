package worldofsweets;
import org.junit.Test;
import static org.junit.Assert.*;

import worldofsweets.WorldOfSweets;
import worldofsweets.Board;
import worldofsweets.Tile;
import worldofsweets.Player;
import worldofsweets.Card;
import worldofsweets.Card.Type;

public class BoardTest {
    @Test
    public void testAddPlayer() {
        Board board = new Board();
        Player[] players = new Player[4];

        for (int i = 0; i < 4; i++) {
            Player player = new Player();
            player.setName(Integer.toString(i));
            players[i] = player;
        }

        board.addPlayers(players);

        Tile[][] tiles = board.getTiles();

        // Starting tile should contain all players we just added
        Player[] tmpPlayers = tiles[WorldOfSweets.TILE_HEIGHT - 1][0].getPlayers();

        for (int i = 0; i < 4; i++) {
            assertEquals(tmpPlayers[i], players[i]);
        }
    }

    @Test
    public void testMovePlayer() {
        Board board = new Board();
        Player testPlr = new Player("John");

        board.addPlayer(testPlr, 1);
        board.movePlayer(testPlr, 3);

        Tile plrLocation = board.getTileAt(3);
        Tile oldLocation = board.getTileAt(1);
        int playerIndex = plrLocation.hasPlayer(testPlr);

        // New tile should have the player, player's internal index should
        // point to that tile's index, and the old tile should have no players
        assertEquals(playerIndex, 0);
        assertEquals(testPlr.getIndex(), 3);
        assertFalse(oldLocation.hasPlayers());
    }

    @Test
    public void testCheckWinner() {
        Board board = new Board();
        Player testPlr = new Player("Hamilton");

        board.addPlayer(testPlr, board.getLength() - 1);

        // checkWinner should return testPlr
        assertTrue(board.checkWinner() != null);
        assertEquals(board.checkWinner(), testPlr);
    }



    @Test
    public void testMoveCard1() {
        Card card = new Card(Card.Type.DOUBLEBLUE);

        Player plr = new Player("Jake");

        Board board = new Board();

        board.addPlayer(plr, 0);

        board.movePlayer(plr, card);
        assertEquals(plr.getIndex(), 8);
    }

    @Test
    public void testMoveCard2() {
        Card card = new Card(Card.Type.RED);
        Card card1 = new Card(Card.Type.ORANGE);
        Card card2 = new Card(Card.Type.DOUBLERED);

        Player plr = new Player("Jake");

        Board board = new Board();

        board.addPlayer(plr, 0);

        board.movePlayer(plr, card);
        assertEquals(plr.getIndex(), 1);

        board.movePlayer(plr, card1);
        assertEquals(plr.getIndex(), 5);

        board.movePlayer(plr, card2);
        assertEquals(plr.getIndex(), 11);
    }
    //------------------checkArriveMiddle()---------
	/*MIDDLE NO LONGER EXISTS
		//Assert that upon drawing a Middle card a player will be located
    //at the Middle space.
		@Test
		public void testCheckArriveMiddle(){
			Card mid = new Card(Card.Type.GOTOBUBBLEGUM);
      Player plr = new Player("James");
      Board board = new Board();
      board.addPlayer(plr,0);
      board.movePlayer(plr,mid);
			assertEquals(plr.getIndex(),board.getLength()/2);
		}
	*/

    //------------------checkSkipMove()---------

		//Assert that upon drawing a Skip card a player will be located
    //at same space without moving.
		@Test
		public void testCheckSkipMove(){
			Card skip = new Card(Card.Type.SKIP);
      Card red = new Card(Card.Type.RED);
      Player plr = new Player("James");
      Board board = new Board();
      board.addPlayer(plr,0);
      int pos = plr.getIndex();
      for(int checks = 0;checks<3;checks++)
      {
      board.movePlayer(plr,skip);
			assertEquals(plr.getIndex(),pos);
      board.movePlayer(plr,red);
      pos = plr.getIndex();
      }
		}

    // Should move the player to the previous instance of the DoubleBlue
    @Test
    public void testMoveBackwards(){
        Card card = new Card(Card.Type.DOUBLEBLUE);

        Player plr = new Player("Jake");

        Board board = new Board();

        board.addPlayer(plr, 12);

        board.movePlayerBackwards(plr, card);
        assertEquals(plr.getIndex(), 3);
    }

    // Should move the player to the move to location, not backwards
    @Test
    public void testMoveBackwards2(){
        Card card = new Card(Card.Type.GOTOLABOONROOM);

        Player plr = new Player("Jake");

        Board board = new Board();

        board.addPlayer(plr, 3);

        board.movePlayerBackwards(plr, card);
        assertEquals(plr.getIndex(), card.getValue());
    }
}
