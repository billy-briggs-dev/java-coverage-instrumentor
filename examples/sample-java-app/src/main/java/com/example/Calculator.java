package com.example;

import org.springframework.stereotype.Service;

/**
 * A calculator service with various methods to demonstrate
 * coverage instrumentation on different code patterns.
 */
@Service
public class Calculator {
    
    private int operationCount = 0;
    
    public int add(int a, int b) {
        operationCount++;
        return a + b;
    }
    
    public int subtract(int a, int b) {
        operationCount++;
        return a - b;
    }
    
    public int multiply(int a, int b) {
        operationCount++;
        if (a == 0 || b == 0) {
            return 0;
        }
        return a * b;
    }
    
    public double divide(double a, double b) {
        operationCount++;
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero");
        }
        return a / b;
    }
    
    public int factorial(int n) {
        operationCount++;
        if (n < 0) {
            throw new IllegalArgumentException("Negative number");
        }
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    public boolean isPrime(int n) {
        operationCount++;
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    
    public int getOperationCount() {
        return operationCount;
    }
    
    public void reset() {
        operationCount = 0;
    }
}
