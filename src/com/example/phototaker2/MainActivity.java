package com.example.phototaker2;

/*
 * http://crw-cmu.googlecode.com/svn-history/r734/luis/PhotoIntentActivity/src/com/example/android/photobyintent/PhotoIntentActivity.java
 * http://stackoverflow.com/a/7266616/18852
 * http://www.mkyong.com/android/how-to-send-email-in-android/
 * http://stackoverflow.com/questions/5151159/calling-one-activity-from-another
 * http://stackoverflow.com/questions/10189044/android-activity-flow
 * http://www.vogella.com/articles/AndroidLocationAPI/article.html
 * http://www.androidhive.info/2011/10/android-listview-tutorial/
 * http://stackoverflow.com/questions/4080165/android-onlistitemclick-in-activity
 */

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import com.example.phototaker2.db.ZomBeeDataSource;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	private ZombieDBAdapter mDbHelper;
	ZomBeeDataSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        //to be deleted
		mDbHelper = new ZombieDBAdapter(this);
        mDbHelper.open();
        
        //might need another datasource if i'm going to use 
        //multiple tables
        datasource = new ZomBeeDataSource(this);
        datasource.open();
        
        
	}

	private File getDir() {
		File sdDir = Environment
		   .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		return new File(sdDir, "BeesPhotos");
	}


	@SuppressLint("SimpleDateFormat")
	public void takePhoto(View view) {
		// Take photo
		File pictureFileDir = getDir();
	    if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
	        Log.d("takePhoto", "Can't create directory to save image.");
	        return;
	    }


	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
	    String date = dateFormat.format(new Date());
	    String photoFile = "Picture_" + date + ".png";
		//File sdcard = Environment.getExternalStorageDirectory();
		String photoname = pictureFileDir.getPath() + File.separator + photoFile;
	    // String photoname = sdcard.getAbsolutePath() + File.separator + photoFile;
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri uriSavedImage = Uri.fromFile(new File(photoname));
		takePictureIntent.putExtra("output", uriSavedImage);
		startActivityForResult(takePictureIntent, RESULT_OK);
		
		// Add the Photo Name and Photo Path to the Database here.
		mDbHelper.createNote(photoFile, photoname);
		

	}

	public void sayMessage(View view) {
		// do nothing
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		String message = "Something good to say";
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);

	}

	public void showMaps(View view) {

		Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
				Uri.parse("geo:0,0?q=My+Location"));
		startActivity(intent);

	}

	public void sendEmail(View view) {
		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL, new String[]{"asauron@gmail.com"});
		email.setType("message/rfc822");
		startActivity(Intent.createChooser(email, "Choose an Email client :"));

	}

	public void activityCycle(View view) {
		// Activity Workflow
		// Capture lat, long and store it for email.
		// Capture photo and store it for email.
		// Open email with lat, long and photo attached.

		Intent intent = new Intent(this, CycleActivity.class);
		Bundle b = new Bundle();
		b.putString("message", "some message to send");
		intent.putExtras(b);
		startActivity(intent);

	}

	public void takeTutorial(View view) {
		Intent intent = new Intent(this, TakeTutorial.class);
		startActivity(intent);

	}

	public void displayNotification(View view) {
		Intent intent = new Intent(this, CreateNotificationActivity.class);
		startActivity(intent);
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
	
	public void startObservation(View view){
		Intent intent = new Intent(this, ObservationActivity.class);
		startActivity(intent);
	}

}
