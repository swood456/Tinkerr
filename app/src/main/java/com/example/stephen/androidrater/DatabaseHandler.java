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

    private static final int DATABASE_VERSION = 5;

    private static final String DATABASE_NAME = "micro_ratings.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FoodTableContract.FoodEntry.TABLE_NAME + " (" +
                    FoodTableContract.FoodEntry._ID + " INTEGER PRIMARY KEY," +
                    FoodTableContract.FoodEntry.COLUMN_NAME_NAME + " TEXT," +
                    FoodTableContract.FoodEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    FoodTableContract.FoodEntry.COLUMN_NAME_RATING + " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FoodTableContract.FoodEntry.TABLE_NAME;

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.d("mine","making new database!");
        Log.d("mine", SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public List<DatabaseElement> getAllContacts() {
        List<DatabaseElement> contactList = new ArrayList<DatabaseElement>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + FoodTableContract.FoodEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DatabaseElement element = new DatabaseElement();
                element.set_id(Integer.parseInt(cursor.getString(0)));
                element.set_name(cursor.getString(1));
                element.set_description(cursor.getString(2));
                // Adding contact to list
                contactList.add(element);
            } while (cursor.moveToNext());
        }
        db.close();
        // return contact list
        return contactList;
    }
}
