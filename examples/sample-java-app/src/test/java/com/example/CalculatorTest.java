package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Calculator service.
 * These tests exercise various code paths to generate coverage data.
 */
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Should add two positive numbers correctly")
    void testAddPositiveNumbers() {
        int result = calculator.add(5, 3);
        assertEquals(8, result);
    }

    @Test
    @DisplayName("Should add negative numbers correctly")
    void testAddNegativeNumbers() {
        int result = calculator.add(-5, -3);
        assertEquals(-8, result);
    }

    @Test
    @DisplayName("Should add mixed sign numbers correctly")
    void testAddMixedNumbers() {
        int result = calculator.add(5, -3);
        assertEquals(2, result);
    }

    @ParameterizedTest
    @CsvSource({
        "10, 5, 5",
        "0, 5, -5",
        "-10, -5, -5",
        "100, 50, 50"
    })
    @DisplayName("Should subtract numbers correctly")
    void testSubtract(int a, int b, int expected) {
        int result = calculator.subtract(a, b);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should multiply positive numbers correctly")
    void testMultiplyPositive() {
        int result = calculator.multiply(4, 5);
        assertEquals(20, result);
    }

    @Test
    @DisplayName("Should return zero when multiplying by zero")
    void testMultiplyByZero() {
        int result1 = calculator.multiply(0, 5);
        int result2 = calculator.multiply(5, 0);
        assertEquals(0, result1);
        assertEquals(0, result2);
    }

    @Test
    @DisplayName("Should multiply negative numbers correctly")
    void testMultiplyNegative() {
        int result = calculator.multiply(-4, 5);
        assertEquals(-20, result);
    }

    @Test
    @DisplayName("Should divide positive numbers correctly")
    void testDividePositive() {
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result, 0.001);
    }

    @Test
    @DisplayName("Should handle division with decimal result")
    void testDivideDecimal() {
        double result = calculator.divide(10, 3);
        assertEquals(3.333, result, 0.001);
    }

    @Test
    @DisplayName("Should throw exception when dividing by zero")
    void testDivideByZero() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.divide(10, 0)
        );
        assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    @DisplayName("Should calculate factorial of zero as 1")
    void testFactorialZero() {
        int result = calculator.factorial(0);
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Should calculate factorial of one as 1")
    void testFactorialOne() {
        int result = calculator.factorial(1);
        assertEquals(1, result);
    }

    @ParameterizedTest
    @CsvSource({
        "2, 2",
        "3, 6",
        "4, 24",
        "5, 120"
    })
    @DisplayName("Should calculate factorial correctly for positive numbers")
    void testFactorialPositive(int n, int expected) {
        int result = calculator.factorial(n);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should throw exception for negative factorial")
    void testFactorialNegative() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.factorial(-1)
        );
        assertEquals("Negative number", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 0, 1})
    @DisplayName("Should return false for non-prime numbers <= 1")
    void testIsPrimeNonPrime(int n) {
        boolean result = calculator.isPrime(n);
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23})
    @DisplayName("Should return true for prime numbers")
    void testIsPrimePrime(int n) {
        boolean result = calculator.isPrime(n);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22})
    @DisplayName("Should return false for composite numbers")
    void testIsPrimeComposite(int n) {
        boolean result = calculator.isPrime(n);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should track operation count correctly")  
    void testOperationCount() {
        int initialCount = calculator.getOperationCount();
        
        calculator.add(1, 2);
        assertEquals(initialCount + 1, calculator.getOperationCount());
        
        calculator.multiply(3, 4);
        assertEquals(initialCount + 2, calculator.getOperationCount());
        
        calculator.divide(10, 2);
        assertEquals(initialCount + 3, calculator.getOperationCount());
    }

    @Test
    @DisplayName("Should handle large numbers in factorial")
    void testFactorialLargeNumber() {
        // Test factorial with a larger number to exercise recursion
        int result = calculator.factorial(6);
        assertEquals(720, result);
    }

    @Test
    @DisplayName("Should handle edge cases in prime checking")
    void testPrimeEdgeCases() {
        // Test some larger numbers to exercise the loop in isPrime
        assertFalse(calculator.isPrime(25)); // 5 * 5
        assertTrue(calculator.isPrime(29));  // Prime
        assertFalse(calculator.isPrime(49)); // 7 * 7
    }
}
