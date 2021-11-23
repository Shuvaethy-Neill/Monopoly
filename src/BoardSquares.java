import java.io.IOException;

/**
 * Represents each board square on the Monopoly board
 *
 * @author Evan Smedley, Harsismran Kanwar, Dorothy Tran, Shuvaethy Neill
 * @version 3.0
 * @since 2021-11-21
 */
//testing

public enum BoardSquares {
    GO("GO", "images/Go.jpg", 0),
    MEDITERRANEAN_AVENUE("MEDITERRANEAN AVENUE",60, "colouredProperty","brown", "images/mediterranean-avenue.jpg", 1, 50,2),
    COMMUNITY_CHEST("COMMUNITY CHEST", "images/community-chest.jpg", 2),
    BALTIC_AVENUE("BALTIC AVENUE",60,"colouredProperty", "brown", "images/baltic.jpg", 3, 50,2),
    INCOME_TAX("INCOME TAX", 200, "images/income-tax.jpg", 4),
    READING_RAILROAD("READING RAILROAD", 200, "railroad", "black", "images/reading-railroad.jpg", 5),
    ORIENTAL_AVENUE("ORIENTAL AVENUE",100,"colouredProperty","light blue", "images/oriental-avenue.jpg", 6, 50,3),
    CHANCE("CHANCE", "images/chance.jpg", 7),
    VERMONT_AVENUE("VERMONT AVENUE",100,"colouredProperty","light blue", "images/vermont-avenue.jpg", 8, 50,3),
    CONNECTICUT_AVENUE("CONNECTICUT AVENUE",120,"colouredProperty","light blue", "images/connecticut.jpg", 9, 50,3),
    JAIL("JAIL","images/jail-just-visiting.jpg","jail",10),
    ST_CHARLES_PLACE("ST. CHARLES PLACE",140,"colouredProperty","magenta", "images/st-charles-place.jpg",11, 100,3),
    ELECTRIC_COMPANY("ELECTRIC COMPANY", 150, "utility", "black", "images/electric-company.jpg", 12),
    STATES_AVENUE("STATES AVENUE",140,"colouredProperty","magenta", "images/states-avenue.jpg", 13, 100,3),
    VIRGINIA_AVENUE("VIRGINIA AVENUE",160,"colouredProperty","magenta", "images/virginia-avenue.jpg", 14, 100,3),
    PENNSYLVANIA_RAILROAD("PENNSYLVANIA RAILROAD", 200, "railroad", "black","images/pennsylvania-railroad.jpg", 15),
    ST_JAMES_PLACE("ST. JAMES PLACE",180,"colouredProperty","orange", "images/st-james-place.jpg", 16, 100,3),
    TENNESSEE_AVENUE("TENNESSEE AVENUE",180,"colouredProperty","orange", "images/tennessee-avenue.jpg", 17, 100,3),
    NEW_YORK_AVENUE("NEW YORK AVENUE",200,"colouredProperty","orange", "images/new-york-avenue.jpg", 18, 100,3),
    FREE_PARKING("FREE PARKING", "images/free-parking.jpg", 19),
    KENTUCKY_AVENUE("KENTUCKY AVENUE",220,"colouredProperty","red", "images/kentucky-avenue.jpg", 20, 150,3),
    INDIANA_AVENUE("INDIANA AVENUE",220,"colouredProperty","red", "images/indiana-avenue.jpg", 21, 150,3),
    ILLINOIS_AVENUE("ILLINOIS AVENUE",240,"colouredProperty","red", "images/illinois-avenue.jpg", 22, 150,3),
    BO_RAILROAD("B & O RAILROAD", 200, "railroad", "black","images/bo.jpg", 23),
    ATLANTIC_AVENUE("ATLANTIC AVENUE",260,"colouredProperty","yellow", "images/atlantic-avenue.jpg", 24, 150,3),
    VENTNOR_AVENUE("VENTNOR AVENUE",260,"colouredProperty","yellow", "images/ventnor-avenue.jpg", 25, 150,3),
    WATER_WORKS("WATER WORKS", 150, "utility", "black", "images/water-works.jpg", 26),
    MARVIN_GARDENS("MARVIN GARDENS",280,"colouredProperty","yellow", "images/marvin-gardens.jpg", 27, 150,3),
    GO_JAIL("GO TO JAIL","images/go-to-jail.jpg","go to jail",28),
    PACIFIC_AVENUE("PACIFIC AVENUE",300,"colouredProperty","green", "images/pacific-avenue.jpg", 29, 200,3),
    NORTH_CAROLINA_AVENUE("NORTH CAROLINA AVENUE",300,"colouredProperty","green", "images/north-carolina-avenue.jpg", 30, 200,3),
    PENNSYLVANIA_AVENUE("PENNSYLVANIA AVENUE",320,"colouredProperty","green", "images/pennsylvania-avenue.jpg", 31, 200,3),
    SHORT_LINE("SHORT LINE", 200, "railroad", "black","images/short-line-railroad.jpg", 32),
    PARK_PLACE("PARK PLACE",350,"colouredProperty","blue", "images/park-place.jpg", 33, 200,2),
    LUXURY_TAX("LUXURY TAX", 100,"images/luxury-tax.jpg", 34),
    BOARDWALK("BOARDWALK",400,"colouredProperty","blue", "images/boardwalk.jpg", 35, 200,2);

    public BoardSpace boardSpace;

    BoardSquares(String name, int price, String type, String color, String path, int position, int houseHotelPrice, int setSize) {
        this.boardSpace = new ColouredProperty(name, price, type, color, path, position, houseHotelPrice, setSize);
    }

    BoardSquares(String name, int price, String type, String color, String path, int position) {
        if(type.equals(RAILROAD)){
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

    private static final String COLOUR_PROPERTY = "colouredProperty";
    private static final String RAILROAD = "railroad";
}