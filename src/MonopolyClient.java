public class MonopolyClient {
    /**
     * The main method
     *
     * @param args String[], command line arguments
     */
    public static void main(String[] args) {
        MonopolyModel model = new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(model);
        BoardPanel boardPanel = new BoardPanel(model);
        PlayerPanel playerPanel = new PlayerPanel(model);
        monopolyFrame.addPanels(boardPanel, playerPanel);
    }
}
