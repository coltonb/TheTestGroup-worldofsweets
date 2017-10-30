package worldofsweets;
import org.junit.Test;
import static org.junit.Assert.*;

import worldofsweets.*;

public class WorldOfSweetsTest {

    public void testNumPlayersPrompt2() {
      GameFrame testgf = new GameFrame();
      BoardPanel testbp = testgf.getPanel();
      assertEquals(testbp.getPlayerNum(), 2);
    }

    @Test
    public void testDeck() {
      Deck testdeck = new Deck();
      assertNotNull(testdeck);
    }

    @Test
    public void testDrawCard() {
      Deck testdeck = new Deck();
      testdeck.addCard(0, 2); //RED DOUBLE
      testdeck.addCard(1, 1); //YELLOW SINGLE

      Card c = testdeck.drawCard(); //Draw the red double
      Card test = new Card(0, 2);

      assertEquals(c.toString(), test.toString());
    }

    @Test
    public void testPlayerTokens() {
      GameFrame testgf = new GameFrame();
      BoardPanel testbp = testgf.getPanel();
      BoardTile[][] bt = testbp.getBoardTiles();
      //number of players found on the board
      int numPlayerTokens = 0;

      for (int i = 0; i < bt.length; i++){
        for (int j = 0;  j < bt[i][0].getPlayerTiles().length; j++){
          if(!(bt[i][0].getPlayerTiles()[j].isEmpty())) {
            numPlayerTokens++;
          }
        }
      }
      assertEquals(numPlayerTokens, testbp.getPlayerNum());
    }

}
