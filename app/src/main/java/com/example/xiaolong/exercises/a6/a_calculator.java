package com.example.xiaolong.exercises.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xiaolong.exercises.R;

import java.util.HashMap;

public class a_calculator extends Activity {

    public static final String EQUATION_EXTRA_STRING = "equation";

    private String current_text;
    private boolean last_input_was_operator = false;

    // the following three members are important for calculation of a result
    private CalculationOperation last_operation = CalculationOperation.PLUS;
    private String first_number = "";
    private String last_number = "";

    private HashMap<CalculationOperation, CalculatorAction> operation_calculation_map = new HashMap<>();

    private TextView input_output_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_calculator);

        fill_operation_action_map();

        input_output_textview = (TextView) findViewById(R.id.a5_calculator_textview);
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
        String input = get_symbol_from_button((Button) view);

        if (last_input_was_operator) {
            clear_input_output_textview();
            last_number += input;
        } else {
            first_number += input;
        }

        add_symbol_to_input_output_textview(input);
        last_input_was_operator = false;
    }

    public void on_decimal_dot(View view) {
        if (last_input_was_operator) {
            // do nothing
        } else {
            if (current_text.contains(".")) {
                // do nothing
            } else {
                add_symbol_to_input_output_textview(".");
            }
        }
    }

    public void on_operation(View view) {
        String operation_string = get_symbol_from_button((Button) view);
        try {
            last_operation = CalculationOperation.get_operation_by_string(operation_string);
            last_input_was_operator = true;
            clear_input_output_textview();
        } catch (UnknownCalculationOperationException ex) {
            ex.printStackTrace();
        }
    }

    public void on_clear(View view) {
        first_number = "";
        last_number = "";
        last_operation = null;
        last_input_was_operator = false;
        current_text = "0";
        clear_input_output_textview();
        input_output_textview.setText("0");
    }

    public void on_equals(View view) {
        if (first_number != "" && last_number != "" && last_operation != null) {
            Intent response_intent = new Intent();
            String equation_string = first_number + last_operation.toString() + last_number + "=" + calculate();
            response_intent.putExtra(a_calculator.EQUATION_EXTRA_STRING, equation_string);
            setResult(RESULT_OK, response_intent);
            finish();
        }
    }

    private double calculate() {
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
        input_output_textview.setText("");
        current_text = "";
    }

    private void add_symbol_to_input_output_textview(String symbol) {
        current_text += symbol;
        input_output_textview.setText(current_text);
    }

}
