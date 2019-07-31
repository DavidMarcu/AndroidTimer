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
                timerHandler.postDelayed(this, 1);
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
        currentState.putLong("miliseconds", stopwatchModel.getMiliseconds());
        currentState.putBoolean("running", stopwatchModel.isRunning());
    }

    public void restoreState(Bundle savedState) {
        stopwatchModel.setMiliseconds(savedState.getLong("miliseconds"));
        stopwatchModel.setRunning(savedState.getBoolean("running"));
        if(stopwatchModel.isRunning()){
            startStopwatch();
        }
        this.stopwatchView.updateStopwatchTimer(stopwatchModel.getMiliseconds());
    }

    private long getStopwatchTime(){
        return stopwatchModel.getMiliseconds();
    }

    private void updateTime(long miliseconds){
        stopwatchModel.setMiliseconds(miliseconds);
        stopwatchView.updateStopwatchTimer(miliseconds);
    }

    private void startStopwatch() {
        timerHandler.postDelayed(timerRunnable, 1);
    }

    private void pauseStopwatch() {
        timerHandler.removeCallbacks(timerRunnable);
    }

    public interface StopwatchView{
        void updateStopwatchTimer(long miliseconds);
    }
}
