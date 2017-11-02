package WorldOfSweets;
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
		testCards[0] = new Card("red", 1);
		testCards[1] = new Card("yellow", 1);
		testCards[2] = new Card("blue", 2);
		testCardPanel.drawnCards = testCards;
		
		assertEquals("blue", testCardPanel.getLastDrawn().getColor());
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
		Card testCard1 = new Card("red", 1);
		Card testCard2 = new Card("yellow", 1);
		Card testCard3 = new Card("blue", 2);
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
			String testString = testCard.getColor();
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
			String testString1 = testDeck2[0].getColor();
			String testString2 = testDeck2[1].getColor();
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
		assertEquals(120, resultCards.length);
		
	}
	
	
}