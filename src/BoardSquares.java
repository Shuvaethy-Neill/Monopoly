public enum BoardSquares {
    GO("GO"), //exception for starting purposes
    MEDITERRANEAN_AVENUE("MEDITERRANEAN AVENUE",60,"brown"),
    BALTIC_AVENUE("BALTIC AVENUE",60,"brown"),
    INCOME_TAX("INCOME TAX", 200),
    ORIENTAL_AVENUE("ORIENTAL AVENUE",100,"light blue"),
    VERMONT_AVENUE("VERMONT AVENUE",100,"light blue"),
    CONNECTICUT_AVENUE("CONNECTICUT AVENUE",120,"light blue"),
    ST_CHARLES_PLACE("ST. CHARLES PLACE",140,"magenta"),
    STATES_AVENUE("STATES AVENUE",140,"magenta"),
    VIRGINIA_AVENUE("VIRGINIA AVENUE",160,"magenta"),
    ST_JAMES_PLACE("ST. JAMES PLACE",180,"orange"),
    TENNESSEE_AVENUE("TENNESSEE AVENUE",180,"orange"),
    NEW_YORK_AVENUE("NEW YORK AVENUE",200,"orange"),
    FREE_PARKING("FREE PARKING"),
    KENTUCKY_AVENUE("KENTUCKY AVENUE",220,"red"),
    INDIANA_AVENUE("INDIANA AVENUE",220,"red"),
    ILLINOIS_AVENUE("ILLINOIS AVENUE",240,"red"),
    ATLANTIC_AVENUE("ATLANTIC AVENUE",260,"yellow"),
    VENTNOR_AVENUE("VENTNOR AVENUE",260,"yellow"),
    MARVIN_GARDENS("MARVIN GARDENS",280,"yellow"),
    PACIFIC_AVENUE("PACIFIC AVENUE",300,"green"),
    NORTH_CAROLINA_AVENUE("NORTH CAROLINA AVENUE",300,"green"),
    PENNSYLVANIA_AVENUE("PENNSYLVANIA AVENUE",320,"green"),
    PARK_PLACE("PARK PLACE",350,"blue"),
    LUXURY_TAX("LUXURY TAX", 100),
    BOARDWALK("BOARDWALK",400,"blue");

    public BoardSpace boardSpace;

    BoardSquares(String name, int price , String color) {
        this.boardSpace = new Property(name, price, color);
    }
    BoardSquares(String name, int cost) {
        this.boardSpace = new Tax(name, cost);
    }
    BoardSquares(String name) {
        this.boardSpace = new FreeParking(name);
    }
}
