package worldofsweets;
public class Card
{
    private String color;
    private int value;

    public Card(String c,int v)
    {
        setColor(c);
        setValue(v);
    }

    public Card(int c,int v)
    {
        setColor(c);
        setValue(v);
    }

    private void setColor(String c)
    {
        color = c;
    }

    private void setColor(int c)
    {
        String sc;
        switch(c)
        {
            case 0: sc = "red"; break;
            case 1: sc = "yellow"; break;
            case 2: sc = "blue"; break;
            case 3: sc = "green"; break;
            case 4: sc = "orange"; break;
            default: sc = "FAILURE"; break;
        }
        color = sc;
    }

    private void setValue(int v)
    {
        value = v;
    }

    public String getColor()
    {
        return color;
    }

    public int getValue()
    {
        return value;
    }
    
    public String toString()
    {
        if(value==1)
        return "Single "+color;
        else if(value==2)
        return "Double "+color;
        else return "FAILURE";
    }
}
