package com.example.scandaloussalesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.O;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    //Initialize Variable
    ArrayList<String> stringArrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Variable
        listView = findViewById(R.id.list_view);

        //Add Item in Array List
        for(int i=0; i<=100; i++){
            stringArrayList.add("Item " + i);
        }

        //Initialize Adapter
        adapter = new ArrayAdapter<>(MainActivity.this
                ,android.R.layout.simple_list_item_1,stringArrayList);

        //Set adapter on list view
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Display click item position in toast
                Toast.makeText(getApplicationContext()
                        ,adapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Initialize Menu Inflater
        MenuInflater menuInflater = getMenuInflater();
        //Inflate Menu
        menuInflater.inflate(R.menu.menu_search,menu);
        //Initialize Menu Item
        MenuItem menuItem = menu.findItem(R.id.search_view);
        //Initialize search view
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Filter Array List
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}