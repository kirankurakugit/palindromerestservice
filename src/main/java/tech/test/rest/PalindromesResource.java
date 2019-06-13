package com.test.rest;

import com.google.common.base.Preconditions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tech.test.domain.PalindromeOperationResult;
import tech.test.services.PalindromeService;

/**
 * Provides rest operations for palindrom resource.
 *
 * 
 */
@RestController
public class PalindromesResource
{
    private final PalindromeService palindromeService;

    public PalindromesResource(PalindromeService palindromeService)
    {
        this.palindromeService = Preconditions.checkNotNull(palindromeService, "Service can not be null");
    }

    @GetMapping("palindromes/{start}/{end}")
    public @ResponseBody
    PalindromeOperationResult getPalindromes(@PathVariable int start, @PathVariable int end)
    {
        return palindromeService.getPalindromes(start, end);
    }
}
