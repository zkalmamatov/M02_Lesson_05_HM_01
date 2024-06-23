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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private boolean isLiked = false;
    private ImageButton imageButtonHeart;
    private Spinner spinnerOptions;
    private SeekBar seekBarMin;
    private SeekBar seekBarMax;
    private View line;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

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
                Toast.makeText(ResultActivity.this, "Выбрано: " + selectedOptions, Toast.LENGTH_LONG).show();
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


}




