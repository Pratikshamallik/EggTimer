package com.pratiksha.eggtimer;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    boolean counterIsActive=false;
    Button controllerButton;
    CountDownTimer countDownTimer;

    public void resetTimer(){

        timerTextView.setText("0.30");
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("GO!");
        timerSeekBar.setEnabled(true);
        counterIsActive=false;

}

    public void updateTimer(int secondsLeft) {
        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;
        String secondString = Integer.toString(seconds);

        if(seconds<=9){
            secondString= "0"+secondString;
        }

        timerTextView.setText(Integer.toString(minutes) + " : " + secondString);
    }

    public void controlTimer(View view) {

        if(counterIsActive == false){
            counterIsActive=true;
            timerSeekBar.setEnabled(false);
            controllerButton.setText("Stop");

            countDownTimer= new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000){
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                }

            @Override
            public void onFinish() {

                resetTimer();
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mplayer.start();
            }
        }.start();
    }
        else{
            resetTimer();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.TimerSeekBar);
        timerTextView = (TextView) findViewById(R.id.TimerTextView);
        controllerButton = (Button) findViewById(R.id.controllerButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
