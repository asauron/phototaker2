package com.example.phototaker2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;


public class DisplayMessageActivity extends ListActivity {

	private int mNoteNumber = 1;
	private ZombieDBAdapter mDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

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
