package com.example.xiaolong.exercises.a6;

/**
 * Created by xiaolong on 18.11.15.
 */
public enum CalculationOperation {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String text;

    CalculationOperation(final String text) {
        this.text = text;
    }

    public static CalculationOperation get_operation_by_string(String calculation_string) throws UnknownCalculationOperationException {
        if (calculation_string.equals("+")) {
            return CalculationOperation.PLUS;
        }
        if (calculation_string.equals("-")) {
            return CalculationOperation.MINUS;
        }
        if (calculation_string.equals("*")) {
            return CalculationOperation.MULTIPLY;
        }
        if (calculation_string.equals("/")) {
            return CalculationOperation.DIVIDE;
        }

        throw new UnknownCalculationOperationException("Could not find CalculationOperation for provided String.");
    }


    @Override
    public String toString() {
        return text;
    }
}
