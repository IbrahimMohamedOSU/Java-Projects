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
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
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

        // creating arrays that store a position in the deJager set
        // setting counters for incrementation
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;

        // declaring the exponent variables for the dejager formula
        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        double result = 0;

        // initializing the variables used to determine the relative error
        final double max = 100;
        final double relError = 0.001; // within a fraction of 1%

        /*
         * utilizing nested while loops to check each value in the deJager set
         * until a suitable combination is found.
         */
        while (count1 < deJager.length) {
            count2 = 0;
            while (count2 < deJager.length) {
                count3 = 0;
                while (count3 < deJager.length) {
                    count4 = 0;
                    while (count4 < deJager.length) {

                        // initializing the exponents into the deJager formula
                        result = Math.pow(w, deJager[count1])
                                * Math.pow(x, deJager[count2])
                                * Math.pow(y, deJager[count3])
                                * Math.pow(z, deJager[count4]);

                        // making sure the result is within 1% relative error
                        if ((Math.abs(result - constant))
                                / constant < relError) {
                            a = deJager[count1];
                            b = deJager[count2];
                            c = deJager[count3];
                            d = deJager[count4];
                        }
                        count4++;
                    }
                    count3++;
                }
                count2++;
            }
            count1++;
        }

        // printing the final exponents
        out.println("The best values for exponents a, b, c, d are: " + a + ", "
                + b + ", " + c + ", " + d);

        // printing the final approximation
        double approximation = Math.pow(w, a) * Math.pow(x, b) * Math.pow(y, c)
                * Math.pow(z, d);
        out.print("\nThe closest approximation for the chosen constant is: ");
        out.println(approximation, 2, false);

        // printing the relative error
        out.print("\nThe corresponding relative error percentage is: ");
        out.print(((Math.abs(approximation - constant)) / constant) * max, 2,
                false);
        out.print("%");

        // close input and output streams
        in.close();
        out.close();
    }

}
