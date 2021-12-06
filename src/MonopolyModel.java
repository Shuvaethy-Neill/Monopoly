import java.io.*;
import java.util.*;

/**
 * The MonopolyModel Class that contains the MVC Model of the Monopoly board
 *
 * @author Harsismran Kanwar, Dorothy Tran, Shuvaethy Neill, and Evan Smedley
 * @version 3.0
 * @since 2021-11-21
 */

public class MonopolyModel implements Serializable {

    private List<MonopolyView> monopolyViews;

    private Random rand;

    private Dice dice;

    private ArrayList<BoardSpace> pieces;

    private static final String HELP = "Help";
    private static final String ROLL = "Roll Dice";
    private static final String BUY = "Buy";
    private static final String BUILD = "Build House/Hotel";
    private static final String PASS = "Pass";
    private static final String QUIT = "Quit";

    private int player; // current player index

    private ArrayList<Player> players;

    private String outputText; //text to notify user of decisions made

    public enum Status implements Serializable {UNDECIDED,PLAYING, BUILDING, BANKRUPT, BANKRUPT2, QUITTING, JAIL};

    private Status playerStatus;
    private boolean ended;

    /**
     * Constructor for the Board Class
     */
    public MonopolyModel() {
        rand = new Random();
        dice = new Dice();
        player = 0;
        playerStatus = Status.UNDECIDED;
        outputText="";
        ended = false;
        players = new ArrayList<>();
        pieces = new ArrayList<>();

        monopolyViews = new ArrayList<>();
    }

    /**
     * Method to add view to the list of view in the model
     * @param view
     */
    public void addView(MonopolyView view) {
        this.monopolyViews.add(view);
    }

    /**
     * Method to remove views from model
     * @param viewIndex
     */
    public void removeView(int viewIndex) {
        this.monopolyViews.remove(viewIndex);
    }

    /**
     *Method to update all the views and notify them
     * that an event has occurred
     */
    public void notifyViews() {
        for (MonopolyView v : monopolyViews) {
            v.update(new MonopolyEvent(this));
        }
    }

    /**
     * add player to the game
     * @param player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Method that retrieves list of players
     * @return players
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Method to fetch current player
     * @return player
     */
    public int getPlayer() {
        return this.player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    /**
     * Method that returns the array of Dice rolled
     * @return dice
     */
    public Dice getDice(){
        return this.dice;
    }

    /**
     * Method that gets the outputText.
     * Used to display messages to the GUI
     * @return outputText
     */
    public String getOutputText() {
        return outputText;
    }

    /**
     * Method to fetch the status of the player
     * @return playerStatus
     */
    public Status getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(Status playerStatus) {
        this.playerStatus = playerStatus;
    }

    /**
     * Method gets the board space pieces
     *
     * @return BoardSpace[], The array of BoardSpace pieces
     */
    public ArrayList<BoardSpace> getPieces() {
        return pieces;
    }

    /**
     *
     * @param pieces
     */
    public void setPieces(ArrayList<BoardSpace> pieces) { this.pieces = pieces; }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Method gets the BoardSpace piece position
     * @param i, the index of the BoardSpace piece
     * @return BoardSpace, the board space piece
     */
    public BoardSpace getP(int i) {
        return pieces.get(i);
    }

    /**
     * Method that starts the game
     * @return name of the player
     */
    public String start() {
        System.out.println();
        System.out.println(players.size());
        player = rand.nextInt(players.size());
        outputText = "Starting the game...\n" + players.get(player).getName() + " will start!";
        if (players.get(player) instanceof MonopolyAIPlayer){
            moveAi();
            outputText = "An AI player has started and completed their turn!"+
                    "\nCheck the side panels to see the moves of the AI player(s)\n"+
                    "\nNow it's " +players.get(player).getName() + "'s turn!";
        }
        notifyViews();
        return players.get(player).getName();
    }

    /**
     * Method that holds the instructions for the game
     */
    private void help(){
        outputText = "Help:" +"\n" + "Press the 'Roll Dice' button to roll dices on your turn"+"\n" +
                "Press the 'Buy Property' button to purchase a property" + "\n" +
                "Press the 'Pass' button to pass your turn to the next player"  + "\n" +
                "Press the 'Quit' button to end and close the game";
    }


    /**
     * Method to check if player is an AI and end their turn if they are
     * @param player
     */
    private void checkIfAI(Player player){
        if (player instanceof MonopolyAIPlayer){
            ended = true;
            ((MonopolyAIPlayer) player).setRolled(false);
            ((MonopolyAIPlayer) player).setBought(false);
            aiTurnEndText();
        }
    }

    /**
     * Method to check if the roll was a double, and if so, end turn
     * @param dice
     */
    private void handleNonDoubleTurnRoll(Dice dice){
        if (!dice.isDouble()) {
            checkIfAI(players.get(player));
            endTurn();
        }
    }

    /**
     * Helper method that displays the generic text for when AI is done their turn
     */
    private void aiTurnEndText(){
        outputText = "AI Players have completed their turns." +
                "\nCheck the side panels to see where they landed and if they bought\nnew property.\n";
    }

    /**
     * Helper method that handles all rent payments when player lands on properties
     */
    private void payRent(Property property){
        if(property instanceof Utility){
            players.get(player).doTransaction((property.getRent()) * dice.getRollValue()); // Deducts the cost from account
            property.getOwner().setMoney((property.getRent()) * dice.getRollValue());
        }
        players.get(player).doTransaction(property.getRent()); // Deducts the cost from account
        property.getOwner().setMoney(property.getRent()); // adds the rent cost to the owner's account
    }

    /**
     * Method that rolls the dice and handles actions users could take on a certain
     * boardspace such as pay rent or pay tax
     */
    public void roll() {
        this.playerStatus = Status.PLAYING;
        dice.roll();
        if (dice.isDouble()) {
            players.get(player).incrementNumDoublesRolled();
            if (players.get(player).isInJail()){
                players.get(player).setJailStatus(false);
            }
        }
        if (players.get(player).getNumDoublesRolled() == 3) {
            outputText= "Oh no you rolled three doubles\n";
            endTurn();
        } // If 3 doubles rolled end turn

        if (!players.get(player).isInJail()) {
            outputText += "Rolling the Dice! You rolled : " + dice.toString() +
                    "\nYou will move up " + dice.getRollValue() + " spaces on the board!";
            players.get(player).move(dice.getRollValue(), pieces.size()); //move the player to right position
        }
        else { outputText += "You are in Jail!\n";}

        if (players.get(player).checkReset()){
            passedGo();
        }
        players.get(player).setPositionName(pieces.get(players.get(player).getPosition()).toString()); //tell player where they are located
        outputText += pieces.get(players.get(player).getPosition()).displayInfo();

        if(checkBankruptcy()){
            checkIfAI(players.get(player));
            endTurn();
        }
        else if (pieces.get(players.get(player).getPosition()) instanceof Jail) {
            handleJail();
        }
        else if ((pieces.get(players.get(player).getPosition()) instanceof FreeParking) || (pieces.get(players.get(player).getPosition()) instanceof Go)) {
            this.playerStatus = Status.UNDECIDED;
            handleNonDoubleTurnRoll(dice);
        }
        else if (pieces.get(players.get(player).getPosition()) instanceof Tax) {
            players.get(player).doTransaction(((Tax) pieces.get(players.get(player).getPosition())).getCost());
            this.playerStatus = Status.UNDECIDED;
            handleNonDoubleTurnRoll(dice);
        }
        else if ((pieces.get(players.get(player).getPosition()) instanceof Property) && !((Property) pieces.get(players.get(player).getPosition())).isAvailable()) { // Property is not available
            // Player who owns the property gets rent
            if (((Property) pieces.get(players.get(player).getPosition())).getOwner().equals(players.get(player))) { // If the player lands on themselves, do nothing
                outputText+="\nYou do not need to pay rent since you own this property.\n";
            } else {
                outputText+="\nTaking the money from your account\n";
                payRent((Property) pieces.get(players.get(player).getPosition()));
            }
            handleNonDoubleTurnRoll(dice);
        }
    }

    /**
     * Method to handle when a player lands in jail
     */
    public void handleJail(){
        if ((pieces.get(players.get(player).getPosition())).getType().equals("go to jail")){ //kinda smellyy
            players.get(player).setJailStatus(true);
            players.get(player).move(18, pieces.size()); //will update once all pieces are on boards
            if (players.get(player) instanceof MonopolyAIPlayer){
                ended = true;
                aiTurnEndText();
            }
            endTurn();
        }
        else{
            //what happens when they're in jail or if they're passing by
            if (!players.get(player).isInJail()){
                outputText += "\nYou're just visiting jail. This is a free space\n";
                if (players.get(player) instanceof MonopolyAIPlayer){
                    ended = true;
                    aiTurnEndText();
                }
                endTurn();
            }
            else{ //actually in jail
                players.get(player).incrementTurn();
                if (players.get(player).getTurns() == 3){
                    buy();
                }
                if (players.get(player) instanceof MonopolyAIPlayer){ended = true;}
                endTurn();
            }
        }
    }

    /**
     * Notifies and handles when a player passes go.
     */
    public void passedGo(){
        if(!players.get(player).isInJail()) {
            players.get(player).setMoney(((Go) pieces.get(0)).getAmount());
            outputText += "\nYou passed Go! Collecting $200!\n";
        }
    }

    /**
     * Method checks if the Player is bankrupt
     *
     * @return boolean, true if they are bankrupt, false otherwise
     */
    public boolean checkBankruptcy() {
        boolean check = false;

        if (pieces.get(players.get(player).getPosition()) instanceof Tax) {
            check = players.get(player).getBankrupt(((Tax) pieces.get(players.get(player).getPosition())).getCost());
        } else if (pieces.get(players.get(player).getPosition()) instanceof Property) {
            if ((!((Property) pieces.get(players.get(player).getPosition())).isAvailable()) && (!(((Property) pieces.get(players.get(player).getPosition())).getOwner().equals(players.get(player))))) { //property not available
                check = players.get(player).getBankrupt(((Property) pieces.get(players.get(player).getPosition())).getRent());
            } else {
                check = (players.get(player).getBankrupt(((Property) pieces.get(players.get(player).getPosition())).getPrice()));
            }
        }

        if (check) {
            outputText += "\nOh no you don't have enough money!\nYou have reached bankruptcy :( \nYou are being eliminated from the game";
            for (int i = 0; i < players.get(player).getProperties().size(); i++) {
                players.get(player).getProperties().get(i).sell();
            }
            if(getPlayers().size() > 2) {
                this.playerStatus = Status.BANKRUPT2;
                players.remove(player);
                player = player -1 ; //set the current player
            }
            else{
                this.playerStatus = Status.BANKRUPT;
                players.remove(player);
                if (players.size() == 1) {
                    outputText += "\nGame Over! " + players.get(0).getName() + " has won the game! \nThanks for playing! ";
                }
            }
            notifyViews();
        }
        return check;
    }

    /**
     * Method to buy a property
     */
    private void buy() {
        if (pieces.get(players.get(player).getPosition()) instanceof Jail){
            players.get(player).doTransaction(50);
            players.get(player).setJailStatus(false);
            outputText+="\nSuccessfully took $50 from your account, you are free to leave jail!\n";
        }
        else if (((Property) pieces.get(players.get(player).getPosition())).isAvailable()) {
            players.get(player).doTransaction(((Property) pieces.get(players.get(player).getPosition())).getPrice()); // price of property is deducted from player's account
            players.get(player).addProperty(((Property) pieces.get(players.get(player).getPosition()))); // property is added to player's account
            ((Property) pieces.get(players.get(player).getPosition())).purchase(); //set property to unavailable
            ((Property) pieces.get(players.get(player).getPosition())).setOwner(players.get(player)); //set owner
            if(pieces.get(players.get(player).getPosition()) instanceof ColouredProperty) {
                updatePropertyLists((ColouredProperty) (pieces.get(players.get(player).getPosition())));
            }
            outputText+="Successfully purchased!\n";
        }
        else {
            outputText= "\nUnfortunately the property you are on is not available for purchase.";
        }
    }

    /**
     * Method to update properties a player has and categorize them in list
     * (acts as property cards a player has)
     * @param property
     */
    private void updatePropertyLists(ColouredProperty property){
        Player currentPlayer = this.getPlayers().get(player);
        //if property colour is not in list add colour
        if (!currentPlayer.getPlayerColours().containsKey(property.getColor())) {
            currentPlayer.getPlayerColours().put(property.getColor(), new ArrayList<>());
        }
        //if property not in list, then add
        if(!currentPlayer.getPlayerColours().containsValue(property)) {
            currentPlayer.getPlayerColours().get(property.getColor()).add(property);
        }
        if ((currentPlayer.getPlayerColours().get(property.getColor()).size() == (property).getSetSize()) && !currentPlayer.getPossibleColoursForBuilding().contains(property.getColor())) {
            currentPlayer.getPossibleColoursForBuilding().add(property.getColor());
        }
    }

    /**
     * Helper method that checks if a player can build a house or hotel
     */
    private void buildHouseHotel() {
        this.playerStatus = Status.BUILDING;
        Player currentPlayer = this.getPlayers().get(player);

        if (currentPlayer.getPossibleColoursForBuilding().size() == 0) {
            currentPlayer.setCanBuild(false);
            outputText += "You cannot build any houses/hotels because you don't have any\nfull property sets yet!";
        } else {
            currentPlayer.setCanBuild(true);
            for(String s: currentPlayer.getPossibleColoursForBuilding()) {
                outputText += "Each house/hotel price on a " + s + " property is $" + currentPlayer.getPlayerColours().get(s).get(0).getHouseHotelPrice()+ "\n";
            }
        }
    }

    /**
     * Method passes the turn to the next player in the game
     */
    public void endTurn() {
        players.get(player).resetNumDoublesRolled();
        player++;
        if (player > players.size() - 1) {
            player = 0;
        }
        outputText += "\nNow it's "+ (players.get(player).getName()) + "'s turn!";
        if(players.get(player).isInJail()){
            this.playerStatus=Status.JAIL;
            if(players.get(player) instanceof MonopolyAIPlayer){
                this.play(((MonopolyAIPlayer) (players.get(player))).getRollDecision());
            }
        }
        else {
            this.playerStatus = Status.UNDECIDED;

            if (players.get(player) instanceof MonopolyAIPlayer) {
                moveAi();
            }
        }
    }

    /**
     * Method to handle the AI player actions
     */
    private void moveAi(){
        this.play(((MonopolyAIPlayer) (players.get(player))).getRollDecision());
        if (!ended) { //endTurn didn't get called in roll()
            if (pieces.get(players.get(player).getPosition()) instanceof Property && players.get(player) instanceof MonopolyAIPlayer) {
                this.play(((MonopolyAIPlayer) (players.get(player))).getRollDecision());
                aiTurnEndText();
                outputText += "\nNow it's " +players.get(player).getName() + "'s turn!";
            }
            if (dice.isDouble() && players.get(player) instanceof MonopolyAIPlayer) {
                this.play(((MonopolyAIPlayer) (players.get(player))).getRollDecision());
            }
        }
        ended = false;
    }

    /**
     * Method displays the user interface of the Monopoly Board that takes user input
     */
    public void play(String command) {
        outputText = this.getPlayers().get(player).getName().toUpperCase(Locale.ROOT) + "'S TURN:\n";

        if (command.equals(HELP)) {
            help();
        }
        else if (command.equals(BUY)) {
            // The situation when the player purchases a property
            if (pieces.get(players.get(player).getPosition()) instanceof Property || pieces.get(players.get(player).getPosition()) instanceof Jail) {
                buy();
            }
            if (!dice.isDouble()) {
                endTurn();
            }
        }
        else if(command.equals(BUILD)){
            buildHouseHotel();
        }
        else if (command.equals(ROLL)) {
            roll();
        } else if (command.equals(PASS)) {
            if (players.get(player) instanceof MonopolyAIPlayer){outputText="AI player has passed/completed their turn! Passing to next player.\n"; }
            else{outputText= players.get(player).getName() + " has passed their turn! Passing to next player.\n";}
            endTurn();
        }
        else if (command.equals(QUIT)) {
            this.playerStatus = Status.QUITTING;
        }
        notifyViews();
    }

    /**Method to save Model Data*/
    public void saveSerialize(String filename){ //save file
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream outputStream = new ObjectOutputStream(file);
            //outputStream.writeObject(playerStatus);
            outputStream.writeObject(getPlayer());
            outputStream.writeObject(getPlayers());
            outputStream.writeObject(getPieces());
            System.out.println("in model saving");
            outputStream.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Method to import Model Data **/
    public void importSerialize(String filename) { //import file
        ArrayList<Player> loadPlayers = new ArrayList<>();
        Status ps = null;
        int currPlayer = 0;
        ArrayList<BoardSpace> loadPieces = new ArrayList<>();
        try {
            FileInputStream f = new FileInputStream(filename);
            ObjectInputStream i = new ObjectInputStream(f);
            //ps = (Status) i.readObject();
            currPlayer = (int) i.readObject();
            loadPlayers = (ArrayList<Player>) i.readObject();
            loadPieces= (ArrayList<BoardSpace>) i.readObject();
            i.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        //setPlayerStatus(ps);
        setPlayer(currPlayer);
        setPlayers(loadPlayers);
        setPieces(loadPieces);

    }




}