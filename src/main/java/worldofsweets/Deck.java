package worldofsweets;
public class Deck
{
    private int size;
    private Card[] deck;
    private int nextCard;

    public Deck()
    {
        size = 0;
        nextCard=0;
    }

    public Card drawCard()
    {
        if(isEmpty())
        shuffle();
        nextCard++;
        return deck[nextCard-1];
    }

    public void addCard(int color,int value)
    {
        addCard(new Card(color,value));
    }

    public void addCard(String color,int value)
    {
        addCard(new Card(color,value));
    }

    public void addCard(Card c)
    {
        Card[] d = new Card[size+1];
        for(int x = 0;x<size;x++)
        {
            d[x]=deck[x];
        }
        d[size]=c;
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
