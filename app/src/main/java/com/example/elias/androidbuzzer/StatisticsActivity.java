package com.example.elias.androidbuzzer;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Stack;

public class StatisticsActivity extends AppCompatActivity {

    private StatisticsModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        model = StatisticsEngine.GetStatisticsModel(this);
        populateListView(model);
    }

    private void populateListView(StatisticsModel model) {
        ListView listView = (ListView) findViewById(R.id.statistics_list_view);
        ArrayList<String> statistics = new ArrayList<>();
        statistics.addAll(formatSinglePlayerStats(model));
        statistics.addAll(formatTwoPlayerStats(model));
        statistics.addAll(formatThreePlayerStats(model));
        statistics.addAll(formatFourPlayerStats(model));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, statistics);
        listView.setAdapter(arrayAdapter);
    }

    private ArrayList<String> formatSinglePlayerStats(StatisticsModel model) {
        return new ArrayList<>();
    }

    private ArrayList<String> formatTwoPlayerStats(StatisticsModel model) {
        Stack<Integer> firstPlayerReaction = model.twoPlayerFirstBuzzer;
        Integer playerOneReactedFirst = 0;
        Integer playerTwoReactedFirst = 0;
        for (Integer firstPlayer : firstPlayerReaction){
            if (firstPlayer == 0){
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
        Stack<Integer> firstPlayerReaction = model.twoPlayerFirstBuzzer;
        Integer playerOneReactedFirst = 0;
        Integer playerTwoReactedFirst = 0;
        Integer playerThreeReactedFirst = 0;
        for (Integer firstPlayer : firstPlayerReaction){
            if (firstPlayer == 0){
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
        Stack<Integer> firstPlayerReaction = model.twoPlayerFirstBuzzer;
        Integer playerOneReactedFirst = 0;
        Integer playerTwoReactedFirst = 0;
        Integer playerThreeReactedFirst = 0;
        Integer playerFourReactedFirst = 0;
        for (Integer firstPlayer : firstPlayerReaction){
            if (firstPlayer == 0){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
