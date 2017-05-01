package com.example.stephen.androidrater;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class AddElementActivity extends AppCompatActivity {

    EditText et_restaurant_name;
    EditText et_element_name;
    EditText et_element_description;
    RatingBar rb_element_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_element);

        // locate the elements in the activity
        et_restaurant_name = (EditText)findViewById(R.id.editText_add_restaurant_name);
        et_element_name = (EditText)findViewById(R.id.editText_add_element_name);
        et_element_description = (EditText)findViewById(R.id.editText_add_element_description);
        rb_element_rating = (RatingBar)findViewById(R.id.ratingBar_add_element_rating);
    }

    public void add_element(View view){

        // collect the data from the ui
        String restaurant_name = et_restaurant_name.getText().toString();
        String element_name = et_element_name.getText().toString();
        String element_description = et_element_description.getText().toString();
        float element_rating = rb_element_rating.getRating();

        // make a database handler to add the the database
        DatabaseHandler m_db_helper = new DatabaseHandler(view.getContext());

        // add the DatabaseElement that we just made into the database
        DatabaseElement element = new DatabaseElement(restaurant_name, element_name, element_description, element_rating);

        // Add to the database and get the ID
        long newRowId = m_db_helper.addElement(element);

        // simple print message to screen saying that we sucessfully added
        Toast t = Toast.makeText(this, "added element with name " + element_name +
                " into _db with id " + newRowId, Toast.LENGTH_LONG);
        t.show();

        // go to the element activity for this database element
        Intent intent = new Intent(AddElementActivity.this, ElementActivity.class);
        intent.putExtra("element", element);
        startActivity(intent);

        m_db_helper.close();
    }
}