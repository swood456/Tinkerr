package com.example.stephen.androidrater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        // get all items in _db
        DatabaseHandler m_db_helper = new DatabaseHandler(this);

        final List<DatabaseElement> allElements = m_db_helper.getAllElements();

        m_db_helper.close();
        ElementAdapter adapter = new ElementAdapter(this, allElements);
        m_listview.setAdapter(adapter);

        m_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                DatabaseElement element = allElements.get(pos);

                Intent intent = new Intent(BrowseActivity.this, ElementActivity.class);
                intent.putExtra("element", element);
                startActivity(intent);
            }
        });

    }
}
