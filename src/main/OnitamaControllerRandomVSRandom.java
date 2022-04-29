package main;

/**
 * An OnitamaControllerRandomVSRandom class which allows a Onitama game to be played among
 * 2 random players
 */
public class OnitamaControllerRandomVSRandom {

    protected Onitama onitama;
    PlayerRandom player1, player2;

    /**
     * Constructs 2 random players and initializes a new Onitama game with the 2 players
     */
    public OnitamaControllerRandomVSRandom() {
        this.player1 = new PlayerRandom(OnitamaBoard.G1);
        this.player2 = new PlayerRandom(OnitamaBoard.G2);
        this.onitama = new Onitama(player1, player2);
        this.player1.addGame(onitama);
        this.player2.addGame(onitama);
    }

    /**
     * This method plays the Onitama game until a certain player wins the game
     * and prints the right reports and messages based on the status of the game
     */
    public void play() {
        while (onitama.getWinner() == OnitamaBoard.EMPTY) {
            this.report();

            Turn turn = null;
            Player whosTurn = onitama.getWhoseTurn();
            turn = whosTurn.getTurn();

            this.reportTurn(whosTurn.getPlayer(), turn);
            onitama.move(turn.getRowO(), turn.getColO(), turn.getRowD(),
                    turn.getColD(), turn.getStyle());
        }
        this.reportFinal();
    }

    /**
     * This method prints out the specific turn in the required string format
     *
     * @param whosTurn indicating the player whos turn it is currently
     * @param turn the Turn object corresping to the move the current player is making
     */
    private void reportTurn(char whosTurn, Turn turn) {
        System.out.println(whosTurn + " makes move " + turn + "\n");
    }

    /**
     * This method prints out the status of the board and styles as well
     * as whose turn it is after every turn in string format
     */
    private void report() {

        String s = onitama.getBoardString() + onitama.getStylesString(OnitamaBoard.G1) +
                onitama.getStylesString(OnitamaBoard.G2) +
                onitama.getStylesString(OnitamaBoard.EMPTY)
                + "  " + onitama.getWhoseTurn().getPlayer() + " moves next";
        System.out.println(s);
    }

    /**
     * This method prints out a string repersenting the winner of the
     * game after the game is won
     */
    private void reportFinal() {

        String s = onitama.getBoardString() + "  "
                + onitama.getWinner() + " won\n";
        System.out.println(s);
    }

    public static void main(String[] args) {

        OnitamaControllerRandomVSRandom oc = new OnitamaControllerRandomVSRandom();
        oc.play();
    }
}
