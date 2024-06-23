package kg.less.calc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

    private boolean isLiked = false;
    private ImageButton imageButtonHeart;

    private Spinner spinnerOptions;

    private SeekBar seekBarMin;
    private SeekBar seekBarMax;
    private View line;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_result);





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


        imageButtonHeart = findViewById(R.id.heart);

        imageButtonHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLiked) {
                    imageButtonHeart.setImageResource(R.drawable.heart_line);
                } else {
                    imageButtonHeart.setImageResource(R.drawable.heart_filled);
                }
                isLiked = !isLiked;
            }
        });


        Button nextButton = findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        spinnerOptions = findViewById(R.id.spiner_options);

        spinnerOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedOptions = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Выбрано: " + selectedOptions, Toast.LENGTH_LONG).show();
            }

        });


        seekBarMin = findViewById(R.id.seekbar_min);
        seekBarMax = findViewById(R.id.seekbar_max);
        line = findViewById(R.id.line);

        seekBarMin.setProgress(20);
        seekBarMax.setProgress(80);

        seekBarMin.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarMax.setOnSeekBarChangeListener(seekBarChangeListener);


    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (seekBar.getId() == R.id.seekbar_min) {
                updateLinePosition();
            } else if (seekBar.getId() == R.id.seekbar_max) {
                updateLinePosition();
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void updateLinePosition() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) line.getLayoutParams();
        layoutParams.leftMargin = seekBarMin.getThumb().getBounds().centerX();
        layoutParams.rightMargin = seekBarMax.getWidth() - seekBarMax.getThumb().getBounds().centerX();
        line.setLayoutParams(layoutParams);
    }

public void goToNextActivity (View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
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


    public MainActivity(ImageButton imageButtonHeart) {
        this.imageButtonHeart = imageButtonHeart;
    }


}