package com.example.stephen.androidrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 4/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "micro_ratings";

    private static final String TABLE_FOOD = "food";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_RATING = "rating";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_FOOD +
                "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," +
                KEY_DESCRIPTION + " TEXT," + KEY_RATING + "REAL" + ")";
        db.execSQL(CREATE_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);

        // Create tables again
        onCreate(db);
    }

    public void addElement(DatabaseElement element){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, element.get_id());
        values.put(KEY_NAME, element.get_name());
        values.put(KEY_DESCRIPTION, element.get_description());
        //values.put(KEY_RATING, element.get_rating());

        // Inserting Row
        db.insert(TABLE_FOOD, null, values);
        db.close(); // Closing database connection
    }

    public DatabaseElement getElement(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOOD,
                new String[] { KEY_ID, KEY_NAME, KEY_DESCRIPTION, KEY_RATING },
                KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        //DatabaseElement element = new DatabaseElement(cursor.getString(1), cursor.getString(2),
        //        Float.parseFloat(cursor.getString(3)));

        DatabaseElement element = new DatabaseElement(cursor.getString(1), cursor.getString(2));
        return element;
    }


    public List<DatabaseElement> getAllContacts() {
        List<DatabaseElement> contactList = new ArrayList<DatabaseElement>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FOOD;

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

        // return contact list
        return contactList;
    }

}
