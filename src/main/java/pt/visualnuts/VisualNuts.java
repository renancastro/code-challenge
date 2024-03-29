package pt.visualnuts;

public class VisualNuts {

    public static void main(String[] args) {
        int inputNumber = 1000;
        for (int i = 1; i <= inputNumber; i++) {
            System.out.println(execute(i));
        }
    }

    /**
     * A modified version of the traditional FizzBuzz problem made for VisualNuts coding test.
     * One of my favorites for TDD practice.
     *
     * This class has a full code coverage at {@link pt.visualnuts.VisualNutsTest}
     *
     * @param input
     * @return
     */
    static String execute(Integer input) {
        boolean isDivisibleByThree = input % 3 == 0;
        boolean isDivisibleByFive = input % 5 == 0;
        String name;

        if (isDivisibleByThree && isDivisibleByFive) {
            name = "VisualNuts";
        } else if (isDivisibleByThree) {
            name = "Visual";
        } else if (isDivisibleByFive) {
            name = "Nuts";
        } else {
            name = String.valueOf(input);
        }
        return name;
    }
}