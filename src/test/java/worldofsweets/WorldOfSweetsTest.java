package worldofsweets;
import org.junit.Test;
import static org.junit.Assert.*;

import worldofsweets.*;

public class WorldOfSweetsTest {

    //Test 7a: tests if a Deck can be drawn from
    @Test
    public void testDrawCard() {
      Deck testdeck = new Deck();
      Card c = testdeck.drawCard(); //Draw the red double

      assertNotNull(c);
    }

    //Test 8a: tests if a Deck can be set up
    @Test
    public void testDeck() {
      Deck testdeck = new Deck();
      assertNotNull(testdeck);
    }
    //Test 8b: tests if the deck that is set up contains 60 cards
    //At this time, this test will fail
    @Test
    public void testDeckNumCards() {
      Deck testdeck = new Deck();
      assertEquals(testdeck.getSize(), 60);
    }
}
