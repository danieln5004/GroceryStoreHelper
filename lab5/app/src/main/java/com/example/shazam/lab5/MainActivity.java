package com.example.shazam.lab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    // Layout
    RelativeLayout RL;
    // Edit Box
    EditText EB;
    // Button
    Button B1, B2;

     counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // create a dynamic layoutsRelativeLayout RL;
        // allows x/y positioning
        RL = new RelativeLayout(this);
        RL.setBackgroundColor(0xFF11FF11);
        // Add a view over the layout
        View V = new View(this); // Create view
        RL.addView(V, 			RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.FILL_PARENT);



        // ------------- ADD EDITTEXT ----------------
        EB = new EditText(this);
        // set initial Text
        EB.setText("10");
        // for identification - not required here, but sometimes useful
        EB.setId(100);

        // Create the layout parameters for this EditText
        int top = 200; // y
        int left = 100; // x
        RelativeLayout.LayoutParams LP = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        // place edittext at position x,y
        LP.setMargins(left, top, 0, 0);
        // apply position to this edittext
        EB.setLayoutParams(LP);


        // -------------- ADD A BUTTON ---------------
        B1 = new Button(this);
        B1.setText("START");
        // Add Id
        B1.setId(23);
        // set onClick listener (need to implements)
        B1.setOnClickListener(this);

        RelativeLayout.LayoutParams LP2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        LP2.setMargins(10, 20, 0, 0); // place edittext at position x,y
        B1.setLayoutParams(LP2); // apply position to this edittext


        // add it now to the layout
        RL.addView(B1);

        // -------------- ADD Another BUTTON ---------------
        B2 = new Button(this);
        B2.setText("Invisible");
        B2.setId(24);
        // set onClick listener
        B2.setOnClickListener(this);
        RelativeLayout.LayoutParams LP3 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        LP3.setMargins(400, 20, 0, 0); // place edittext at position x,y
        B2.setLayoutParams(LP3); // apply position to this edittext
        RL.addView(B2); // add it now to the layout

        // ------------ ACTIVATE LAYOUT -------------
        setContentView(RL); // and activate it now



    }


    @Override
    public void onClick(View view) {

    }
}

}
