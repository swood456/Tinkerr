package com.example.stephen.androidrater;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stephen on 4/9/2017.
 */

public class DatabaseElement implements Parcelable{

    String _restaurant;
    String _food_name;
    String _description;
    float _rating;

    public DatabaseElement(){
    }

    public DatabaseElement(Parcel p)
    {
        this._restaurant = p.readString();
        this._food_name = p.readString();
        this._description = p.readString();
        this._rating = p.readFloat();
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(_restaurant);
        dest.writeString(_food_name);
        dest.writeString(_description);
        dest.writeFloat(_rating);
    }

    public int describeContents()
    {
        return 0;
    }

    public static final Parcelable.Creator<DatabaseElement> CREATOR
            = new Parcelable.Creator<DatabaseElement>() {

        public DatabaseElement createFromParcel(Parcel in) {
            return new DatabaseElement(in);
        }

        public DatabaseElement[] newArray(int size) {
            return new DatabaseElement[size];
        }
    };


    public DatabaseElement(String restaurant, String name, String description, float rating){
        this._restaurant = restaurant;
        this._food_name = name;
        this._description = description;
        this._rating = rating;
    }

    public String get_restaurant() { return _restaurant; }

    public String get_food_name(){ return this._food_name; }

    public String get_description(){ return this._description; }

    public float get_rating(){
        return this._rating;
    }


    public void set_restaurant(String restaurant) {this._restaurant = restaurant; }

    public void set_food_name(String name){
        this._food_name = name;
    }

    public void set_description(String description){
        this._description = description;
    }

    public void set_rating(float rating){
        this._rating = rating;
    }


}
