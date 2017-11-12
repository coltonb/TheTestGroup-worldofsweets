package worldofsweets;
import org.junit.Test;
import static org.junit.Assert.*;
import worldofsweets.*;

public class CardPanelTest{

	//Placeholder test
	@Test
	public void testTest(){
		assertTrue(true);
	}

	//------------------buildDeck()----------------

	//Assert that buildDeck() creates a properly sized
	//deck of 60 cards. 10 single + 2 double per color
	@Test
	public void testBuildDeckSize(){
		Deck testDeck = new Deck();
		assertEquals(60, testDeck.getSize());
	}

	//------------------getLastDrawn()--------------

	//Assert that getLastDrawn() returns the last
	//card played.
	@Test
	public void testGetLastDrawnSingleAccurate(){
		CardPanel testCardPanel = new CardPanel();
		testCardPanel.cardsPlayed = 3;
		Card[] testCards = new Card[3];
		testCards[0] = new Card(Card.Type.RED);
		testCards[1] = new Card(Card.Type.YELLOW);
		testCards[2] = new Card(Card.Type.DOUBLEBLUE);
		testCardPanel.drawnCards = testCards;

		assertEquals(WorldOfSweets.Color.BLUE, testCardPanel.getLastDrawn().getColor());
		assertEquals(2, testCardPanel.getLastDrawn().getValue());
	}

	//------------------getLastDrawn(int x)----------

	//Assert that getLastDrawn(int x) returns the
	//last "x" cards played in correct order.
	@Test
	public void testGetLastDrawnMultipleAccurate(){
		CardPanel testCardPanel = new CardPanel();
		testCardPanel.cardsPlayed = 3;

		Card[] testCards = new Card[3];
		Card testCard1 = new Card(Card.Type.RED);
		Card testCard2 = new Card(Card.Type.YELLOW);
		Card testCard3 = new Card(Card.Type.DOUBLEBLUE);
		testCards[0] = testCard1;
		testCards[1] = testCard2;
		testCards[2] = testCard3;
		testCardPanel.drawnCards = testCards;

		Card[] resultCards = testCardPanel.getLastDrawn(3);
		assertArrayEquals(testCards, resultCards);
	}

	//-------------------drawCard(Deck)--------------

	//Assert that drawCard(Deck) properly returns a
	//single non-null card
	@Test
	public void testDrawCardSingleExist(){
		Deck testDeck = new Deck();
		try{
			Card testCard = CardPanel.drawCard(testDeck);
			WorldOfSweets.Color testColor = testCard.getColor();
			assertTrue(true);
		}catch(NullPointerException npe){
			fail();
		}
	}

	//--------------------drawCard(Deck, int)-----------


	//Assert that drawCard(deck, int) properly returns
	//multiple non-null cards.
	@Test
	public void testDrawCardMultipleExist(){
		Deck testDeck = new Deck();
		try{
			assertTrue(true);
			Card[] testDeck2 = CardPanel.drawCard(testDeck, 2);
			WorldOfSweets.Color testColor1 = testDeck2[0].getColor();
			WorldOfSweets.Color testColor2 = testDeck2[1].getColor();
			assertTrue(true);
		}catch(NullPointerException npe){
			fail();
		}
	}

	//---------------------resizeDrawnCards()------------

	//Assert that resizeDrawnCards properly doubles
	//the size of the previous array.
	@Test
	public void testResizeDrawnCardsDoubles(){
		CardPanel testCardPanel = new CardPanel();
		Card[] resultCards = testCardPanel.resizeDrawnCards();
		assertEquals(140, resultCards.length);

	}
	//Assert that a special deck only contains Skip cards
	@Test
	public void testSkipOnly(){
		Deck d = new Deck("skip");
		Card skip = new Card(Card.Type.SKIP);
		while(!d.isEmpty())
		{
		Card dc = d.drawCard();
		assertEquals(skip.getName(),dc.getName());
		}
	}
	
	/*MIDDLE NO LONGER EXISTS
	//Assert that a special deck only contains Middle cards
	@Test
	public void testMiddleOnly(){
		Deck d = new Deck("middle");
		Card middle = new Card(Card.Type.MIDDLE);
		while(!d.isEmpty())
		{
		Card dc = d.drawCard();
		assertEquals(middle.getName(),dc.getName());
		}
	}
	*/


	/*MIDDLE NO LONGER EXISTS
	//------------------checkAmountMiddle()---------

	//Assert that a full deck contains 3 Middle cards
	@Test
	public void testCheckAmountMiddle(){
		Deck test = new Deck("full");
		test.shuffle();
		int mids = 0;
		while(!test.isEmpty())
		{
			Card c = test.drawCard();
			if(c.getType()==Card.Type.MIDDLE)
			mids++;
		}
		assertEquals(mids,3);
	}
	*/

	//------------------checkAmountSkip()---------

	//Assert that a full deck contains 5 Skip cards
	@Test
	public void testCheckAmountSkip(){
		Deck test = new Deck("full");
		test.shuffle();
		int skips = 0;
		while(!test.isEmpty())
		{
			Card c = test.drawCard();
			if(c.getType()==Card.Type.SKIP)
			skips++;
		}
		assertEquals(skips,5);
	}

}
