package com.dmarcu.mytimer.model;

public class Stopwatch {
    private long timeElapsed;
    private boolean running;

    public Stopwatch(){
        this(0L, false);
    }

    public Stopwatch(long timeElapsed){
        this(timeElapsed, false);
    }

    public Stopwatch(long timeElapsed, boolean running) {
        this.timeElapsed = timeElapsed;
        this.running = running;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
