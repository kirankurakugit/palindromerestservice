package com.test.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import tech.test.domain.PalindromeOperationResult;

import java.io.IOException;

/**
 * Rest palindromes end point integration test.
 *
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PalindromesIntegrationTest
{
    @Autowired
    private TestRestTemplate restTemplate;

    private JacksonTester<PalindromeOperationResult> json;

    @Before
    public void setup()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }


    @Test
    public void testGetPalindromes() throws IOException
    {
        String responseBody = this.restTemplate.getForObject("/palindromes/0/1000000", String.class);
        PalindromeOperationResult result = json.parseObject(responseBody);

        assertThat(result.getCount()).isEqualTo(20);
        assertThat(result.getOperations()).isEqualTo(113079);
        assertThat(result.getPalindromes()).contains(0, 1, 3, 5, 7, 9, 33, 99, 313, 585, 717, 7447, 9009, 15351, 32223, 39993, 53235, 53835, 73737, 585585);
    }
}
