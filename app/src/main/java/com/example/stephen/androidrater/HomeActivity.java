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
        // go to browse activity
        Intent intent = new Intent(HomeActivity.this, BrowseActivity.class);
        startActivity(intent);
    }

    public void search_database(View view) {
        // create new activity for searching the database
        Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
        startActivity(intent);

    }
}