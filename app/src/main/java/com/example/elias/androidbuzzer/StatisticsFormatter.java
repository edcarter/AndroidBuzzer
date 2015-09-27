package com.example.elias.androidbuzzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by elias on 27/09/15.
 */
public class StatisticsFormatter {

    private StatisticsModel model;
    private ArrayList<String> statistics;

    public StatisticsFormatter(StatisticsModel model){
        this.model = model;
    }
    public ArrayList<String> formatStatistics(){
        if (statistics != null) return statistics;
        statistics = new ArrayList<>();
        statistics.addAll(formatSinglePlayerStats(model));
        statistics.addAll(formatTwoPlayerStats(model));
        statistics.addAll(formatThreePlayerStats(model));
        statistics.addAll(formatFourPlayerStats(model));
        return statistics;
    }

    private ArrayList<String> formatSinglePlayerStats(StatisticsModel model) {
        ArrayList<String> formattedStats = new ArrayList<>();
        formattedStats.addAll(getMinimumReactionTimes(model));
        formattedStats.addAll(getMaximumReactionTimes(model));
        formattedStats.addAll(getAverageReactionTimes(model));
        formattedStats.addAll(getMedianReactionTimes(model));
        return formattedStats;
    }

    private ArrayList<String> getMinimumReactionTimes(StatisticsModel model){
        ArrayList<String> formattedStats = new ArrayList<>();
        Stack<Integer> reactionTimes = model.singlePlayerReactionTimesMs;
        if (reactionTimes.size() == 0) return new ArrayList<>();
        Integer minimumReactionTime = reactionTimes.peek();
        for (int i = 0; i < reactionTimes.size(); i++){
            if (reactionTimes.elementAt(i) < minimumReactionTime) minimumReactionTime = reactionTimes.elementAt(i);
            if (i == 9){
                formattedStats.add("1 Player: Last 10 Tries Minimum Reaction Time: " + minimumReactionTime + " ms");
            } else if (i == 99){
                formattedStats.add("1 Player: Last 100 Tries Minimum Reaction Time: " + minimumReactionTime + " ms");
            }
        }
        if (reactionTimes.size() < 10){
            formattedStats.add("1 Player: Last 10 Tries Minimum Reaction Time: " + minimumReactionTime + " ms");
        }
        if (reactionTimes.size() < 100){
            formattedStats.add("1 Player: Last 100 Tries Minimum Reaction Time: " + minimumReactionTime + " ms");
        }
        formattedStats.add("1 Player: Global Minimum Reaction Time: " + minimumReactionTime + " ms");

        return formattedStats;
    }

    private ArrayList<String> getMaximumReactionTimes(StatisticsModel model){
        ArrayList<String> formattedStats = new ArrayList<>();
        Stack<Integer> reactionTimes = model.singlePlayerReactionTimesMs;
        if (reactionTimes.size() == 0) return new ArrayList<>();
        Integer maximumReactionTime = reactionTimes.peek();
        for (int i = 0; i < reactionTimes.size(); i++){
            if (reactionTimes.elementAt(i) > maximumReactionTime) maximumReactionTime = reactionTimes.elementAt(i);
            if (i == 9){
                formattedStats.add("1 Player: Last 10 Tries Maximum Reaction Time: " + maximumReactionTime + " ms");
            } else if (i == 99){
                formattedStats.add("1 Player: Last 100 Tries Maximum Reaction Time: " + maximumReactionTime + " ms");
            }
        }
        if (reactionTimes.size() < 10){
            formattedStats.add("1 Player: Last 10 Tries Maximum Reaction Time: " + maximumReactionTime + " ms");
        }
        if (reactionTimes.size() < 100){
            formattedStats.add("1 Player: Last 100 Tries Maximum Reaction Time: " + maximumReactionTime + " ms");
        }
        formattedStats.add("1 Player: Global Maximum Reaction Time: " + maximumReactionTime + " ms");

        return formattedStats;
    }

    private ArrayList<String> getAverageReactionTimes(StatisticsModel model){
        ArrayList<String> formattedStats = new ArrayList<>();
        Stack<Integer> reactionTimes = model.singlePlayerReactionTimesMs;
        if (reactionTimes.size() == 0) return new ArrayList<>();
        Integer accumulator = 0;
        for (int i = 0; i < reactionTimes.size(); i++){
            accumulator += reactionTimes.elementAt(i);
            if (i == 9){
                formattedStats.add("1 Player: Last 10 Tries Average Reaction Time: " + accumulator/i + " ms");
            } else if (i == 99){
                formattedStats.add("1 Player: Last 100 Tries Average Reaction Time: " + accumulator/i + " ms");
            }
        }
        if (reactionTimes.size() < 10){
            formattedStats.add("1 Player: Last 10 Tries Average Reaction Time: " + accumulator/reactionTimes.size() + " ms");
        }
        if (reactionTimes.size() < 100){
            formattedStats.add("1 Player: Last 100 Tries Average Reaction Time: " + accumulator/reactionTimes.size() + " ms");
        }
        formattedStats.add("1 Player: Global Average Reaction Time: " + accumulator/reactionTimes.size() + " ms");

        return formattedStats;

    }

    private ArrayList<String> getMedianReactionTimes(StatisticsModel model){
        ArrayList<String> formattedStats = new ArrayList<>();
        Stack<Integer> reactionTimes = model.singlePlayerReactionTimesMs;
        if (reactionTimes.size() == 0) return new ArrayList<>();
        ArrayList<Integer> reactionTimesList = new ArrayList<>(reactionTimes);
        Collections.sort(reactionTimesList);
        for (int i = 0; i < reactionTimes.size(); i++){
            if (i == 9){
                formattedStats.add("1 Player: Last 10 Tries Median Reaction Time: " + (reactionTimesList.get(5) + reactionTimesList.get(6))/2 + " ms");
            } else if (i == 99){
                formattedStats.add("1 Player: Last 100 Tries Median Reaction Time: " + (reactionTimesList.get(50) + reactionTimesList.get(51))/ 2 + " ms");
            }
        }
        if (reactionTimes.size() < 10){
            formattedStats.add("1 Player: Last 10 Tries Median Reaction Time: " + reactionTimesList.get(reactionTimes.size() / 2) + " ms");
        }
        if (reactionTimes.size() < 100){
            formattedStats.add("1 Player: Last 100 Tries Median Reaction Time: " + reactionTimesList.get(reactionTimes.size() / 2) + " ms");
        }
        formattedStats.add("1 Player: Global Median Reaction Time: " + reactionTimesList.get(reactionTimes.size() / 2) + " ms");

        return formattedStats;

    }

    private ArrayList<String> formatTwoPlayerStats(StatisticsModel model) {
        Stack<Integer> firstPlayerReaction = model.twoPlayerFirstBuzzer;
        if (firstPlayerReaction.size() == 0) return new ArrayList<>();
        Integer playerOneReactedFirst = 0;
        Integer playerTwoReactedFirst = 0;
        for (Integer firstPlayer : firstPlayerReaction) {
            if (firstPlayer == 0) {
                playerOneReactedFirst++;
            } else {
                playerTwoReactedFirst++;
            }
        }
        ArrayList<String> formattedString = new ArrayList<>();
        formattedString.add("2 Player: Player 1 Buzzed First: " + playerOneReactedFirst + " times");
        formattedString.add("2 Player: Player 2 Buzzed First: " + playerTwoReactedFirst + " times");
        return formattedString;
    }

    private ArrayList<String> formatThreePlayerStats(StatisticsModel model) {
        Stack<Integer> firstPlayerReaction = model.threePlayerFirstBuzzer;
        if (firstPlayerReaction.size() == 0) return new ArrayList<>();
        Integer playerOneReactedFirst = 0;
        Integer playerTwoReactedFirst = 0;
        Integer playerThreeReactedFirst = 0;
        for (Integer firstPlayer : firstPlayerReaction) {
            if (firstPlayer == 0) {
                playerOneReactedFirst++;
            } else if (firstPlayer == 1) {
                playerTwoReactedFirst++;
            } else {
                playerThreeReactedFirst++;
            }
        }
        ArrayList<String> formattedString = new ArrayList<>();
        formattedString.add("3 Player: Player 1 Buzzed First: " + playerOneReactedFirst + " times");
        formattedString.add("3 Player: Player 2 Buzzed First: " + playerTwoReactedFirst + " times");
        formattedString.add("3 Player: Player 3 Buzzed First: " + playerThreeReactedFirst + " times");
        return formattedString;
    }

    private ArrayList<String> formatFourPlayerStats(StatisticsModel model) {
        Stack<Integer> firstPlayerReaction = model.fourPlayerFirstBuzzer;
        if (firstPlayerReaction.size() == 0) return new ArrayList<>();
        Integer playerOneReactedFirst = 0;
        Integer playerTwoReactedFirst = 0;
        Integer playerThreeReactedFirst = 0;
        Integer playerFourReactedFirst = 0;
        for (Integer firstPlayer : firstPlayerReaction) {
            if (firstPlayer == 0) {
                playerOneReactedFirst++;
            } else if (firstPlayer == 1) {
                playerTwoReactedFirst++;
            } else if (firstPlayer == 2) {
                playerThreeReactedFirst++;
            } else {
                playerFourReactedFirst++;
            }
        }
        ArrayList<String> formattedString = new ArrayList<>();
        formattedString.add("4 Player: Player 1 Buzzed First: " + playerOneReactedFirst + " times");
        formattedString.add("4 Player: Player 2 Buzzed First: " + playerTwoReactedFirst + " times");
        formattedString.add("4 Player: Player 3 Buzzed First: " + playerThreeReactedFirst + " times");
        formattedString.add("4 Player: Player 4 Buzzed First: " + playerFourReactedFirst + " times");
        return formattedString;
    }
}
