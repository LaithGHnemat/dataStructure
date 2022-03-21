package service;

import java.io.PrintStream;

public interface DodgeballManagerService {

    void printThrowers(PrintStream stream);

    void printDodgers(PrintStream stream);

    void printWinner(PrintStream stream);

    boolean throwersContains(String name);

    boolean dodgersContains(String name);

    void hit(String throwerName, String dodgerName);

    void dodge(String throwerName, String dodgerName);

    int getMaximumScore();

}
