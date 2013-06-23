package com.example.phototaker2.db;

import java.util.ArrayList;
import java.util.List;

import com.example.phototaker2.model.Zombees;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ZomBeeDataSource {

   public static final String LOGTAG="Zombee Watch data source method";
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	
	private static final String[] allColumns = {
		ZomBeeDBOpenHelper.COLUMN_DATE1,
		ZomBeeDBOpenHelper.COLUMN_DATE2,
		ZomBeeDBOpenHelper.COLUMN_DATE3,
		ZomBeeDBOpenHelper.COLUMN_IMAGE1,
		ZomBeeDBOpenHelper.COLUMN_IMAGE2,
		ZomBeeDBOpenHelper.COLUMN_IMAGE3,
		ZomBeeDBOpenHelper.COLUMN_ID,
		ZomBeeDBOpenHelper.COLUMN_LATTITUDE,
		ZomBeeDBOpenHelper.COLUMN_LONGITUDE,
		ZomBeeDBOpenHelper.COLUMN_METHOD,
		ZomBeeDBOpenHelper.COLUMN_NAME,
		ZomBeeDBOpenHelper.COLUMN_NOTES1,
		ZomBeeDBOpenHelper.COLUMN_NOTES2,
		ZomBeeDBOpenHelper.COLUMN_NOTES3,
		ZomBeeDBOpenHelper.COLUMN_NUMBERBEES,
		ZomBeeDBOpenHelper.COLUMN_PUPAE,
		ZomBeeDBOpenHelper.COLUMN_FLIES
		};
	
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
	
	public Zombees create(Zombees zombee){
		Log.i(LOGTAG, "Create got called");
		ContentValues values = new ContentValues();
		values.put(ZomBeeDBOpenHelper.COLUMN_NAME, zombee.getTitle());
		values.put(ZomBeeDBOpenHelper.COLUMN_NUMBERBEES, zombee.getNumberofbees());
		values.put(ZomBeeDBOpenHelper.COLUMN_METHOD, zombee.getMethod());
		values.put(ZomBeeDBOpenHelper.COLUMN_IMAGE1, zombee.getImage1());
		values.put(ZomBeeDBOpenHelper.COLUMN_IMAGE2, zombee.getImage2());
		values.put(ZomBeeDBOpenHelper.COLUMN_IMAGE3, zombee.getImage3());
		values.put(ZomBeeDBOpenHelper.COLUMN_NOTES1, zombee.getNotes1());
		values.put(ZomBeeDBOpenHelper.COLUMN_NOTES2, zombee.getNotes2());
		values.put(ZomBeeDBOpenHelper.COLUMN_NOTES3, zombee.getNotes3());
		values.put(ZomBeeDBOpenHelper.COLUMN_LATTITUDE, zombee.getLattitude());
		values.put(ZomBeeDBOpenHelper.COLUMN_LONGITUDE, zombee.getLongitude());
		values.put(ZomBeeDBOpenHelper.COLUMN_PUPAE, zombee.getPuape());
		values.put(ZomBeeDBOpenHelper.COLUMN_FLIES, zombee.getFlies());
		values.put(ZomBeeDBOpenHelper.COLUMN_DATE1, zombee.getDate1());
		values.put(ZomBeeDBOpenHelper.COLUMN_DATE2, zombee.getDate2());
		values.put(ZomBeeDBOpenHelper.COLUMN_DATE3, zombee.getDate3());
		long insertid = database.insert(ZomBeeDBOpenHelper.TABLE_Zombees, null, values);
		zombee.setId(insertid);
		return zombee;
		
	}
	
	public List<Zombees> findAll() {
		List<Zombees> zombees = new ArrayList<Zombees>();
		
		Cursor cursor = database.query(ZomBeeDBOpenHelper.TABLE_Zombees, allColumns, 
				null, null, null, null, null);
		
		Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				Zombees zombee = new Zombees();
				zombee.setId(cursor.getLong(cursor.getColumnIndex(ZomBeeDBOpenHelper.COLUMN_ID)));
				zombee.setTitle(cursor.getString(cursor.getColumnIndex(ZomBeeDBOpenHelper.COLUMN_NAME)));
				zombee.setNumberofbees(cursor.getString(cursor.getColumnIndex(ZomBeeDBOpenHelper.COLUMN_NUMBERBEES)));
				zombee.setImage1(cursor.getString(cursor.getColumnIndex(ZomBeeDBOpenHelper.COLUMN_IMAGE1)));
				zombee.setNotes1(cursor.getString(cursor.getColumnIndex(ZomBeeDBOpenHelper.COLUMN_NOTES1)));
				zombees.add(zombee);
			}
		}
		return zombees;		
	}
	
}
