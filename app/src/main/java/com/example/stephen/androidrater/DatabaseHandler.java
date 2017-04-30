package com.example.stephen.androidrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 4/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // current iteration of the database representation.
    //  if this gets changed onUpdate will be called
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "micro_ratings.db";

    // Query that gets called when the database is created
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FoodTableContract.FoodEntry.TABLE_NAME + " (" +
                    FoodTableContract.FoodEntry._ID + " INTEGER PRIMARY KEY," +
                    FoodTableContract.FoodEntry.COLUMN_NAME_NAME + " TEXT," +
                    FoodTableContract.FoodEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    FoodTableContract.FoodEntry.COLUMN_NAME_RATING + " FLOAT)";

    // Query that gets called to delete the database
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FoodTableContract.FoodEntry.TABLE_NAME;

    SQLiteDatabase db;

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long addElement(DatabaseElement element) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FoodTableContract.FoodEntry.COLUMN_NAME_NAME, element.get_name());
        values.put(FoodTableContract.FoodEntry.COLUMN_NAME_RATING, element.get_rating());
        values.put(FoodTableContract.FoodEntry.COLUMN_NAME_DESCRIPTION, element.get_description());

        // add the element, return the id of the row added
        return db.insert(FoodTableContract.FoodEntry.TABLE_NAME, null, values);
    }


    public List<DatabaseElement> getAllElements() {
        List<DatabaseElement> elementList = new ArrayList<DatabaseElement>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + FoodTableContract.FoodEntry.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        /*
        while(cursor.moveToNext()){
            DatabaseElement element = new DatabaseElement();
            element.set_name(cursor.getString(1));
            element.set_description(cursor.getString(2));
            element.set_rating(cursor.getFloat(3));
            // Adding contact to list
            elementList.add(element);
        }*/
        while(cursor.moveToNext()) {
            DatabaseElement element = new DatabaseElement();

            element.set_name(cursor.getString(
                    cursor.getColumnIndexOrThrow(FoodTableContract.FoodEntry.COLUMN_NAME_NAME)));

            element.set_description(cursor.getString(
                    cursor.getColumnIndexOrThrow(FoodTableContract.FoodEntry.COLUMN_NAME_DESCRIPTION)));

            element.set_rating(cursor.getFloat(
                    cursor.getColumnIndex(FoodTableContract.FoodEntry.COLUMN_NAME_RATING)));

            elementList.add(element);
        }

        // return contact list
        return elementList;
    }

    public List<DatabaseElement> getAllElementsWithQuery(String query_column, String[] query_words) {

        // list of elements that match the given query
        List<DatabaseElement> elementList = new ArrayList<DatabaseElement>();

        // defines which columns we will look through. Want to do them all
        String[] projection = {
                FoodTableContract.FoodEntry._ID,
                FoodTableContract.FoodEntry.COLUMN_NAME_NAME,
                FoodTableContract.FoodEntry.COLUMN_NAME_DESCRIPTION,
                FoodTableContract.FoodEntry.COLUMN_NAME_RATING
        };

        // filter that SQLite will look for
        String selection = query_column + " = ?";

        // Define how things will be sorted
        String sortOrder = FoodTableContract.FoodEntry.COLUMN_NAME_DESCRIPTION + " DESC";

        Cursor cursor = db.query(
                FoodTableContract.FoodEntry.TABLE_NAME,     // The table to query
                projection,                                 // The columns to return
                selection,                                  // The columns for the WHERE clause
                query_words,                                // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                sortOrder                                   // The sort order
        );

        while(cursor.moveToNext()) {
            DatabaseElement element = new DatabaseElement();

            element.set_name(cursor.getString(
                    cursor.getColumnIndexOrThrow(FoodTableContract.FoodEntry.COLUMN_NAME_NAME)));

            element.set_description(cursor.getString(
                    cursor.getColumnIndexOrThrow(FoodTableContract.FoodEntry.COLUMN_NAME_DESCRIPTION)));

            element.set_rating(cursor.getFloat(
                    cursor.getColumnIndex(FoodTableContract.FoodEntry.COLUMN_NAME_RATING)));

            elementList.add(element);
        }

        return elementList;
    }
}
