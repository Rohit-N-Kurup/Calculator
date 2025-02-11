package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView inputDisplay;
    private StringBuilder input;
    private double num1, num2;
    private char operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputDisplay = findViewById(R.id.inputDisplay);
        input = new StringBuilder();

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(v -> {
                input.append(((Button) v).getText().toString());
                inputDisplay.setText(input.toString());
            });
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorButtonIds = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};
        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(v -> {
                if (input.length() > 0) {
                    num1 = Double.parseDouble(input.toString());
                    operator = ((Button) v).getText().toString().charAt(0);
                    input.setLength(0);
                    inputDisplay.setText(String.valueOf(operator));
                }
            });
        }

        findViewById(R.id.btnEquals).setOnClickListener(v -> {
            if (input.length() > 0) {
                num2 = Double.parseDouble(input.toString());
                double result = 0;
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': result = num2 != 0 ? num1 / num2 : 0; break;
                }
                inputDisplay.setText("Result: " + result);
                input.setLength(0);
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(v -> {
            input.setLength(0);
            inputDisplay.setText("");
        });
    }
}
