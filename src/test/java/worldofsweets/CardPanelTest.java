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

	//-----------------stringToCard()------------
	//Assert that cardToString(), when passed the String "laboon_room"
	//creates a Card object with the Type GOTOLABOONROOM
	@Test
	public void testStringToCardCorrectType(){
		String test = "cookie";
		Card result = CardPanel.stringToCard(test);
		Card baseline = new Card(Card.Type.GOTOLABOONROOM);
		assertEquals(result.getType(), baseline.getType());
	}

	//------------------cardToString()-----------
	//Assert that stringToCard(), when passed a Card object of
	//Type DOUBLEYELLOW, properly returns the String "double_yellow".
	@Test
	public void testCardToStringCorrectType(){
		Card test = new Card(Card.Type.DOUBLEYELLOW);
		String result = CardPanel.cardToString(test);
		String baseline = "double_yellow";
		assertEquals(result, baseline);
	}

	//-----------------save()--------------------
	//Assert that save() returns the properly formatted string
	@Test
	public void testSaveCorrect(){
		CardPanel testPanel = new CardPanel("test");
		String result = testPanel.save();
		String baseline = "0 1 red 0 87490f28e72eecbb7de0a2f817cla908008e42ecbdf2f124b932d749c3d23c4";
		assertEquals(result, baseline);

	}

	//-----------------load()--------------------
	//Assert that load() properly sets the fields of CardPanel
	//from a properly formatted string.
	@Test
	public void testLoadCorrect(){
		WorldOfSweets game = null;
		String testSave = "0 1 red 0 87490f28e72eecbb7de0a2f817cla908008e42ecbdf2f124b932d749c3d23c4";
		CardPanel testPanel = new CardPanel(game, testSave, null);
		
		assertEquals(testPanel.cardsRemaining, 1);
	}

	//Assert that a player named "Dad" sets a boolean that checks that the player is indeed Dad to true
	//To be used for the rest of the game
	@Test
	public void testDadCheat(){
		WorldOfSweets game = null;
		Board board = new Board();
		Player plr = new Player("Dad");

		assertTrue(plr.dad);
	}

	//Assert that the timer loads and saves properly
	@Test
	public void testTimerLoadSave() {
		GameTimer gt = new GameTimer(null);
		gt.load("500");
		String time = gt.save();
		assertEquals("500", time);
	}

	@Test
	public void testTimerStringGeneration() {
		long seconds = 500;
		String timeString = GameTimer.getTimeString(seconds);
		assertEquals("00:08:20", timeString);
	}
}
