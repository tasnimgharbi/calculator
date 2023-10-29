package tn.mpdam.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
    }

    public void onDigitClick(View view) {
        Button button = (Button) view;
        currentInput += button.getText().toString();
        editText.append(button.getText().toString());
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        if (!currentInput.isEmpty() && operator.isEmpty()) {
            operator = button.getText().toString();
            firstOperand = Double.parseDouble(currentInput);
            editText.append(operator);
            currentInput = "";
        }
    }

    public void onDotClick(View view) {
        if (!currentInput.contains(".")) {
            currentInput += ".";
            editText.append(".");
        }
    }

    public void onEqualsClick(View view) {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        editText.setText("Error");
                        return;
                    }
                    break;
            }
            if (result == (int) result) {
                editText.setText(String.valueOf((int) result));
            } else {
                editText.setText(String.valueOf(result));
            }
            currentInput = String.valueOf(result);
            firstOperand = result; //pour faire plus de calcul
            operator = "";
        }
    }

    public void onResetClick(View view) {
        currentInput = "";
        operator = "";
        firstOperand = 0;
        editText.setText("");
    }

    /**public void onDeleteClick(View view) {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            editText.setText(currentInput);

        }
    }        this function didnt work xD  **/
}
