package com.example.stephen.androidrater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    Spinner search_type_spinner;
    EditText search_field;
    ListView m_listview;
    List<DatabaseElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // find the UI elements
        search_type_spinner = (Spinner)findViewById(R.id.search_spinner_type);
        search_field = (EditText)findViewById(R.id.search_field);

        // populate the spinner with the different columns in the database
        String[] columns = new String[3];
        columns[0] = FoodTableContract.FoodEntry.COLUMN_NAME_NAME;
        columns[1] = FoodTableContract.FoodEntry.COLUMN_NAME_DESCRIPTION;
        columns[2] = FoodTableContract.FoodEntry.COLUMN_NAME_RATING;

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.database_columns, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        search_type_spinner.setAdapter(adapter);

        m_listview = (ListView)findViewById(R.id.search_listview);

        m_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                DatabaseElement element = elements.get(pos);

                Intent intent = new Intent(SearchActivity.this, ElementActivity.class);
                intent.putExtra("element", element);
                startActivity(intent);
            }
        });
    }

    public void search_db(View view)
    {
        String column = search_type_spinner.getSelectedItem().toString();
        //String query = search_field.getText().toString();
        String[] query = new String[1];
        query[0] = search_field.getText().toString();
        DatabaseHandler m_db_helper = new DatabaseHandler(this);

        elements = m_db_helper.getAllElementsWithQuery(column, query);

        m_db_helper.close();

        ElementAdapter adapter = new ElementAdapter(this, elements);
        m_listview.setAdapter(adapter);
    }
}
