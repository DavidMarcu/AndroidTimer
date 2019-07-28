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
    private final int HOUR_IN_SECONDS = 3600;
    private final int MINUTE_IN_SECONDS = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stopwatchPresenter = new StopwatchPresenter(this);
        stopwatchTextView = findViewById(R.id.stopwatch_text_view);

        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(button -> {
            stopwatchPresenter.updateRunning(true);
        });
        Button stopButton = findViewById(R.id.stop_button);
        stopButton.setOnClickListener(button -> {
            stopwatchPresenter.updateRunning(false);
        });

        final Handler timerHandler = new Handler();
        timerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopwatchPresenter.updateSeconds(stopwatchPresenter.getStopwatchTime() + 1);
                timerHandler.postDelayed(this, 1000);
            }
        }, 1000);
    }


    @Override
    public void updateStopwatchTimer(int seconds) {
        int hours = seconds / HOUR_IN_SECONDS;
        int minutes = (seconds % 3600) / MINUTE_IN_SECONDS;
        String formattedTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds % 60);
        stopwatchTextView.setText(formattedTime);
    }

    @Override
    public void startStopwatch() {

    }

    @Override
    public void stopStopwatch() {

    }
}
