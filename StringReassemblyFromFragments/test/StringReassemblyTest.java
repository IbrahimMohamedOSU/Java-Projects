import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Test for printWithLineSeparators.
 */

public class StringReassemblyTest {

    /**
     * Test cases for combination.
     */

    /**
     * First test case for combination.
     */

    @Test
    public void testcombination_Hell_lloWorld() {
        String str1 = "Hell";
        String str2 = "llo World";

        String strfinal = StringReassembly.combination(str1, str2, 2);
        String strfinalExpected = "Hello World";
        assertEquals(strfinal, strfinalExpected);
    }

    /**
     * Second test case for combination.
     */

    @Test
    public void testcombination_Ibrahim_mmohamed() {
        String str1 = "Ibrahim";
        String str2 = "mmohamed";

        String strfinal = StringReassembly.combination(str1, str2, 1);
        String strfinalExpected = "Ibrahimmohamed";
        assertEquals(strfinal, strfinalExpected);
    }

    /**
     * Third test case for combination.
     */
    @Test
    public void testcombination_GoBucks_oBucksB() {
        String str1 = "Go Bucks";
        String str2 = "o Bucks -- B";

        String strfinal = StringReassembly.combination(str1, str2, 7);
        String strfinalExpected = "Go Bucks -- B";
        assertEquals(strfinal, strfinalExpected);
    }

    /**
     * Test cases for addToSetAvoidingSubstrings.
     */

    /**
     * First Test case for addToSetAvoidingSubstrings.
     */

    @Test
    public void testAddToSetAvoidingSubstringsCantAdd() {
        Set<String> strSet = new Set1L<>();
        strSet.add("Ibrahim");
        Set<String> expectedSet = new Set1L<>();
        expectedSet.add("Ibrahim");
        StringReassembly.addToSetAvoidingSubstrings(strSet, "him");
        assertEquals(strSet, expectedSet);
    }

    /**
     * Second Test case for addToSetAvoidingSubstrings.
     */

    @Test
    public void testAddToSetAvoidingSubstringsCanAddZero() {
        Set<String> strSet = new Set1L<>();
        Set<String> expectedSet = new Set1L<>();
        expectedSet.add("Bicycle");
        StringReassembly.addToSetAvoidingSubstrings(strSet, "Bicycle");
        assertEquals(strSet, expectedSet);
    }

    /**
     * Third test case for addToSetAvoidingSubstrings.
     */

    @Test
    public void testAddToSetAvoidingSubstringsCanAddAndRemoveOne() {
        Set<String> strSet = new Set1L<>();
        strSet.add("Dan");
        Set<String> expectedSet = new Set1L<>();
        expectedSet.add("Danger");
        StringReassembly.addToSetAvoidingSubstrings(strSet, "Danger");
        assertEquals(strSet, expectedSet);
    }

    /**
     * Test cases for linesFromInput.
     */

    /**
     * First test case for linesFromInput.
     */

    @Test
    public void testlinesFromInputOneLine() {
        Set<String> expectedSet = new Set1L<>();
        expectedSet.add("Michigan");

        SimpleReader in = new SimpleReader1L("data/oneLine.txt");
        Set<String> set = StringReassembly.linesFromInput(in);

        assertEquals(set, expectedSet);
    }

    /**
     * Second test case for linesFromInput.
     */

    @Test
    public void testlinesFromInputManyLines() {
        Set<String> expectedSet = new Set1L<>();
        expectedSet.add("Bucks -- Beat");
        expectedSet.add("Go Bucks");
        expectedSet.add("o Bucks -- B");
        expectedSet.add("Beat Mich");

        expectedSet.add("Michigan~");

        SimpleReader in = new SimpleReader1L("data/cheer-8-2.txt");
        Set<String> set = StringReassembly.linesFromInput(in);

        assertEquals(set, expectedSet);
    }

    /**
     * Test case for printWithLineSeparators.
     */

    @Test
    public void testPrintWithLineSeparator() {
        SimpleWriter out = new SimpleWriter1L("data/printOneLine.txt");
        StringReassembly.printWithLineSeparators("Michigan~", out);
        SimpleReader in = new SimpleReader1L("data/printOneLine.txt");

        String result = in.nextLine();

        assertEquals(result, "Michigan");
    }

}
