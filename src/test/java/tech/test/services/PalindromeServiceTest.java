package tech.test.services;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

import java.util.List;

/**
 * Test suite for {@link PalindromeService}
 *
 * @author Juan Rada
 */
public class PalindromeServiceTest
{
    private final PalindromeService testInstance = new PalindromeService();

    /**
     * palindromes numbers = 20
     * Operations executed: 113079
     */
    @Test
    public void testGetPalindromes()
    {
        List<Integer> palindromes = testInstance.getPalindromes(0, 1000_000).getPalindromes();
        assertRange(palindromes, 0, 1000_000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenInvalidRange()
    {
        testInstance.getPalindromes(10, 5);
    }

    @Test
    public void testGetPalindromesWhenSequenceDoNotStartInZero()
    {
        List<Integer> palindromes = testInstance.getPalindromes(100, 200).getPalindromes();
        assertRange(palindromes, 100, 200);
    }

    /**
     * Assert the given arrange value correspond to the int and binary palindromes numbers.
     *
     * @param result the result array to validate.
     * @param start  the sequence start value.
     * @param end    the end value.
     */
    private void assertRange(final List<Integer> result, final int start, final int end)
    {
        for (int i = start; i <= end; i++)
        {
            assertThat(result.contains(i)).as("The number %d palindrome validation", i).isEqualTo(isPalindrome(i));
        }
    }

    /**
     * Is palindrome Testing implementation. Simple but poor performance implementation used for assert real
     * implementation.
     *
     * @param number the number to validate if is a palindrome.
     * @return true if is palindrome otherwise false.
     */
    private boolean isPalindrome(int number)
    {
        String reverse = new StringBuilder(String.valueOf(number)).reverse().toString();
        String binary = Integer.toBinaryString(number);
        String reverseBinary = new StringBuilder(binary).reverse().toString();

        return reverse.equals(String.valueOf(number)) && reverseBinary.equals(binary);
    }

}