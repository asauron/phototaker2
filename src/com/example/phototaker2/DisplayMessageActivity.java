package com.example.phototaker2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;


public class DisplayMessageActivity extends ListActivity {

	public static final int INSERT_ID = Menu.FIRST;

	private int mNoteNumber = 1;
	private ZombieDBAdapter mDbHelper;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	    // Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	    // Create the text view
	    // TextView textView = new TextView(this);
	    // textView.setTextSize(40);
	    // textView.setText(message);

	    // Set the text view as the activity layout
	    // setContentView(textView);

        setContentView(R.layout.notepad_list);
        mDbHelper = new ZombieDBAdapter(this);
        mDbHelper.open();
        createNote(message);
    }

    private void createNote(String note) {
        String noteName =  " Title " + mNoteNumber++;
        mDbHelper.createNote(noteName, note);
    }

}
