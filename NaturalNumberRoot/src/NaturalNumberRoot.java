import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Ibrahim Mohamed
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        // declaring variables for toohigh and n
        // ensuring that toohigh = n + 1 (incrementation)
        NaturalNumber tooHigh = n.newInstance();
        tooHigh.copyFrom(n);
        tooHigh.increment();

        // setting variables that hold values for 1 and 2
        NaturalNumber one = n.newInstance();
        one.multiplyBy10(1);

        NaturalNumber two = n.newInstance();
        two.multiplyBy10(2);

        // initializing guess value to toohigh/2
        NaturalNumber guess = n.newInstance();
        guess.copyFrom(tooHigh);
        guess.divide(two);

        // initializing the end result, empty guesses, and
        // lowenough variable to 0
        NaturalNumber endResult = n.newInstance();
        NaturalNumber emptyGuess = n.newInstance();
        NaturalNumber lowEnough = n.newInstance();

        // initializing toohigh value into endresult
        // then subracting lowenough value
        endResult.copyFrom(tooHigh);
        endResult.subtract(lowEnough);

        // setting difference value to toohigh - lowenough (calculation done above)
        int diff = endResult.compareTo(one);

        // while loop will run as long as the difference value is positive
        while (diff > 0) {

            emptyGuess.copyFrom(guess);
            emptyGuess.power(r);

            // setting the guess values difference to
            // empty guess and n comparison
            int guessDifference = emptyGuess.compareTo(n);

            // if the difference for guesses is positive, guess value
            // will be copied into toohigh
            if (guessDifference > 0) {
                tooHigh.copyFrom(guess);
            } else { // else it will copy into lowenough
                lowEnough.copyFrom(guess);
            }

            // declaring an empty variable for toohigh
            NaturalNumber tooHighEmpty = n.newInstance();
            tooHighEmpty.copyFrom(tooHigh);

            // performing arithmetic between toohighempty and endresult
            tooHighEmpty.add(lowEnough); // tooHighEmpty + lowEnough
            tooHighEmpty.divide(two); // tooHighEmpty/2
            guess.transferFrom(tooHighEmpty); //transferring between tooHighmpty and guess
            endResult.copyFrom(tooHigh); // endresult = toohigh
            endResult.subtract(lowEnough); // endresult - lowenough

            // initializing the result value into difference variable
            diff = endResult.compareTo(one);

        }
        n.transferFrom(lowEnough);

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}
