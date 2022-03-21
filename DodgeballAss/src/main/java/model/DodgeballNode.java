package model;

public class DodgeballNode {

    public String name;
    public int score;
    public DodgeballNode next;

    public DodgeballNode(String name) {
        this(name, null);
    }

    public DodgeballNode(String name, DodgeballNode next) {
        this.name = name;
        this.score = 0;
        this.next = next;
        constructorCount++;
    }

    private static int constructorCount = 0;

    public static void resetCount() {
        constructorCount = 0;
    }

    public static int getCount() {
        return constructorCount;
    }

}
