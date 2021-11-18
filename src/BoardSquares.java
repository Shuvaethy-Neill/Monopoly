/**
 * Represents each board square on the Monopoly board
 */
public enum BoardSquares {
    GO("GO", "images/Go.jpg", 0),
    MEDITERRANEAN_AVENUE("MEDITERRANEAN AVENUE",60, "colouredProperty","brown", "images/mediterranean-avenue.jpg", 1),
    BALTIC_AVENUE("BALTIC AVENUE",60,"colouredProperty", "brown", "images/baltic.jpg", 2),
    INCOME_TAX("INCOME TAX", 200, "images/income-tax.jpg", 3),
    ORIENTAL_AVENUE("ORIENTAL AVENUE",100,"colouredProperty","light blue", "images/oriental-avenue.jpg", 4),
    VERMONT_AVENUE("VERMONT AVENUE",100,"colouredProperty","light blue", "images/vermont-avenue.jpg", 5),
    CONNECTICUT_AVENUE("CONNECTICUT AVENUE",120,"colouredProperty","light blue", "images/connecticut.jpg", 6),
    JAIL("JAIL","images/jail-just-visiting.jpg","jail",7),
    ST_CHARLES_PLACE("ST. CHARLES PLACE",140,"colouredProperty","magenta", "images/st-charles-place.jpg", 8),
    STATES_AVENUE("STATES AVENUE",140,"colouredProperty","magenta", "images/states-avenue.jpg", 9),
    VIRGINIA_AVENUE("VIRGINIA AVENUE",160,"colouredProperty","magenta", "images/virginia-avenue.jpg", 10),
    ST_JAMES_PLACE("ST. JAMES PLACE",180,"colouredProperty","orange", "images/st-james-place.jpg", 11),
    TENNESSEE_AVENUE("TENNESSEE AVENUE",180,"colouredProperty","orange", "images/tennessee-avenue.jpg", 12),
    NEW_YORK_AVENUE("NEW YORK AVENUE",200,"colouredProperty","orange", "images/new-york-avenue.jpg", 13),
    FREE_PARKING("FREE PARKING", "images/free-parking.jpg", 14),
    KENTUCKY_AVENUE("KENTUCKY AVENUE",220,"colouredProperty","red", "images/kentucky-avenue.jpg", 15),
    INDIANA_AVENUE("INDIANA AVENUE",220,"colouredProperty","red", "images/indiana-avenue.jpg", 16),
    ILLINOIS_AVENUE("ILLINOIS AVENUE",240,"colouredProperty","red", "images/illinois-avenue.jpg", 17),
    ATLANTIC_AVENUE("ATLANTIC AVENUE",260,"colouredProperty","yellow", "images/atlantic-avenue.jpg", 18),
    VENTNOR_AVENUE("VENTNOR AVENUE",260,"colouredProperty","yellow", "images/ventnor-avenue.jpg", 19),
    MARVIN_GARDENS("MARVIN GARDENS",280,"colouredProperty","yellow", "images/marvin-gardens.jpg", 20),
    GO_JAIL("GO TO JAIL","images/go-to-jail.jpg","go to jail",21),
    PACIFIC_AVENUE("PACIFIC AVENUE",300,"colouredProperty","green", "images/pacific-avenue.jpg", 22),
    NORTH_CAROLINA_AVENUE("NORTH CAROLINA AVENUE",300,"colouredProperty","green", "images/north-carolina-avenue.jpg", 23),
    PENNSYLVANIA_AVENUE("PENNSYLVANIA AVENUE",320,"colouredProperty","green", "images/pennsylvania-avenue.jpg", 24),
    PARK_PLACE("PARK PLACE",350,"colouredProperty","blue", "images/park-place.jpg", 25),
    LUXURY_TAX("LUXURY TAX", 100,"images/luxury-tax.jpg", 26),
    BOARDWALK("BOARDWALK",400,"colouredProperty","blue", "images/boardwalk.jpg", 27);

    public BoardSpace boardSpace;

    //temporarily using type to identify which constructor to call (waiting on the rest of the spaces to be added to test if logic works)
    BoardSquares(String name, int price, String type, String color, String path, int position) {
        if(type.equals("colouredProperty")) {
            this.boardSpace = new ColouredProperty(name, price, type, color, path, position);
        }
        else if(type.equals("railroad")){
            this.boardSpace = new Railroad(name, price, type, color, path, position);
        }
        else{
            this.boardSpace = new Utility(name, price, type, color, path, position);
        }
    }
    BoardSquares(String name, int cost, String path, int position) {
        this.boardSpace = new Tax(name, cost, path, position);
    }
    BoardSquares(String name, String path,int position) {
        if (position==0){
            this.boardSpace = new Go(name, path,position);
        }
        else {
            this.boardSpace = new FreeParking(name, path, position);
        }
    }
    BoardSquares(String name, String path, String type, int position){
        this.boardSpace = new Jail(name, path,type,position);

    }
}
