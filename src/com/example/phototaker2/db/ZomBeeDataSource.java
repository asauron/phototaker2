package com.example.phototaker2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ZomBeeDataSource {

   public static final String LOGTAG="Zombee Watch";
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	
	public ZomBeeDataSource(Context context){
		dbhelper = new ZomBeeDBOpenHelper(context);
	}
	
	public void open() {
		Log.i(LOGTAG, "Database opened");
		database = dbhelper.getWritableDatabase();
	}

	public void close() {
		Log.i(LOGTAG, "Database closed");		
		dbhelper.close();
	}
	
}
