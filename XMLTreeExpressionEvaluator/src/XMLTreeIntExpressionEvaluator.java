import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Ibrahim Mohamed
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        // declaring a variable for the calculation result
        int outcome = 0;
        /*
         * setting an if loop that performs arithmetic using addition,
         * subtraction, multiplication, and division.
         */
        if (!exp.hasAttribute("value")) {
            // a variable that corresponds to child zero
            int firstChild = evaluate(exp.child(0));
            // label values correspond to a specific operation
            if (exp.label().equals("plus")) { // addition
                outcome = firstChild + evaluate(exp.child(1));
            } else if (exp.label().equals("minus")) { // subtraction
                outcome = firstChild - evaluate(exp.child(1));
            } else if (exp.label().equals("times")) { // multiplication
                outcome = firstChild * evaluate(exp.child(1));
            } else if (exp.label().equals("divide")) { // division
                outcome = firstChild / evaluate(exp.child(1));
            }
        } else {
            /*
             * declaring a variable to convert the integer expression value into
             * a string
             */
            String expressionVal = exp.attributeValue("value");
            outcome = Integer.parseInt(expressionVal);
        }
        return outcome;
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
