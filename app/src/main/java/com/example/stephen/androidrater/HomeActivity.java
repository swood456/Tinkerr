package com.example.stephen.androidrater;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void make_new_element(View view) {
        // create a new activity for the addition of an element to the database
        Intent intent = new Intent(HomeActivity.this, AddElementActivity.class);
        startActivity(intent);
    }

    public void browse_database(View view) {
        // TODO: make the activity for the browse and allow users to get to it
    }

    public void search_database(View view) {
        // create new activity for searching the database
        //Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
        //startActivity(intent);
        DatabaseHandler m_db_helper = new DatabaseHandler(this);

        List<DatabaseElement> allElements = m_db_helper.getAllElements();

        for (DatabaseElement dbe: allElements
             ) {
            Log.d("mine", "name: " + dbe.get_name() + " des: " + dbe.get_description() + " rating: " + dbe.get_rating());
        }

        m_db_helper.close();
    }
}