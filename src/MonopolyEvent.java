import java.util.EventObject;

public class MonopolyEvent extends EventObject {

    public MonopolyEvent(MonopolyModel source) {
        super(source);
    }
}
