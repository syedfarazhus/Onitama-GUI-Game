package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A class that represents a Human player as a child class of player in an onitama game.
 * A human player plays the game based on input from a human on the console
 */
public class PlayerHuman extends Player{

    private static final String INVALID_MOVE_INPUT_MESSAGE = "Invalid number, please enter 0-4";
    private static final String INVALID_STYLE_INPUT_MESSAGE = "Invalid style, please enter one of this player's styles " +
            "(Dragon, Crab, Horse, Mantis, Rooster)";
    private static final BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Constructs a new Human player that knows its char on the game board
     *
     * @param player	Char representing the player
     */
    public PlayerHuman(char player) {
        super(player);
    }

    /**
     * returns a Turn object of the next move made by the player based
     * on the input of the human on the console
     * order of input is style, rowO, colO, rowD, colD
     *
     * @return a turn object according to the input of the human
     */
    @Override
    public Turn getTurn() {
        String style_name = "";
        try {
            System.out.print("Choose your style:");
            style_name = PlayerHuman.stdin.readLine();
        } catch (IOException e) {
            System.out.println(INVALID_STYLE_INPUT_MESSAGE);
        }
        int row_o = getMove("row origin: ");
        int col_o = getMove("col origin: ");
        int row_d = getMove("row destination: ");
        int col_d = getMove("col destination: ");
        return new Turn(row_o, col_o, row_d, col_d, style_name, this.player);
    }

    /**
     * returns an int based on the input of the human on the console. it also checks that
     * the imputted int is valid and in the bounds, otherwise it throws and exception
     *
     * @param message	String repersenting a message to the human about what to input
     * @return a int according to the input of the human or -1 if its invalid
     */
    private int getMove(String message) {
        int move, lower = 0, upper = 4;
        while (true) {
            try {
                System.out.print(message);
                String line = PlayerHuman.stdin.readLine();
                move = Integer.parseInt(line);
                if (lower <= move && move <= upper) {
                    return move;
                } else {
                    System.out.println(INVALID_MOVE_INPUT_MESSAGE);
                }
            } catch (IOException e) {
                System.out.println(INVALID_MOVE_INPUT_MESSAGE);
                break;
            } catch (NumberFormatException e) {
                System.out.println(INVALID_MOVE_INPUT_MESSAGE);
            }
        }
        return -1;
    }
}