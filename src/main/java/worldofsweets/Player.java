package worldofsweets;

public class Player {
    String name = null;
    int index = -1;
    private int boomerangs = 3;

    public Player() {
        this("Player");
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
        name = newName;
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
}