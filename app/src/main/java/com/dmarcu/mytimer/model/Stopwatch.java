package com.dmarcu.mytimer.model;

public class Stopwatch {
    private long miliseconds;
    private boolean running;

    public Stopwatch(){
        miliseconds = 0L;
        running = false;
    }

    public Stopwatch(long miliseconds, boolean running) {
        this.miliseconds = miliseconds;
        this.running = running;
    }

    public long getMiliseconds() {
        return miliseconds;
    }

    public void setMiliseconds(long miliseconds) {
        this.miliseconds = miliseconds;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
