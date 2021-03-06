package com.example.shazam.groceryhelper;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    Button rewardsButton;
    Button checklistButton;
    Button receiptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dl = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        rewardsButton = (Button) findViewById(R.id.Rewards_card_button);
        checklistButton = (Button) findViewById(R.id.Check_list_button);
        receiptButton = (Button) findViewById(R.id.Receipts_button);

        rewardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RewardsActivity.class));
            }
        });

        checklistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, checkList.class));

            }
        });

        receiptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, receipt.class));
            }
        });




        dl.addDrawerListener(abdt);
        abdt.syncState();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final NavigationView nav_view =  (NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.my_profile){
                    //Toast.makeText(MainActivity.this, "My Profile", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                }
                else if(id == R.id.check_list){
                    //Toast.makeText(MainActivity.this, "Check List", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, checkList.class));
                }

                else if(id == R.id.reward_cards){
                    //Toast.makeText(MainActivity.this, "Reward Cards", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, RewardsActivity.class));
                }

                else if(id == R.id.receipt){
                    //Toast.makeText(MainActivity.this, "Receipt", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, receipt.class));
                }

                return true;
            }
        });




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
