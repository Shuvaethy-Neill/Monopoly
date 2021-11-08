import java.util.EventObject;

public class MonopolyEvent extends EventObject {

    private Dice dice;
    private String instruction;


    public MonopolyEvent(MonopolyModel source, Dice dice) {
        super(source);
        this.dice = dice;
    }

    public Dice getDice() {
        return dice;
    }
}
