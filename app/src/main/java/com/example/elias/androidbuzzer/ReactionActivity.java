package com.example.elias.androidbuzzer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.concurrent.Callable;

public class ReactionActivity extends AppCompatActivity {

    ReactionTimer reactionTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction);
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

    public void reaction_button_clicked(View view) throws Exception {
        Callable reaction_timer_callback = new Callable(){
            public Object call(){
                reaction_timer_triggered();
                return null;
            }
        };
        reactionTimer.Start(reaction_timer_callback);
    }

    private void reaction_timer_triggered(){
        change_button_to_triggered();
    }

    private void change_button_to_triggered(){

    }
}
