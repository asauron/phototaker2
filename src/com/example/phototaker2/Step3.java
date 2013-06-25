package com.example.phototaker2;

import java.io.File;

import com.example.phototaker2.db.ZomBeeDataSource;
import com.example.phototaker2.model.Zombees;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class Step3 extends Activity {

	ZomBeeDataSource datasource;
	public static final String LOGTAG="Step 3";
	private static final int MY_DATE_DIALOG_ID = 0;
	
	Zombees currentZombee  = new Zombees();
	

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        datasource = new ZomBeeDataSource(this);
        datasource.open();
        Log.i(LOGTAG,"WHAT 2");
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
    	Zombees currentZombee  = new Zombees();
    	final EditText nameField = (EditText) findViewById(R.id.EditTextName);  
    	String name = nameField.getText().toString();  
    	currentZombee.setFlies(name);
    	
    	//final EditText emailField = (EditText) findViewById(R.id.EditTextEmail);  
    	//String email = emailField.getText().toString();  
    	final EditText feedbackField = (EditText) findViewById(R.id.EditTextFeedbackBody);  
    	String feedback = feedbackField.getText().toString();
    	currentZombee.setNotes3(feedback);
    	
    	//final Spinner feedbackSpinner = (Spinner) findViewById(R.id.SpinnerFeedbackType);  
    	//String feedbackType = feedbackSpinner.getSelectedItem().toString();
    	
    	final CheckBox responseCheckbox = (CheckBox) findViewById(R.id.CheckBoxResponse);  
    	boolean bRequiresResponse = responseCheckbox.isChecked(); 
    	
    	
    	  Log.i(LOGTAG,"CLose here");
  	    //	mDbHelper.createNote(name,email);

  	   	    currentZombee = datasource.createStep3(currentZombee);
  	   		
  	   		//Log.i(LOGTAG,"failed here");
  	   		Log.i(LOGTAG,"STEP_3: Zombee created with id"+currentZombee.getId());
    	
    	
    } 
    
	public void takePhoto(View view) {
		// Take photo
		Zombees currentZombee  = new Zombees();
		File sdcard = Environment.getExternalStorageDirectory();
		String photoname = sdcard.getAbsolutePath() + File.separator + "beesphoto3.png";
		currentZombee.setImage3(photoname);
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri uriSavedImage = Uri.fromFile(new File(photoname));
		takePictureIntent.putExtra("output", uriSavedImage);
		startActivityForResult(takePictureIntent, RESULT_OK); /* What is RESULT_OK */
		

	}
	
	public void onDateDialogButtonClick(View v) {
	     showDialog(MY_DATE_DIALOG_ID);
	}

	protected Dialog onCreateDialog(int id) {
		switch(id) {
		case MY_DATE_DIALOG_ID:
		 DatePickerDialog dateDlg = new DatePickerDialog(this,
		         new DatePickerDialog.OnDateSetListener() {
		         public void onDateSet(DatePicker view, int year,
		                                             int monthOfYear, int dayOfMonth)
		         {
		                    Time chosenDate = new Time();
		                    chosenDate.set(dayOfMonth, monthOfYear, year);
		                    long dtDob = chosenDate.toMillis(true);
		                    CharSequence strDate = DateFormat.format("MMMM dd, yyyy", dtDob);
		                    Log.i(LOGTAG,"date will be saved as"+ strDate.toString());
		                    currentZombee.setDate3(strDate.toString());
		                    Toast.makeText(Step3.this,
		                         "Date picked: " + strDate, Toast.LENGTH_SHORT).show();
		        }}, 2011,0, 1);
		      dateDlg.setMessage("Enter today's date");
		    
		      return dateDlg;
		}
		return null;
	}

}
