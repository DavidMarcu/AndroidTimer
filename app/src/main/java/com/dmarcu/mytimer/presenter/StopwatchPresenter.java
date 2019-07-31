package com.dmarcu.mytimer.presenter;

import android.os.Bundle;
import android.os.Handler;
import com.dmarcu.mytimer.model.Stopwatch;

public class StopwatchPresenter {

    private Stopwatch stopwatchModel;
    private StopwatchView stopwatchView;
    private Runnable timerRunnable;
    private Handler timerHandler;

    public StopwatchPresenter(StopwatchView stopwatchView){
        stopwatchModel = new Stopwatch();
        this.stopwatchView = stopwatchView;
        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                updateTime(getStopwatchTime() + 1);
                timerHandler.postDelayed(this, 1000);
            }
        };
    }

    public void onStartPressed(){
        stopwatchModel.setRunning(true);
        startStopwatch();
    }

    public void onPausePressed(){
        stopwatchModel.setRunning(false);
        pauseStopwatch();
    }

    public void onResetPressed(){
        stopwatchModel.setRunning(false);
        pauseStopwatch();
        updateTime(0);
    }

    public void saveState(Bundle currentState){
        currentState.putInt("seconds", stopwatchModel.getSeconds());
        currentState.putBoolean("running", stopwatchModel.isRunning());
    }

    public void restoreState(Bundle savedState) {
        stopwatchModel.setSeconds(savedState.getInt("seconds"));
        stopwatchModel.setRunning(savedState.getBoolean("running"));
        if(stopwatchModel.isRunning()){
            startStopwatch();
        }
        this.stopwatchView.updateStopwatchTimer(stopwatchModel.getSeconds());
    }

    private int getStopwatchTime(){
        return stopwatchModel.getSeconds();
    }

    private void updateTime(int seconds){
        stopwatchModel.setSeconds(seconds);
        stopwatchView.updateStopwatchTimer(seconds);
    }

    private void startStopwatch() {
        timerHandler.postDelayed(timerRunnable, 1000);
    }

    private void pauseStopwatch() {
        timerHandler.removeCallbacks(timerRunnable);
    }

    public interface StopwatchView{
        void updateStopwatchTimer(int seconds);
    }
}
