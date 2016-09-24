package tech.test.domain;

import java.util.List;

/**
 * Encapsulate palindrome operation result payload.
 *
 * @author Juan Rada
 */
public class PalindromeOperationResult
{
    private List<Integer> palindromes;
    private int count;
    private int operations;

    //Required by Jackson
    public PalindromeOperationResult(){}

    public PalindromeOperationResult(List<Integer> palindromes, int count, int operations)
    {
        this.palindromes = palindromes;
        this.count = count;
        this.operations = operations;
    }

    public List<Integer> getPalindromes()
    {
        return palindromes;
    }

    public int getCount()
    {
        return count;
    }

    public int getOperations()
    {
        return operations;
    }
}
