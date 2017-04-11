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

        SQLiteDatabase db = m_db_helper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                FoodTableContract.FoodEntry._ID,
                FoodTableContract.FoodEntry.COLUMN_NAME_NAME,
                FoodTableContract.FoodEntry.COLUMN_NAME_DESCRIPTION
        };

// Filter results WHERE "title" = 'My Title'
        String selection = FoodTableContract.FoodEntry.COLUMN_NAME_NAME + " = ?";
        String[] selectionArgs = { "name" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                FoodTableContract.FoodEntry.COLUMN_NAME_DESCRIPTION + " DESC";

        Cursor cursor = db.query(
                FoodTableContract.FoodEntry.TABLE_NAME,     // The table to query
                projection,                                 // The columns to return
                selection,                                  // The columns for the WHERE clause
                selectionArgs,                              // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                sortOrder                                   // The sort order
        );

        while(cursor.moveToNext()) {
            Long id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FoodTableContract.FoodEntry._ID));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(FoodTableContract.FoodEntry.COLUMN_NAME_NAME));
            String description = cursor.getString(
                    cursor.getColumnIndexOrThrow(FoodTableContract.FoodEntry.COLUMN_NAME_DESCRIPTION));
            // just print to log for now
            Log.d("mine", "id: " + id + " name: " + name + " des: " + description);
        }

        db.close();

    }
}