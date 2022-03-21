package validaitions;

import java.util.List;

public class ListValidation {

    public final static void checkStringList(List<String> initialThrowers, List<String> initialDodgers) {
        if (initialThrowers == null)
            throw new IllegalArgumentException("initialThrowers has null value");
        if (initialThrowers.size() == 0)
            throw new IllegalArgumentException("initialThrowers array =0");
        if (initialDodgers == null)
            throw new IllegalArgumentException("InitialDodgers has null value");
        if (initialDodgers.size() == 0)
            throw new IllegalArgumentException("InitialDodgers array  =0");
        checkRepeated(initialThrowers, initialDodgers);
        checkRepeatedInTheSameArray(initialThrowers, initialThrowers);
    }

    private static void checkRepeatedInTheSameArray(List<String> firstList, List<String> SecondList) {
        for (int i = 0; i < firstList.size(); i++) {
            String s = firstList.get(i);
            for (int j = 0; j < SecondList.size(); j++) {
                if (i == j) continue;
                else {
                    if (s.equalsIgnoreCase(SecondList.get(j))) {
                        throw new IllegalArgumentException("there are duplicates name, " + s + " is repeated name");
                    }
                }
            }
        }
    }

    private static void checkRepeated(List<String> firstList, List<String> SecondList) {
        for (int i = 0; i < firstList.size(); i++) {
            String s = firstList.get(i);
            for (int j = 0; j < SecondList.size(); j++) {
                if (s.equalsIgnoreCase(SecondList.get(j))) {
                    throw new IllegalArgumentException("there are duplicates name, " + s + " is repeated name .");
                }
            }
        }
    }
}
