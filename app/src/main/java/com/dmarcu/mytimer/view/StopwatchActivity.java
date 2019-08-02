package com.dmarcu.mytimer.view;

import android.os.Bundle;
import android.util.Log;
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
        stopwatchTextView = findViewById(R.id.stopwatch_text_view);
        stopwatchPresenter = new StopwatchPresenter(this);
        if(savedInstanceState != null){
            stopwatchPresenter.restoreState(savedInstanceState);
        }

        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(button -> stopwatchPresenter.onStartPressed());
        Button pauseButton = findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(button -> stopwatchPresenter.onPausePressed());
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(button -> stopwatchPresenter.onResetPressed());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        stopwatchPresenter.saveState(outState);
    }

    @Override
    public void updateStopwatchTimer(long miliseconds) {
        long HOUR_IN_MILISECONDS = 3600000;
        long hours = miliseconds / HOUR_IN_MILISECONDS;

        int MINUTE_IN_MILISECONDS = 60000;
        long minutes = (miliseconds % HOUR_IN_MILISECONDS) / MINUTE_IN_MILISECONDS;

        int SECONDS_IN_MILISECONDS = 1000;
        long seconds = (miliseconds % MINUTE_IN_MILISECONDS) / SECONDS_IN_MILISECONDS;
        Log.d("TIME", hours + " " + minutes + " " + seconds + " " + miliseconds);
        String formattedTime = String.format(Locale.getDefault(), "%02d:%02d:%02d.%03d", hours, minutes, seconds, miliseconds % 1000);
        stopwatchTextView.setText(formattedTime);
    }
}
