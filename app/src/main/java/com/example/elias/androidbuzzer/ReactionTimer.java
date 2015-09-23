package com.example.elias.androidbuzzer;

import java.util.concurrent.Callable;

/**
 * Created by elias on 13/09/15.
 */
public class ReactionTimer {

    private Timer timer;

    public ReactionTimer(){
        timer = new Timer();
    }

    public void Start(Callable reactionTriggeredCallback) throws Exception {
        int minWaitTime = GetMinWaitTimeMs();
        int maxWaitTime = GetMaxWaitTimeMs();
        WaitForIndeterminateTime(minWaitTime, maxWaitTime);
        timer.Start();
        reactionTriggeredCallback.call();
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

    }
}
