package worldofsweets;

public class Tile {
    private WorldOfSweets.Color color = null;
    private Player[] players = null;
    private int numPlayers = -1;

    public Tile() {
        this(WorldOfSweets.Color.RED);
    }

    public Tile(WorldOfSweets.Color c) {
        color = c;
        players = new Player[4];
        numPlayers = 0;
    }

    public int hasPlayer(Player player) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == player) {
                return i;
            }
        }
        return -1;
    }

    // Adds a Player to this tile. Returns false if there is no space for
    // the Player and true if the Player was added successfully
    public boolean addPlayer(Player player) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = player;
                numPlayers++;
                return true;
            }
        }
        return false;
    }

    // Removes a Player from this tile. Returns false if the player was not
    // found and true if they were successfully removed
    public boolean removePlayer(Player player) {
        int index = hasPlayer(player);
        if (index == -1) return false;
        players[index] = null;
        numPlayers--;
        return false;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getFirstPlayer() {
        if (!hasPlayers()) {
            return null;
        }

        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                return players[i];
            }
        }

        return null;
    }

    public boolean hasPlayers() {
        if (numPlayers != 0) {
            return true;
        } else {
            return false;
        }
    }

    public WorldOfSweets.Color getColor() {
        return color;
    }

    public void setColor(WorldOfSweets.Color c) {
        color = c;
    }
}