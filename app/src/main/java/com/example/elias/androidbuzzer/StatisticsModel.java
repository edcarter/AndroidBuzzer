package com.example.elias.androidbuzzer;

import java.util.Stack;

/**
 * Created by elias on 26/09/15.
 */
public class StatisticsModel {

    public StatisticsModel(){
        singlePlayerReactionTimesMs = new Stack<>();
        twoPlayerFirstBuzzer = new Stack<>();
        threePlayerFirstBuzzer = new Stack<>();
        fourPlayerFirstBuzzer = new Stack<>();
    }

    //TODO(revamp model? what if you could have more than 4 players?)
    public Stack<Integer> singlePlayerReactionTimesMs;
    public Stack<Integer> twoPlayerFirstBuzzer;
    public Stack<Integer> threePlayerFirstBuzzer;
    public Stack<Integer> fourPlayerFirstBuzzer;
}

