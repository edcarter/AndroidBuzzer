package com.example.elias.androidbuzzer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.Callable;

public class ReactionActivity extends AppCompatActivity {

    private ReactionTimer reactionTimer;
    private Boolean timerRunning = false;
    private Boolean waitingForUserReaction = false;
    MediaPlayer hitMarkerPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hitMarkerPlayer = MediaPlayer.create(this, R.raw.hitmarker);
        setContentView(R.layout.activity_reaction);
        notifyRules();
        reactionTimer = new ReactionTimer();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction, menu);
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

    public void reactionButtonClicked(View view) throws Exception {
        //TODO(refactor button click handler for different states)
        if (timerRunning && waitingForUserReaction){
            hitMarkerPlayer.start();
            Integer reactionTime = stopReactionTimer();
            changeButtonToNotTiming();
            StatisticsEngine.AddReactionStatistic(reactionTime, this);
            displayReactionTime(reactionTime);
        } else if (timerRunning) {
            //user hit too early
            stopReactionTimer();
            reactionTimer.Cancel();
            reactionTimer = new ReactionTimer();
            diplayTooEarlyDialog();
            changeButtonToNotTiming();
        } else {
            startReactionTimer();
            changeButtontToWaitingForUser();
        }
    }

    private void diplayTooEarlyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You hit the button to early!!! Try again.").setTitle("Dont Do That!");
        builder.setPositiveButton(R.string.reaction_rules_dialog_ok_button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog rulesDialog = builder.create();
        rulesDialog.show();
    }

    private void displayReactionTime(Integer timeMs) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(timeMs.toString() + "Ms").setTitle("Reaction Time:");
        builder.setPositiveButton(R.string.reaction_rules_dialog_ok_button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog rulesDialog = builder.create();
        rulesDialog.show();
    }

    private Integer stopReactionTimer() {
        reactionTimer.Stop();
        timerRunning = false;
        waitingForUserReaction = false;
        return reactionTimer.GetReactionTimeMs();
    }

    private void startReactionTimer() throws Exception {
        Callable reactionTimerCallback = new Callable(){
            public Object call(){
                if (timerRunning){
                    reactionTimerTriggered();
                    waitingForUserReaction = true;
                }
                return null;
            }
        };
        reactionTimer.Start(reactionTimerCallback, this);
        timerRunning = true;
    }


    private void reactionTimerTriggered(){
         if (timerRunning) changeButtonToHitByUser();
    }

    private void changeButtonToHitByUser() {
        Button reactionButton = (Button)findViewById(R.id.button4);
        reactionButton.setBackgroundColor(Color.RED);
    }

    private void changeButtontToWaitingForUser(){
        Button reactionButton = (Button)findViewById(R.id.button4);
        reactionButton.setBackgroundColor(Color.GREEN);
    }

    private void changeButtonToNotTiming(){
        Button reactionButton = (Button)findViewById(R.id.button4);
        reactionButton.setBackgroundColor(Color.LTGRAY);
    }

    private void notifyRules(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.reaction_rules_dialog_message).setTitle(R.string.reaction_rules_dialog_title);
        builder.setPositiveButton(R.string.reaction_rules_dialog_ok_button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog rulesDialog = builder.create();
        rulesDialog.show();
    }
}
