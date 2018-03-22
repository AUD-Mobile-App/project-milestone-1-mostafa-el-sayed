package com.mustafa.mostafa.mostafaproject;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Mostafa on 3/22/2018.
 */

public class CustomListAdapter extends ArrayAdapter<String>
{
    private final Activity context;
    MainActivity mainActivity;
    public List<BucketItem> listOfBucketItems;

    // constructor
    public CustomListAdapter(Activity context, List<BucketItem> listOfBucketItems, String[] array, MainActivity mainActivity)
    {
        super(context, R.layout.mylist, array);

        this.context = context;
        this.listOfBucketItems = listOfBucketItems;
        this.mainActivity = mainActivity;
    }

    // get view for a certain row
    public View getView(final int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);

        // grab gui elements
        TextView itemName = (TextView) rowView.findViewById(R.id.itemName);
        TextView itemDate = (TextView) rowView.findViewById(R.id.itemDate);

        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.checkbox);

        // set checkbox callback
        checkBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                checkboxClicked((CheckBox) view, position);
            }
        });

        // set text values
        itemName.setText(listOfBucketItems.get(position).itemName);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // format date
        itemDate.setText(dateFormat.format(listOfBucketItems.get(position).dueDate));

        // set checkbox and color of text according to whether item is finished or not
        if (listOfBucketItems.get(position).isDone)
        {
            itemName.setTextColor(Color.parseColor("#6ab344"));
            checkBox.setChecked(true);
        }

        return rowView;
    }

    // fires when a checkbox is toggled
    void checkboxClicked(CheckBox checkbox, int position)
    {
        // update value
        mainActivity.listOfBucketItems.get(position).isDone = checkbox.isChecked();

        // update GUI of list view
        mainActivity.populateListview();
    }
}
