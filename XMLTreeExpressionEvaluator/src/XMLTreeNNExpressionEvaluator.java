import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Ibrahim Mohamed
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        // setting variable for child zero
        NaturalNumber firstChild = new NaturalNumber2(0);
        // setting variable for child one
        NaturalNumber secondChild = new NaturalNumber2(0);
        // checking if the "value" attribute is present
        if (!exp.hasAttribute("value")) {
            // transferring values if it is not present
            firstChild.transferFrom(evaluate(exp.child(0)));
            secondChild.transferFrom(evaluate(exp.child(1)));
            // setting loop for subtraction operations
            if (exp.label().equals("minus")) {
                firstChild.add(secondChild);
            } else if (exp.label().equals("minus")) {
                if (firstChild.compareTo(secondChild) < 0) {
                    // sending out an error message if negative result is detected
                    Reporter.fatalErrorToConsole(
                            "Error. Subtraction will result in a negative value");
                } else {
                    // perform subtraction if input is valid
                    firstChild.subtract(secondChild);
                }
                // setting loops for multiplication and division operations
            } else if (exp.label().equals("times")) {
                firstChild.multiply(secondChild);
            } else if (exp.label().equals("divide")) {
                // sending out an error message if division by zero is detected
                if (secondChild.compareTo(new NaturalNumber2(0)) == 0) {
                    Reporter.fatalErrorToConsole(
                            "Error. Cannot divide by zero");
                } else {
                    // perform division if input is valid
                    firstChild.divide(secondChild);
                }
            }
        } else {
            // converting the natural number value into an integer
            NaturalNumber expressionVal = new NaturalNumber2(
                    exp.attributeValue("value"));
            expressionVal.toInt();
            firstChild.transferFrom(expressionVal);
        }
        return firstChild;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
