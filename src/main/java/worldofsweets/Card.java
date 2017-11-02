package worldofsweets;
enum CardTypes
{
  R(1, WorldOfSweets.Color.RED, "red",false),
  Y(1, WorldOfSweets.Color.YELLOW, "yellow",false),
  B(1, WorldOfSweets.Color.BLUE, "blue",false),
  G(1, WorldOfSweets.Color.GREEN, "green",false),
  O(1, WorldOfSweets.Color.ORANGE, "orange",false),
  DR(2, WorldOfSweets.Color.RED,"double red",false),
  DY(2, WorldOfSweets.Color.YELLOW,"double yellow",false),
  DB(2, WorldOfSweets.Color.BLUE,"double blue",false),
  DG(2, WorldOfSweets.Color.GREEN,"double green",false),
  DO(2, WorldOfSweets.Color.ORANGE,"double orange",false),
  SKIP(0,null,"skip",false),
  MIDDLE(0,null,"middle",false);
  public final int value;
  public final WorldOfSweets.Color color;
  public final String name;
  public final boolean special;
  CardTypes(int value,WorldOfSweets.Color color,String name,boolean special)
  {
    this.value = value;
    this.color = color;
    this.name = name;
    this.special = special;
  }
}
public class Card
{
    private CardTypes type;

    public Card(CardTypes t){
        type = t;
    }
    public WorldOfSweets.Color getColor(){
        return type.color;
    }
    public int getValue(){
        return type.value;
    }
    public String getName()
    {
      return type.name;
    }
    public boolean isSpecial()
    {
      return type.special;
    }
    public String toString()
    {
      return getName();
    }
}
