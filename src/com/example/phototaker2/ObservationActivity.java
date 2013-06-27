package com.example.phototaker2;

import java.util.Vector;

import com.example.phototaker2.db.ZomBeeDBOpenHelper;
import com.example.phototaker2.db.ZomBeeDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ObservationActivity extends Activity {

     ZomBeeDataSource datasource;
    private ZomBeeDBOpenHelper database;
    private Long mRowId;
    ListView lv;
    public String dbstuff;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation);
        datasource = new ZomBeeDataSource(this);
        datasource.open();
        lv = (ListView) findViewById(R.id.list);
        populateList();
        
        // database = new ZomBeeDBOpenHelper(this);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_observation, menu);
        return true;
    }
    
    
    public void createObservations(View view){
		Intent intent = new Intent(this, CreateSteps.class);
		startActivity(intent);
	}
    
    public void pendingObservations(View view){
		Intent intent = new Intent(this,DisplayDatabase.class);
		startActivity(intent);
	}
    
    public void populateList(){
    	
    	Cursor note = datasource.fetchAllObservations();
        startManagingCursor(note);
        Log.d("AAA","AAA1");
//        beesnumber.setText(note.getString(
//                note.getColumnIndexOrThrow(mDbHelper.COLUMN_NUMBERBEES)));
//        Log.i(LOGTAG,"BEES NUMBER IS"+ mDbHelper.COLUMN_NUMBERBEES);
//        samplename.setText(note.getString(
//                note.getColumnIndexOrThrow(mDbHelper.COLUMN_NAME)));
//        Log.i(LOGTAG,"BEES Name IS"+ mDbHelper.COLUMN_NAME);
        Vector<String> vec = new Vector<String>();
       
        
        while(note.moveToNext()){
        	Log.d("AAA","AAA");
        	TextView tv = new TextView(getApplicationContext());
        	//Log.d("AAA", note.getString(note.getColumnIndexOrThrow(database.COLUMN_STEP1_ID)));
        	tv.setText(note.getString(note.getColumnIndexOrThrow(database.COLUMN_STEP1_ID)));
        	dbstuff = tv.getText().toString();
        	vec.add(dbstuff);
        	}
        
        long longValue = Long.parseLong(dbstuff);
        Cursor note2 = datasource.fetchStep1Note(longValue);
        startManagingCursor(note2);
    // note2.getString(note2.getColumnIndexOrThrow(database.COLUMN_NUMBERBEES));
        Log.d("Step 1 return","BEES Name IS "+  note2.getString(note2.getColumnIndexOrThrow(database.COLUMN_NAME)));
      
        Log.d("Step 1 return","BEES Name IS "+ database.COLUMN_NAME);
        
        vec.add( note2.getString(note2.getColumnIndexOrThrow(database.COLUMN_NAME)));
        
        
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, vec);
		lv.setAdapter(adapter);
    }
    
    
    
}
