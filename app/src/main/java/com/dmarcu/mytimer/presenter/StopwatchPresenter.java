package com.dmarcu.mytimer.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import com.dmarcu.mytimer.model.Stopwatch;

public class StopwatchPresenter {

    private Stopwatch stopwatchModel;
    private StopwatchView stopwatchView;
    private Runnable timerRunnable;
    private Handler timerHandler;
    private long pauseTimestamp;
    private boolean wasPaused;

    public StopwatchPresenter(StopwatchView stopwatchView){
        stopwatchModel = new Stopwatch();
        pauseTimestamp = stopwatchModel.getTimeElapsed();
        this.stopwatchView = stopwatchView;
        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                updateTime(SystemClock.elapsedRealtime());
                timerHandler.postDelayed(this, 10);
            }
        };
        wasPaused = false;
    }

    public void onStartPressed(){
        if(wasPaused){
            long pauseTime = SystemClock.elapsedRealtime() - pauseTimestamp;
            stopwatchModel.setTimeElapsed(stopwatchModel.getTimeElapsed() + pauseTime);
            wasPaused = false;
        }
        else {
            stopwatchModel.setTimeElapsed(SystemClock.elapsedRealtime());
        }
        stopwatchModel.setRunning(true);
        startStopwatch();
    }

    public void onPausePressed(){
        pauseTimestamp = SystemClock.elapsedRealtime();
        stopwatchModel.setRunning(false);
        pauseStopwatch();
        wasPaused = true;
    }

    public void onResetPressed(){
        stopwatchModel.setRunning(false);
        pauseStopwatch();
        resetTime();
        wasPaused = false;
    }

    public void saveState(Bundle currentState){
        currentState.putLong("timeElapsed", stopwatchModel.getTimeElapsed());
        currentState.putBoolean("running", stopwatchModel.isRunning());
    }

    public void restoreState(Bundle savedState) {
        stopwatchModel.setTimeElapsed(savedState.getLong("timeElapsed"));
        stopwatchModel.setRunning(savedState.getBoolean("running"));
        if(stopwatchModel.isRunning()){
            startStopwatch();
        }
        this.stopwatchView.updateStopwatchTimer(stopwatchModel.getTimeElapsed());
    }

    private void updateTime(long timeElapsed){
        long realTimeElapsed = timeElapsed - stopwatchModel.getTimeElapsed();
        stopwatchView.updateStopwatchTimer(realTimeElapsed);
    }

    private void resetTime() {
        stopwatchView.updateStopwatchTimer(0);
    }

    private void startStopwatch() {
        timerHandler.postDelayed(timerRunnable, 10);
    }

    private void pauseStopwatch() {
        timerHandler.removeCallbacks(timerRunnable);
    }

    public interface StopwatchView{
        void updateStopwatchTimer(long timeElapsed);
    }
}
