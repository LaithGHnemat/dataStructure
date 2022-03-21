import controller.DodgeballManager;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        List<String> throwers=new ArrayList<>(); throwers.add("Laith");throwers.add("Anas");throwers.add("Mohannad");
        List<String> Dodgers=new ArrayList<>(); Dodgers.add("Aya"); Dodgers.add("Dalal"); Dodgers.add("yara");




        DodgeballManager dodgeballManager=new DodgeballManager(throwers,Dodgers);
        PrintStream stream = new PrintStream(System.out);

        dodgeballManager.dodge("laith","Aya");
        dodgeballManager.dodge("Anas","Aya");
        dodgeballManager.dodge("Mohannad","Aya");//aya points = 3

        dodgeballManager.dodge("laith","Dalal");
        dodgeballManager.dodge("Anas","Dalal");
        dodgeballManager.dodge("Mohannad","Dalal"); //aya points = 3



//
        dodgeballManager.printThrowers(stream);

        dodgeballManager.printDodgers(stream);


        System.out.println("..............................");
        int c=dodgeballManager.getMaximumScore();
        System.out.println(c);
//

//        dodgeballManager.printThrowers(stream);
//        System.out.println();
//        dodgeballManager.printDodgers(stream);


        dodgeballManager.printWinner(stream);
        System.out.println();
    }
}
