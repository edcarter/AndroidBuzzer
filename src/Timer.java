/*
Copyright 2015 Elias Carter

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.example.elias.androidbuzzer;

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
