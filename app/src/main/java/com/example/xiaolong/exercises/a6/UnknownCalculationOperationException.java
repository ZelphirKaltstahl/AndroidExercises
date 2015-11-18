package com.example.xiaolong.exercises.a6;

/**
 * Created by xiaolong on 18.11.15.
 */
public class UnknownCalculationOperationException extends Exception {
    //Parameterless Constructor
    public UnknownCalculationOperationException() {}

    //Constructor that accepts a message
    public UnknownCalculationOperationException(String message)
    {
        super(message);
    }
}
