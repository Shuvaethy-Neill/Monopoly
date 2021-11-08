import java.util.EventObject;

public class MonopolyEvent extends EventObject {

    private Dice dice;
    private String instruction;
    MonopolyModel.Status status;


    public MonopolyEvent(MonopolyModel source) {
        super(source);
        this.dice = source.getDice();
        this.instruction = source.getOutputText();
    }

    public String getInstruction() {
        return instruction;
    }

    public Dice getDice() {
        return dice;
    }

}
