/**
 * Represents each board square on the Monopoly board
 */
public enum BoardSquares {
    GO("GO", "images/Go.jpg"), //exception for starting purposes
    MEDITERRANEAN_AVENUE("MEDITERRANEAN AVENUE",60,"brown", "images/mediterranean-avenue.jpg"),
    BALTIC_AVENUE("BALTIC AVENUE",60,"brown", "images/baltic.jpg"),
    INCOME_TAX("INCOME TAX", 200, "images/income-tax.jpg"),
    ORIENTAL_AVENUE("ORIENTAL AVENUE",100,"light blue", "images/oriental-avenue.jpg"),
    VERMONT_AVENUE("VERMONT AVENUE",100,"light blue", "images/vermont-avenue.jpg"),
    CONNECTICUT_AVENUE("CONNECTICUT AVENUE",120,"light blue", "images/connecticut.jpg"),
    ST_CHARLES_PLACE("ST. CHARLES PLACE",140,"magenta", "images/st-charles-place.jpg"),
    STATES_AVENUE("STATES AVENUE",140,"magenta", "images/states-avenue.jpg"),
    VIRGINIA_AVENUE("VIRGINIA AVENUE",160,"magenta", "images/virginia-avenue.jpg"),
    ST_JAMES_PLACE("ST. JAMES PLACE",180,"orange", "images/st-james-place.jpg"),
    TENNESSEE_AVENUE("TENNESSEE AVENUE",180,"orange", "images/tennessee-avenue.jpg"),
    NEW_YORK_AVENUE("NEW YORK AVENUE",200,"orange", "images/new-york-avenue.jpg"),
    FREE_PARKING("FREE PARKING", "images/free-parking.jpg"),
    KENTUCKY_AVENUE("KENTUCKY AVENUE",220,"red", "images/kentucky-avenue.jpg"),
    INDIANA_AVENUE("INDIANA AVENUE",220,"red", "images/indiana-avenue.jpg"),
    ILLINOIS_AVENUE("ILLINOIS AVENUE",240,"red", "images/illinois-avenue.jpg"),
    ATLANTIC_AVENUE("ATLANTIC AVENUE",260,"yellow", "images/atlantic-avenue.jpg"),
    VENTNOR_AVENUE("VENTNOR AVENUE",260,"yellow", "images/ventnor-avenue.jpg"),
    MARVIN_GARDENS("MARVIN GARDENS",280,"yellow", "images/marvin-gardens.jpg"),
    PACIFIC_AVENUE("PACIFIC AVENUE",300,"green", "images/pacific-avenue.jpg"),
    NORTH_CAROLINA_AVENUE("NORTH CAROLINA AVENUE",300,"green", "images/north-carolina-avenue.jpg"),
    PENNSYLVANIA_AVENUE("PENNSYLVANIA AVENUE",320,"green", "images/pennsylvania-avenue.jpg"),
    PARK_PLACE("PARK PLACE",350,"blue", "images/park-place.jpg"),
    LUXURY_TAX("LUXURY TAX", 100,"images/luxury-tax.jpg"),
    BOARDWALK("BOARDWALK",400,"blue", "images/boardwalk.jpg");

    public BoardSpace boardSpace;

    BoardSquares(String name, int price , String color, String path) {
        this.boardSpace = new Property(name, price, color, path);
    }
    BoardSquares(String name, int cost, String path) {
        this.boardSpace = new Tax(name, cost, path);
    }
    BoardSquares(String name, String path) {
        this.boardSpace = new FreeParking(name, path);
    }
}
