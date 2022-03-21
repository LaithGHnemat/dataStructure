package validaitions;

public class NamesValidation {
    public static void checkNames(String throwerName, String dodgerName, boolean throwersContains, boolean dodgersContains) {
        if (throwerName.equals("") || dodgerName.equals("")) {
            throw new IllegalArgumentException("Empty name ");
        }
        if (throwerName == null || dodgerName == null) {
            throw new IllegalArgumentException("null name  ");
        }
        if (!throwersContains) {
            throw new IllegalArgumentException("The player " + throwerName + " is not in the thrower list");
        }
        if (!dodgersContains) {
            throw new IllegalArgumentException("The player  " + dodgerName + " is not in the dodger list ");
        }
    }
}
