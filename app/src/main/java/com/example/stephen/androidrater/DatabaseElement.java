package com.example.stephen.androidrater;

/**
 * Created by Stephen on 4/9/2017.
 */

public class DatabaseElement {

    int _id;
    String _name;
    String _description;
    float _rating;

    public DatabaseElement(){
    }

    public DatabaseElement(int id, String name, String description, float rating){
        this._id = id;
        this._name = name;
        this._description = description;
        this._rating = rating;
    }

    public int get_id(){
        return this._id;
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


    public void set_id(int id){
        this._id = id;
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
