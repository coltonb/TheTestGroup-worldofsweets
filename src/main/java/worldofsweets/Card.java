package worldofsweets;

public class Card
{
    public static enum Type
    {
        RED(1, WorldOfSweets.Color.RED, "red",false),
        YELLOW(1, WorldOfSweets.Color.YELLOW, "yellow",false),
        BLUE(1, WorldOfSweets.Color.BLUE, "blue",false),
        GREEN(1, WorldOfSweets.Color.GREEN, "green",false),
        ORANGE(1, WorldOfSweets.Color.ORANGE, "orange",false),
        DOUBLERED(2, WorldOfSweets.Color.RED,"double red",false),
        DOUBLEYELLOW(2, WorldOfSweets.Color.YELLOW,"double yellow",false),
        DOUBLEBLUE(2, WorldOfSweets.Color.BLUE,"double blue",false),
        DOUBLEGREEN(2, WorldOfSweets.Color.GREEN,"double green",false),
        DOUBLEORANGE(2, WorldOfSweets.Color.ORANGE,"double orange",false),
        SKIP(-1, WorldOfSweets.Color.SKIP, "skip",true),
        GOTOCHOCOLATE(0,WorldOfSweets.Color.GOTOCHOCOLATE,"chocolate",true),
        GOTOBUBBLEGUM(35, WorldOfSweets.Color.GOTOBUBBLEGUM, "snowcone",true),
        GOTOCANDYCORN(60, WorldOfSweets.Color.GOTOCANDYCORN,"lolipop",true),
        GOTOICECREAM(27, WorldOfSweets.Color.GOTOICECREAM,"ice cream",true),
        GOTOLABOONROOM(7, WorldOfSweets.Color.GOTOLABOONROOM,"cookie",true);
        public final int value;
        public final WorldOfSweets.Color color;
        public final String name;
        public final boolean special;
        Type(int value,WorldOfSweets.Color color,String name,boolean special)
        {
            this.value = value;
            this.color = color;
            this.name = name;
            this.special = special;
        }

    }

    private Type type = null;

    public Card(Type t){
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
    public Type getType() {
      return type;
    }
}
