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
public final class Newton4 {

    /**
     * a private constructor that prevents instantiation.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     * @param estimate
     *            estimate positive number to compute square root of, and
     *            epsilon value
     *
     * @requires x > 0, x needs to be a positive number
     * @input EPS, ask the user for the value of EPSILON
     * @return estimate of square root
     */
    private static double sqrt(double x, double estimate) {
        double EPS = estimate; // initializing epsilon to user input
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
        out.println("Please enter a positive number: ");
        double x = in.nextDouble();

        // while the value is positive, the loop will run
        while (x > 0) {

            out.println("Please enter an estimate for EPSILON: ");
            double estimate = in.nextDouble(); // user input for epsilon
            double result = sqrt(x, estimate);

            out.println("The square root of " + x + " is " + result);

            out.println("Please enter another positive number");
            x = in.nextDouble(); // if the user types 'y', input is taken
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
