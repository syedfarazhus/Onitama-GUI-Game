package main;

/**
 * An abstract class to represent a player in an onitama game.
 */
public class Player {

    protected final char player;

    /**
     * Constructs a new player that knows its char on the game board
     *
     * @param player	Char representing the player
     */
    public Player(char player) {
        this.player = player;
    }

    /**
     * returns a Turn object of the next move made by the player based
     * on the child classes implementation
     *
     * @return null because it is not implemented
     */
    public Turn getTurn() {
        return null;
    }


    /**
     * returns a char repersenting the player on the board
     *
     * @return char of player
     */
    public char getPlayer() {
        return player;
    }
}