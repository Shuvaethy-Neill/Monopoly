import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
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
        //ask user if they want to load
        int state = gameState(); //load =1 new=0
        if(state==0) {
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
        }
        else{
            this.model = model.importSerialize("MonopolyGame.txt");
            this.view = new MonopolyFrame(this.model);
            BoardPanel boardPanel = new BoardPanel(this.model);
            PlayerPanel playerPanel = new PlayerPanel(this.model);
            view.addPanels(boardPanel, playerPanel);

            System.out.println("imported!");
        }
    }


    private int gameState(){
        Object[] options = { "New Game", "Load Previous Game" };
        int s = JOptionPane.showOptionDialog(null, "How would you like to start?", "Monopoly",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                options, options[0]);
        return s;
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
    public void importFromXmlFile(String filename) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(filename, this);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        this.stack.push(qName);
        if (qName.equals("ColouredProperty")) {
            this.boardSpaces.add(new ColouredProperty());
        } else if (qName.equals("Railroad")) {
            this.boardSpaces.add(new Railroad());
        } else if (qName.equals("Utility")) {
            this.boardSpaces.add(new Utility());
        } else if (qName.equals("Tax")) {
            this.boardSpaces.add(new Tax());
        } else if (qName.equals("Go")) {
            this.boardSpaces.add(new Go());
        } else if (qName.equals("Free Parking")) {
            this.boardSpaces.add(new FreeParking());
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
                ((ColouredProperty) boardSpaces.get(boardSpaces.size() - 1)).setPrice(Integer.parseInt(val));
            } else if (stack.peek().equals("type")) {
                boardSpaces.get(boardSpaces.size() - 1).setType(val);
            } else if (stack.peek().equals("color")) {
                ((ColouredProperty) boardSpaces.get(boardSpaces.size() - 1)).setColor(val);
            } else if (stack.peek().equals("houseHotelPrice")) {
                ((ColouredProperty) boardSpaces.get(boardSpaces.size() - 1)).setHouseHotelPrice(Integer.parseInt(val));
            } else if (stack.peek().equals("setSize")) {
                ((ColouredProperty) boardSpaces.get(boardSpaces.size() - 1)).setSetSize(Integer.parseInt(val));
            }
        }
    }
}
