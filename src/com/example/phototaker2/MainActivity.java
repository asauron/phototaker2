package com.example.phototaker2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void takePhoto(View view) {
		// Take photo
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivity(takePictureIntent);
		
	}
	
	public void sayMessage(View view) {
		// do nothing
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		String message = "Something good to say";
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
		
	}

}
