import java.util.Random;

/**
 * The Dice class manages the user's dice roll
 * @author
 *
 * @version 1.0
 *
 * @since 2021-10-19
 *
 */

public class Dice {

    private Random random;
    private static final int NUM_DICE = 2;
    private int[] dice;

    /**
     * Constructor for Dice class
     */
    public Dice(){
        random = new Random();
        dice = new int [NUM_DICE];
    }

    //could be void or return the value
    //could just be two statements as well
    /**
     * This method handles the user's dice roll by generating two
     * random numbers between 1-6 and storing each in the dice array
     */
    public void roll () {
        //dice[0] = 1 + random.nextInt(6);
        //dice[1] = 1 + random.nextInt(6);
        for (int i=0; i<NUM_DICE; i++) {
            dice[i] = 1 + random.nextInt(6);
        }
    }

    /**
     * This method gets the current dice
     * @return int[], the array containing the dice
     */
    public int[] getDice () {
        return dice;
    }

    /**
     * This method checks if the dice roll was a double
     * (if the two dice have the same value)
     * @return boolean, true if it is a double or false otherwise
     */
    public boolean isDouble() {
        return dice[0] == dice[1];
    }

    /**
     * This method gets the total value of the dice roll
     * @return int, the sum of the two rolled dice
     */
    public int getRollValue(){
        return (dice[0] + dice[1]);
    }

    /**
     * This method overrides the toString method in the Object
     * class and provides a more accurate String representation
     * @return String, a statement of the two separate dice values
     */
    public String toString () {
        return "["+ dice[0]+ "]" + " " + "["+ dice[1] +"]";
    }

}