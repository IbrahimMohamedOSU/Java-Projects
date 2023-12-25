import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * calculating the value of a constant via de jager formula.
 *
 * @author Ibrahim Mohamed
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.println("Please enter a positive universal constant: ");
        String u = in.nextLine(); // read the input as a string
        // check if the string contains a positive double
        while (!FormatChecker.canParseDouble(u)
                || Double.parseDouble(u) <= 0.0) {
            out.println("Error. Please enter a positive value: ");
            u = in.nextLine();
        }
        return Double.parseDouble(u);

    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        out.println("Please enter a value that is special to you and not 1: ");
        String value = in.nextLine();
        while (!FormatChecker.canParseDouble(value)
                || Double.parseDouble(value) == 1.0) {
            out.println("Error. Please enter a positive value that is not 1: ");
            value = in.nextLine();
        }
        return Double.parseDouble(value);

    }

    /**
     * printing out the results, exponents, and relative error.
     *
     * @param approximation
     *            the result of the calculation
     * @param out
     *            the output stream
     * @param a
     *            first exponent
     * @param b
     *            second exponent
     * @param c
     *            third exponent
     * @param d
     *            fourth exponent
     * @param constant
     *            the universal constant chosen by the user
     * @param max
     *            the 100 percent value used to calc the relative error
     */

    private static void print(double approximation, SimpleWriter out, double a,
            double b, double c, double d, double constant, double max) {

        // printing the final exponents
        out.println("The best values for exponents a, b, c, d are: " + a + ", "
                + b + ", " + c + ", " + d);

        // printing the best approximation
        out.print("\nThe closest approximation for the chosen constant is: ");
        out.println(approximation, 2, false);

        // printing the relative error
        out.print("\nThe corresponding relative error percentage is: ");
        out.print(((Math.abs(approximation - constant)) / constant) * max, 2,
                false);
        out.print("%");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // initializing the user inputs into the main method
        // double constant is used as the max number within the 1% relative error range
        double constant = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        // de jager set and calculation
        final double[] deJager = { -5, -4, -3, -2, -1, -1.0 / 2.0, -1.0 / 3.0,
                -1.0 / 4.0, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1, 2, 3, 4, 5 };

        // declaring the exponent variables and result for the dejager formula
        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        double result = 0;

        // initializing the variables used to determine the relative error
        final double max = 100;
        final double relError = 0.001; // within a fraction of 1%

        /*
         * utilizing for loops to check each value in the deJager set until a
         * suitable combination is found.
         */
        for (int i = 0; i < deJager.length; i++) {
            int j = 0;
            for (j = 0; j < deJager.length; j++) {
                int k = 0;
                for (k = 0; k < deJager.length; k++) {
                    int l = 0;
                    for (l = 0; l < deJager.length; l++) {

                        // initializing the exponents into the deJager formula
                        result = Math.pow(w, deJager[i])
                                * Math.pow(x, deJager[j])
                                * Math.pow(y, deJager[k])
                                * Math.pow(z, deJager[l]);

                        // making sure the result is within 1% relative error
                        if ((Math.abs(result - constant))
                                / constant < relError) {
                            a = deJager[i];
                            b = deJager[j];
                            c = deJager[k];
                            d = deJager[l];
                        }

                    }

                }

            }

        }

        // printing the final approximation
        double approximation = Math.pow(w, a) * Math.pow(x, b) * Math.pow(y, c)
                * Math.pow(z, d);

        print(approximation, out, a, b, c, d, constant, max);

        // close input and output streams
        in.close();
        out.close();
    }

}
