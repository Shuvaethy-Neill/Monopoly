/**
 * Jail Class that extends the BoardSpace Class
 */
public class Jail extends BoardSpace{
    private String type;
    public Jail(String name, String path, String type,int position) {
        super(name, type, path, position);
        this.type = type;
    }

    /**
     *
     * @return
     */
    public String getType(){
        return this.type;
    }

    /**
     *
     * @return
     */
    @Override
    public String displayInfo() {
        if (type.equals("go to jail")) {
            return "\nYou are being sent to Jail!";
        }
        return "";
    }

}
