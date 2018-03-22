package com.mustafa.mostafa.mostafaproject;

import android.app.DatePickerDialog;
import android.app.usage.NetworkStats;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewItemActivity extends AppCompatActivity
{
    // global variables
    MainActivity mainActivity;
    EditText itemTitleEditText;
    EditText itemDescriptionEditText;
    Button dateButton;

    Calendar c2 = Calendar.getInstance();
    SimpleDateFormat txtDate = new SimpleDateFormat("dd/MM/yyyy");
    DatePickerDialog.OnDateSetListener d2 = new DatePickerDialog.OnDateSetListener()
    {
        // fires when a date is chosen
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
        {
            c2.set(Calendar.YEAR, year);
            c2.set(Calendar.MONTH, month);
            c2.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            // set text of button to reflect chosen date
            dateButton.setText(txtDate.format(c2.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        // initialize global variable
        itemTitleEditText = (EditText) findViewById(R.id.itemTitleEditText);
        itemDescriptionEditText = (EditText) findViewById(R.id.itemDescriptionEditText);
        dateButton = (Button) findViewById(R.id.dateButton);

        // set text of button to today's date
        dateButton.setText(txtDate.format(c2.getTime()));
    }

    // date button clicked
    public void dateClicked(View v)
    {
        // show date picker window
        new DatePickerDialog(this, d2, c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DAY_OF_MONTH)).show();
    }
    // save item clicked
    public void saveClicked(View v)
    {
        // check if fields are empty
        if (itemTitleEditText.getText().length() == 0 || itemDescriptionEditText.getText().length() == 0)
        {
            Toast.makeText(this, R.string.emptyFieldsToast, Toast.LENGTH_LONG).show();
            return;
        }

        // create new item
        BucketItem item = new BucketItem(itemTitleEditText.getText().toString(), itemDescriptionEditText.getText().toString(), c2.getTime(), 0, 0, false);
        // get main activity reference
        Global global = ((Global) this.getApplication());
        global.mainActivity.listOfBucketItems.add((item));
        global.mainActivity.populateListview();

        finish(); // exit
    }
}
