package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class PlayerInfo extends PlayerTile {
    private String pName;

    public PlayerInfo() {
        super();
    }

    private void setPName(String name){
        this.pName = name;
    }
    public String getPName()
    {
      return pName;
    }

    //TO DO: get images for icons
}
