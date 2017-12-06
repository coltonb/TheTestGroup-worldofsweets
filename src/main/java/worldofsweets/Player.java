package worldofsweets;

public class Player {
    String name = null;
    boolean dad = false;
    int index = -1;
    private int boomerangs = 3;
    private String player_type = null;

    public Player() {
        this("Player");
    }
    public Player(Player p)
    {
      name = p.getName();
      dad = p.dad;
      index = p.getIndex();
      boomerangs = p.getNumBoomerangs();
      player_type = p.getType();
    }
    public Player(String newName) {
        name = newName;
        index = -1;
    }

    public void setIndex(int newIndex) {
        index = newIndex;
    }

    public int getIndex() {
        return index;
    }

    public void setName(String newName) {
        if(player_type.equals("Human"))
        name = newName;
        else name = newName+" (AI)";
    }

    public String getName() {
        return name;
    }

    public int getNumBoomerangs() {
        return boomerangs;
    }

    public void decrementBoomerangs() {
        boomerangs--;
    }

    public void setNumBoomerangs(int num) {
        boomerangs = num;
    }
    public void setType(String type){
        player_type = type;
    }
    public String getType(){
        return player_type;
    }
}
