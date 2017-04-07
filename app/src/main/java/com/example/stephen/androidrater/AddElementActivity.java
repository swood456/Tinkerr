package com.example.stephen.androidrater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

public class AddElementActivity extends AppCompatActivity {

    EditText et_element_name;
    EditText et_element_description;
    RatingBar rb_element_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_element);

        et_element_name = (EditText)findViewById(R.id.editText_add_element_name);
        et_element_description = (EditText)findViewById(R.id.editText_add_element_description);
        rb_element_rating = (RatingBar)findViewById(R.id.ratingBar_add_element_rating);
    }

    public void add_element(View view){
        String element_name = et_element_name.getText().toString();
        String element_description = et_element_description.getText().toString();

        float element_rating = rb_element_rating.getRating();
    }
}
