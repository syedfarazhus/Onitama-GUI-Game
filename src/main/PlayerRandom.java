package main;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents a Random player as a child class of player in an onitama game.
 * A Random player plays the game by choosing a randomized valid move per turn
 */
public class PlayerRandom extends Player{

    static Random rand = new Random();
    private static ArrayList<Object[]> valid_moves;
    private Onitama game;

    /**
     * Constructs a new Random player that knows its char on the game board
     *
     * @param player	Char representing the player
     */
    public PlayerRandom(char player) {
        super(player);
        valid_moves = null;
    }

    /**
     * Sets the game attribute of the Random Player with the game it will participate
     * in so the player object can access the games attributes and methods
     *
     * @param current_game    an Onitama object of the game the player will participate in
     */
    public void addGame(Onitama current_game) {
        this.game = current_game;
    }

    /**
     * returns a Turn object of the next move made by the player based
     * on a randomized turn from an ArrayList of possible moves
     *
     * @return a turn object comprised from a randomly chosen move
     */
    @Override
    public Turn getTurn() {
        // get all valid moves
        valid_moves = collect_moves();
        // choose random move
        Object[] move = randomItem(valid_moves);

        // change all objects back to required type
        int rowO = (int) move[0];
        int colO = (int) move[1];
        int rowD = (int) move[2];
        int colD = (int) move[3];
        String style = (String) move[4];

        // create and return turn object
        return new Turn(rowO, colO, rowD, colD, style, player);
    }

    /**
     * returns a randomized Object[] from a given ArrayList<Object[]> using
     * Javas Random class
     *
     * @param mylist   an ArrayList<Object[]> of object arrays
     * @return a randomly chosen Object[] from the given ArrayList
     */
    public Object[] randomItem( ArrayList<Object[]> mylist) {
        return mylist.get(rand.nextInt(mylist.size()));
    }

    /**
     * Checks each spot on the board and returns an ArrayList<int[]> of int {a,b}
     * pairs corresponding to current players tokens on the board
     *
     * @return a ArrayList<int[]> comprised of {a,b} int pairs corresponding to players tokens
     */
    public ArrayList<int[]> findTokens() {
        ArrayList<int[]> placements = new ArrayList<>();
        char[][] board = game.getBoard();

        // loop through the board
        for (int i = 0; i < Onitama.DIMENSION; i++) {
            for (int j = 0; j < Onitama.DIMENSION; j++) {

                // if spot on board is players token then add spot to ArrayList
                if (board[i][j] == player || board[i][j] == Character.toLowerCase(player)) {
                    int[] place = {i,j};
                    placements.add(place);
                }
            }
        }
        return placements;
    }

    /**
     * Returns an ArrayList of the Styles currently owned by the current player
     *
     * @return a ArrayList<Style> comprised of the styles owned by current player
     */
    public ArrayList<Style> getPlayerStyles() {
        ArrayList<Style> styles = new ArrayList<>();

        for (Style style: game.getStyles()) {
            if (style.getOwner() == player) {
                styles.add(style);
            }
        }
        return styles;
    }

    /**
     * Returns an ArrayList comprised of int[] moves that are allowed in the given style
     *
     * @param style   a Style object that the player is using
     * @return a ArrayList<int[]> comprised of all the moves that the style allows
     */
    public ArrayList<int[]> style_movements(Style style) {
        ArrayList<int[]> moves = new ArrayList<>();

        for (Move x : style.getMoves()){
            // create int array of specific move and add to arraylist
            int[] style_move = {x.getRow(), x.getCol()};
            moves.add(style_move);
        }
        return moves;
    }

    /**
     * Checks whether the given move is legal and valid in the current game based on the
     * requirements of Onitama's isLegalMove() and moveValid()
     *
     * @param rowO      integer representing the row origin
     * @param colO      integer representing the column origin
     * @param rowD      integer representing the row destination
     * @param colD      integer representing the column destination
     * @param styleName string representing the name of the movement style
     * @return whether the move is valid
     */
    public boolean isLegalAndValid(int rowO,int colO,int rowD,int colD, String styleName) {
        return game.isLegalMove(rowO, colO, rowD, colD) && game.moveValid(rowO, colO, rowD, colD, styleName);
    }


    public ArrayList<Object[]> collect_moves() {
        ArrayList<Object[]> moves = new ArrayList<>();

        // get lists of token placements and styles of player
        ArrayList<int[]> tokens = findTokens();
        ArrayList<Style> styles = getPlayerStyles();

        //loop through all tokens and styles to check if move is valid
        for (int[] token: tokens) {
            for (Style style: styles) {
                for (int[] move: style_movements(style)) {
                    int rowO,colO,rowD,colD;

                    rowO = token[0];
                    colO = token[1];

                    // if player is player1 then moves must be flipped
                    if (player == OnitamaBoard.G1) {
                        rowD = rowO - move[0];
                        colD = colO - move[1];
                    }

                    // regular moves
                    else {
                        rowD = rowO + move[0];
                        colD = colO + move[1];
                    }
                    // if given move is valid then add to moves ArrayList
                    if (isLegalAndValid(rowO,colO,rowD,colD, style.getName())) {
                        Object[] turn = {rowO,colO,rowD,colD, style.getName()};
                        moves.add(turn);
                    }
                }
            }
        }
        return moves;
    }


}
