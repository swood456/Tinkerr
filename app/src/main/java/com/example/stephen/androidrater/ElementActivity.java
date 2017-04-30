package com.example.stephen.androidrater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ElementActivity extends AppCompatActivity {

    TextView tv_element_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element);

        DatabaseElement element = this.getIntent().getExtras().getParcelable("element");

        tv_element_name = (TextView) findViewById(R.id.text_element_name);

        tv_element_name.setText(element.get_food_name());
    }
}
