package com.example.stephen.androidrater;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class AddElementActivity extends AppCompatActivity {

    EditText et_element_name;
    EditText et_element_description;
    RatingBar rb_element_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_element);

        // locate the elements in the activity
        et_element_name = (EditText)findViewById(R.id.editText_add_element_name);
        et_element_description = (EditText)findViewById(R.id.editText_add_element_description);
        rb_element_rating = (RatingBar)findViewById(R.id.ratingBar_add_element_rating);
    }

    public void add_element(View view){
        // collect the data from the elements
        String element_name = et_element_name.getText().toString();
        String element_description = et_element_description.getText().toString();
        float element_rating = rb_element_rating.getRating();

        // TODO: store the data collected somewhere
        DatabaseHandler m_db_helper = new DatabaseHandler(view.getContext());

        // Gets the data repository in write mode
        SQLiteDatabase db = m_db_helper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FoodTableContract.FoodEntry.COLUMN_NAME_NAME, element_name);
        values.put(FoodTableContract.FoodEntry.COLUMN_NAME_DESCRIPTION, element_description);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FoodTableContract.FoodEntry.TABLE_NAME, null, values);

        Toast t = Toast.makeText(this, "added element with name " + element_name +
                " into db with id " + newRowId, Toast.LENGTH_LONG);
        t.show();

        m_db_helper.close();

    }
}