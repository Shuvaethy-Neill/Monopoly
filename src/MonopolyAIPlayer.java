/**
 * MonopolyAI Class that handles the AI players of the game
 *
 * @author Shuvaethy Neill
 * @verion 1.0
 * @since 2021-12-02
 */

public class MonopolyAIPlayer extends Player {
    private static final String ROLL = "Roll Dice";
    private static final String BUY = "Buy";
    private static final String PASS = "Pass";

    private boolean rolled;
    private boolean bought;

    /**
     * Constructor for MonopolyAIPlayer class
     * @param name, name of player
     */
    public MonopolyAIPlayer(String name) {
        super(name);
        rolled = false;
        bought = false;
    }

    /**
     * Method to set the value of rolled to true or false
     * depending on if AI has rolled already for current turn
     * @param result
     */
    public void setRolled(boolean result){
        rolled = result;
    }

    /**
     * Method to set the value of bought to true or false
     * depending on if AI has already bought property for current turn
     * @param result
     */
    public void setBought(boolean result){
        bought = result;
    }

    /**
     * Method to set decision of what the AI will do during its turn
     * @return
     */
    public String getRollDecision(){
        //always buy first round in jail
        if(this.isInJail()){
            return BUY;
        }
        //if AI hasn't rolled then roll
        else if(!rolled){
            rolled = true;
            return ROLL;
        }
        //if AI rolled but hasn't bought anything then always buy
        else if((rolled) && (!bought) && (this.getNumDoublesRolled() < 2)){
            bought = true;
            return BUY;
        }
        else{
            //any other case AI will pass their turn
            return PASS;
        }
    }
}
