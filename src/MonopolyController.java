import javax.swing.*;
import java.util.Objects;

public class MonopolyController {
    private MonopolyModel model;
    private MonopolyFrame view;

    private static final int MAX_PLAYERS = 8;

    private int aiPlayers;
    private int humanPlayers;

    public MonopolyController(MonopolyModel model, MonopolyFrame view) {
        this.model = model;
        this.view = view;
        this.humanPlayers = getHumanPlayers();
        this.aiPlayers = aiPlayers();
        for (int i = 0; i < humanPlayers; i++) { //adding human player to model
            model.addPlayer(getPlayerInformation());
        }
        for (int i = 1; i <= aiPlayers; i++) {
            model.addPlayer(new MonopolyAIPlayer("Player " + (humanPlayers + i)));
        }
    }

    private int getHumanPlayers() {
        Object[] options = new Object[MAX_PLAYERS - 1];
        for (int i = 0; i < MAX_PLAYERS - 1; i++) {
            options[i] = i + 2;
        }
        boolean validInput = false;
        Object numPlayersObject = null;
        String message = "How many human players are there?";
        while (!validInput) {
            numPlayersObject = JOptionPane.showInputDialog(
                    view, message, "Player Selection",
                    JOptionPane.PLAIN_MESSAGE, null, options, 2
            );
            if (numPlayersObject == null) {
                message = "How many players are there?\nPlease press 'Ok', not 'Cancel'";
            } else {
                validInput = true;
            }
        }
        return (int) numPlayersObject;
    }

    /** Returns the number of players the user decides to play with
     * @return the number the user has chosen
     */
    private int aiPlayers() {
        Object[] options = new Object[MAX_PLAYERS - humanPlayers + 1]; //0-7 index
        for (int i = 0; i < MAX_PLAYERS - humanPlayers + 1; i++) {
            options[i] = i;
        }
        boolean validInput = false;
        Object numPlayersObject = null;
        String message = "How many AI players are there?";
        while (!validInput) {
            numPlayersObject = JOptionPane.showInputDialog(
                    view, message, "AI Player Selection",
                    JOptionPane.PLAIN_MESSAGE, null, options, 0
            );
            if (numPlayersObject == null) {
                message = "How many players are there?\nPlease press 'Ok', not 'Cancel'";
            } else {
                validInput = true;
            }
        }
        return (int) numPlayersObject;
    }


    /** Returns the Player object with a username chosen by the user
     * @return the player object
     */
    private Player getPlayerInformation() {
        boolean validInput = false;
        String playerName = "";
        String message = "Please choose a name that is 10 characters or less.\n" +
                "If it is too long it will be truncated.";
        while (!validInput) {
            playerName = (String) JOptionPane.showInputDialog(
                    view, message,
                    "Player " + (model.getPlayers().size() + 1) + " Name Selection", JOptionPane.PLAIN_MESSAGE
            );
            if (playerName == null) {
                message = "Please choose a name that is 10 characters or less.\n" +
                        "If it is too long it will be truncated.\nPlease try again and press 'Ok' not 'Cancel'";
            } else if (playerName.equals("")) {
                message = "Please choose a name that is 10 characters or less.\n" +
                        "If it is too long it will be truncated.\nYour name cannot be blank";
            } else {
                validInput = true;
                playerName = playerName.substring(0, Math.min(playerName.length(), 10));
            }
        }

        return new Player(playerName);
    }
}
