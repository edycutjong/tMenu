package com.ect.dals;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Database helper to manage database application.
 * @author Edy Cu Tjong
 * @version 1.1, 11/22/11
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
	// Debugging
	public static boolean D = false;
	private final String TAG = "DatabaseHelper";
	
	// Database name and version
	public static final String DATABASE_NAME = "TORDER.db";
	public static final int DATABASE_VERSION = 1;
	
	// Default value for insert, delete and update
	static final int DEFAULT_ERROR_ID_INSERT_ROW = -1;
	static final int DEFAULT_NUMBER_UPDATE_ROW = 0;
	static final int DEFAULT_NUMBER_DELETE_ROW = 0;
	
	// List create and drop table
	private final String[] listCreateTable = new String[] {
			DALProduct.CREATE_TABLE,
			DALOrder.CREATE_TABLE,
			DALOrderProduct.CREATE_TABLE
	};
	private final String[] listDropTable = new String[] {
			DALOrderProduct.DROP_TABLE,
			DALOrder.DROP_TABLE,
			DALProduct.DROP_TABLE
	};
	
	/**
	 * Constructor specify by global information application.
	 * @param context  Global information application.
	 */
	public DatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * Creating table / updating table
	 */
	public void create()
	{
		if (D) Log.d(TAG, "create");
		
		SQLiteDatabase db = super.getWritableDatabase();
		db.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		if (D) Log.d(TAG, "onCreate");
		
		for (String statement : listCreateTable)
		{
			db.beginTransaction();
			try
			{
				db.execSQL(statement);
				db.setTransactionSuccessful();
			}
			catch (SQLException ex)
			{
				Log.e(TAG, ex.toString());
			}
			catch (IllegalStateException ex)
			{
				Log.e(TAG, ex.toString());
			}
			finally
			{
				db.endTransaction();
			}
		} // end of for
	} // end of method

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		if (D) Log.d(TAG, "onUpgrade");
		
		if (newVersion > oldVersion)
		{
			for (String statement : listDropTable)
			{
				db.beginTransaction();
				try
				{
					db.execSQL(statement);
					db.setTransactionSuccessful();
				}
				catch (SQLException ex)
				{
					Log.e(TAG, ex.toString());
				}
				catch (IllegalStateException ex)
				{
					Log.e(TAG, ex.toString());
				}
				finally
				{
					db.endTransaction();
				}
			} // end of for
		} // end of if
	} // end of method
} // end of class
