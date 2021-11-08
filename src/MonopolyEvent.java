import java.util.EventObject;

public class MonopolyEvent extends EventObject {

    private Dice dice;
    private String instruction;
    MonopolyModel.Status status;
    private int numPlayers;


    public MonopolyEvent(MonopolyModel source) {
        super(source);
        this.dice = source.getDice();
        this.instruction = source.getOutputText();
        this.status = source.getPlayerStatus();
        this.numPlayers = source.getPlayers().size();
    }

    public String getInstruction() {
        return instruction;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public Dice getDice() {
        return dice;
    }

}
