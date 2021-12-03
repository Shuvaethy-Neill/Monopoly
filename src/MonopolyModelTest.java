import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.util.Locale;

import static org.junit.Assert.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MonopolyModelTest {
    MonopolyModel mm; //model attribute
    private BoardSpace[] pieces;

    //NOTE: FOR TESTING USE 2 PLAYERS

    /**
     * Tests if the specified number of players are added
     */
    @Test
    public void getPlayers() {
        mm = new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(mm);
        MonopolyController mc = new MonopolyController(mm,monopolyFrame);
        assertEquals(2,mm.getPlayers().size()); //choose 2 players
    }

    /**
     * Tests if the expected output text is displayed to the user
     */
    @Test
    public void getOutputText() {
        mm = new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(mm);
        MonopolyController mc = new MonopolyController(mm,monopolyFrame);
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
        mm = new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(mm);
        MonopolyController mc = new MonopolyController(mm,monopolyFrame);
        assertEquals(MonopolyModel.Status.UNDECIDED,mm.getPlayerStatus());
        mm.play("Roll Dice");
        assertEquals(MonopolyModel.Status.PLAYING,mm.getPlayerStatus());
        mm.play("Quit");
        assertEquals(MonopolyModel.Status.QUITTING,mm.getPlayerStatus());
    }

    /**
     *
     */
    @Test
    public void start() {
        mm =  new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(mm);
        MonopolyController mc = new MonopolyController(mm,monopolyFrame);
        String s = mm.start();
        assertEquals(mm.getPlayers().get(mm.getPlayer()).getName(),s);
    }

    /**
     * Tests when the player selected the Buy command, a property is purchased
     */
    @Test
    public void buyProperty() {
        mm =  new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(mm);
        MonopolyController mc = new MonopolyController(mm,monopolyFrame);
        mm.play("Roll Dice");
        String prev = mm.getPlayers().get(mm.getPlayer()).getName().toUpperCase(Locale.ROOT);
        mm.play("Buy");
        assertEquals( prev + "'S TURN:\nSuccessfully purchased!\n\nNow it's " + mm.getPlayers().get(mm.getPlayer()).getName() +"'s turn!", mm.getOutputText());
    }

    // Not reaching this test again for some reason
    /**
     * Tests if a player is bankrupt
     */
    @Test
    public void zBankruptcy() {
        mm =  new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(mm);
        MonopolyController mc = new MonopolyController(mm,monopolyFrame);
        Player p1 = mm.getPlayers().get(mm.getPlayer());
        mm.play("Roll Dice");
        p1.doTransaction(1500); //force player to go bankrupt
        assertTrue(mm.checkBankruptcy());
    }

    /**
     * Tests whether a player receives $200 after fully rotating throughout the board passing the GO board square
     */
    @Test
    public void passedGo() {
        mm =  new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(mm);
        MonopolyController mc = new MonopolyController(mm,monopolyFrame);
        Player p1 = mm.getPlayers().get(mm.getPlayer());
        mm.play("Roll Dice");
        p1.move(40);
        mm.passedGo();
        assertEquals(1700.0, p1.getMoney(), 2);
    }

    /**
     * Method tests whether a player is sent to jail when they land on the Go To Jail board square
     */
    @Test
    public void jail() {
        mm =  new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(mm);
        MonopolyController mc = new MonopolyController(mm,monopolyFrame);
        //mm.play("Roll Dice");
        Player p1 = mm.getPlayers().get(mm.getPlayer());
        p1.move(28);
        mm.handleJail();
        assertTrue(p1.isInJail());
    }

    public void moveAI() {
    }
}