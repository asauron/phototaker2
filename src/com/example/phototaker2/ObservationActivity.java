package com.example.phototaker2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ObservationActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation);
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
		Intent intent = new Intent(this, PendingObservation.class);
		startActivity(intent);
	}
    
    
}
