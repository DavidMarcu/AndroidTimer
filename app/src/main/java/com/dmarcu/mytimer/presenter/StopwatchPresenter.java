package com.dmarcu.mytimer.presenter;

import com.dmarcu.mytimer.model.Stopwatch;

public class StopwatchPresenter {

    private Stopwatch stopwatchModel;
    private StopwatchView stopwatchView;

    public StopwatchPresenter(StopwatchView stopwatchView){
        stopwatchModel = new Stopwatch();
        this.stopwatchView = stopwatchView;
    }

    public int getStopwatchTime(){
        return stopwatchModel.getSeconds();
    }

    public void updateSeconds(int seconds){
        stopwatchModel.setSeconds(seconds);
        stopwatchView.updateStopwatchTimer(seconds);
    }

    public void updateRunning(boolean running){
        stopwatchModel.setRunning(running);
        if(running){
            stopwatchView.startStopwatch();
        } else{
            stopwatchView.stopStopwatch();
        }
    }

    public interface StopwatchView{
        void updateStopwatchTimer(int seconds);
        void startStopwatch();
        void stopStopwatch();
    }
}
