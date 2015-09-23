package com.example.elias.androidbuzzer;

/**
 * Created by elias on 13/09/15.
 */
public class Timer {

    private long startTime;
    private long stopTime;
    private boolean running;

    public Timer(){
        startTime = 0L;
        stopTime = 0L;
        running = false;
    }

    public void Start(){
        startTime = System.nanoTime();
        running = true;
    }

    public void Stop(){
        stopTime = System.nanoTime();
        running = false;
    }

    public long GetDurationNs(){
        if (IsRunning()){
            return System.nanoTime() - startTime;
        } else {
            return stopTime - startTime;
        }
    }

    private boolean IsRunning(){
        return running;
    }
}
