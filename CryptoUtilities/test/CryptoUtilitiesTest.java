import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Ibrahim Mohamed
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    /**
     * Basic test.
     */
    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Basic test.
     */
    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Moderate test.
     */
    @Test
    public void testReduceToGCD_50_27() {
        NaturalNumber n = new NaturalNumber2(50);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(27);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Challenging test.
     */
    @Test
    public void testReduceToGCD_10050_250() {
        NaturalNumber n = new NaturalNumber2(10050);
        NaturalNumber nExpected = new NaturalNumber2(50);
        NaturalNumber m = new NaturalNumber2(250);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    /**
     * Basic test.
     */
    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /**
     * Basic test.
     */
    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /**
     * Moderate test.
     */
    @Test
    public void testIsEven_67() {
        NaturalNumber n = new NaturalNumber2(67);
        NaturalNumber nExpected = new NaturalNumber2(67);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /**
     * Challenging test.
     */
    @Test
    public void testIsEven_17765932() {
        NaturalNumber n = new NaturalNumber2(17765932);
        NaturalNumber nExpected = new NaturalNumber2(17765932);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    /**
     * Basic test.
     */
    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * Moderate test.
     */
    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * Basic test.
     */
    @Test
    public void testPowerMod_5_7_12() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(5);
        NaturalNumber p = new NaturalNumber2(7);
        NaturalNumber pExpected = new NaturalNumber2(7);
        NaturalNumber m = new NaturalNumber2(12);
        NaturalNumber mExpected = new NaturalNumber2(12);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * Challenging test.
     */
    @Test
    public void testPowerMod_30_4_10() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        NaturalNumber p = new NaturalNumber2(5);
        NaturalNumber pExpected = new NaturalNumber2(5);
        NaturalNumber m = new NaturalNumber2(10);
        NaturalNumber mExpected = new NaturalNumber2(10);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */

    /**
     * Basic test.
     */
    @Test
    public void testIsWitnessToCompositeness_3_20() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber wExpected = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(20);
        NaturalNumber nExpected = new NaturalNumber2(20);
        assertEquals(true, CryptoUtilities.isWitnessToCompositeness(w, n));
        assertEquals(w, wExpected);
        assertEquals(n, nExpected);
    }

    /**
     * Moderate test.
     */
    @Test
    public void testIsWitnessToCompositeness_3_89() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber wExpected = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(89);
        NaturalNumber nExpected = new NaturalNumber2(89);
        assertEquals(false, CryptoUtilities.isWitnessToCompositeness(w, n));
        assertEquals(w, wExpected);
        assertEquals(n, nExpected);
    }

    /**
     * Challenging test.
     */
    @Test
    public void testIsWitnessToCompositeness_60_30000() {
        NaturalNumber w = new NaturalNumber2(60);
        NaturalNumber wExpected = new NaturalNumber2(60);
        NaturalNumber n = new NaturalNumber2(30000);
        NaturalNumber nExpected = new NaturalNumber2(30000);
        assertEquals(true, CryptoUtilities.isWitnessToCompositeness(w, n));
        assertEquals(w, wExpected);
        assertEquals(n, nExpected);
    }

    /**
     * Challenging test.
     */
    @Test
    public void testIsWitnessToCompositeness_402_42323() {
        NaturalNumber w = new NaturalNumber2(402);
        NaturalNumber wExpected = new NaturalNumber2(402);
        NaturalNumber n = new NaturalNumber2(42323);
        NaturalNumber nExpected = new NaturalNumber2(42323);
        assertEquals(false, CryptoUtilities.isWitnessToCompositeness(w, n));
        assertEquals(w, wExpected);
        assertEquals(n, nExpected);
    }

    /*
     * Tests of isPrime1.
     */

    /**
     * Basic test.
     */
    @Test
    public void testIsPrime1_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        assertEquals(false, CryptoUtilities.isPrime1(n));
        assertEquals(n, nExpected);
    }

    /**
     * Basic test.
     */
    @Test
    public void testIsPrime1_43() {
        NaturalNumber n = new NaturalNumber2(43);
        NaturalNumber nExpected = new NaturalNumber2(43);
        assertEquals(true, CryptoUtilities.isPrime1(n));
        assertEquals(n, nExpected);
    }

    /**
     * Moderate test (Boundary).
     */
    @Test
    public void testIsPrime1_MAX() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE);
        NaturalNumber nExpected = new NaturalNumber2(Integer.MAX_VALUE);
        assertEquals(true, CryptoUtilities.isPrime1(n));
        assertEquals(n, nExpected);
    }

    /**
     * Challenging test.
     */
    @Test
    public void testIsPrime1_1456243227563() {
        NaturalNumber n = new NaturalNumber2("1456243227563");
        NaturalNumber nExpected = new NaturalNumber2("1456243227563");
        assertEquals(false, CryptoUtilities.isPrime1(n));
        assertEquals(n, nExpected);
    }

    /*
     * Tests of isPrime2.
     */

    /**
     * Basic test.
     */
    @Test
    public void testIsPrime2_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        assertEquals(false, CryptoUtilities.isPrime1(n));
        assertEquals(n, nExpected);
    }

    /**
     * Basic test.
     */
    @Test
    public void testIsPrime2_43() {
        NaturalNumber n = new NaturalNumber2(43);
        NaturalNumber nExpected = new NaturalNumber2(43);
        assertEquals(true, CryptoUtilities.isPrime1(n));
        assertEquals(n, nExpected);
    }

    /**
     * Moderate test (Boundary).
     */
    @Test
    public void testIsPrime2_MAX() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE);
        NaturalNumber nExpected = new NaturalNumber2(Integer.MAX_VALUE);
        assertEquals(true, CryptoUtilities.isPrime1(n));
        assertEquals(n, nExpected);
    }

    /**
     * Challenging test.
     */
    @Test
    public void testIsPrime2_1456243227563() {
        NaturalNumber n = new NaturalNumber2("1456243227563");
        NaturalNumber nExpected = new NaturalNumber2("1456243227563");
        assertEquals(false, CryptoUtilities.isPrime1(n));
        assertEquals(n, nExpected);
    }

    /*
     * Tests of generateNextLikelyPrime.
     */

    /**
     * Basic test.
     */
    @Test
    public void testGenerateNextLikelyPrime_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(3);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(n, nExpected);
    }

    /**
     * Moderate test.
     */
    @Test
    public void testGenerateNextLikelyPrime_51() {
        NaturalNumber n = new NaturalNumber2(51);
        NaturalNumber nExpected = new NaturalNumber2(53);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(n, nExpected);
    }

    /**
     * Challenging test).
     */
    @Test
    public void testGenerateNextLikelyPrime_42691384() {
        NaturalNumber n = new NaturalNumber2("42691384");
        NaturalNumber nExpected = new NaturalNumber2("42691391");
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(n, nExpected);
    }

}
