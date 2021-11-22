import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * The MonopolyModel Class that contains the MVC Model of the Monopoly board
 *
 * @author Harsismran Kanwar, Dorothy Tran, Shuvaethy Neill, and Evan Smedley
 * @version 3.0
 * @since 2021-11-21
 */

//bugs to note:
    //- when player before AI buys a property, it doesnt give them confirmation that they bought it (since AI text is too speedy) BUT it does show in side panel so not a big deal???
    //- if ai rolled doubles, it shows "your turn is now over" instead of usual "AI players have completed their turn" since we call pass in that case. We can change or keep? nt a big deal
    // if ai player goes to jail, it shows what they rolled along with that message. Should we change it to the generic ai turn completed message to keep it consistent?
        //also it makes user choose what ai does in jail
    //index out of bounds error for bankruptcy with ai

public class MonopolyModel {

    private List<MonopolyView> monopolyViews;

    private Random rand;

    private Dice dice;

    private BoardSpace[] pieces;

    private static final String HELP = "Help";
    private static final String ROLL = "Roll Dice";
    private static final String BUY = "Buy Property";
    private static final String PASS = "Pass";
    private static final String QUIT = "Quit";

    private int position;

    private int player; // current player

    private ArrayList<Player> players;

    private String outputText; //text to notify user of decisions made

    public enum Status {UNDECIDED,PLAYING, BANKRUPT, BANKRUPT2, QUITTING, JAIL};

    private Status playerStatus;
    private boolean ended;

    /**
     * Constructor for the Board Class
     */
    public MonopolyModel() {
        rand = new Random();
        dice = new Dice();
        pieces = new BoardSpace[BoardSquares.values().length];
        position = 0;
        player = 0;
        playerStatus = Status.UNDECIDED;
        outputText="";
        ended = false;
        for (BoardSquares s : BoardSquares.values()) {
            if (position == BoardSquares.values().length) {
                continue;
            } else {
                pieces[position] = s.boardSpace;
            }
            position += 1;
        }
        players = new ArrayList<>();

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
     * that an event has occured
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

    /**
     * Method gets the board space pieces
     *
     * @return BoardSpace[], The array of BoardSpace pieces
     */
    public BoardSpace[] getPieces() {
        return pieces;
    }

    /**
     * Method gets the BoardSpace piece position
     * @param i, the index of the BoardSpace piece
     * @return BoardSpace, the board space piece
     */
    public BoardSpace getP(int i) {
        return pieces[i];
    }

    /**
     * Method that starts the game
     * @return name of the player
     */
    public String start() {
        for (int i = 0; i < players.size(); i++){
            System.out.println(players.get(i) instanceof MonopolyAIPlayer);
        }
        System.out.println();
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
            if (players.get(player) instanceof MonopolyAIPlayer){ended = true;}
            endTurn();
        } // If 3 doubles rolled end turn


        if (!players.get(player).isInJail()) {
            outputText += "Rolling the Dice! You rolled : " + dice.toString() +
                    "\nYou will move up " + dice.getRollValue() + " spaces on the board!";
            players.get(player).move(dice.getRollValue()); //move the player to right position
        }
        else { outputText += "You are in Jail!\n";}

        if (players.get(player).checkReset()){
            passedGo();
        }
        players.get(player).setPositionName(pieces[players.get(player).getPosition()].toString()); //tell player where they are located
        outputText += pieces[players.get(player).getPosition()].displayInfo();
        if(checkBankruptcy()){
            if (players.get(player) instanceof MonopolyAIPlayer){ended = true;}
            endTurn();
        }
        else if (pieces[players.get(player).getPosition()] instanceof Jail) {
            if ((pieces[players.get(player).getPosition()]).getType().equals("go to jail")){ //kinda smellyy
                players.get(player).setJailStatus(true);
                players.get(player).move(18); //will update once all pieces are on boards
                if (players.get(player) instanceof MonopolyAIPlayer){ended = true;}
                endTurn();
            }
            else{
                //what happens when they're in jail or if they're passing by
                if (!players.get(player).isInJail()){
                    outputText += "\nYou're just visiting jail. This is a free space\n";
                    if (players.get(player) instanceof MonopolyAIPlayer){ended = true;}
                    endTurn();
                }
                else{ //actually in jail
                    //this.playerStatus = Status.JAIL;
                    System.out.println("in jail");
                    players.get(player).incrementTurn();
                    if (players.get(player).getTurns() == 3){
                        buy();
                    }
                    if (players.get(player) instanceof MonopolyAIPlayer){ended = true;}
                    endTurn();
                }
            }

            System.out.print(ended + " rooll\n");


        }
        else if (pieces[players.get(player).getPosition()] instanceof FreeParking) {
            this.playerStatus = Status.UNDECIDED;
            if (!dice.isDouble()) {
                if (players.get(player) instanceof MonopolyAIPlayer){ended = true;}
                endTurn();
            }
        }
        else if (pieces[players.get(player).getPosition()] instanceof Go) {
            this.playerStatus = Status.UNDECIDED;
            if (!dice.isDouble()) {
                if (players.get(player) instanceof MonopolyAIPlayer) {
                    ended = true;
                }
                endTurn();
            }
        }
        else if (pieces[players.get(player).getPosition()] instanceof Tax) {
            players.get(player).doTransaction(((Tax) pieces[players.get(player).getPosition()]).getCost());
            this.playerStatus = Status.UNDECIDED;
            if (!dice.isDouble()) {
                if (players.get(player) instanceof MonopolyAIPlayer){ended = true;}
                endTurn();
            }
        } else if ((pieces[players.get(player).getPosition()] instanceof Property) && !((Property) pieces[players.get(player).getPosition()]).isAvailable()) { // Property is not available
            // Player who owns the property gets rent
            if (((Property) pieces[players.get(player).getPosition()]).getOwner().equals(players.get(player))) { // If the player lands on themselves, do nothing
                outputText+="\nYou do not need to pay rent since you own this property.\n";
            } else {
                outputText+="\nTaking the money from your account\n";
                if(pieces[players.get(player).getPosition()] instanceof Utility){
                    System.out.println("here");
                    players.get(player).doTransaction((((Property) pieces[players.get(player).getPosition()]).getRent()) * dice.getRollValue()); // Deducts the cost from account
                    ((Property) pieces[players.get(player).getPosition()]).getOwner().setMoney((((Property) pieces[players.get(player).getPosition()]).getRent()) * dice.getRollValue());
                }
                players.get(player).doTransaction(((Property) pieces[players.get(player).getPosition()]).getRent()); // Deducts the cost from account
                ((Property) pieces[players.get(player).getPosition()]).getOwner().setMoney(((Property) pieces[players.get(player).getPosition()]).getRent()); // adds the rent cost to the owner's account
            }
            if (!dice.isDouble()) {
                if (players.get(player) instanceof MonopolyAIPlayer){ended = true;}
                endTurn();
            }
        }
    }



    private void passedGo(){
        if(!players.get(player).isInJail()) {
            players.get(player).setMoney(((Go) pieces[0]).getAmount());
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

        if (pieces[players.get(player).getPosition()] instanceof Tax) {
            check = players.get(player).isBankrupt(((Tax) pieces[players.get(player).getPosition()]).getCost());
        } else if (pieces[players.get(player).getPosition()] instanceof Property) {
            if ((!((Property) pieces[players.get(player).getPosition()]).isAvailable()) && (!(((Property) pieces[players.get(player).getPosition()]).getOwner().equals(players.get(player))))) { //property not available
                check = players.get(player).isBankrupt(((Property) pieces[players.get(player).getPosition()]).getRent());
            } else {
                check = (players.get(player).isBankrupt(((Property) pieces[players.get(player).getPosition()]).getPrice()));
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
        if (pieces[players.get(player).getPosition()] instanceof Jail){
            players.get(player).doTransaction(50);
            players.get(player).setJailStatus(false);
            System.out.println("here");
            outputText+="\nSuccessfully took $50 from your account, you are free to leave!\n";
        }
        else if (((Property) pieces[players.get(player).getPosition()]).isAvailable()) {
            players.get(player).doTransaction(((Property) pieces[players.get(player).getPosition()]).getPrice()); // price of property is deducted from player's account
            players.get(player).addProperty(((Property) pieces[players.get(player).getPosition()])); // property is added to player's account
            ((Property) pieces[players.get(player).getPosition()]).purchase(); //set property to unavailable
            ((Property) pieces[players.get(player).getPosition()]).setOwner(players.get(player)); //set owner
            outputText+="Successfully purchased!\n";
        }
        else {
            outputText= "\nUnfortunately the property you are on is not available for purchase.";
        }
    }

    /**
     * Method passes the turn to the next player in the game
     */
    public void endTurn() {
        players.get(player).resetNumDoublesRolled();
        System.out.println(players.get(player).getName() + "before");
        player++; //should be incrementing??????
        if (player > players.size() - 1) {
            player = 0;
        }
        outputText += "\nNow it's "+ (players.get(player).getName()) + "'s turn!";
        if(players.get(player).isInJail()){
            this.playerStatus=Status.JAIL;
        }
        else {
            System.out.println(players.get(player).getName() + "in else");
            this.playerStatus = Status.UNDECIDED;
            System.out.println((players.get(player) instanceof MonopolyAIPlayer)+ players.get(player).getName());
            if (players.get(player) instanceof MonopolyAIPlayer) {
                moveAi();
            }

        }
    }

    private void moveAi(){
        System.out.println(players.get(player).getName() +" in AI");
        outputText = "AI Player is playing\n";
        this.play(ROLL);
        System.out.println(players.get(player).getName() +" HERRREE");
        //for some reason it won't come here????
        if (!ended) { //endTurn didn't get called in roll()
            if (pieces[players.get(player).getPosition()] instanceof Property && players.get(player) instanceof MonopolyAIPlayer) {
                this.play(BUY);
                //buy();
                System.out.println(players.get(player).getName() + " in AI3");
                outputText = "AI Players have completed their turns." +
                        "\nCheck the side panels to see where they landed and if they bought\nnew property.\n"
                            +"\nNow it's " +players.get(player).getName() + "'s turn!";
            }
            if (dice.isDouble() && players.get(player) instanceof MonopolyAIPlayer) {
                this.play(PASS);
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
            if (pieces[players.get(player).getPosition()] instanceof Property || pieces[players.get(player).getPosition()] instanceof Jail) {
                buy();
            }
            if (!dice.isDouble()) {
                System.out.println("wrong" + players.get(player).getName());
                endTurn();
            }
        } else if (command.equals(ROLL)) {
            roll();
        } else if (command.equals(PASS)) {
            outputText= players.get(player).getName() + " has passed their turn! Passing to next player.\n";
            endTurn();
        }
        else if (command.equals(QUIT)) {
            this.playerStatus = Status.QUITTING;
        }
        notifyViews();
    }
}