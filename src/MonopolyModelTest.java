import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.util.Locale;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MonopolyModelTest {
    MonopolyModel mm; //model attribute

    //NOTE: FOR TESTING USE 2 PLAYERS

    @org.junit.Before
    public void setUp() throws Exception {
        mm = new MonopolyModel();
        Player h = new Player("harsi");
        Player d = new Player("doro");
        mm.addPlayer(h);
        mm.addPlayer(d);
    }

    /**
     * Tests if the specified number of players are added
     */
    @Test
    public void getPlayers() {
        assertEquals(2,mm.getPlayers().size()); //choose 2 players
    }

    /**
     * Tests if the expected output text is displayed to the user
     */
    @Test
    public void getOutputText() {
        assertEquals("", mm.getOutputText());
        String prev = mm.getPlayers().get(mm.getPlayer()).getName();
        mm.play("Pass");
        assertEquals(prev + " has passed their turn! Passing to next player.\n\nNow it's " +
                mm.getPlayers().get(mm.getPlayer()).getName() +  "'s turn!", mm.getOutputText());
    }

    /**
     * Tests if the player's status changes when different button commands are selected
     */
    @Test
    public void getPlayerStatus() {
        assertEquals(MonopolyModel.Status.UNDECIDED,mm.getPlayerStatus());
        //can't test roll because we can't figure out where they land
        mm.play("Quit");
        assertEquals(MonopolyModel.Status.QUITTING,mm.getPlayerStatus());
    }

    /**
     * Method to test if start works
     */
    @Test
    public void start() {
        String s = mm.start();
        assertEquals(mm.getPlayers().get(mm.getPlayer()).getName(),s);
    }

    /**
     * Tests when the player selected the Buy command, a property is purchased
     */
    @Test
    public void buyProperty() {
        mm.play("Roll Dice");
        String prev = mm.getPlayers().get(mm.getPlayer()).getName().toUpperCase(Locale.ROOT);
        mm.play("Buy");
        assertEquals( prev + "'S TURN:\nSuccessfully purchased!\n\nNow it's " + mm.getPlayers().get(mm.getPlayer()).getName() +"'s turn!", mm.getOutputText());
    }

    /**
     * Tests if a player is bankrupt
     */
    @Test
    public void zBankruptcy() {
        Player p1 = mm.getPlayers().get(mm.getPlayer());
        assertTrue(p1.getBankrupt(2000));
    }

    /**
     * Tests whether a player receives $200 after fully rotating throughout the board passing the GO board square
     */
    @Test
    public void passedGo() {
        Player p1 = mm.getPlayers().get(mm.getPlayer());
        p1.move(45, 36);
        mm.passedGo();
        assertEquals(1700.0, p1.getMoney(), 2);
    }

    /**
     * Method tests whether a player is sent to jail when they land on the Go To Jail board square
     */
    @Test
    public void jail() {
        Player p1 = mm.getPlayers().get(mm.getPlayer());
        p1.move(28, 36);
        mm.handleJail();
        assertTrue(p1.isInJail());
    }

    @Test
    public void moveAI() {
        MonopolyAIPlayer AI1 = new MonopolyAIPlayer("AI1");
        mm.addPlayer(AI1);
        AI1.setRolled(true);
        assertEquals("Buy", AI1.getRollDecision());
        AI1.setRolled(false);
        assertEquals("Roll Dice", AI1.getRollDecision());
    }

    @Test
    public void saveSerialize(){
        mm.saveSerialize("testFile.txt");
        assertTrue(new File("testFile.txt").exists());
        MonopolyModel mm2 = new MonopolyModel();
        mm2.importSerialize("testFile.txt");
        assertEquals(mm.getPlayers().size(),mm2.getPlayers().size());
    }
}