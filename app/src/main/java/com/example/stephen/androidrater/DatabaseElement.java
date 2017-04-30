package com.example.stephen.androidrater;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stephen on 4/9/2017.
 */

public class DatabaseElement implements Parcelable{

    String _name;
    String _description;
    float _rating;

    public DatabaseElement(){
    }

    public DatabaseElement(Parcel p)
    {
        this._name = p.readString();
        this._description = p.readString();
        this._rating = p.readFloat();
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(_name);
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


    public DatabaseElement(String name, String description, float rating){
        this._name = name;
        this._description = description;
        this._rating = rating;
    }



    public String get_name(){
        return this._name;
    }

    public String get_description(){
        return this._description;
    }

    public float get_rating(){
        return this._rating;
    }



    public void set_name(String name){
        this._name = name;
    }

    public void set_description(String description){
        this._description = description;
    }

    public void set_rating(float rating){
        this._rating = rating;
    }


}
