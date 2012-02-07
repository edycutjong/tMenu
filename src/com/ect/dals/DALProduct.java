package com.ect.dals;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ect.dtos.DTOProduct;
import com.ect.dtos.DTOProductPurchase;
import com.ect.interfaces.IProduct;
import com.ect.interfaces.IProductPurchase;

/**
 * Class use to connect database specific for product.
 * @author Edy Cu Tjong
 * @version 1.0, 01/10/12
 * @version 1.1, 02/07/12
 * *	Add method insert using float 
 */
public class DALProduct extends SQLiteOpenHelper
{
	// Debugging
	public static boolean D = false;
	private final String TAG = "DALProduct";
	
	// Constant value
	private final int START_PRODUCT_AMOUNT = 0;
	
	// Table and column name
	static final String TABLE_NAME			= "Product";
	static final String COLUMN_AS_ID		= "id";
	static final String COLUMN_AS_NAME		= "name";
	static final String COLUMN_AS_PRICE		= "price";
	static final String COLUMN_AS_SEQUENCE	= "sequence";
	
	// Create and drop table
	static final String CREATE_TABLE = 
		"CREATE TABLE IF NOT EXISTS [" + TABLE_NAME + "]\n" 
		+ "(\n"
		+ "\t["   + COLUMN_AS_ID       + "] INTEGER PRIMARY KEY\n"
		+ "\t, [" + COLUMN_AS_NAME     + "] TEXT UNIQUE NOT NULL\n"
		+ "\t, [" + COLUMN_AS_PRICE    + "] REAL NOT NULL\n"
		+ "\t, [" + COLUMN_AS_SEQUENCE + "] INTEGER\n"
		+ ");"
		;
	static final String DROP_TABLE = 
		"DROP TABLE IF EXISTS [" + TABLE_NAME + "]"
		;
	
	// Data member
	private SQLiteDatabase db;
	
	/**
	 * Constructor specify by global information.
	 * @param context  Global information.
	 */
	public DALProduct(Context context)
	{
		super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
		db = super.getWritableDatabase();
		db.close();
	}
	
	/**
	 * Delete product specify by id.
	 * @param id  Identifier product.
	 * @return Number of rows has been deleted.
	 */
	public int delete(int id)
	{
		if (D) Log.d(TAG, "delete");
		
		// Declare and initialize number of rows has been deleted.
		int numberRowsDeleted = DatabaseHelper.DEFAULT_NUMBER_DELETE_ROW;
		
		// Open database for writing.
		db = super.getWritableDatabase();
		
		// Number of rows deleted.
		numberRowsDeleted = db.delete(
				TABLE_NAME
				, String.format("[%s]=?", COLUMN_AS_ID)
				, new String[] { String.valueOf(id) }
				);
		
		// Close database
		db.close();
		
		return numberRowsDeleted;
	} // end of method
	
	/**
	 * Insert new product specify by name and price.
	 * @param name  Name product.
	 * @param price  Price product in float.
	 * @return Id new product.
	 */
	public long insert(String name, float price)
	{
		if (D) Log.d(TAG, "insert(float)");
		
		// Declare and initialize id new product
		long idNewProduct = DatabaseHelper.DEFAULT_ERROR_ID_INSERT_ROW;
		
		// Initialize database
		db = super.getWritableDatabase();
		
		// Declare and set values
		ContentValues values = new ContentValues();
		values.put(COLUMN_AS_NAME, name);
		values.put(COLUMN_AS_PRICE, price);
		
		// Insert new record and return latest id
		idNewProduct = db.insert(TABLE_NAME, null, values);
		
		// Close database
		db.close();
		
		return idNewProduct;
	} // end of method
	
	/**
	 * Update record specify by id product.
	 * @param id  Identifier product.
	 * @param name  Name product.
	 * @param price  Price product.
	 * @return Number updated row.
	 */
	public int update(long id, String name, int price)
	{
		if (D) Log.d(TAG, "update");
		
		// Declare and initialize number updated row
		int numberUpdatedRow = DatabaseHelper.DEFAULT_NUMBER_UPDATE_ROW;
		
		// Initialize database
		db = super.getWritableDatabase();
		
		// Declare and set values
		ContentValues values = new ContentValues();
		if (null != name) values.put(COLUMN_AS_NAME, name);
		values.put(COLUMN_AS_PRICE, price);
		
		// Update record and return number rows effected
		numberUpdatedRow = db.update(
				TABLE_NAME
				, values
				, String.format("[%s]=?", COLUMN_AS_ID)
				, new String[] { String.valueOf(id) }
				);
		
		// Close database
		db.close();
		
		return numberUpdatedRow; 
	} // end of method
	
	/**
	 * Select all product order by sequence.
	 * @return  List collection of interface product.
	 */
	public List<IProduct> selectAllOrderBySequence()
	{
		if (D) Log.d(TAG, "selectAllOrderBySequence");
		
		// Declare list collection of product
		List<IProduct> listProduct = new ArrayList<IProduct>();
		
		// Initial database
		db = super.getReadableDatabase();
		
		// Declare and initialize interface result database query
		Cursor cursor = db.query(
				TABLE_NAME
				, null
				, null
				, null
				, null
				, null
				, String.format("[%s]", COLUMN_AS_SEQUENCE)
				);
		
		// Check existence first record and move cursor at first record
		if (cursor.moveToFirst())
		{
			do
			{
				// Declare and initialize temporary interface counter
				IProduct product = new DTOProduct(
						cursor.getLong(cursor.getColumnIndex(COLUMN_AS_ID))
						, cursor.getString(cursor.getColumnIndex(COLUMN_AS_NAME))
						, cursor.getFloat(cursor.getColumnIndex(COLUMN_AS_PRICE))
						, cursor.getInt(cursor.getColumnIndex(COLUMN_AS_SEQUENCE))
						);
				
				// Adding list product
				listProduct.add(product);
			} while(cursor.moveToNext());
			
			// Close cursor
			if (cursor != null && !cursor.isClosed())
			{
				cursor.close();
			}
		} // end of if
		
		return listProduct;
	} // end of method

	/**
	 * Select all product purchase order by sequence.
	 * @return  List collection of interface product purchase.
	 */
	public List<IProductPurchase> selectAllPurchaseInSequence()
	{
		if (D) Log.d(TAG, "selectAllPurchaseOrderBySequence");
		
		// Declare list collection of product
		List<IProductPurchase> listProductPurchase = new ArrayList<IProductPurchase>();
		
		try{
		// Initial database
		db = super.getReadableDatabase();
		
		// Declare and initialize interface result database query
		Cursor cursor = db.query(
				TABLE_NAME
				, null
				, null
				, null
				, null
				, null
				, String.format("[%s]", COLUMN_AS_SEQUENCE)
				);
		
		// Check existence first record and move cursor at first record
		if (cursor.moveToFirst())
		{
			do
			{
				// Declare and initialize temporary interface counter
				IProductPurchase productPurchase = new DTOProductPurchase();
				productPurchase.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_AS_ID)));
				productPurchase.setName(cursor.getString(cursor.getColumnIndex(COLUMN_AS_NAME)));
				productPurchase.setPrice(cursor.getFloat(cursor.getColumnIndex(COLUMN_AS_PRICE)));
				productPurchase.setSequence(cursor.getInt(cursor.getColumnIndex(COLUMN_AS_SEQUENCE)));
				productPurchase.setAmount(START_PRODUCT_AMOUNT);
				
				// Adding list product
				listProductPurchase.add(productPurchase);
			} while(cursor.moveToNext());
			
			// Close cursor
			if (cursor != null && !cursor.isClosed())
			{
				cursor.close();
			}
		} // end of if
		}
		catch (Exception e)
		{
			Log.e(TAG, "error");
		}
		
		return listProductPurchase;
	} // end of method
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		if (D) Log.d(TAG, "onCreate");
		
		try
		{
			db.beginTransaction();
			db.execSQL(CREATE_TABLE);
			db.setTransactionSuccessful();
		}
		catch (SQLException e)
		{
			Log.e(TAG, e.toString());
		}
		catch (IllegalStateException e)
		{
			Log.e(TAG, e.toString());
		}
		finally
		{
			db.endTransaction();
		}
	} // end of method
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		if (D) Log.d(TAG, "onUpgrade");
		
		// Check whether database required to update to new version
		if (oldVersion > newVersion)
		{
			try
			{
				db.beginTransaction();
				db.execSQL(DROP_TABLE);
				db.setTransactionSuccessful();
			}
			catch (SQLException e)
			{
				Log.e(TAG, e.toString());
			}
			catch (IllegalStateException e)
			{
				Log.e(TAG, e.toString());
			}
			finally
			{
				db.endTransaction();
			}
			
			this.onCreate(db);
		} // end of if
	} // end of method
	
} // end of class
