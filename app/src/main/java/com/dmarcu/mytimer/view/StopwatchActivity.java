package com.dmarcu.mytimer.view;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.dmarcu.mytimer.R;
import com.dmarcu.mytimer.presenter.StopwatchPresenter;

import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity implements StopwatchPresenter.StopwatchView {

    private StopwatchPresenter stopwatchPresenter;
    private TextView stopwatchTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stopwatchPresenter = new StopwatchPresenter(this);
        stopwatchTextView = findViewById(R.id.stopwatch_text_view);

        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(button -> stopwatchPresenter.onStartPressed());
        Button pauseButton = findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(button -> stopwatchPresenter.onPausePressed());
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(button -> stopwatchPresenter.onResetPressed());
    }


    @Override
    public void updateStopwatchTimer(int seconds) {
        int HOUR_IN_SECONDS = 3600;
        int hours = seconds / HOUR_IN_SECONDS;

        int MINUTE_IN_SECONDS = 60;
        int minutes = (seconds % 3600) / MINUTE_IN_SECONDS;
        String formattedTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds % 60);
        stopwatchTextView.setText(formattedTime);
    }
}
