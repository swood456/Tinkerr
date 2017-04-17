package com.example.stephen.androidrater;

/**
 * Created by Stephen on 4/9/2017.
 */

public class DatabaseElement {

    String _name;
    String _description;
    float _rating;

    public DatabaseElement(){
    }

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
