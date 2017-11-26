package com.example.shazam.groceryhelper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class checkList extends AppCompatActivity {
    ArrayList<String> selectedItems = new ArrayList<>();
    ArrayList<String> items = new ArrayList<>();
    Button showCheckListButton;
    EditText addItemView;
    RequestQueue queue;
    Boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        queue = Volley.newRequestQueue(this);




        setContentView(R.layout.activity_check_list);
        ListView chl = (ListView) findViewById(R.id.Checkable_list);
        showCheckListButton = (Button)findViewById(R.id.add_list_button);
        addItemView = (EditText)findViewById(R.id.addItemTextView);
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        items.add("corn");
        items.add("paper towels");
        items.add("bread");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.content_check_list, R.id.txt_lan, items);
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
                //Toast.makeText(checkList.this, "SELECTED : " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });





        showCheckListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selecteditems="";
                for(String item:selectedItems){
                    selecteditems+="-"+item+"\n";
                }
                //Toast.makeText(checkList.this, "You have selected \n"+ selecteditems, Toast.LENGTH_LONG).show();
                items.add(addItemView.getText().toString());
                addItemView.setText("");
                adapter.notifyDataSetChanged();



                ////post request for button, button sends data
                String url = "https://cs449-grocerystore.herokuapp.com/spent/";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPONSE", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/x-www-form-urlencoded";
                    }



                    //hash map for string and checkmark(boolean)
                    @Override
                    protected Map<String, Boolean> getParams() {
                        Map<String, Boolean> params = new HashMap<String, Boolean>();
                        //Log.d("RESPONSE", "DATE: " + item_name + " AMOUNT: " + editText.getText().toString());
                        params.put("item_name", items);
                        params.put("check", checked );
                        return params;
                    }
            }
        });
    }

}
