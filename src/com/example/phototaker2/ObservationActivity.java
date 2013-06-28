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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
		Intent intent = new Intent(this, Step1.class);
		startActivity(intent);
	}
    
    public void pendingObservations(View view){
		Intent intent = new Intent(this,DisplayDatabase.class);
		startActivity(intent);
	}
    
    public void populateList(){
    	
    	Cursor note = datasource.fetchAllObservations();
        startManagingCursor(note);

        Vector<String> vec = new Vector<String>();
       
        
        while(note.moveToNext()){
        	Log.d("AAA","AAA");
        	TextView tv = new TextView(getApplicationContext());
        	//Log.d("AAA", note.getString(note.getColumnIndexOrThrow(database.COLUMN_STEP1_ID)));
        	tv.setText(note.getString(note.getColumnIndexOrThrow(database.COLUMN_NAME)));
        	dbstuff = tv.getText().toString();
        	vec.add(dbstuff);
        	}
        
        
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, vec);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Log.d("observation method", (((TextView) arg1).getText().toString()));
				//retrieve next todo step from db
				int nextStep = datasource.getNextStepfromName(((TextView) arg1).getText().toString());
				Log.d("AAAA","next :"+nextStep);
				if (nextStep == 2){
					int observationId = datasource.getObservationIdByName(((TextView) arg1).getText().toString());
					Intent intent = new Intent(getApplicationContext(), Step2.class);
					intent.putExtra("obId",observationId);
					startActivity(intent);
				}
				if (nextStep == 3){
					int observationId = datasource.getObservationIdByName(((TextView) arg1).getText().toString());
					Intent intent = new Intent(getApplicationContext(), Step3.class);
					intent.putExtra("obId",observationId);
					startActivity(intent);
				}
						
				
			}
			
		});
			
		}
		
    }
    
    
    

