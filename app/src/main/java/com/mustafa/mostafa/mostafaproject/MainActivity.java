package com.mustafa.mostafa.mostafaproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    // global variables
    public List<BucketItem> listOfBucketItems; // list of bucket items
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set instance of this class
        Global global = ((Global) this.getApplication());
        global.mainActivity = this;

        // initialize global variable
        list = (ListView) findViewById(R.id.list);
        listOfBucketItems = new ArrayList<>();

        // create a few bucket list items
        listOfBucketItems.add(new BucketItem("Pass classes", "", new Date(2018 - 1900, 4, 24), 0, 0, false));
        listOfBucketItems.add(new BucketItem("Get a part-time job", "", new Date(2018 - 1900, 5, 15), 0, 0, false));
        listOfBucketItems.add(new BucketItem("Visit Siberia", "", new Date(2018 - 1900, 12, 31), 0, 0, false));
        listOfBucketItems.add(new BucketItem("Buy a car", "", new Date(2017 - 1900, 12, 24), 0, 0, true));

        // populate list
        populateListview();

        // set listener for list item click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = listOfBucketItems.get(position).itemName;
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void newItemClicked(View v)
    {
        // launch activity
        Intent intent = new Intent(this, NewItemActivity.class);
        startActivity(intent);
    }

    public void populateListview()
    {
        // sort list of bucket items
        Collections.sort(listOfBucketItems, new Comparator<BucketItem>()
        {
            public int compare(BucketItem obj1, BucketItem obj2)
            {
                if (obj1.dueDate.before(obj2.dueDate))
                {
                    return -1;
                }
                else {
                    return 1;
                }

            }
        });

        // update GUI
        String[] array = new String[listOfBucketItems.size()]; // CustomListAdapter requires a traditional string array in the constructor
        CustomListAdapter adapter = new CustomListAdapter(this, listOfBucketItems, array, this);
        list.setAdapter(adapter);
    }
}
