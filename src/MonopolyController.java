import javax.swing.*;
import java.util.Objects;

/**
 * WE NEED TO FIX THIS
 * bug where if user presses enter when asked for a username, the username becomes blank, we should prevent this..
 */

public class MonopolyController {
    private MonopolyModel model;
    private MonopolyFrame view;

    private static final int MAX_PLAYERS = 4;

    private int totalPlayers;
    private int humanPlayers;

    public MonopolyController(MonopolyModel model, MonopolyFrame view) {
        this.model = model;
        this.view = view;
        this.totalPlayers = getNumPlayers();
        for (int i = 0; i < totalPlayers; i++) {
            model.addPlayer(getPlayerInformation());
        }
    }

    /** Returns the number of players the user decides to play with
     * @return the number the user has chosen
     */
    private int getNumPlayers() {
        Object[] options = new Object[MAX_PLAYERS-1];
        for (int i = 0; i < MAX_PLAYERS - 1; i++) {
            options[i] = i + 2;
        }
        boolean validInput = false;
        Object numPlayersObject = null;
        String message = "How many players are there?";
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
