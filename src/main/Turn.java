package main;

/**
 * A class to represent a potential turn (piece selection and movement).
 */

public class Turn {
    private int rowO, colO, rowD, colD;
    private String styleName;
    private char player;


    /**
     * Constructs a move (piece placement) that knows its row, col
     * movement from origin to destination on the grid
     *
     * @param row_o	integer representing the origin row of the piece to move
     * @param col_o	integer representing the origin column of the piece to move
     * @param row_d	integer representing the destination row of the piece to move
     * @param col_d	integer representing the destination column of the piece to move
     * @param styleName string representing the style being used to move
     * @param player the character representing the player making this Turn
     */
    public Turn(int row_o, int col_o, int row_d, int col_d, String styleName, char player) {
        this.rowO = row_o;
        this.colO = col_o;
        this.rowD = row_d;
        this.colD = col_d;
        this.styleName = styleName;
        this.player = player;
    }

    /**
     * Returns the original row associated with the token in this turn
     *
     * @return the original row
     */
    public int getRowO() { return this.rowO; }

    /**
     * Returns the original column associated with the token in this turn
     *
     * @return the original column
     */
    public int getColO() { return this.colO; }

    /**
     * Returns the Destination row associated with the token in this turn
     *
     * @return the Destination row
     */
    public int getRowD() { return this.rowD; }

    /**
     * Returns the Destination column associated with the token in this turn
     *
     * @return the Destination column
     */
    public int getColD() { return this.colD; }

    /**
     * Returns the style used in this turn
     *
     * @return the style used in the move
     */
    public String getStyle() { return this.styleName; }

    /**
     * Returns a string associated with the turn that is being completed
     * based on requirements from the ReadMe
     *
     * @return a string repersentation of the turn
     */
    public String toString() {

        // creating structure we need
        String s, comma, arrow, p1, p2;
        s = "";
        comma = ",";
        arrow = " -> ";
        p1 = "(";
        p2 = ")";

        // first bracket with the originating position ans style
        s += p1;
        s += Integer.toString(getRowO());
        s += comma;
        s += Integer.toString(getColO());
        s += comma;
        s += getStyle();
        s += p2;

        s += arrow;

        // second bracket with destination position
        s += p1;
        s += Integer.toString(getRowD());
        s += comma;
        s += Integer.toString(getColD());
        s += p2;

        return s;
    }

    public static void main(String[] args){
        // Create Turns
        Turn t1 = new Turn(0, 0, 1, 2, "dragon", 'X');
        Turn t2 = new Turn(3, 2, 2, 2, "crab", 'O');
        Turn t3 = new Turn(2, 2, 3, 1, "rooster", 'X');

        // Print Turns
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
    }
}
