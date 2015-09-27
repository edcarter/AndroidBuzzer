package com.example.elias.androidbuzzer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class GameShowActivity extends AppCompatActivity {

    private Integer playersSelected;
    private ArrayList<Button> playerButtons;
    private int[] buttonColors = {Color.CYAN, Color.RED, Color.GREEN, Color.YELLOW};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_show);
        Intent intent = getIntent();
        playersSelected = intent.getIntExtra(PlayerSelectionActivity.playersSelectedExtra, 2);
        createPlayerButtons();
    }

    private void createPlayerButtons() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.game_show_linear_layout);
        //kcoppock, http://stackoverflow.com/questions/4641072/how-to-set-layout-weight-attribute-dynamically-from-code
        LinearLayout.LayoutParams params = getLayoutParams();
        for (int i = 0; i < playersSelected; i++){
            Button button = new Button(this);
            button.setTag(new Integer(i));
            button.setText("PLAYER " + formatPlayerText(i));
            button.setBackgroundColor(buttonColors[i%buttonColors.length]);
            button.setLayoutParams(params);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickHandler(v);
                }
            });
            linearLayout.addView(button);
        }
    }

    private String formatPlayerText(Integer playerNumber){
        return "PLAYER " + Integer.toString(playerNumber+1);
    }

    private void onClickHandler(View v){
        Button button = (Button) v;
        Integer playerNumber = (Integer) v.getTag();
        displayWinningPlayer(playerNumber);
    }

    private void displayWinningPlayer(Integer i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(formatPlayerText(i)+" Reacted First!").setTitle("Buzzer");
        builder.setPositiveButton(R.string.reaction_rules_dialog_ok_button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog rulesDialog = builder.create();
        rulesDialog.show();
        //TODO(stop ok dialog being hit by accident by other player?)
    }

    private LinearLayout.LayoutParams getLayoutParams(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(0, 0, 0, 20);
        params.weight = 1.0f;
        return params;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_show, menu);
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
