package com.example.stephen.androidrater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class BrowseActivity extends AppCompatActivity {

    ListView m_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        // get listview
        m_listview = (ListView)findViewById(R.id.browse_listView);

        // get all items in db
        DatabaseHandler m_db_helper = new DatabaseHandler(this);

        List<DatabaseElement> allElements = m_db_helper.getAllElements();

        m_db_helper.close();

        String[] listItems = new String[allElements.size()];

        for(int i = 0; i < allElements.size(); i++){
            DatabaseElement element = allElements.get(i);
            listItems[i] = element.get_name();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        m_listview.setAdapter(adapter);

        // tutorial for this: https://www.raywenderlich.com/124438/android-listview-tutorial

    }
}
