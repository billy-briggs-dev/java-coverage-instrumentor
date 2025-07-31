package com.example.model;

import jakarta.validation.constraints.NotNull;

/**
 * Request model for calculator operations.
 */
public class CalculationRequest {
    
    @NotNull(message = "Value 'a' is required")
    private Integer a;
    
    @NotNull(message = "Value 'b' is required")
    private Integer b;
    
    public CalculationRequest() {}
    
    public CalculationRequest(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }
    
    public Integer getA() {
        return a;
    }
    
    public void setA(Integer a) {
        this.a = a;
    }
    
    public Integer getB() {
        return b;
    }
    
    public void setB(Integer b) {
        this.b = b;
    }
    
    @Override
    public String toString() {
        return "CalculationRequest{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
