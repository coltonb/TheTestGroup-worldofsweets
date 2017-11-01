package worldofsweets;
package worldofsweets;
enum CardTypes
{
  R(1,"red","red",false),
  Y(1,"yellow","yellow",false),
  B(1,"blue","blue",false),
  G(1,"green","green",false),
  O(1,"orange","orange",false),
  DR(2,"red","double red",false),
  DY(2,"yellow","double yellow",false),
  DB(2,"blue","double blue",false),
  DG(2,"green","double green",false),
  DO(2,"orange","double orange",false),
  SKIP(0,"none","skip",false),
  MIDDLE(0,"none","middle",false);
  public final int value;
  public final String color;
  public final String name;
  public final boolean special;
  CardTypes(int value,String color,String name,boolean special)
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
    public String getColor(){
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
