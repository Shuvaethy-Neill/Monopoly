public class MonopolyClient {
    /**
     * The main method
     *
     * @param args String[], command line arguments
     */
    public static void main(String[] args) {
        MonopolyModel b = new MonopolyModel();
        BoardPanel bp = new BoardPanel(b);
        PlayerPanel pp = new PlayerPanel(b);
        MonopolyFrame mf = new MonopolyFrame(b, bp, pp);
    }
}
