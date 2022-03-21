package controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DodgeballManagerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    DodgeballManager dodgeballManager;

    @Test
    public void whenGivenArraysWithRepeatedNameInTheSameArrayThenIllegalArgumentException() {
        List<String> l=new ArrayList<>();
        l.add("leonardo");l.add("Edward");l.add("Mark");l.add("Mark");
        // two players with same name "Mark" then IllegalArgumentException
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina"); DodgerPlayers.add("elisha");
        assertThrows(IllegalArgumentException.class,
                () ->  dodgeballManager=new DodgeballManager(l,DodgerPlayers));
    }
    @Test
    public void whenGivenArraysWithRepeatedNameInDifferentThenIllegalArgumentException() {
        List<String> l=new ArrayList<>(); l.add("leonardo");l.add("Edward");l.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
         DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");l.add("Edward");
        // we have the same player (Edward)in both teams so IllegalArgumentException
        assertThrows(IllegalArgumentException.class,
                () ->  dodgeballManager=new DodgeballManager(l,DodgerPlayers));
    }
    @Test
    public void whenGivenNullArraysThenIllegalArgumentException() {
        List<String> l=null;
        List<String> DodgerPlayers=null;
        assertThrows(IllegalArgumentException.class,
                () ->  dodgeballManager=new DodgeballManager(l,DodgerPlayers));
    }

    @Test
    public void whenGivenValidArraysThenNoException() {
        List<String> l=new ArrayList<>(); l.add("leonardo");l.add("Edward");l.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina"); DodgerPlayers.add("elisha");
        assertDoesNotThrow(() ->  dodgeballManager=new DodgeballManager(l,DodgerPlayers));
    }

    @Test
    public void whenGivenValidArraysThenStoreTheDataInTheSameOrder() {
        List<String> l=new ArrayList<>(); l.add("leonardo");l.add("Edward");l.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina"); DodgerPlayers.add("elisha");
        PrintStream stream = new PrintStream(System.out);
        dodgeballManager=new DodgeballManager(l,DodgerPlayers);
        dodgeballManager.printThrowers(stream);
        dodgeballManager.printDodgers(stream);
        assertEquals("leonardo 0,Edward 0,Mark 0,scarlett 0,georgina 0,elisha 0,", outContent.toString());
    }
    @Test
    public void whenGivenExistingNameInThrowersListThenThrowersContainsMethodReturnTrue(){
        List<String> l=new ArrayList<>(); l.add("leonardo");l.add("Edward");l.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("mare"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");
        dodgeballManager=new DodgeballManager(l,DodgerPlayers);
        boolean edward = dodgeballManager.throwersContains("Edward");
        assertEquals(true,edward);
    }

    @Test
    public void whenGivenNameDoesNotExistInThrowersListThenThrowersContainsMethodReturnFalse(){
        List<String> l=new ArrayList<>(); l.add("leonardo");l.add("Edward");l.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("mare"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");
        dodgeballManager=new DodgeballManager(l,DodgerPlayers);
        boolean edward = dodgeballManager.throwersContains("marcelo");
        assertEquals(false,edward);
    }

    @Test
    public void whenGivenExistingNameInDodgersListThenDodgersContainsMethodReturnTrue(){
        List<String> l=new ArrayList<>(); l.add("leonardo");l.add("Edward");l.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("Mare"); DodgerPlayers.add("Scarlett"); DodgerPlayers.add("Georgina");
        dodgeballManager=new DodgeballManager(l,DodgerPlayers);
        boolean georgina = dodgeballManager.dodgersContains("Georgina");
        assertEquals(true,georgina);
    }

    @Test
    public void whenGivenNameDoesNotExistInDodgersListThenDodgersContainsMethodReturnFalse(){
        List<String> l=new ArrayList<>(); l.add("leonardo");l.add("Edward");l.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("mare"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");
        dodgeballManager=new DodgeballManager(l,DodgerPlayers);
        //   marcelo isn't Existing in Dodgers List
        boolean marcelo = dodgeballManager.dodgersContains("marcelo");
        assertEquals(false,marcelo);
    }

    @Test
    public void whenDoesNotExistingPlayerTryToHitExistingPlayerThenHitMethodThrowsIllegalArgumentException(){
        List<String> l=new ArrayList<>(); l.add("leonardo");l.add("Edward");l.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("mare"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");
        dodgeballManager=new DodgeballManager(l,DodgerPlayers);
        assertThrows(IllegalArgumentException.class,
             //   marcelo isn't Existing in both lists
                () ->  dodgeballManager.hit("marcelo","georgina"));

    }
    @Test
    public void whenExistingPlayerTryToHitNotExistingPlayerThenHitMethodThrowsIllegalArgumentException(){
        List<String> l=new ArrayList<>(); l.add("leonardo");l.add("Edward");l.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("mare"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");
        dodgeballManager=new DodgeballManager(l,DodgerPlayers);
        //  leonardo is in throwers list, but marcelo isn't Existing in Dodgers List
        assertThrows(IllegalArgumentException.class,
                () ->  dodgeballManager.hit("leonardo","marcelo"));
    }

    @Test
    public void whenDodgerPlayerTryToHitOtherPlayerThenHitMethodThrowsIllegalArgumentException(){
        List<String> l=new ArrayList<>(); l.add("leonardo");l.add("Edward");l.add("Mark");
        List<String> l2=new ArrayList<>(); l2.add("mare"); l2.add("scarlett"); l2.add("georgina");
        dodgeballManager=new DodgeballManager(l,l2);
        //scarlett is a thrower
        assertThrows(IllegalArgumentException.class,
                () ->  dodgeballManager.hit("scarlett","georgina"));

    }
    @Test
    public void whenThrowerPlayerTryToHitOtherThrowerPlayerThenHitMethodThrowsIllegalArgumentException(){
        List<String> l=new ArrayList<>(); l.add("leonardo");l.add("Edward");l.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("mare"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");
        dodgeballManager=new DodgeballManager(l,DodgerPlayers);
        //leonardo and Edward both are throwers
        assertThrows(IllegalArgumentException.class,
                () ->  dodgeballManager.hit("leonardo","Edward"));
    }
    @Test
    public void ThrowerPlayerHitDodgerPlayerThenHitMethodSwitchTheirPlacesAndIncreasesThrowerPointsByOne(){
        List<String> l=new ArrayList<>();
        l.add("leonardo");l.add("Edward");l.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("mare"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");
        PrintStream stream = new PrintStream(System.out);
        dodgeballManager=new DodgeballManager(l,DodgerPlayers);
        // leonardo is a thrower,and scarlett is a Dodger, so leonardo points Increases by one
        // and thy change their places
        dodgeballManager.hit("leonardo","scarlett");
        dodgeballManager.printThrowers(stream);
        dodgeballManager.printDodgers(stream);
        assertEquals("scarlett 0,Edward 0,Mark 0,mare 0,leonardo 1,georgina 0,", outContent.toString());
    }
    @Test
    public void whenDodgerPlayerDodgeNotExistingPlayerThenDodgeMethodThrowsIllegalArgumentException(){
        List<String> ThrowerPlayers=new ArrayList<>();
        ThrowerPlayers.add("leonardo");ThrowerPlayers.add("Edward");ThrowerPlayers.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("mare"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");
        dodgeballManager=new DodgeballManager(ThrowerPlayers,DodgerPlayers);
        //scarlett and georgina both are Dodgers.
        assertThrows(IllegalArgumentException.class,
                () ->  dodgeballManager.dodge("scarlett","georgina"));

    }

    @Test
    public void whenNotDodgerPlayerTryToDodgeThrowerPlayerThenDodgeMethodThrowsIllegalArgumentException(){
        List<String> ThrowerPlayers=new ArrayList<>();
        ThrowerPlayers.add("leonardo");ThrowerPlayers.add("Edward");ThrowerPlayers.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("mare"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");
        dodgeballManager=new DodgeballManager(ThrowerPlayers,DodgerPlayers);
        //leonardo and Edward both are Throwers.
        assertThrows(IllegalArgumentException.class,
                () ->  dodgeballManager.dodge("leonardo","Edward"));

    }

    @Test
    public void whenDodgerPlayerDodgeThrowerPlayerThenDodgeMethodLetThemStayInTheSameOrderAndDodgerPointsIncreasesByOne(){
        List<String> ThrowerPlayers=new ArrayList<>();
        ThrowerPlayers.add("leonardo");ThrowerPlayers.add("Edward");ThrowerPlayers.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("elisha"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");
        PrintStream stream = new PrintStream(System.out);
        dodgeballManager=new DodgeballManager(ThrowerPlayers,DodgerPlayers);
        dodgeballManager.dodge("Edward","elisha");
        dodgeballManager.dodge("Mark","elisha");
        dodgeballManager.dodge("leonardo","elisha");
        dodgeballManager.dodge("Mark","georgina");
        dodgeballManager.printThrowers(stream);
        dodgeballManager.printDodgers(stream);
        assertEquals("leonardo 0,Edward 0,Mark 0,elisha 3,scarlett 0,georgina 1,", outContent.toString());

    }

    @Test
    public void whenTheMaxScoreWithDodgerPlayerThenGetMaximumScoreMethodShouldGetItTrue(){
        List<String> ThrowerPlayers=new ArrayList<>();
        ThrowerPlayers.add("leonardo");ThrowerPlayers.add("Edward");ThrowerPlayers.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("elisha"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");

        dodgeballManager=new DodgeballManager(ThrowerPlayers,DodgerPlayers);
        dodgeballManager.dodge("Edward","elisha");
        dodgeballManager.dodge("Mark","elisha");
        dodgeballManager.dodge("leonardo","elisha");
        dodgeballManager.dodge("Mark","georgina");
        int maxScore=dodgeballManager.getMaximumScore();
        // elisha points = 3, in Dodgers team
        assertEquals(3,maxScore);
    }

    @Test
    public void whenTheMaxScoreWithThrowerPlayerThenGetMaximumScoreMethodShouldGetItTrue(){
        List<String> ThrowerPlayers=new ArrayList<>();
        ThrowerPlayers.add("leonardo");ThrowerPlayers.add("Edward");ThrowerPlayers.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("elisha"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");
        dodgeballManager=new DodgeballManager(ThrowerPlayers,DodgerPlayers);
        dodgeballManager.hit("Edward","scarlett");
        dodgeballManager.dodge("scarlett","Edward");
        dodgeballManager.dodge("Mark","Edward"); //Edward=3 in Dodgers team
        dodgeballManager.dodge("Mark","elisha");
        dodgeballManager.dodge("leonardo","elisha"); // elisha= 2 in Dodgers team
        dodgeballManager.hit("leonardo","Edward"); //Edward=3 in Throwers team
        int maxScore=dodgeballManager.getMaximumScore();
        assertEquals(3,maxScore);
    }


    @Test
    public void whenTowOrMoreHaveTheMaximumResultThenIllegalStateException(){
        // here let us say that the winner should have 3 points to win! but in this case we have tow with same result
        List<String> ThrowerPlayers=new ArrayList<>();
        PrintStream stream = new PrintStream(System.out);
        ThrowerPlayers.add("leonardo");ThrowerPlayers.add("Edward");ThrowerPlayers.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("elisha"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");
        dodgeballManager=new DodgeballManager(ThrowerPlayers,DodgerPlayers);
        dodgeballManager.hit("Edward","scarlett");//Edward  points =1, in DodgersTeam
        dodgeballManager.dodge("scarlett","Edward");//Edward points =2, in DodgersTeam
        dodgeballManager.dodge("Mark","Edward");// Edward =3, in ThrowersTeam
        dodgeballManager.dodge("Mark","elisha");//Mark points =2, in DodgersTeam
        dodgeballManager.dodge("leonardo","elisha");//elisha  points =2, in DodgersTeam
        dodgeballManager.hit("leonardo","Edward");//leonardo  points =1, in DodgersTeam
        dodgeballManager.dodge("Mark","elisha");//elisha  points =3, in DodgersTeam
        // both Edward and elisha have their points = 3 then exception
        assertThrows(IllegalStateException.class, () ->  dodgeballManager.printWinner(stream));
    }

    @Test
    public void whenJustOnePlayerInThrowersTeamHasTheMaximumResultThenPrintResult(){
        List<String> ThrowerPlayers=new ArrayList<>();
        ThrowerPlayers.add("leonardo");ThrowerPlayers.add("Edward");ThrowerPlayers.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("elisha"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");

        PrintStream stream = new PrintStream(System.out);

        dodgeballManager=new DodgeballManager(ThrowerPlayers,DodgerPlayers);
        dodgeballManager.dodge("Edward","scarlett");//scarlett points =1, in DodgersTeam
        dodgeballManager.dodge("leonardo","scarlett");//scarlett  points =2, in DodgersTeam
        dodgeballManager.dodge("leonardo","scarlett");//scarlett  points =3, in DodgersTeam
        dodgeballManager.hit("Edward","scarlett");//scarlett  points =3, in ThrowersTeam
        dodgeballManager.printWinner(stream);
        assertEquals("The winner is {scarlett} with {3} points,", outContent.toString());

    }

    @Test
    public void whenJustOnePlayerInDodgersTeamHasTheMaximumResultThenPrintResult(){
        List<String> ThrowerPlayers=new ArrayList<>();
        ThrowerPlayers.add("leonardo");ThrowerPlayers.add("Edward");ThrowerPlayers.add("Mark");
        List<String> DodgerPlayers=new ArrayList<>();
        DodgerPlayers.add("elisha"); DodgerPlayers.add("scarlett"); DodgerPlayers.add("georgina");
        PrintStream stream = new PrintStream(System.out);
        dodgeballManager=new DodgeballManager(ThrowerPlayers,DodgerPlayers);
        dodgeballManager.dodge("Mark","georgina");//georgina points =1, in DodgersTeam
        dodgeballManager.dodge("leonardo","georgina");//georgina  points =2,in DodgersTeam
        dodgeballManager.dodge("Edward","georgina");//georgina points =3, in DodgersTeam
        dodgeballManager.printWinner(stream);
        assertEquals("The winner is {georgina} with {3} points,", outContent.toString());
    }


}