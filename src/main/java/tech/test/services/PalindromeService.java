package tech.test.services;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.mutable.MutableInt;
import org.springframework.stereotype.Service;
import tech.test.domain.PalindromeOperationResult;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Provide Palindrome function operations. @see <a href=" https://en.wikipedia.org/wiki/Palindrome">Palindrome Wiki definition</a>.
 *
 * @author Juan Rada
 */
@Service
public class PalindromeService
{
    public PalindromeOperationResult getPalindromes(final int start, final int end)
    {
        MutableInt operations = new MutableInt(0);
        List<Integer> palindromes =  getPalindromesList(start, end, operations);

        return new PalindromeOperationResult(palindromes, palindromes.size(), operations.getValue());
    }

    /**
     * Calculates the palindromes in the given closed range. Algorithm execute n times the {@code isPalindrome} and
     * {@code isBinaryPalindrome} operations. as both operation performs O(n) time the algorithm theorical complexity
     * is O(n)^2, but effective complexity is expected because early termination.
     *
     * @param start the first integer number in the range.
     * @param end   the last integer number in the range validate.
     * @return an array with binary and integer palindromes in the range.
     * @throws IllegalArgumentException if an invalid range is provided.
     */
    List<Integer> getPalindromesList(final int start, final int end, MutableInt  operations)
    {
        Preconditions.checkArgument(start <= end, "start value of the range needs to be lesser or equals that last value");

        return IntStream.range(start, end)
                .filter(i -> isPalindrome(i, operations) && isBinaryPalindrome(i, operations))
                .boxed().collect(Collectors.toList());
    }

    /**
     * Validate if the given number is palindrome. Algorithm complexity is O(n) where n is the number of decimal positions
     * of the number. Algorithm validate msb and lsb values each iteration so early termination is highly possible.
     *
     * @param number the number to validate if decimal representation is null.
     * @return true if number binary representation is decimal otherwise false.
     */
    private boolean isPalindrome(int number, MutableInt operations)
    {
        int digits = (int) Math.log10(number) + 1;
        int msd_divisor = (int) Math.pow(10, digits - 1);
        int remaining = number;

        for (int i = 0; i < (digits / 2); i++)
        {
            int msb = number / msd_divisor;
            int lsb = remaining % 10;

            if (msb != lsb)
            {
                return false;
            }

            number %= msd_divisor;
            remaining /= 10;
            msd_divisor /= 10;
            operations.increment();
        }

        return true;
    }

    /**
     * Validate if the given number is palindrome. Algorithm complexity is O(n) where n is the number of binary positions
     * of the number. Algorithm validate msb and lsb values using shift operations each iteration so early
     * termination is highly possible.
     *
     * @param number the number to validate if binary representation is null.
     * @return true if number binary representation is binary otherwise false.
     */
    private boolean isBinaryPalindrome(int number, MutableInt operations)
    {
        int digits = (int) (Math.log(number) / Math.log(2) + 1);

        int msb_shift = digits - 1;
        int lsb_shift = 0;

        for (int i = 0; i < (digits / 2); i++)
        {
            int msb = (number & (1 << msb_shift)) >> msb_shift;
            int lsb = (number >> lsb_shift) & 1;

            if (msb != lsb)
            {
                return false;
            }

            msb_shift--;
            lsb_shift++;
            operations.increment();
        }

        return true;
    }
}