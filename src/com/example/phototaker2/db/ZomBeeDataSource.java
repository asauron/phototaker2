package com.example.phototaker2.db;

import java.util.ArrayList;
import java.util.List;

import com.example.phototaker2.model.Zombees;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract.Columns;
import android.util.Log;

public class ZomBeeDataSource {

   public static final String LOGTAG="Zombee Watch data source method ";
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	
	private static final String[] allColumnsStep1 = {
		ZomBeeDBOpenHelper.COLUMN_DATE1,
		ZomBeeDBOpenHelper.COLUMN_IMAGE1,
		ZomBeeDBOpenHelper.COLUMN_ID,
		ZomBeeDBOpenHelper.COLUMN_LATTITUDE,
		ZomBeeDBOpenHelper.COLUMN_LONGITUDE,
		ZomBeeDBOpenHelper.COLUMN_METHOD,
		ZomBeeDBOpenHelper.COLUMN_NAME,
		ZomBeeDBOpenHelper.COLUMN_NOTES1,
		ZomBeeDBOpenHelper.COLUMN_NUMBERBEES,
		
		};
	
	private static final String[] allColumnsStep2 = {
		
		ZomBeeDBOpenHelper.COLUMN_DATE2,
		ZomBeeDBOpenHelper.COLUMN_IMAGE2,
		ZomBeeDBOpenHelper.COLUMN_ID,
	    ZomBeeDBOpenHelper.COLUMN_NOTES2,
		ZomBeeDBOpenHelper.COLUMN_PUPAE
		};
	
	private static final String[] allColumnsStep3 = {
	
		ZomBeeDBOpenHelper.COLUMN_DATE3,
		ZomBeeDBOpenHelper.COLUMN_IMAGE3,
		ZomBeeDBOpenHelper.COLUMN_ID,
		ZomBeeDBOpenHelper.COLUMN_NOTES3,
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
	
	public Zombees createStep1(Zombees zombee){
		Log.i(LOGTAG, "Create got called");
		ContentValues values = new ContentValues();
		values.put(ZomBeeDBOpenHelper.COLUMN_NAME, zombee.getTitle());
		values.put(ZomBeeDBOpenHelper.COLUMN_NUMBERBEES, zombee.getNumberofbees());
		values.put(ZomBeeDBOpenHelper.COLUMN_METHOD, zombee.getMethod());
		values.put(ZomBeeDBOpenHelper.COLUMN_IMAGE1, zombee.getImage1());
		//values.put(ZomBeeDBOpenHelper.COLUMN_IMAGE2, zombee.getImage2());
		//values.put(ZomBeeDBOpenHelper.COLUMN_IMAGE3, zombee.getImage3());
		values.put(ZomBeeDBOpenHelper.COLUMN_NOTES1, zombee.getNotes1());
		//values.put(ZomBeeDBOpenHelper.COLUMN_NOTES2, zombee.getNotes2());
		//values.put(ZomBeeDBOpenHelper.COLUMN_NOTES3, zombee.getNotes3());
		values.put(ZomBeeDBOpenHelper.COLUMN_LATTITUDE, zombee.getLattitude());
		values.put(ZomBeeDBOpenHelper.COLUMN_LONGITUDE, zombee.getLongitude());
		//values.put(ZomBeeDBOpenHelper.COLUMN_PUPAE, zombee.getPuape());
		//values.put(ZomBeeDBOpenHelper.COLUMN_FLIES, zombee.getFlies());
		values.put(ZomBeeDBOpenHelper.COLUMN_DATE1, zombee.getDate1());
		//values.put(ZomBeeDBOpenHelper.COLUMN_DATE2, zombee.getDate2());
		//values.put(ZomBeeDBOpenHelper.COLUMN_DATE3, zombee.getDate3());
		long insertid = database.insert(ZomBeeDBOpenHelper.TABLE_Zombees_Step1, null, values);
		zombee.setId(insertid);
		return zombee;
	}
	
	public Zombees createStep2(Zombees zombee){
		Log.i(LOGTAG, "Create step 2 got called");
		ContentValues values = new ContentValues();
		
		values.put(ZomBeeDBOpenHelper.COLUMN_IMAGE2, zombee.getImage2());
		values.put(ZomBeeDBOpenHelper.COLUMN_NOTES2, zombee.getNotes2());
	    values.put(ZomBeeDBOpenHelper.COLUMN_PUPAE, zombee.getPuape());
		values.put(ZomBeeDBOpenHelper.COLUMN_DATE2, zombee.getDate2());

		long insertid = database.insert(ZomBeeDBOpenHelper.TABLE_Zombees_Step2, null, values);
		zombee.setId(insertid);
		return zombee;
	}
	
	public Zombees createStep3(Zombees zombee){
		Log.i(LOGTAG, "Create  step 3 got called");
		ContentValues values = new ContentValues();
		values.put(ZomBeeDBOpenHelper.COLUMN_IMAGE3, zombee.getImage3());
		values.put(ZomBeeDBOpenHelper.COLUMN_NOTES3, zombee.getNotes3());
		values.put(ZomBeeDBOpenHelper.COLUMN_FLIES, zombee.getFlies());
		values.put(ZomBeeDBOpenHelper.COLUMN_DATE3, zombee.getDate3());
		long insertid = database.insert(ZomBeeDBOpenHelper.TABLE_Zombees_Step3, null, values);
		zombee.setId(insertid);
		return zombee;
	}
	

	
	public Cursor fetchAllNotesStep1() {

        return database.query(ZomBeeDBOpenHelper.TABLE_Zombees_Step1, allColumnsStep1 , null, null, null, null, null);
    }
	
	 public boolean deleteStep1Row(long rowId) {

	        return database.delete(ZomBeeDBOpenHelper.TABLE_Zombees_Step1, ZomBeeDBOpenHelper.COLUMN_ID + "=" + rowId, null) > 0;
	    }

	   

	    public Cursor fetchStep1Note(long rowId) throws SQLException {

	        Cursor mCursor =

	            database.query(true, ZomBeeDBOpenHelper.TABLE_Zombees_Step1, allColumnsStep1 , ZomBeeDBOpenHelper.COLUMN_ID + "=" + rowId, null,
	                    null, null, null, null);
	        if (mCursor != null) {
	            mCursor.moveToFirst();
	        }
	        return mCursor;

	    }

//	    public boolean updateStep1Note(long rowId, String title, String body) {
//	        ContentValues args = new ContentValues();
//	        args.put(KEY_TITLE, title);
//	        args.put(KEY_BODY, body);
//
//	        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
//	    }
	    
	    
	
}
