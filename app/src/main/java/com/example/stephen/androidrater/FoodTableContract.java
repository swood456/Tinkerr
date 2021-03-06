package com.example.stephen.androidrater;

import android.provider.BaseColumns;

/**
 * Created by Stephen on 4/10/2017.
 */

public final class FoodTableContract {
    private FoodTableContract(){}

    public static class FoodEntry implements BaseColumns{
        public static final String TABLE_NAME = "food";
        public static final String COLUMN_NAME_RESTAURANT = "restaurant";
        public static final String COLUMN_NAME_NAME = "food_name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_RATING = "rating";
    }
}
