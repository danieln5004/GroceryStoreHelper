package com.example.shazam.colorbackground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.grey);
    }


    public void goRed(View v){
        view.setBackgroundResource(R.color.red);
    }

    public void goBlue(View v){
        view.setBackgroundResource(R.color.blue);
    }

    public void goGreen(View v){
        view.setBackgroundResource(R.color.green);
    }

    public void goYellow(View v){
        view.setBackgroundResource(R.color.yellow);
    }

}
