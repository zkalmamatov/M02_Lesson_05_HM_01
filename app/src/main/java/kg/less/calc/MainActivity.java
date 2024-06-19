package kg.less.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String currentNumber = "";
    private double num1 = Double.NaN;
    private double num2 = Double.NaN;
    private char operator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.text_view);

        setupButtonClickListener(R.id.button0, "0");
        setupButtonClickListener(R.id.button1, "1");
        setupButtonClickListener(R.id.button2, "2");
        setupButtonClickListener(R.id.button3, "3");
        setupButtonClickListener(R.id.button4, "4");
        setupButtonClickListener(R.id.button5, "5");
        setupButtonClickListener(R.id.button6, "6");
        setupButtonClickListener(R.id.button7, "7");
        setupButtonClickListener(R.id.button8, "8");
        setupButtonClickListener(R.id.button9, "9");
        setupButtonClickListener(R.id.buttonClear, "AC");

        findViewById(R.id.buttonDot).setOnClickListener(v -> handleDot());
        findViewById(R.id.buttonSign).setOnClickListener(v -> changeSign());
        findViewById(R.id.buttonPer).setOnClickListener(v -> calculatePerc());


        findViewById(R.id.buttonAdd).setOnClickListener(v -> setOperator('+'));
        findViewById(R.id.buttonSub).setOnClickListener(v -> setOperator('-'));
        findViewById(R.id.buttonX).setOnClickListener(v -> setOperator('*'));
        findViewById(R.id.buttonDiv).setOnClickListener(v -> setOperator('/'));
        findViewById(R.id.buttonEqual).setOnClickListener(v -> calculateResult());

    }

    private void setupButtonClickListener(int buttonId, String text) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            if (text.equals("AC")) {
                clear();
            } else {
                appendNumber(text);
            }
        });
    }


    private void handleDot() {
        if (!currentNumber.contains(".")) {
            currentNumber += ".";
            resultTextView.setText(currentNumber);
        }
    }

    private void changeSign() {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            number = -number;
            currentNumber = String.valueOf(number);
            resultTextView.setText(currentNumber);
        }
    }


    private void calculatePerc() {
        if (!Double.isNaN(num1)) {
            double number = Double.parseDouble(currentNumber);
            number = num1 * (number / 100.0);
            currentNumber = String.valueOf(number);
            resultTextView.setText(currentNumber);
        }
    }

    private void appendNumber(String num) {
        currentNumber += num;
        resultTextView.setText(currentNumber);
    }

    private void setOperator(char op) {
        if (!Double.isNaN(num1)) {
            num2 = Double.parseDouble(currentNumber);
            switch (operator) {
                case '+':
                    num1 += num2;
                    break;
                case '-':
                    num1 -= num2;
                    break;
                case '*':
                    num1 *= num2;
                    break;
                case '/':
                    num1 /= num2;
                    break;
                default:
                    break;
            }
            operator = op;
            currentNumber = "";
            resultTextView.setText(String.valueOf(num1));
        } else {
            num1 = Double.parseDouble(currentNumber);
            operator = op;
            currentNumber = "";
        }
    }


    private void calculateResult() {
        if (!Double.isNaN(num1)) {
            num2 = Double.parseDouble(currentNumber);
            switch (operator) {
                case '+':
                    num1 += num2;
                    break;
                case '-':
                    num1 -= num2;
                    break;
                case '*':
                    num1 *= num2;
                    break;
                case '/':
                    num1 /= num2;
                    break;
                default:
                    break;
            }
            resultTextView.setText(String.valueOf(num1));
            num2 = Double.NaN;
            num1 = Double.NaN;
        }
    }


    private void clear() {
        currentNumber = "";
        num1 = Double.NaN;
        num2 = Double.NaN;
        operator = ' ';
        resultTextView.setText("0");
    }

//    public void oneClick(View view) {
//        String textButton = ((MaterialButton) view).getText().toString();
//        if (textButton.equals("AC")) {
//            textView.setText("0");
//        } else if (textView.getText().toString().equals("0")) {
//            textView.setText(textButton);
//        } else {
//            textView.append(textButton);
//        }
//
//    }
}