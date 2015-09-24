package com.example.elias.androidbuzzer;

import android.app.Activity;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by elias on 13/09/15.
 */
public class ReactionTimer {

    private Timer timer;
    private Random rand = new Random();

    public ReactionTimer(){
        timer = new Timer();
    }

    public void Start(final Callable reactionTriggeredCallback, final Activity activity) throws Exception {
        final int minWaitTime = GetMinWaitTimeMs();
        final int maxWaitTime = GetMaxWaitTimeMs();

        //yo dawg, I heard you like inner classes
        //This is used to run a task in the background and then dispatch to the main UI thread
        Runnable r = new Runnable() {
            public void run() {
                WaitForIndeterminateTime(minWaitTime, maxWaitTime);
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                reactionTriggeredCallback.call();
                            } catch (Exception ex){
                                throw new RuntimeException(ex);
                            }
                        }
                    });
            }
        };
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(r);
        timer.Start();
    }

    public void Stop() {
        timer.Stop();
    }

    public int GetReactionTimeMs(){
        long reactionTimeNs = timer.GetDurationNs();
        long msPerNs = 1000000;
        return (int)(reactionTimeNs / msPerNs);
    }

    //Return the minimum time to wait before triggering the reaction
    //Overload this to change the minimum wait time
    private int GetMinWaitTimeMs(){
        return 20;
    }

    //Return the maximum time to wait before triggering the reaction
    //Overload this to change the maximum wait time
    private int GetMaxWaitTimeMs(){
        return 2000;
    }

    private void WaitForIndeterminateTime(int minWaitMs, int maxWaitMs){
        Integer randomSleepTime = randInt(minWaitMs, maxWaitMs);
        try {
            TimeUnit.MILLISECONDS.sleep(randomSleepTime);
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     * Greg Case, http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
     */
    private int randInt(int min, int max) {


        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
