package com.example.elias.androidbuzzer;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

/**
 * Created by elias on 26/09/15.
 */
public class StatisticsEngine {

    private static String savePath = "ReactionStatistics";

    public static void AddReactionStatistic(Integer ReactionTimeMS, Context context) {
        StatisticsModel model = loadStatisticsModel(context);
        model.singlePlayerReactionTimesMs.push(ReactionTimeMS);
        saveStatisticsModel(model, context);
    }

    public static void AddBuzzerStatistic(Integer playerCount, Integer playerBuzzed, Context context){
        StatisticsModel model = loadStatisticsModel(context);
        switch (playerCount){
            case 2:
                model.twoPlayerFirstBuzzer.push(playerBuzzed);
                break;
            case 3:
                model.threePlayerFirstBuzzer.push(playerBuzzed);
                break;
            case 4:
                model.fourPlayerFirstBuzzer.push(playerBuzzed);
                break;
            default:
                throw new RuntimeException("Statistics Engine Error: wrong number of players");
        }
        saveStatisticsModel(model, context);
    }


    public static void ClearStatisticsModel(Context context){
        saveStatisticsModel(new StatisticsModel(), context);
    }

    public static StatisticsModel GetStatisticsModel(Context context){
        return loadStatisticsModel(context);
    }

    private static StatisticsModel loadStatisticsModel(Context context) {
        StatisticsModel model;
        try {
            FileInputStream fis = context.openFileInput(savePath);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<StatisticsModel>() {
            }.getType();
            model = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            model = new StatisticsModel();
        }
        return model;
    }

    private static void saveStatisticsModel(StatisticsModel model, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(savePath, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(model, writer);
            writer.flush();
            fos.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
