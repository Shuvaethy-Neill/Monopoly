import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Locale;

import static org.junit.Assert.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MonopolyModelTest {
    MonopolyModel mm; //model attribute

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
        mm.play("Pass");
        assertEquals("Your turn is now over! Passing to next player.\nNow it's " + mm.getPlayers().get(mm.getPlayer()).getName() +  " 's turn!", mm.getOutputText());
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
        mm.play("Buy Property");
        assertEquals( prev + "'S TURN:\nSuccessfully purchased!\nNow it's " + mm.getPlayers().get(mm.getPlayer()).getName() +" 's turn!", mm.getOutputText());

    }

    @Test
    public void zBankruptcy() {
        mm =  new MonopolyModel();
        MonopolyFrame monopolyFrame = new MonopolyFrame(mm);
        MonopolyController mc = new MonopolyController(mm,monopolyFrame);
        Player p1 = mm.getPlayers().get(mm.getPlayer());
        mm.play("Roll Dice");
        p1.doTransaction(1500); //force player to go bankrupt
        assertEquals(true, mm.checkBankruptcy());

    }

}