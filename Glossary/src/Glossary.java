import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * An easily maintainable glossary facility designed for Cy Burnett.
 *
 * @author Ibrahim Mohamed
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */

    private static class StringCOMP implements Comparator<String> {
        @Override

        // comparing the initial strings
        public int compare(String line1, String line2) {
            return line1.compareTo(line2);
        }
    }

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * It returns the very first term that is not within splitters
     * ({@code splitters}) or the splitter string (which is the max amount of
     * string characters within {@code splitter} in the {@code content}
     * beginning with the initial {@code location}.
     *
     * @param content
     *            the {@code String} from which the word or splitter string is
     *            attained
     * @param location
     *            the beginning index
     * @param splitters
     *            the {@code Set} of characters that act as dividers/separators
     * @return the first term or splitter string found in {@code content}
     *         starting from the index {@code location}
     * @requires 0 <= location < |content|
     * @ensures <pre>
     * nextTermOrSplitter = content[location, location + | nextTermOrSplitter|)
     * and if
     * entries(content[location, location + 1)) intersection splitters = {}
     * then
     * entries(nextTermOrSplitter) intersection splitters = {}
     * </pre>
     */

    private static String nextTermOrSplitter(String content, int location,
            Set<Character> splitters) {

        boolean initialCharIsSplitter = splitters
                .contains(content.charAt(location));
        boolean alignCharacter = true;
        boolean splitterCharacter = initialCharIsSplitter;
        String termOrSplitter = "";
        int k = location;

        while (alignCharacter && k < content.length()) {
            splitterCharacter = splitters.contains(content.charAt(k));
            if (splitterCharacter != initialCharIsSplitter) {
                alignCharacter = false;
            }
            if (alignCharacter) {
                k++;
            }
        }

        termOrSplitter = content.substring(location, k);
        return termOrSplitter;

    }

    /**
     * Retrieves the words from the input {@code inputFile} and places them into
     * a map {@code words} of terms and their respective definitions.
     *
     * @param wordMap
     *            stores the terms and definitions
     * @param inputFile
     *            the source of the strings (input file)
     * @replaces wordMap
     * @requires inputFile.is_open,[terms are a single word]
     * @ensures <pre>
     * wordMap=[the totality of the content within the input file]
     * </pre>
     */

    public static void educeWords(Map<String, String> wordMap,
            SimpleReader inputFile) {

        while (!inputFile.atEOS()) {
            //variables for the lines, values, and keys
            String strLine = inputFile.nextLine();
            String strValue;
            String strKey;

            //string builder variable to modify length
            StringBuilder str = new StringBuilder();
            strKey = strLine;
            str.delete(0, str.length());

            while (!strLine.isEmpty() && !inputFile.atEOS()) {
                strLine = inputFile.nextLine();
                str.append(strLine);
            }

            strValue = str.toString();
            wordMap.add(strKey, strValue);

        }
    }

    /**
     * Places the terms from the {@code wordMap} into a {@code Queue}.
     *
     * @param wordMap
     *            Map from which the terms are taken
     * @param words
     *            a {@code Queue} of terms
     * @requires wordMap /={}
     * @ensures <pre>
     * generateQueue=[totality of terms from the wordMap]
     * </pre>
     */

    public static void generateQueue(Map<String, String> wordMap,
            Queue<String> words) {

        Map<String, String> copyOfGlossary = new Map1L<>();
        copyOfGlossary.transferFrom(wordMap);

        while (copyOfGlossary.size() != 0) {
            Map.Pair<String, String> j;
            j = copyOfGlossary.removeAny();
            //variables that store the term and definition
            String definition = j.value();
            String term = j.key();
            words.enqueue(term);
            wordMap.add(term, definition);
        }

    }

    /**
     * Prints the glossary's index page.
     *
     * @param outputFile
     *            the source of the output (output file)
     *
     * @param terms
     *            the Queue that holds the words
     * @updates outputFile
     * @requires outputFile.is_open
     * @ensures <pre>
     * out.content=[the index page of the glossary]
     * </pre>
     */

    public static void printIndex(SimpleWriter outputFile,
            Queue<String> terms) {

        //printing the index page in the correct order starting from the header
        outputFile.println("<html>\n<head>");
        outputFile.println("<title>Glossary</title></head> ");
        outputFile.println("<body>");
        outputFile.println("<h2>Glossary</h2>");
        outputFile.println("<h3>index</h3>");
        outputFile.println("<ul>");

        //for loop that prints the "current" term
        for (String presentTerm : terms) {
            outputFile.println("<li> <a href= \"" + presentTerm + ".html\">"
                    + presentTerm + "</a></li>");
        }

        //printing the final parts of the page
        outputFile.println("</ul>");
        outputFile.println("</body>");
        outputFile.println("</html>");

    }

    /**
     * Prints the definitions.
     *
     * @param directory
     *            directory that stores the output files
     * @param words
     *            terms queue
     * @param wordMap
     *            Map containing all the terms and definitions
     * @requires terms /{},
     * @ensures <pre>
     * out.content=[pages that contain the definitions of each term]
     * </pre>
     */

    public static void outputDefinitions(String directory, Queue<String> words,
            Map<String, String> wordMap) {

        Set<Character> splitters = new Set1L<>();
        splitters.add(',');
        splitters.add(' ');

        for (String presentWord : words) {
            SimpleWriter outputWord = new SimpleWriter1L(
                    directory + "/" + presentWord + ".html");

            String value = wordMap.value(presentWord);

            //printing the definitions in proper order starting from the header
            outputWord.println("<html>\n<head>");
            outputWord.println("<title>" + presentWord + "</title></head> ");
            outputWord.println("<body>");
            outputWord.println("<h2><b><i><font color=\"red\">" + presentWord
                    + "</font></i></b></h2>");
            outputWord.print("<blockquote>");
            //initializing the location to 0
            int location = 0;

            //while the value size is positive
            while (location < value.length()) {
                String token = nextTermOrSplitter(value, location, splitters);

                //if the word is a map key, a link is given
                if (wordMap.hasKey(token)) {
                    //printing the link
                    outputWord.print("<a href=\"" + token
                            + ".html\"><font color=\\\"orange\">" + token
                            + "</font></a>");

                    //if not, no link is provided
                } else {
                    outputWord.print(token);
                }

                location += token.length();
            }

            //printing the final parts
            outputWord.println(("</blockquote>"));
            outputWord.println(
                    "<p>Return to <a href=\"index.html\">index</a>.</p>");
            outputWord.println("</body>\n</html>");
            outputWord.close();
        }

    }

    /**
     * Main method.
     *
     * @param args
     *
     *            the command line arguments
     */

    public static void main(String[] args) {

        //opening input and output streams
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        Map<String, String> wordMap = new Map1L<>();
        Queue<String> terms = new Queue1L<>();

        //prompting the user to enter the name of their input file
        out.print("Enter an input file: ");
        String fileName = in.nextLine();
        SimpleReader inputFile = new SimpleReader1L(fileName);
        educeWords(wordMap, inputFile);

        generateQueue(wordMap, terms);
        Comparator<String> srt = new StringCOMP();
        terms.sort(srt);

        //prompting the user to enter a storage location
        out.print("Enter a directory (folder) to store the files: ");
        String directory = in.nextLine();
        String index = "index.html";
        SimpleWriter outputFile = new SimpleWriter1L(directory + "/" + index);
        printIndex(outputFile, terms);
        outputDefinitions(directory, terms, wordMap);

        // closing the input and output streams
        in.close();
        out.close();

    }

}
