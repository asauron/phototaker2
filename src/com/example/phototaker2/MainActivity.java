package com.example.phototaker2;

/*
 * http://crw-cmu.googlecode.com/svn-history/r734/luis/PhotoIntentActivity/src/com/example/android/photobyintent/PhotoIntentActivity.java
 */

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	public File getAlbumStorageDir(String albumName) {
	    // Get the directory for the user's public pictures directory. 
	    File file = new File(Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_PICTURES), albumName);
	    if (!file.mkdirs()) {
	        Log.e("PhotoTaker1", "Directory not created");
	    }
	    return file;
	}
	

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
	
	public void showMaps(View view) {
		
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("google.navigation:q=My+Location"));
		startActivity(intent);
		
	
	}

}
