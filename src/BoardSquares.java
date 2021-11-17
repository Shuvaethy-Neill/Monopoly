/**
 * Represents each board square on the Monopoly board
 */
public enum BoardSquares {
    GO("GO", "images/Go.jpg", 0), //exception for starting purposes
    MEDITERRANEAN_AVENUE("MEDITERRANEAN AVENUE",60,"brown", "images/mediterranean-avenue.jpg", 1),
    BALTIC_AVENUE("BALTIC AVENUE",60,"brown", "images/baltic.jpg", 2),
    INCOME_TAX("INCOME TAX", 200, "images/income-tax.jpg", 3),
    ORIENTAL_AVENUE("ORIENTAL AVENUE",100,"light blue", "images/oriental-avenue.jpg", 4),
    VERMONT_AVENUE("VERMONT AVENUE",100,"light blue", "images/vermont-avenue.jpg", 5),
    CONNECTICUT_AVENUE("CONNECTICUT AVENUE",120,"light blue", "images/connecticut.jpg", 6),
    ST_CHARLES_PLACE("ST. CHARLES PLACE",140,"magenta", "images/st-charles-place.jpg", 7),
    STATES_AVENUE("STATES AVENUE",140,"magenta", "images/states-avenue.jpg", 8),
    VIRGINIA_AVENUE("VIRGINIA AVENUE",160,"magenta", "images/virginia-avenue.jpg", 9),
    ST_JAMES_PLACE("ST. JAMES PLACE",180,"orange", "images/st-james-place.jpg", 10),
    TENNESSEE_AVENUE("TENNESSEE AVENUE",180,"orange", "images/tennessee-avenue.jpg", 11),
    NEW_YORK_AVENUE("NEW YORK AVENUE",200,"orange", "images/new-york-avenue.jpg", 12),
    FREE_PARKING("FREE PARKING", "images/free-parking.jpg", 13),
    KENTUCKY_AVENUE("KENTUCKY AVENUE",220,"red", "images/kentucky-avenue.jpg", 14),
    INDIANA_AVENUE("INDIANA AVENUE",220,"red", "images/indiana-avenue.jpg", 15),
    ILLINOIS_AVENUE("ILLINOIS AVENUE",240,"red", "images/illinois-avenue.jpg", 16),
    ATLANTIC_AVENUE("ATLANTIC AVENUE",260,"yellow", "images/atlantic-avenue.jpg", 17),
    VENTNOR_AVENUE("VENTNOR AVENUE",260,"yellow", "images/ventnor-avenue.jpg", 18),
    MARVIN_GARDENS("MARVIN GARDENS",280,"yellow", "images/marvin-gardens.jpg", 19),
    PACIFIC_AVENUE("PACIFIC AVENUE",300,"green", "images/pacific-avenue.jpg", 20),
    NORTH_CAROLINA_AVENUE("NORTH CAROLINA AVENUE",300,"green", "images/north-carolina-avenue.jpg", 21),
    PENNSYLVANIA_AVENUE("PENNSYLVANIA AVENUE",320,"green", "images/pennsylvania-avenue.jpg", 22),
    PARK_PLACE("PARK PLACE",350,"blue", "images/park-place.jpg", 23),
    LUXURY_TAX("LUXURY TAX", 100,"images/luxury-tax.jpg", 24),
    BOARDWALK("BOARDWALK",400,"blue", "images/boardwalk.jpg", 25);

    public BoardSpace boardSpace;

    BoardSquares(String name, int price , String color, String path, int position) {
        this.boardSpace = new Property(name, price, color, path, position);
    }
    BoardSquares(String name, int cost, String path, int position) {
        this.boardSpace = new Tax(name, cost, path, position);
    }
    BoardSquares(String name, String path, int position) {
        this.boardSpace = new FreeParking(name, path, position);
    }
}
