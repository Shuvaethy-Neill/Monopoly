import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Monopoly Controller class translates the user's interaction with the view
 * to the MonopolyModel class
 *
 * @author Evan Smedley, Shuvaethy Neill, and Harsimran Kanwar
 * @verion 2.0
 * @since 2021-11-21
 */

public class MonopolyController extends DefaultHandler {

    /**
     *
     */
    private MonopolyModel model;

    /**
     *
     */
    private MonopolyFrame view;

    /**
     *
     */
    private static final int MAX_PLAYERS = 8;

    /**
     *
     */
    private int aiPlayers;

    /**
     *
     */
    private int humanPlayers;

    /**
     *
     */
    private Stack<String> stack;

    /**
     *
     */
    private ArrayList<BoardSpace> boardSpaces;

    /**
     *
     * @param model
     * @param view
     */
    public MonopolyController(MonopolyModel model, MonopolyFrame view) {
        // Initialize Variables
        this.model = model;
        this.view = view;
        this.boardSpaces = new ArrayList<>();
        this.stack = new Stack<>();

        // Get number of human players
        this.humanPlayers = getHumanPlayers();
        for (int i = 0; i < humanPlayers; i++) { //adding human player to model
            model.addPlayer(getPlayerInformation());
        }

        // Get number of ai players
        this.aiPlayers = aiPlayers();
        for (int i = 1; i <= aiPlayers; i++) {
            model.addPlayer(new MonopolyAIPlayer("Player " + (humanPlayers + i)));
        }

        // Get game version
        importFromXmlFile(getGameVersionFilename());
        model.setPieces(boardSpaces);
    }

    /**
     * Returns the number of human players the user decides to play with
     * @return the number the user has chosen
     */
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

    /**
     * Returns the number of ai players the user decides to play with
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

    private String getGameVersionFilename() {
        boolean validInput = false;
        String versionFilename = "";
        String message = "Please type the filename for the version you would like to play.";
        while (!validInput) {
            versionFilename = (String) JOptionPane.showInputDialog(view, message, "Version Filename", JOptionPane.PLAIN_MESSAGE);
            if (versionFilename == null) {
                message = "Please type the filename for the version you would like to player.\nPlease try again and press 'Ok' not 'Cancel'";
            } else if (versionFilename.equals("")) {
                message = "Please type the filename for the version you would like to player.\nThe filename cannot be blank.";
            } else {
                validInput = true;
            }
        }
        return "src/versions/" + versionFilename + ".xml";
    }


    /**
     * Returns the Player object with a username chosen by the user
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

    /**
     *
     * @param filename
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public void importFromXmlFile(String filename){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            parser.parse(filename, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        this.stack.push(qName);
        if (qName.equals("ColouredProperty")) {
            this.boardSpaces.add(new ColouredProperty());
            boardSpaces.get(boardSpaces.size() - 1).setType("colouredProperty");
        } else if (qName.equals("Railroad")) {
            this.boardSpaces.add(new Railroad());
            boardSpaces.get(boardSpaces.size() - 1).setType("railroad");
        } else if (qName.equals("Utility")) {
            this.boardSpaces.add(new Utility());
            boardSpaces.get(boardSpaces.size() - 1).setType("utility");
        } else if (qName.equals("Tax")) {
            this.boardSpaces.add(new Tax());
            boardSpaces.get(boardSpaces.size() - 1).setType("tax");
        } else if (qName.equals("Go")) {
            this.boardSpaces.add(new Go());
            boardSpaces.get(boardSpaces.size() - 1).setType("go");
        } else if (qName.equals("Free Parking")) {
            this.boardSpaces.add(new FreeParking());
            boardSpaces.get(boardSpaces.size() - 1).setType("freeParking");
        } else if (qName.equals("Jail")) {
            this.boardSpaces.add(new Jail());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        this.stack.pop();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String val = new String(ch, start, length);
        if (!(val.length() == 0)) {
            if (stack.peek().equals("name")) {
                boardSpaces.get(boardSpaces.size() - 1).setName(val);
            } else if (stack.peek().equals("path")) {
                boardSpaces.get(boardSpaces.size() - 1).setPath(val);
            } else if (stack.peek().equals("position")) {
                boardSpaces.get(boardSpaces.size() - 1).setPosition(Integer.parseInt(val));
            } else if (stack.peek().equals("price")) {
                ((Property) boardSpaces.get(boardSpaces.size() - 1)).setPrice(Integer.parseInt(val));
            } else if (stack.peek().equals("type")) {
                boardSpaces.get(boardSpaces.size() - 1).setType(val);
            } else if (stack.peek().equals("color")) {
                ((Property) boardSpaces.get(boardSpaces.size() - 1)).setColor(val);
            } else if (stack.peek().equals("houseHotelPrice")) {
                ((ColouredProperty) boardSpaces.get(boardSpaces.size() - 1)).setHouseHotelPrice(Integer.parseInt(val));
            } else if (stack.peek().equals("setSize")) {
                ((ColouredProperty) boardSpaces.get(boardSpaces.size() - 1)).setSetSize(Integer.parseInt(val));
            }
        }
    }
}
