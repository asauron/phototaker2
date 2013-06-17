package com.example.phototaker2;

import java.io.File;

import com.example.phototaker2.model.Zombees;

import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v4.app.NavUtils;



public class Step1 extends Activity {
	
	
	private Zombees currentZombee  = null;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        
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
    	
    	final CheckBox responseCheckbox = (CheckBox) findViewById(R.id.CheckBoxResponse);  
    	boolean bRequiresResponse = responseCheckbox.isChecked(); 
    	
    	
    	
    } 
    
	public void takePhoto(View view) {
		// Take photo
		File sdcard = Environment.getExternalStorageDirectory();
		String photoname = sdcard.getAbsolutePath() + File.separator + "beesphoto.png";
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri uriSavedImage = Uri.fromFile(new File(photoname));
		takePictureIntent.putExtra("output", uriSavedImage);
		startActivityForResult(takePictureIntent, RESULT_OK); /* What is RESULT_OK */
		currentZombee.setImage1(photoname);

	}
    

}
