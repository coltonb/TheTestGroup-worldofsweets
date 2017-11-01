package worldofsweets;
public class Deck
{
    private int size;
    private Card[] deck;
    private int nextCard;

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
          addCard(CardTypes.R);
          addCard(CardTypes.Y);
          addCard(CardTypes.B);
          addCard(CardTypes.G);
          addCard(CardTypes.O);
        }
        for(int doubles = 0;doubles<2;doubles++)
        {
          addCard(CardTypes.DR);
          addCard(CardTypes.DY);
          addCard(CardTypes.DB);
          addCard(CardTypes.DG);
          addCard(CardTypes.DO);
        }
    }
    public Card drawCard()
    {
        if(isEmpty())
        shuffle();
        nextCard++;
        return deck[nextCard-1];
    }

    public void addCard(CardTypes t)
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
