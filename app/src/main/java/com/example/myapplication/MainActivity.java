package com.example.parthcalculatordemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result;
    String current = "";
    String operator = "";
    double num1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);

        int[] ids = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide,
                R.id.btnEquals, R.id.btnDot, R.id.btnClear, R.id.btnBack
        };

        for (int id : ids) {
            findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        String text = b.getText().toString();

        switch (text) {
            case "C":
                current = "";
                operator = "";
                num1 = 0;
                result.setText("0");
                break;
            case "⌫":
                if (!current.isEmpty()) current = current.substring(0, current.length() - 1);
                result.setText(current.isEmpty() ? "0" : current);
                break;
            case "+": case "-": case "×": case "/":
                if (!current.isEmpty()) {
                    num1 = Double.parseDouble(current);
                    operator = text;
                    current = "";
                }
                break;
            case "=":
                if (!current.isEmpty() && !operator.isEmpty()) {
                    double num2 = Double.parseDouble(current);
                    double ans = 0;
                    switch (operator) {
                        case "+": ans = num1 + num2; break;
                        case "-": ans = num1 - num2; break;
                        case "×": ans = num1 * num2; break;
                        case "/":
                            if (num2 == 0) {
                                Toast.makeText(this, "Cannot divide by 0", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            ans = num1 / num2; break;
                    }
                    result.setText(String.valueOf(ans));
                    current = String.valueOf(ans);
                    operator = "";
                }
                break;
            default:
                current += text;
                result.setText(current);
                break;
        }
    }
}
