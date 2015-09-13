package com.example.elias.androidbuzzer;

import java.util.concurrent.Callable;

/**
 * Created by elias on 13/09/15.
 */
public class ReactionTimer {

    public void Start(Callable reactionTriggeredCallback) throws Exception {
        int minWaitTime = GetMinWaitTimeMs();
        int maxWaitTime = GetMaxWaitTimeMs();
        WaitForIndeterminateTime(minWaitTime, maxWaitTime);


        reactionTriggeredCallback.call();
    }

    public void Stop() {

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
