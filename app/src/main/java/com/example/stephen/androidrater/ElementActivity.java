package com.example.stephen.androidrater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class ElementActivity extends AppCompatActivity {

    TextView tv_food_name;
    TextView tv_restaurant_name;
    RatingBar rb_rating;
    TextView tv_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element);

        DatabaseElement element = this.getIntent().getExtras().getParcelable("element");

        tv_food_name = (TextView) findViewById(R.id.text_element_name);
        tv_restaurant_name = (TextView) findViewById(R.id.text_element_restaurant);
        rb_rating = (RatingBar) findViewById(R.id.ratingBar_element_activity_rating);
        tv_description = (TextView) findViewById(R.id.text_element_description);

        tv_restaurant_name.setText(element.get_restaurant());
        tv_food_name.setText(element.get_food_name());
        rb_rating.setRating(element.get_rating());
        tv_description.setText(element.get_description());
    }
}
