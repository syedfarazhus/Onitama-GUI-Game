package main;

public class OnitamaBoard {
    public static final char EMPTY = ' ', M1 = 'x', G1 = 'X', M2 = 'o', G2 = 'O';
    private int size = 5;
    private char[][] board;
    private Style[] styles;

    /**
     * Constructs an empty main.Onitama board. Places four monks and one grandmaster on
     * opposite sides of the board. Creates five Styles and distributes them among
     * the players.
     *
     * @param size this board's width and height
     */
    public OnitamaBoard(int size) {

        // if size isnt valid create one with size 5
        if (size % 2 == 0 || size < 5) {
            new OnitamaBoard(5);
        }

        else {
            this.size = size;
            char[][] board = new char[size][size];

            // set all spaces to EMPTY
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    board[i][j] = EMPTY;
                }
            }

            // set the monks on their respective sides
            for (int i = 0; i < size; i++) {
                board[0][i] = M1;
                board[size-1][i] = M2;
            }

            // set the grandmasters in their positions
            board[0][size/2] = G1;
            board[size-1][size/2] = G2;

            this.board = board;
            constructStyles();
        }
    }

    /**
     * Constructs a preset main.Onitama board from the provided input board.
     * Creates five styles and distributes it amongst the players.
     *
     * @param size this boards width and height
     * @param board the preset board to copy over PRE: board is a size x size array.
     */
    public OnitamaBoard(int size, char[][] board){

        // if size isnt valid create one with size 5
        if (size % 2 == 0 || size < 5) {
            new OnitamaBoard(5);
        }
        else {
            this.size = size;
            this.board = board;
            constructStyles();
        }

    }

    /**
     * Constructs the 5 movement styles of main.Onitama for this board.
     * Normally, there are 16 movement styles and they are distributed randomly, however for this assignment,
     * you are only required to use 5 of them (Dragon, Crab, Horse, Mantis, and Rooster).
     *
     * You can find the movement patterns for these styles under assets/{style}.png, where {style} is one of
     * the five styles mentioned above. Additionally, you can also find the images in README.md.
     *
     * IMPORTANT:
     * Additionally, we are going to distribute the styles at the start of the game in a static or consistent manner.
     *      Player 1 (G1) must get the Crab and Horse styles.
     *      Player 2 (G2) must get the Mantis and Rooster styles.
     *      Extra (EMPTY) must get the Dragon style.
     *
     * Please be sure to follow the distribution of styles as mentioned above as this is important for testing.
     * Failure to follow this distribution of styles will result in the LOSS OF A LOT OF MARKS.
     */
    public void constructStyles() {

        // creating all the move sets for styles
        int[][] c_moves = {{-1,0}, {0,-2},{0,2}};
        int[][] h_moves = {{-1,0}, {1,0},{0,-1}};
        int[][] m_moves = {{1,0}, {-1,-1}, {-1,1}};
        int[][] r_moves = {{0,-1}, {0,1}, {-1,1}, {1,-1}};
        int[][] d_moves = {{1,-1}, {-1,1}, {-1,-2}, {-1,2}};

        // initializing Style objects for each style
        Style crab = new Style(c_moves, "crab");
        Style horse = new Style(h_moves, "horse");
        Style mantis = new Style(m_moves, "mantis");
        Style rooster = new Style(r_moves, "rooster");
        Style dragon = new Style(d_moves, "dragon");

        // setting owners of styles
        crab.setOwner(G1);
        horse.setOwner(G1);
        mantis.setOwner(G2);
        rooster.setOwner(G2);
        dragon.setOwner(EMPTY);

        // update styles attribute
        this.styles = new Style[] {crab, horse, mantis, rooster, dragon};

    }

    /**
     * Returns the dimensions of this board.
     *
     * @return this board's width/height
     */
    public int getDimension() { return this.size; }

    /**
     * Returns the styles of this board.
     *
     * @return this board's styles
     */
    public Style[] getStyles() { return this.styles; }

    /**
     * Returns the player token that is in the given position, or the
     * empty character if no player token is there or if the position provided is
     * invalid.
     *
     * @param row integer representing a row on this board
     * @param col integer representing a column on this board
     * @return character representing M1,G1,M2,G2 or EMPTY
     */
    public char getToken(int row, int col) { return this.board[row][col]; }

    /**
     * Sets the given position on the board to be the given player (or throne/empty)
     * token.
     *
     * @param row   integer representing a row on this board
     * @param col   integer representing a column on this board
     * @param token character for M1, M2, G1, G2, or EMPTY
     */
    public void setToken(int row, int col, char token) { this.board[row][col] = token; }

    /**
     * Returns true iff the provided coordinates are valid (exists on the board).
     *
     * @param row integer representing a row on this board
     * @param col integer representing a column on this board
     * @return whether (row, col) is a position on the board. Example: (6,12) is not
     *         a valid position on an 8x8 board.
     */
    private boolean validCoordinate(int row, int col) {

        boolean row_valid = 0 <= row && row < this.size;
        boolean col_valid = 0 <= col && col < this.size;

        return row_valid && col_valid;
    }

    /**
     * Exchange the given style with the empty style (the style whose owner is EMPTY).
     * Hint: Exchanging will involve swapping the owners of the style.
     *
     * @param style the movement style to be exchanged.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean exchangeStyle(Style style){

        // get owner of style
        char owner = style.getOwner();

        // loop through all styles
        for (Style empty_style: this.styles) {
            // if style owner is empty then switch owners
            if (empty_style.getOwner() == EMPTY) {
                style.setOwner(EMPTY);
                empty_style.setOwner(owner);
                return true;
            }
        }
        return false;
    }

    /*
        DO NOT CHANGE ANYTHING BELOW!!!
        Changing things below will mess up the Auto tests and result in a MAJOR LOSS OF MARKS!!!
     */

    /**
     * DO NOT MODIFY THIS!!!
     * Creates and returns a deep copy of this OnitamaBoard's current state.
     *
     * @return a deep copy of the current board.
     */
    public char[][] getBoard(){

        char [][] boardCopy = new char[this.size][this.size];
        for (int i = 0; i < this.size; i++){
            System.arraycopy(this.board[i], 0, boardCopy[i], 0, this.size);
        }
        return boardCopy;
    }

    /**
     * Returns a string representation of this game board.
     *
     * @return a string representation of this, just the play area, with no
     *         additional information.
     */
    // DO NOT MODIFY THIS!! DOING SO MAY CAUSE OUR AUTOTESTS TO FAIL
    // WHICH WOULD LEAD TO A SIGNIFICANT LOSS OF CORRECTNESS MARKS
    public String toString() {
        /**
         * See assignment web page for sample output.
         */
        String s = "";
        s += "  ";
        for (int col = 0; col < this.size; col++) {
            s += col + " ";
        }
        s += '\n';

        s += " +";
        for (int col = 0; col < this.size; col++) {
            s += "-+";
        }
        s += '\n';

        for (int row = 0; row < this.size; row++) {
            s += row + "|";
            for (int col = 0; col < this.size; col++) {
                s += this.board[row][col] + "|";
            }
            s += row + "\n";

            s += " +";
            for (int col = 0; col < this.size; col++) {
                s += "-+";
            }
            s += '\n';
        }
        s += "  ";
        for (int col = 0; col < this.size; col++) {
            s += col + " ";
        }
        s += '\n';
        return s;
    }
}
