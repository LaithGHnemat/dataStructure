package service;

import model.DodgeballNode;
import validaitions.NamesValidation;

import java.io.PrintStream;

public class DodgeballManagerServiceImpl implements DodgeballManagerService {

    DodgeballNode throwerFirstNode = null;
    DodgeballNode dodgerFirstNode = null;

    public DodgeballManagerServiceImpl(DodgeballNode throwerFirstNode, DodgeballNode dodgerFirstNode) {
        this.throwerFirstNode = throwerFirstNode;
        this.dodgerFirstNode = dodgerFirstNode;
    }

    @Override
    public void printThrowers(PrintStream stream) {
        printDodgeballNode(stream, throwerFirstNode);
    }

    @Override
    public void printDodgers(PrintStream stream) {
        printDodgeballNode(stream, dodgerFirstNode);
    }

    @Override
    public void printWinner(PrintStream stream) {
        printFinalWinner(stream);
    }

    @Override
    public boolean throwersContains(String name) {
        return isContains(name, throwerFirstNode);
    }

    @Override
    public boolean dodgersContains(String name) {
        return isContains(name, dodgerFirstNode);
    }

    @Override
    public void hit(String throwerName, String dodgerName) {
        doHit(throwerName, dodgerName);
    }

    @Override
    public void dodge(String throwerName, String dodgerName) {
        doDodge(throwerName, dodgerName);
    }

    @Override
    public int getMaximumScore() {
        return maxScore();
    }

    private void printDodgeballNode(PrintStream stream, DodgeballNode dodgeballNode) {
        DodgeballNode pointer = dodgeballNode;
        while (pointer != null) {
            String format = "%1$s %2$s,";
            stream.format(format, pointer.name, pointer.score);
            stream.flush();
            pointer = pointer.next;
        }
    }

    private boolean isContains(String name, DodgeballNode dodgeballNode) {
        DodgeballNode pointer = dodgeballNode;
        while (pointer != null) {
            if (pointer.name.equalsIgnoreCase(name))
                return true;
            pointer = pointer.next;
        }
        return false;
    }

    public void printFinalWinner(PrintStream stream) {
        String winnerName = winnerName();
        String format = "The winner is {%1$s} with {%2$s} points,";
        stream.format(format, winnerName, getMaximumScore());
        stream.flush();
    }

    private String winnerName() {
        String s = findWinner(throwerFirstNode);
        String s1 = findWinner(dodgerFirstNode);
        return compareResult(s, s1);
    }

    private String compareResult(String s, String s1) {
        if (s.equalsIgnoreCase("notHere") && !s1.equalsIgnoreCase("notHere")) {
            return s1;
        } else if (!s.equalsIgnoreCase("notHere") && s1.equalsIgnoreCase("notHere")) {
            return s;
        } else {
            throw new IllegalStateException("more then one winner ");
        }
    }

    private String findWinner(DodgeballNode dodgeballNode) {
        DodgeballNode pointer = dodgeballNode;// throwers
        int max = getMaximumScore();// aya =3
        String winnerName = "";// winnerName
        int repate = 0;//
        while (pointer != null) {
            if (pointer.score == max) {
                winnerName = pointer.name;
                repate++;
            }
            pointer = pointer.next;
        }
        return checkWinnerName(winnerName, repate);
    }

    private String checkWinnerName(String winnerName, int repate) {
        if (repate > 1) {
            throw new IllegalStateException("more then one winner ");
        } else if (repate < 1) {
            return "notHere";
        } else return winnerName;
    }

    private void doHit(String throwerName, String dodgerName) {
        NamesValidation.checkNames(throwerName, dodgerName, throwersContains(throwerName), dodgersContains(dodgerName));
        DodgeballNode thrower = getThrower(throwerName, throwerFirstNode);
        DodgeballNode dodger = getDodgeball(dodgerName, dodgerFirstNode);
        replaceThrowerWithDodger(dodger, throwerName);
        replaceDodgerWithThrower(thrower, dodgerName);
    }

    private int getPositionByName(String name, DodgeballNode dodgeballNode) {
        int position = -1;
        DodgeballNode pointer1 = dodgeballNode;
        while (pointer1 != null) {
            position++;
            if (pointer1.name.equalsIgnoreCase(name)) {
                return position;
            }
            pointer1 = pointer1.next;
        }
        return 0;
    }

    public DodgeballNode getThrower(String throwerName, DodgeballNode dodgeballNode) {
        DodgeballNode pointer1 = dodgeballNode;
        while (pointer1 != null) {
            if (pointer1.name.equalsIgnoreCase(throwerName)) {
                DodgeballNode value = new DodgeballNode(pointer1.name, pointer1.next);
                value.score = pointer1.score + 1;
                return value;
            }
            pointer1 = pointer1.next;
        }
        return null;
    }

    public DodgeballNode getDodgeball(String throwerName, DodgeballNode dodgeballNode) {
        DodgeballNode pointer1 = dodgeballNode;
        while (pointer1 != null) {
            if (pointer1.name.equalsIgnoreCase(throwerName)) {
                DodgeballNode value = new DodgeballNode(pointer1.name, pointer1.next);
                value.score = pointer1.score;
                return value;
            }
            pointer1 = pointer1.next;
        }
        return null;
    }

    private void replaceThrowerWithDodger(DodgeballNode newThrower, String oldThrower) {
        int throwerPosition = getPositionByName(oldThrower, throwerFirstNode);
        DodgeballNode pointer = throwerFirstNode;
        int position = 0;
        if (throwerPosition == 0) {
            newThrower.next = pointer.next;
            throwerFirstNode = newThrower;
        } else if (0 == throwerPosition - 1) {
            replaceSecond(newThrower, pointer);
        } else {
            replaceInCinter(newThrower, throwerPosition, pointer, position);
        }
    }

    private void replaceSecond(DodgeballNode newDodger, DodgeballNode pointer) {
        newDodger.next = pointer.next.next;
        pointer.next = newDodger;
    }

    private void replaceInCinter(DodgeballNode newThrower, int throwerPosition, DodgeballNode pointer, int position) {
        while (position != throwerPosition) {
            pointer = pointer.next;
            position++;
            if (position == throwerPosition - 1) {
                newThrower.next = pointer.next.next;
                pointer.next = newThrower;
            }
        }
    }

    private void replaceDodgerWithThrower(DodgeballNode newDodger, String oldDodger) {
        int dodgerPosition = getPositionByName(oldDodger, dodgerFirstNode);
        DodgeballNode pointer = dodgerFirstNode;
        int position = 0;
        if (dodgerPosition == 0) {
            newDodger.next = pointer.next;
            dodgerFirstNode = newDodger;

        } else if (0 == dodgerPosition - 1) {
            replaceSecond(newDodger, pointer);
        } else {
            replaceInCinter(newDodger, dodgerPosition, pointer, position);
        }
    }

    public void doDodge(String throwerName, String dodgerName) {
        NamesValidation.checkNames(throwerName, dodgerName, throwersContains(throwerName), dodgersContains(dodgerName));
        DodgeballNode pointer = dodgerFirstNode;
        while (pointer != null) {
            if (pointer.name.equalsIgnoreCase(dodgerName)) {
                pointer.score = pointer.score + 1;
            }
            pointer = pointer.next;
        }
    }

    private int max(DodgeballNode dodgeballNode) {
        DodgeballNode pointer = dodgeballNode;
        int max = -1;
        while (pointer != null) {
            if (pointer.score > max) {
                max = pointer.score;
            }
            pointer = pointer.next;
        }
        return max;
    }

    private int maxScore() {
        int maxDodger = max(dodgerFirstNode);
        int maxThrower = max(throwerFirstNode);
        if (maxDodger > maxThrower)
            return maxDodger;
        else return maxThrower;
    }
}
