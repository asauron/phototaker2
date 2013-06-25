package com.example.phototaker2;

import java.util.List;

import com.example.phototaker2.db.ZomBeeDataSource;
import com.example.phototaker2.model.Zombees;


import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.support.v4.app.NavUtils;

public class DisplayDatabase extends ListActivity {
	
	ZomBeeDataSource datasource;
	
	private SharedPreferences settings;
	private OnSharedPreferenceChangeListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_database);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        datasource = new ZomBeeDataSource(this);
		datasource.open();
		
//		//List<Zombees> zombee = datasource.findAll();
////		if (zombee.size() == 0) {
////			createData();
////			tours = datasource.findAll();
////		}
//				
//		ArrayAdapter<Zombees> adapter = new ArrayAdapter<Zombees>(this, 
//				android.R.layout.simple_list_item_1, zombee);
//		setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_display_database, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
