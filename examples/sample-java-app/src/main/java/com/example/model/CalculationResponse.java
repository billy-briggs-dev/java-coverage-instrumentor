package com.example.model;

/**
 * Response model for calculator operations.
 */
public class CalculationResponse {
    
    private double result;
    private String operation;
    private long timestamp;
    
    public CalculationResponse() {
        this.timestamp = System.currentTimeMillis();
    }
    
    public CalculationResponse(double result, String operation) {
        this.result = result;
        this.operation = operation;
        this.timestamp = System.currentTimeMillis();
    }
    
    public double getResult() {
        return result;
    }
    
    public void setResult(double result) {
        this.result = result;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
        return "CalculationResponse{" +
                "result=" + result +
                ", operation='" + operation + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
