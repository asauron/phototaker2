package com.example.phototaker2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;

public class CreateSteps extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_steps);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_steps, menu);
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
    
    public void step1(View view){
		Intent intent = new Intent(this, Step1.class);
		startActivity(intent);
	}
    public void step2(View view){
		Intent intent = new Intent(this, Step2.class);
		startActivity(intent);
	}
    public void step3(View view){
		Intent intent = new Intent(this, Step3.class);
		startActivity(intent);
	}

}
