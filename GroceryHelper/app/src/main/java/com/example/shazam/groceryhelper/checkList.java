package com.example.shazam.groceryhelper;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class checkList extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<>();
    Button showCheckListButton;
    EditText addItemView;
    RequestQueue queue;
    ListView chl;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        queue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_check_list);
        chl = (ListView) findViewById(R.id.Checkable_list);
        showCheckListButton = (Button) findViewById(R.id.add_list_button);
        addItemView = (EditText) findViewById(R.id.addItemTextView);
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        populateView(checkList.this);
        adapter = new ArrayAdapter<String>(this, R.layout.content_check_list, R.id.txt_lan, items);
        chl.setAdapter(adapter);
        chl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selectedItem = ((TextView) view).getText().toString();

                AlertDialog.Builder adb = new AlertDialog.Builder(checkList.this);
                adb.setTitle("Confirm check");
                adb.setMessage("Are you sure?");
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Okay", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int which) {
                        items.remove(selectedItem);
                        adapter.notifyDataSetChanged();

                        String url = "https://cs449-grocerystore.herokuapp.com/checklist/" + selectedItem;
                        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("RESPONSE_CHECK", response.toString());
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
                        };
                        queue.add(stringRequest);
                    }
                });
                adb.show();
            }
        });


        showCheckListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String itemToAdd = addItemView.getText().toString();
                items.add(itemToAdd);
                adapter.notifyDataSetChanged();

                ////post request for button, button sends data
                String url = "https://cs449-grocerystore.herokuapp.com/checklist";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPONSE_CHECK", response.toString());
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
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        //Log.d("RESPONSE", "DATE: " + item_name + " AMOUNT: " + editText.getText().toString());
                        params.put("item_name", itemToAdd);
                        return params;
                    }
                };
                addItemView.setText("");
                queue.add(stringRequest);
            }
        });
    }

    void populateView(final Context context) {
        String url = "https://cs449-grocerystore.herokuapp.com/checklist";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                try {
                    for (int i = 0; i < response.length(); ++i) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        items.add(jsonObject.getString("item_name"));
                    }
                    adapter.notifyDataSetChanged();
                }
                catch (JSONException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonArrayRequest);
    }
}
