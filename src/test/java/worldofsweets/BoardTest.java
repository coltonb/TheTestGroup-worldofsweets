import org.junit.Test;
import static org.junit.Assert.*;

import worldofsweets.WorldOfSweets;
import worldofsweets.Board;
import worldofsweets.Tile;
import worldofsweets.Player;

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
}