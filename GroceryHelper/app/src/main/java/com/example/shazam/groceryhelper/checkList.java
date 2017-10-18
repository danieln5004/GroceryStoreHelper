package com.example.shazam.groceryhelper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class checkList extends AppCompatActivity {
    ArrayList<String> selectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_list);
        ListView chl = (ListView) findViewById(R.id.Checkable_list);
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String [] items = {"corn", "paper towels", "bread"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.content_check_list, R.id.txt_lan, items);
        chl.setAdapter(adapter);
        chl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id ) {
                String selectedItem = ((TextView)view).getText().toString();
                if(selectedItems.contains(selectedItem))
                {
                  selectedItems.remove(selectedItem);
                }
                else
                    selectedItems.add(selectedItem);
            }
        });

    }


    public void showSelectedItem (View view){
        String items="";
        for(String item:selectedItems){
            items+="-"+items+"\n";
        }
        Toast.makeText(this, "You have selected \n"+ items, Toast.LENGTH_LONG).show();
    }
}
