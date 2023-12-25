import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Calculating and returning the square root of a number via newton iteration.
 *
 * @author Ibrahim Mohamed
 *
 */
public final class Newton2 {

    /**
     * a private constructor that prevents instantiation.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @requires x > 0, x needs to be a positive number
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        final double EPS = 0.0001; // initializing epsilon to 0.01%
        double r = x;

        // if x is greater than 0, the loop will run
        if (x > 0) {
            // implementing the (r + x/r)/2 and |r2 – x |/x < ε2 formulas
            while (Math.abs(r * r - x) / r > EPS * EPS) {
                r = (x / r + r) / 2.0;
            }
            return r;
        } else {
            return 0;
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     *
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call sqrt as shown
         */
        char response = 'y';
        while (response == 'y') {
            out.println("Please enter a positive number: ");

            double value = in.nextDouble(); // if the user types 'y', input is taken
            out.println("The square root of " + value + " is " + sqrt(value));

            out.println(
                    "Want to calculate another square root? if so, type the letter 'y' ");
            response = in.nextLine().charAt(0);
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
