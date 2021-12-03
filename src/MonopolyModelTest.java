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
    @Test
    public void getPlayers() {
        mm = new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(mm);
        MonopolyController mc = new MonopolyController(mm,monopolyFrame);
        assertEquals(2,mm.getPlayers().size()); //choose 2 players
    }

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

    @Test
    public void start() {
        mm =  new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(mm);
        MonopolyController mc = new MonopolyController(mm,monopolyFrame);
        String s = mm.start();
        assertEquals(mm.getPlayers().get(mm.getPlayer()).getName(),s);
    }

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

    @Test
    public void passedGo() {
        mm =  new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(mm);
        MonopolyController mc = new MonopolyController(mm,monopolyFrame);
        Player p1 = mm.getPlayers().get(mm.getPlayer());
        mm.play("Roll Dice");
        /*if(pieces[p1.getPosition()] instanceof Property || pieces[p1.getPosition()] instanceof Railroad || pieces[p1.getPosition()] instanceof Utility) {
            p1.move(25);
        }*/
        // tryna get the player to auto move fully rotating 25 squares on the board wherever they rolled

        assertEquals(1700.0, p1.getMoney(), 2);
    }

    @Test
    public void jail() {
        mm =  new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(mm);
        MonopolyController mc = new MonopolyController(mm,monopolyFrame);
        mm.play("Roll Dice");
        Player p1 = mm.getPlayers().get(mm.getPlayer());
        // tryna somehow get them to move to the jail boardsquare but for ours they move up spaces to start the game

        // assertTrue(p1.isInJail());
    }

    public void moveAI() {
    }
}