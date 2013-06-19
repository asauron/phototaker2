package com.example.phototaker2;

import java.io.File;
import java.util.GregorianCalendar;

import com.example.phototaker2.db.ZomBeeDataSource;
import com.example.phototaker2.model.Zombees;

import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.provider.CalendarContract.Events;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v4.app.NavUtils;



public class Step1 extends Activity {
	
	public static final String LOGTAG="bees bees bees   ";
	private Zombees currentZombee  = null;
	ZomBeeDataSource datasource;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Log.i(LOGTAG,"WHAT");
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_step1, menu);
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
    
    public void sendFeedback(View button) {  
        // Do click handling here  
    	Log.i(LOGTAG,"name is ");
    	
    	final EditText nameField = (EditText) findViewById(R.id.EditTextName);  
    	String name = nameField.getText().toString();  
    	currentZombee.setTitle(name);
    	
    	
    	final EditText emailField = (EditText) findViewById(R.id.EditTextEmail);  
    	String email = emailField.getText().toString();  
    	currentZombee.setNumberofbees(email);
    	
    	final EditText feedbackField = (EditText) findViewById(R.id.EditTextFeedbackBody);  
    	String feedback = feedbackField.getText().toString();
    	currentZombee.setNotes1(feedback);
    	
    	final Spinner feedbackSpinner = (Spinner) findViewById(R.id.SpinnerFeedbackType);  
    	String feedbackType = feedbackSpinner.getSelectedItem().toString();
    	currentZombee.setMethod(feedbackType);
    	
    Log.i(LOGTAG,"CLose here");
    	

   		//currentZombee = datasource.create(currentZombee);
   		//Log.i(LOGTAG,"Zombee created with id"+currentZombee.getId());

    	
    } 
    
	public void takePhoto(View view) {
		// Take photo
		File sdcard = Environment.getExternalStorageDirectory();
		String photoname = sdcard.getAbsolutePath() + File.separator + "beesphoto.png";
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri uriSavedImage = Uri.fromFile(new File(photoname));
		takePictureIntent.putExtra("output", uriSavedImage);
		startActivityForResult(takePictureIntent, RESULT_OK); /* What is RESULT_OK */
		//currentZombee.setImage1(photoname);

	}
	
	public void createCalender(View view){
		Intent calIntent = new Intent(Intent.ACTION_EDIT);
		calIntent.setType("vnd.android.cursor.item/event");

		calIntent.putExtra(Events.TITLE, "Time to take the zombee picture!");
		calIntent.putExtra(Events.DESCRIPTION, "Please mention the stage of the pupae development");
		GregorianCalendar calDate = new GregorianCalendar();
		calIntent.putExtra(Events.RRULE, "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");
		calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
		calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
		     calDate.getTimeInMillis());
		calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
		     calDate.getTimeInMillis());
		startActivity(calIntent);
	}
	
	
	
	
	
	
    
	

}
