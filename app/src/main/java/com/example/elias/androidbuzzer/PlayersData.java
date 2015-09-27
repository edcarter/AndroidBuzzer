package com.example.elias.androidbuzzer;

import java.util.ArrayList;

public class PlayersData {
    private ArrayList<Integer> reactionData;

    public PlayersData(Integer numPlayers){
        reactionData = new ArrayList<Integer>(numPlayers);
    }

    public void setReactionTimeMS(Integer playerNum, Integer reactionTimeMS){
        reactionData.set(playerNum, reactionTimeMS);
    }

    public void getReactionTimeMS(Integer playerNum){
        reactionData.get(playerNum);
    }
}
