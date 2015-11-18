package com.example.xiaolong.exercises.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xiaolong.exercises.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class a_calculator extends Activity {

    private ArrayList<String> equations = new ArrayList<>();

    private static final String CALCULATOR_LOG_TAG = "CALCULATOR";
    public static final String EQUATIONS_EXTRA_STRING = "equations";
    public static final String SINGLE_EQUATION_EXTRA_STRING = "equation";

    private String current_text = "";
    private boolean last_input_was_operator = false;

    // the following three members are important for calculation of a result
    private CalculationOperation last_operation = null;
    private String first_number = "";
    private String last_number = "";

    private HashMap<CalculationOperation, CalculatorAction> operation_calculation_map = new HashMap<>();

    private TextView input_output_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_calculator);

        fill_operation_action_map();

        Intent initial_intent = getIntent();

        input_output_textview = (TextView) findViewById(R.id.a5_calculator_textview);

        if(initial_intent.hasExtra(a_calculator.SINGLE_EQUATION_EXTRA_STRING)) {
            String equation_string = initial_intent.getStringExtra(a_calculator.SINGLE_EQUATION_EXTRA_STRING);
            Log.d(a_calculator.CALCULATOR_LOG_TAG, "Intent carries for key: " + a_calculator.SINGLE_EQUATION_EXTRA_STRING + " data: " + equation_string);
            insert_equation(equation_string);
        }

    }

    private void insert_equation(String equation_string) {
        String[] equation_elements = equation_string.split(" ", -1);
        for(String elem : equation_elements) {
            Log.d(a_calculator.CALCULATOR_LOG_TAG, "element:" + elem);
        }

        first_number = equation_elements[0];
        try {
            last_operation = CalculationOperation.get_operation_by_string(equation_elements[1]);
        } catch (UnknownCalculationOperationException e) {
            e.printStackTrace();
        }

        reset();
        first_number = equation_elements[0];
        try {
            last_operation = CalculationOperation.get_operation_by_string(equation_elements[1]);
        } catch (UnknownCalculationOperationException e) {
            e.printStackTrace();
        }
        last_number = equation_elements[2];
        current_text = equation_elements[4];

        input_output_textview.setText(equation_elements[0] + equation_elements[1] + equation_elements[2] + "=" + equation_elements[4]);
    }

    private void fill_operation_action_map() {
        operation_calculation_map.put(CalculationOperation.PLUS, new CalculatorAction() {
            @Override
            public double calculate(double input_1, double input_2) {
                return input_1 + input_2;
            }
        });
        operation_calculation_map.put(CalculationOperation.MINUS, new CalculatorAction() {
            @Override
            public double calculate(double input_1, double input_2) {
                return input_1 - input_2;
            }
        });
        operation_calculation_map.put(CalculationOperation.MULTIPLY, new CalculatorAction() {
            @Override
            public double calculate(double input_1, double input_2) {
                return input_1 * input_2;
            }
        });
        operation_calculation_map.put(CalculationOperation.DIVIDE, new CalculatorAction() {
            @Override
            public double calculate(double input_1, double input_2) {
                return input_1 / input_2;
            }
        });
    }

    public void on_number(View view) {
        Log.d(a_calculator.CALCULATOR_LOG_TAG, "received number");
        String input = get_symbol_from_button((Button) view);

        if (last_input_was_operator) {
            clear_input_output_textview();
            last_number += input;
            Log.d(a_calculator.CALCULATOR_LOG_TAG, "Adding " + input + " to last_number.");
        } else {
            if (last_number.equals("")) {
                first_number += input;
                Log.d(a_calculator.CALCULATOR_LOG_TAG, "Adding " + input + " to first_number.");
            } else {
                last_number += input;
                Log.d(a_calculator.CALCULATOR_LOG_TAG, "Adding " + input + " to last_number.");
            }
        }

        add_symbol_to_input_output_textview(input);
        Log.d(a_calculator.CALCULATOR_LOG_TAG, "Adding " + input + " to input_output_textview.");
        last_input_was_operator = false;
        Log.d(a_calculator.CALCULATOR_LOG_TAG, "last_input_was_operator: " + last_input_was_operator);
    }

    public void on_decimal_dot(View view) {
        Log.d(a_calculator.CALCULATOR_LOG_TAG, "received decimal dot");
        if (!last_input_was_operator) {
            if (!current_text.contains(".")) {
                add_symbol_to_input_output_textview(".");
            }
        }
    }

    public void on_operation(View view) {
        Log.d(a_calculator.CALCULATOR_LOG_TAG, "received operation");
        if (!first_number.equals("")) {
            String operation_string = get_symbol_from_button((Button) view);
            try {
                last_operation = CalculationOperation.get_operation_by_string(operation_string);
                last_input_was_operator = true;
                clear_input_output_textview();
            } catch (UnknownCalculationOperationException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void on_clear(View view) {
        Log.d(a_calculator.CALCULATOR_LOG_TAG, "received clear command");
        reset();
    }

    private void reset() {
        first_number = "";
        last_number = "";
        last_operation = null;
        last_input_was_operator = false;
        current_text = "0";
        clear_input_output_textview();
        input_output_textview.setText("0");
    }

    public void on_equals(View view) {
        Log.d(a_calculator.CALCULATOR_LOG_TAG, "received equals");

        Log.d(a_calculator.CALCULATOR_LOG_TAG, "first number is " + first_number);
        Log.d(a_calculator.CALCULATOR_LOG_TAG, "last operation is " + last_operation);
        Log.d(a_calculator.CALCULATOR_LOG_TAG, "last number is " + last_number);

        if(!first_number.equals("") && !last_number.equals("") && last_operation != null) {
            double result = calculate();
            equations.add(first_number + " " + last_operation.toString() + " " + last_number + " = " + result);
            reset();
            first_number = Double.toString(result);
            input_output_textview.setText(Double.toString(result));
        }
        for(String equation : equations) {
            Log.d(a_calculator.CALCULATOR_LOG_TAG, equation);
        }
    }

    public void on_submit(View view) {
        if (!equations.isEmpty()) {
            Intent response_intent = new Intent();
            String[] list = new String[equations.size()];
            response_intent.putExtra(a_calculator.EQUATIONS_EXTRA_STRING, equations.toArray(list));
            setResult(RESULT_OK, response_intent);
            finish();
        } else {
            Intent response_intent = new Intent();
            setResult(RESULT_OK, response_intent);
            finish();
        }
    }

    private double calculate() {
        Log.d(a_calculator.CALCULATOR_LOG_TAG, "calculating result");
        double result = 0;
        try {
            result = operation_calculation_map.get(last_operation).calculate(Double.parseDouble(first_number), Double.parseDouble(last_number));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        } finally {
            return result;
        }
    }

    private String get_symbol_from_button(Button button) {
        return button.getText().toString();
    }

    private void clear_input_output_textview() {
        Log.d(a_calculator.CALCULATOR_LOG_TAG, "clearing");
        input_output_textview.setText("");
        current_text = "";
    }

    private void add_symbol_to_input_output_textview(String symbol) {
        Log.d(a_calculator.CALCULATOR_LOG_TAG, "adding symbol");
        if(current_text.equals("0")) {
            current_text = symbol;
        } else {
            current_text += symbol;
        }
        input_output_textview.setText(current_text);
    }

}
