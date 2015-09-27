package com.example.elias.androidbuzzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class PlayerSelectionActivity extends AppCompatActivity {

    private Integer minPlayers = 2;
    private Integer playersSelected = minPlayers;

    public static String playersSelectedExtra = "playersSelectedExtra";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_selection);
        Integer maxPlayers = 4;
        setupSeekBar(maxPlayers);
        setupPlayersTextView();
    }

    private void setupSeekBar(Integer maxPlayers){
        SeekBar seekBar = (SeekBar) findViewById(R.id.player_selecter_bar);
        seekBar.setMax(maxPlayers - minPlayers);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView playersSelectedText = (TextView) findViewById(R.id.players_selected);
                playersSelected = progress + minPlayers; //set minimum players to 2. cannot set seekbar min
                playersSelectedText.setText(getPlayersSelectedTextFormat(playersSelected));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private String getPlayersSelectedTextFormat(Integer playersSelected){
        return "Players: " + Integer.toString(playersSelected);
    }

    private void setupPlayersTextView(){
        TextView playersSelectedText = (TextView) findViewById(R.id.players_selected);
        playersSelectedText.setText(getPlayersSelectedTextFormat(playersSelected));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public void navigate_to_gameshow(View view) {
        Intent intent = new Intent(this, GameShowActivity.class);
        intent.putExtra(playersSelectedExtra, playersSelected);
        startActivity(intent);
    }
}
