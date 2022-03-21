package controller;

import model.DodgeballNode;
import service.DodgeballManagerServiceImpl;
import service.DodgeballManagerService;
import validaitions.ListValidation;

import java.io.PrintStream;
import java.util.List;

public class DodgeballManager {

    DodgeballNode throwerFirstNode = null;
    DodgeballNode dodgerFirstNode = null;

    DodgeballManagerService managerService;


    public DodgeballManager(List<String> initialThrowers, List<String> initialDodgers) {
        dodgeballInitalizer(initialThrowers, initialDodgers);
        managerService = new DodgeballManagerServiceImpl(throwerFirstNode, dodgerFirstNode);
    }

    public void printThrowers(PrintStream stream) {
        managerService.printThrowers(stream);
    }

    public void printDodgers(PrintStream stream) {
        managerService.printDodgers(stream);
    }

    public void printWinner(PrintStream stream) {
        managerService.printWinner(stream);
    }

    public boolean throwersContains(String name) {
        return managerService.throwersContains(name);
    }

    public boolean dodgersContains(String name) {
        return managerService.dodgersContains(name);
    }

    public void hit(String throwerName, String dodgerName) {
        managerService.hit(throwerName, dodgerName);
    }

    public void dodge(String throwerName, String dodgerName) {
        managerService.dodge(throwerName, dodgerName);
    }

    public int getMaximumScore() {
        return managerService.getMaximumScore();
    }

    private void initializeThrowers(List<String> initialThrowers) {
        for (int i = 0; i < initialThrowers.size(); i++) {
            if (throwerFirstNode == null) {
                insertFirstThrower(initialThrowers.get(i));
            } else {
                insertLastThrower(initialThrowers.get(i));
            }
        }
    }

    private void dodgeballInitalizer(List<String> initialThrowers, List<String> initialDodgers) {
        ListValidation.checkStringList(initialThrowers, initialDodgers);
        initializeThrowers(initialThrowers);
        initializeDodgers(initialDodgers);
    }

    private void insertFirstThrower(String data) {
        DodgeballNode newNode = new DodgeballNode(data);
        newNode.next = throwerFirstNode;
        throwerFirstNode = newNode;
    }

    private void insertLastThrower(String data) {
        DodgeballNode current = throwerFirstNode;
        while (current.next != null) {
            current = current.next;
        }
        DodgeballNode newNode = new DodgeballNode(data);
        current.next = newNode;
    }

    private void initializeDodgers(List<String> initialDodgers) {
        for (int i = 0; i < initialDodgers.size(); i++) {
            if (dodgerFirstNode == null) {
                insertFirstDodgers(initialDodgers.get(i));
            } else {
                insertLastDodgers(initialDodgers.get(i));
            }
        }
    }
    private void insertFirstDodgers(String data) {
        DodgeballNode newNode = new DodgeballNode(data);
        newNode.next = dodgerFirstNode;
        dodgerFirstNode = newNode;
    }

    private void insertLastDodgers(String data) {
        DodgeballNode current = dodgerFirstNode;
        while (current.next != null) {
            current = current.next;
        }
        DodgeballNode newNode = new DodgeballNode(data);
        current.next = newNode;
    }
}
