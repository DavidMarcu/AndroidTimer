package com.dmarcu.mytimer.model;

public class Stopwatch {
    private int seconds;
    private boolean running;

    public Stopwatch(){
        seconds = 0;
        running = false;
    }

    public Stopwatch(int seconds, boolean running) {
        this.seconds = seconds;
        this.running = running;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
