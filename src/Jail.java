public class Jail extends BoardSpace{
    private String type;
    public Jail(String name, String path, String type,int position) {
        super(name, type, path, position);
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
    @Override
    public String displayInfo() {
        if (type.equals("go to jail")) {
            return "\nYou are being sent to Jail!";
        }
        return "";
    }

}
