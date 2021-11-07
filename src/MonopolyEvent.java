import java.util.EventObject;

public class MonopolyEvent extends EventObject {

    private int[] dice;
    private String instruction;


    public MonopolyEvent(MonopolyModel source, int[] dice) {
        super(source);
        this.dice = dice;
    }

    public int[] getDice() {
        return dice;
    }
}
