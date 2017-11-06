package worldofsweets;
public class Deck
{
    private int size = -1;
    private Card[] deck = null;
    private int nextCard = -1;

    public Deck()
    {
      size = 0;
      nextCard = 0;
      basicDeck();
    }
    public void basicDeck()
    {
        for(int singles = 0;singles<10;singles++)
        {
          addCard(Card.Type.R);
          addCard(Card.Type.Y);
          addCard(Card.Type.B);
          addCard(Card.Type.G);
          addCard(Card.Type.O);
        }
        for(int doubles = 0;doubles<2;doubles++)
        {
          addCard(Card.Type.DR);
          addCard(Card.Type.DY);
          addCard(Card.Type.DB);
          addCard(Card.Type.DG);
          addCard(Card.Type.DO);
        }
    }
    public Card drawCard()
    {
        if(isEmpty())
        shuffle();
        nextCard++;
        return deck[nextCard-1];
    }

    public void addCard(Card.Type t)
    {
      Card[] d = new Card[size+1];
      for(int x = 0;x<size;x++)
      {
        d[x]=deck[x];
      }
      d[size] = new Card(t);
      size++;
      deck = d;
    }
    public void shuffle()
    {
        for(int x=size-1;x>0;x--)
        {
            int r = (int)(Math.random()*(x+1));
            Card temp = deck[x];
            deck[x] = deck[r];
            deck[r] = temp;
        }
        nextCard = 0;
    }

    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return nextCard == size;
    }
}
