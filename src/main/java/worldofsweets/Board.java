package worldofsweets;

public class Board {
    private Tile[][] board;
    private Tile[] path;

    private final WorldOfSweets.Color[] C_LOOP = {
        WorldOfSweets.Color.RED,
        WorldOfSweets.Color.YELLOW,
        WorldOfSweets.Color.BLUE,
        WorldOfSweets.Color.GREEN,
        WorldOfSweets.Color.ORANGE
    };

    public Board() {
        board = new Tile[WorldOfSweets.TILE_HEIGHT][WorldOfSweets.TILE_WIDTH];
        path = new Tile[WorldOfSweets.TILE_HEIGHT * WorldOfSweets.TILE_WIDTH];
        createBoard();
    }

    // colton: the hacky border fix (so that edge tiles were flush with each
    // other) had to be commented out here. I'll reimplement it back in
    // BoardPanel later
    private void createBoard() {
        int pathIter = 0;
        for (int i = WorldOfSweets.TILE_HEIGHT - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                for (int j = 0; j < WorldOfSweets.TILE_WIDTH; j++) {
                    Tile newTile = new Tile(C_LOOP[(j + 4) % C_LOOP.length]);
                    board[i][j] = newTile;
                    path[pathIter++] = newTile;
                    /* if (j == WIDTH - 1)
                        newTile.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0,
                            Color.WHITE));
                    if (j == 0)
                        newTile.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0,
                            Color.WHITE)); */
                }
            } else {
                for (int j = 0; j < WorldOfSweets.TILE_WIDTH; j++) {
                    Tile newTile = new Tile(C_LOOP[(j + 4) % C_LOOP.length]);
                    board[i][WorldOfSweets.TILE_WIDTH - 1 - j] = newTile;
                    path[pathIter++] =  newTile;
                    /* if (j == 0)
                        newTile.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0,
                            Color.WHITE));
                    if (j == WIDTH - 1)
                        newTile.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0,
                            Color.WHITE)); */
                }
            }
        }
        path[0].setColor(WorldOfSweets.Color.START);
        path[path.length - 1].setColor(WorldOfSweets.Color.FINISH);
    }

    public Tile[][] getTiles() {
        return board;
    }

    // Determines if any player is in the final, grandma's house, tile
    // there should only feasibly be one winner, so returning the first player
    // works fine
    public Player checkWinner() {
        if (path[path.length - 1].hasPlayers()) {
            return path[path.length - 1].getFirstPlayer();
        } else {
            return null;
        }
    }

    // Based on a starting index, returns the index of the next tile of Color c
    // from that index. If it cannot find a tile, we default to grandma's
    // house
    public int getNextTileIndex(int startIndex, WorldOfSweets.Color c) {
        for (int i = startIndex; i < path.length; i++) {
            if (path[i].getColor() == c) {
                return i;
            }
        }
        return path.length - 1;
    }

    public int getLength() {
        return path.length;
    }

    public Tile getTileAt(int index) {
        return path[index];
    }

    public boolean addPlayers(Player[] players) {
        for (Player player : players) {
            boolean playerAdded = path[0].addPlayer(player);
            if (!playerAdded) return false;
            player.setIndex(0);
        }
        return true;
    }

    // attempts to add a player to a given tile at index. should always be
    // successful unless more than 4 players are in that tile
    public boolean addPlayer(Player player, int index) {
        boolean playerAdded = path[index].addPlayer(player);
        if (playerAdded) player.setIndex(index);
        return playerAdded;
    }

    // returning false: very bad. We tried to remove the player based on
    // its internally kept index but they weren't there
    public boolean removePlayer(Player player) {
        int index = player.getIndex();
        if (index == -1) return false;
        boolean playerRemoved = path[index].removePlayer(player);
        if (playerRemoved) player.setIndex(-1);
        return playerRemoved;
    }

    public boolean movePlayer(Player player, int index) {
        // move the player to the appropriate index
        // index to be determined based on card drawn
        
        // success of removing the player doesn't matter
        removePlayer(player);
        boolean success = addPlayer(player, index);
        return success;
    }
}