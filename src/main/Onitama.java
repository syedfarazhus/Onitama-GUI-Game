package main;

import java.util.Arrays;

/**
 * An main.Onitama game class consisting of a game board, and keeping track of
 * which player's turn it currently is and some statistics about the game (e.g.
 * how many tokens each player has). It knows who the winner of the game is, and
 * when the game is over.
 */
public class Onitama {
    public static int DIMENSION = 5; // This is a 5x5 game
    private OnitamaBoard board = new OnitamaBoard(Onitama.DIMENSION); // The main game board

    private final Player player1;
    private final Player player2;
    private Player whoseTurn; // player1 moves first!

    /**
     * Constructs a game of Onitama by creating 2 new players. Sets whoseTurn to
     * player1
     */
    public Onitama() {
        this.player1 = new Player(OnitamaBoard.G1);
        this.player2 = new Player(OnitamaBoard.G2);
        this.whoseTurn = this.player1;
    }

    /**
     * Constructs a game of Onitama with 2 players passed in as parameters Sets
     * whoseTurn to player1
     *
     * @param player1 the first player, G1
     * @param player2 the second player, G2
     */
    public Onitama(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.whoseTurn = this.player1;
    }

    /**
     * Constructs a game of Onitama by creating 2 new players. Sets whoseTurn to
     * player1 Sets the dimension of Onitama to the passed in dimension if valid.
     * The dimension must be odd and greater than or equal to 5.
     *
     * @param dimension the dimension of this Onitama
     */
    public Onitama(int dimension) {
        this.board = new OnitamaBoard(dimension);
        DIMENSION = this.board.getDimension();
        this.player1 = new Player(OnitamaBoard.G1);
        this.player2 = new Player(OnitamaBoard.G2);
        this.whoseTurn = this.player1;
    }

    /**
     * Constructs a game of Onitama with 2 players passed in as parameters Sets
     * whoseTurn to player1 Sets the dimension of Onitama to the passed in dimension
     * if valid. The dimension must be odd and greater than or equal to 5.
     *
     * @param player1   the first player, G1
     * @param player2   the second player, G2
     * @param dimension the dimension of this Onitama
     */
    public Onitama(Player player1, Player player2, int dimension) {
        this.board = new OnitamaBoard(dimension);
        DIMENSION = this.board.getDimension();
        this.player1 = player1;
        this.player2 = player2;
        this.whoseTurn = this.player1;
    }

    /**
     * Returns the Player for player1, player2 depending on who moves next.
     *
     * @return the Player for player1, player2
     */
    public Player getWhoseTurn() { return this.whoseTurn; }

    /**
     * Given one player, returns the other player. If the given player is invalid,
     * returns null.
     *
     * @param player Player object for a player - should be either player1 or
     *               player2
     * @return player1 or player2, the opposite of the given player, or null if the
     *         given player object was invalid
     */
    public Player otherPlayer(Player player) {
        if (player == player1) {
            return player2;
        }
        if (player == player2) {
            return player1;
        }
        return null;
    }

    /**
     * Returns true iff the provided coordinates are valid (exists on the board).
     *
     * @param row integer representing a row on this board
     * @param col integer representing a column on this board
     * @return whether (row, col) is a position on the board. Example: (6,12) is not
     *         a valid position on an 8x8 board.
     */
    private boolean validCoordinate(int row, int col) {

        boolean row_valid = 0 <= row && row < DIMENSION;
        boolean col_valid = 0 <= col && col < DIMENSION;

        return row_valid && col_valid;
    }

    /**
     * Checks if a move with the given parameters would be legal based on the origin
     * and destination coordinates. This method should specifically check for the
     * following 3 conditions: 1) The movement is in the bounds of this game's
     * board. 2) The correct piece is being moved based on the current player's
     * turn. 3) The destination is valid. A player CANNOT move on top of their own
     * piece.
     *
     * @param rowO integer representing the row origin
     * @param colO integer representing the column origin
     * @param rowD integer representing the row destination
     * @param colD integer representing the column destination
     * @return true if this is a legal move, false otherwise
     */
    public boolean isLegalMove(int rowO, int colO, int rowD, int colD) {
        // first we check if the 2 pairs of coordinates are valid
        if (this.validCoordinate(rowO, colO) && this.validCoordinate(rowD, colD)) {

            // check if the token corresponds with the right player
            if (this.getWhoseTurn().getPlayer() == board.getToken(rowO, colO) ||
                    Character.toLowerCase(this.getWhoseTurn().getPlayer()) == board.getToken(rowO, colO)) {

                Player other_player = otherPlayer(getWhoseTurn());

                // checks that the destination is either empty or enemy position
                if (board.getToken(rowD, colD) == OnitamaBoard.EMPTY ) { return true; }
                if (board.getToken(rowD, colD) == other_player.getPlayer() ||
                    board.getToken(rowD, colD) == Character.toLowerCase(other_player.getPlayer())) { return true; }
            }
        }
        return false;
    }

    /**
     * Checks whether the move is contained by the given style
     *
     * @param style     Style object to check moves in
     * @param move      int[] object that represents the move being used
     * @return whether move is in the style's moves
     */
    public boolean containsMove(Style style, int[] move) {
        // loop through all the moves in a style
        for (Move x : style.getMoves()){
            // if given move is equal to a move inside the styles moves then return true
            int[] style_move = {x.getRow(), x.getCol()};
            if(Arrays.equals(style_move, move)) { return true; }
        }
        return false;
    }

    /**
     * Checks which player is making the move and ensures that the style is owned by
     * the player and checks if move is contained in the style. returns whether the
     * move is valid
     *
     * @param rowO      integer representing the row origin
     * @param colO      integer representing the column origin
     * @param rowD      integer representing the row destination
     * @param colD      integer representing the column destination
     * @param styleName string representing the name of the movement style
     * @return whether the move is valid
     */
    public boolean moveValid(int rowO, int colO, int rowD, int colD, String styleName) {

        int[] move;

        // loops through all the styles in the game
        for (Style style : board.getStyles()) {

            // checks if the style is the same as styleName and makes sure the player is the owner
            if (style.getName().equals(styleName) && style.getOwner() == getWhoseTurn().getPlayer()) {

                // use the origin and destination pairs to get move and check if its in the styles moves
                if (getWhoseTurn().getPlayer() == OnitamaBoard.G2) {
                    move = new int[]{rowD - rowO, colD - colO};
                    if (containsMove(style, move)) {return true;}
                }

                // same as previouse if statement but it is player one so the moves are flipped
                if (getWhoseTurn().getPlayer() == OnitamaBoard.G1) {
                    move = new int[]{rowO - rowD, colO - colD};
                    if (containsMove(style, move)) {return true;}
                }
            }
        }
        return false;
    }

    /**
     * returns the style associated with the stylename
     *
     * @param stylename string representing the name of the movement style
     * @return the style associated with the stylename
     */
    public Style getStyleUsed(String stylename) {
        for (Style style : board.getStyles()) {
            if (style.getName().equals(stylename)){
                return style;
            }
        }
        return null;
    }

    /**
     * Completes the needed requirements for a movement
     *
     * @param rowO      integer representing the row origin
     * @param colO      integer representing the column origin
     * @param rowD      integer representing the row destination
     * @param colD      integer representing the column destination
     * @param styleName string representing the name of the movement style
     */
    public void complete_move(int rowO, int colO, int rowD, int colD, String styleName) {

        // moves the tokens as required
        char move_token = board.getToken(rowO,colO);
        board.setToken(rowO,colO, OnitamaBoard.EMPTY);
        board.setToken(rowD,colD, move_token);

        // exchange used style with empty one
        Style used_style = getStyleUsed(styleName);
        board.exchangeStyle(used_style);

        // set next players turn
        Player current_player = getWhoseTurn();
        this.whoseTurn = otherPlayer(current_player);
    }

    /**
     * Attempts to make a move for player1 or player2 (depending on whose turn it
     * is) from position rowO, colO to position rowD, colD. Returns true if the move
     * was successfully made.
     *
     * @param rowO      integer representing the row origin
     * @param colO      integer representing the column origin
     * @param rowD      integer representing the row destination
     * @param colD      integer representing the column destination
     * @param styleName string representing the name of the movement style
     * @return true if the move was successfully made, false otherwise
     */
    public boolean move(int rowO, int colO, int rowD, int colD, String styleName) {

        // makes sure move is legal on game board
        if (isLegalMove(rowO,colO,rowD,colD)) {

            // makes sure move is valid regarding styles and positions
            if (moveValid(rowO, colO, rowD, colD, styleName)) {
                // complete successful move requirements
                complete_move(rowO, colO, rowD, colD, styleName);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the winner of the game if the game is over, or the board token for
     * EMPTY if the game is not yet finished. As per main.Onitama's rules, the
     * winner of the game is the player whose Grandmaster reaches the middle column
     * on the opposite row from the start position, OR the player who captures the
     * other player's Grandmaster.
     *
     * @return the character of the winning player's Grandmaster (G1 or G2) or the
     *         token for EMPTY if the game is not finished.
     */
    public char getWinner() {

        int middle = DIMENSION/2;
        char[] game_board = getBoardString().toCharArray();
        boolean G1_alive = false;
        boolean G2_alive = false;

        // checking if Grandmasters are in the winning positions
        if (OnitamaBoard.G1 == board.getToken(DIMENSION-1, middle)) { return OnitamaBoard.G1; }
        if (OnitamaBoard.G2 == board.getToken(0, middle)) { return OnitamaBoard.G2; }

        // checking if Grandmasters are captured or not
        for (char spot: game_board) {
            if (spot == OnitamaBoard.G1) { G1_alive = true; }
            if (spot == OnitamaBoard.G2) { G2_alive = true; }
        }

        // checks if either grandmaster has stayed alive while other is captured
        if (G1_alive && !G2_alive) { return OnitamaBoard.G1; }
        if (G2_alive && !G1_alive) { return OnitamaBoard.G2; }

        // if all if statements failed nobody has won
        return OnitamaBoard.EMPTY;
    }

    /*
     * DO NOT CHANGE ANYTHING BELOW!!! Changing things below will mess up the Auto
     * tests and result in a MAJOR LOSS OF MARKS!!!
     */

    /**
     * DO NOT MODIFY THIS!!! Returns string representation of this board.
     *
     * @return a string representation of this board
     */
    // NOTE: This method is already done for you. DO NOT MODIFY THIS!!
    public String getBoardString() {
        return this.board.toString() + "\n";
    }

    /**
     * DO NOT MODIFY THIS!!! Returns string representation of a player's available
     * styles.
     *
     * @param player the grandmaster of the player whose styles are printed, or
     *               EMPTY for the fifth style
     * @return a string representation of the style
     */
    // NOTE: This method is already done for you. DO NOT MODIFY THIS!!
    public String getStylesString(char player) {
        String s = "";
        if (player == OnitamaBoard.EMPTY) {
            s += "Fifth style: \n";
        } else {
            s += "Player " + player + " styles: \n";
        }
        for (int i = 0; i < 5; i++) {
            if (this.board.getStyles()[i].getOwner() == player) {
                s += this.board.getStyles()[i].toString() + "\n";
            }
        }
        return s;
    }

    /**
     * DO NOT MODIFY THIS!!! Get the different styles of movement in main.Onitama
     *
     * @return an array of all styles of movement.
     */
    public Style[] getStyles() {
        return this.board.getStyles();
    }

    /**
     * DO NOT MODIFY THIS!!! Gets a copy of this OnitamaBoard from
     * OnitamaBoard.getBoard()
     *
     * @return a copy of the current game board.
     */
    public char[][] getBoard() {
        return this.board.getBoard();
    }

    /**
     * DO NOT MODIFY THIS!!! Construct a new OnitamaBoard with the given size and
     * preset board.
     * 
     * @param size  the dimension of the OnitamaBoard
     * @param board the preset board state of the OnitamaBoard
     */
    public void setBoard(int size, char[][] board) {
        this.board = new OnitamaBoard(size, board);
    }

    /**
     * Main function which creates and runs a random main.Onitama game.
     */
    // DO NOT MODIFY THIS!!
    // Run this to test the current class. We speedrun a game of Onitama in 4 moves.
    // The output should match EXACTLY to onitamaMainOutput.txt
    public static void main(String[] args) {
        // Speed run a game of Onitama where player 'O' wins
        Onitama o = new Onitama();
        int[][] moves = { { 0, 2, 1, 2 }, { 4, 2, 3, 3 }, { 1, 2, 2, 4 }, { 3, 3, 2, 4 } };
        String[] styleNames = { "crab", "rooster", "dragon", "mantis" };
        int rowO, colO, rowD, colD;
        String styleName;

        for (int i = 0; i < moves.length; i++) {
            System.out.println(o.getBoardString() + o.getWhoseTurn().getPlayer() + " moves next");
            rowO = moves[i][0];
            colO = moves[i][1];
            rowD = moves[i][2];
            colD = moves[i][3];
            styleName = styleNames[i];
            o.move(rowO, colO, rowD, colD, styleName);
            // Print the move made, board, who's turn it is, and player styles
            System.out.println("makes move " + styleName + ": (" + rowO + ", " + colO + ", " + rowD + ", " + colD + ")");
            System.out.println(o.getBoardString() + o.getWhoseTurn().getPlayer() + " moves next");
            System.out.println(o.getStylesString(OnitamaBoard.EMPTY));
            System.out.println(o.getStylesString(OnitamaBoard.G1));
            System.out.println(o.getStylesString(OnitamaBoard.G2));
        }

        // Print final board state and who won
        System.out.println(o.getBoardString());
        System.out.println("==============================\nGame Finished: " + o.getWinner() + " won the game!");
    }
}