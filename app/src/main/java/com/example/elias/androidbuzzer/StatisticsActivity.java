package com.example.elias.androidbuzzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {

    private StatisticsModel model;
    private StatisticsFormatter statisticsFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        model = StatisticsEngine.GetStatisticsModel(this);
        statisticsFormatter = new StatisticsFormatter(model);
        populateListView(model);
    }

    private void populateListView(StatisticsModel model) {
        ListView listView = (ListView) findViewById(R.id.statistics_list_view);
        ArrayList<String> statistics = statisticsFormatter.formatStatistics();
        ArrayAdapter<String> statisticsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, statistics);
        listView.setAdapter(statisticsArrayAdapter);
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

    public void clear_statistics(View view) {
        StatisticsEngine.ClearStatisticsModel(this);
        model = StatisticsEngine.GetStatisticsModel(this);
        statisticsFormatter = new StatisticsFormatter(model);
        populateListView(model);
    }

    public void email_statistics(View view) {
        String[] emails = {"your email here!"};
        String subject = "Android Buzzer Statistics";
        String messageBody = emailStatisticsFormat(statisticsFormatter.formatStatistics());

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emails);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, messageBody);

        // need this to prompts email client only
        emailIntent.setType("message/rfc822");

        try {
            startActivity(emailIntent);
        } catch (RuntimeException ex){
            notifyNoEmailHandler();
        }
    }

    private String emailStatisticsFormat(ArrayList<String> statistics){
        String formatted = "";
        for (String s : statistics){
            formatted += s + "\n\n";
        }
        if (formatted == "") formatted = "No Statistics Yet!";
        return formatted;
    }

    private void notifyNoEmailHandler(){
        CharSequence text = "You Don't Have An Email App Set Up! Sign Into Or Install The Email App.";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }
}
