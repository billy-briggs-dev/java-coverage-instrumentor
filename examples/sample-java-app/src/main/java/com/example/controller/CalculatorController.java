package com.example.controller;

import com.example.Calculator;
import com.example.model.CalculationRequest;
import com.example.model.CalculationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

/**
 * REST controller for calculator operations.
 * Provides endpoints for various mathematical operations.
 */
@RestController
@RequestMapping("/api/calculator")
@Validated
public class CalculatorController {

    private final Calculator calculator;

    @Autowired
    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }

    @PostMapping("/add")
    public ResponseEntity<CalculationResponse> add(@Valid @RequestBody CalculationRequest request) {
        int result = calculator.add(request.getA(), request.getB());
        return ResponseEntity.ok(new CalculationResponse(result, "addition"));
    }

    @PostMapping("/subtract")
    public ResponseEntity<CalculationResponse> subtract(@Valid @RequestBody CalculationRequest request) {
        int result = calculator.subtract(request.getA(), request.getB());
        return ResponseEntity.ok(new CalculationResponse(result, "subtraction"));
    }

    @PostMapping("/multiply")
    public ResponseEntity<CalculationResponse> multiply(@Valid @RequestBody CalculationRequest request) {
        int result = calculator.multiply(request.getA(), request.getB());
        return ResponseEntity.ok(new CalculationResponse(result, "multiplication"));
    }

    @PostMapping("/divide")
    public ResponseEntity<CalculationResponse> divide(@Valid @RequestBody CalculationRequest request) {
        try {
            double result = calculator.divide(request.getA(), request.getB());
            return ResponseEntity.ok(new CalculationResponse(result, "division"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new CalculationResponse(0, "error: " + e.getMessage()));
        }
    }

    @GetMapping("/factorial/{n}")
    public ResponseEntity<CalculationResponse> factorial(@PathVariable @Min(0) int n) {
        try {
            int result = calculator.factorial(n);
            return ResponseEntity.ok(new CalculationResponse(result, "factorial"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new CalculationResponse(0, "error: " + e.getMessage()));
        }
    }

    @GetMapping("/prime/{n}")
    public ResponseEntity<CalculationResponse> isPrime(@PathVariable @Min(1) int n) {
        boolean result = calculator.isPrime(n);
        return ResponseEntity.ok(new CalculationResponse(result ? 1 : 0, 
                "prime check: " + n + " is " + (result ? "" : "not ") + "prime"));
    }

    @GetMapping("/operations-count")
    public ResponseEntity<CalculationResponse> getOperationsCount() {
        int count = calculator.getOperationCount();
        return ResponseEntity.ok(new CalculationResponse(count, "operations count"));
    }

    @PostMapping("/complex-calculation")
    public ResponseEntity<CalculationResponse> complexCalculation(@Valid @RequestBody CalculationRequest request) {
        // Demonstrate complex logic with multiple branches
        int a = request.getA();
        int b = request.getB();
        double result;

        if (a > 0 && b > 0) {
            // Both positive - multiply and add
            result = calculator.multiply(a, b) + calculator.add(a, b);
        } else if (a < 0 && b < 0) {
            // Both negative - subtract and divide
            result = calculator.subtract(Math.abs(a), Math.abs(b));
            if (result != 0) {
                result = calculator.divide(100, result);
            }
        } else if (a == 0 || b == 0) {
            // One is zero
            result = calculator.add(a, b);
        } else {
            // Mixed signs - complex calculation
            int sum = calculator.add(Math.abs(a), Math.abs(b));
            if (sum > 10) {
                result = calculator.factorial(Math.min(sum, 10));
            } else {
                result = calculator.multiply(a, b);
            }
        }

        return ResponseEntity.ok(new CalculationResponse(result, "complex calculation"));
    }
}
