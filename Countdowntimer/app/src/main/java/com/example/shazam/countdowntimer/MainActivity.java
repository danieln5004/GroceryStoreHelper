package com.example.shazam.countdowntimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView countdown;
    Button start;
    Button stop;
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countdown = (TextView)findViewById(R.id.time);
        start = (Button) findViewById(R.id.Start_button);
        stop = (Button) findViewById(R.id.Stop_button);

    }


    public void Starttime(View view){
        timer = new CountDownTimer(900000, 1000) {
            @Override
            public void onTick(final long minutestofinish) {
                String time = String.valueOf(minutestofinish / 1000);
                countdown.setText(time);

            }

            @Override
            public void onFinish() {
                countdown.setText("Boom! Done!");
            }
        };
        timer.start();
    }

    public void Stoptime(View view){
        timer.cancel();
        countdown.setText("0");
    }




}
