package com.ect.dals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ect.dtos.DTOOrder;
import com.ect.enumerations.EnumOrderStatus;
import com.ect.interfaces.IOrder;
import com.ect.util.Convert;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class use to connect database specific for product.
 * @author Edy Cu Tjong
 * @version 1.0, 01/10/12
 */
public class DALOrder extends SQLiteOpenHelper
{
	// Debugging
	public static boolean D = false;
	private final String TAG = "DALOrder";
	
	// Table and column name
	static final String TABLE_NAME					= "Order";
	static final String COLUMN_AS_ID				= "id";
	static final String COLUMN_AS_TIMESTAMP			= "timestamp";
	static final String COLUMN_AS_TEMP_TOTAL_AMOUNT	= "temp_total_amount";
	static final String COLUMN_AS_TEMP_TOTAL_PRICE	= "temp_total_price";
	static final String COLUMN_AS_STATUS            = "status";
	
	// Create and drop table
	static final String CREATE_TABLE = 
		"CREATE TABLE IF NOT EXISTS [" + TABLE_NAME + "]\n"
		+ "(\n"
		+ "\t["   + COLUMN_AS_ID                + "] INTEGER PRIMARY KEY\n"
		+ "\t, [" + COLUMN_AS_TIMESTAMP         + "] TIMESTAMP DEFAULT (DATETIME('NOW', 'LOCALTIME'))\n"
		+ "\t, [" + COLUMN_AS_TEMP_TOTAL_AMOUNT + "] INTEGER\n"
		+ "\t, [" + COLUMN_AS_TEMP_TOTAL_PRICE  + "] REAL\n"
		+ "\t, [" + COLUMN_AS_STATUS            + "] INTEGER\n"
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
	public DALOrder(Context context)
	{
		super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
		db = super.getWritableDatabase();
		db.close();
	}
	
	/**
	 * Insert new order specify by total amount, total price, and specific status.
	 * @param totalAmount  Total amount.
	 * @param totalPrice  Total price.
	 * @param status  Status order.
	 * @return Id new order.
	 */
	public long insert (Integer totalAmount, Float totalPrice, EnumOrderStatus status)
	{
		if (D) Log.d(TAG, "insert");
		
		// Declare and initialize id new order
		long idNewOrder = DatabaseHelper.DEFAULT_ERROR_ID_INSERT_ROW;
		
		// Initialize database
		db = super.getWritableDatabase();
		
		// Declare and set values
		ContentValues values = new ContentValues();
		values.put(COLUMN_AS_TEMP_TOTAL_AMOUNT, totalAmount);
		values.put(COLUMN_AS_TEMP_TOTAL_PRICE, totalPrice);
		values.put(COLUMN_AS_STATUS, status.getCode());
		
		// Insert new record and return latest id
		idNewOrder = db.insert(
				String.format("[%s]", TABLE_NAME)
				, null
				, values);
		
		// Close database
		db.close();
		
		return idNewOrder;
	}
	
	/**
	 * Insert new order specify by total amount and total price.
	 * @param totalAmount  Total amount.
	 * @param totalPrice  Total price.
	 * @return Id new order.
	 */
	public long insert(Integer totalAmount, Float totalPrice)
	{
		return insert(totalAmount, totalPrice, EnumOrderStatus.PROCESS_SERVED);
	} // end of method
	
	/**
	 * Delete order specify by id.
	 * @param id  Identifier order.
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
				String.format("[%s]", TABLE_NAME)
				, String.format("[%s]=?", COLUMN_AS_ID)
				, new String[] { String.valueOf(id) });
		
		// Close database
		db.close();
		
		return numberRowsDeleted;
	} // end of method
	
	/**
	 * List interface order specific by date.
	 * @param date  Specific date.
	 * @return  List collection of interface order.
	 */
	public List<IOrder> selectAllProductByDate(Date date)
	{
		if (D) Log.d(TAG, "selectAllProductByDate");
		
		// Declare and initialize list interface order.
		List<IOrder> listOrder = new ArrayList<IOrder>();
		
		// Open database for writing.
		db = super.getWritableDatabase();
		
		// Declare and initialize interface result database query
		Cursor cursor = db.query(
				String.format("[%s]", TABLE_NAME)
				, null
				, date != null ? String.format("DATE([%s])=%s", COLUMN_AS_TIMESTAMP, Convert.fromDateToDateOnly(date)) : null
				, null
				, null
				, null
				, String.format("[%s], [%s] DESC", COLUMN_AS_STATUS, COLUMN_AS_TIMESTAMP)
				);
		
		// Check existence first record and move cursor until end
		while (cursor.moveToNext())
		{
			IOrder order = new DTOOrder();
			order.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_AS_ID)));
			order.setTimestamp(Convert.fromStringToDate(cursor.getString(cursor.getColumnIndex(COLUMN_AS_TIMESTAMP))));
			order.setTempTotalAmount(cursor.getInt(cursor.getColumnIndex(COLUMN_AS_TEMP_TOTAL_AMOUNT)));
			order.setTempTotalPrice(cursor.getFloat(cursor.getColumnIndex(COLUMN_AS_TEMP_TOTAL_PRICE)));
			order.setStatus(Convert.toEnumOrderStatus(cursor.getInt(cursor.getColumnIndex(COLUMN_AS_STATUS))));
			listOrder.add(order);
		}
		
		// Close cursor
		if (cursor != null && !cursor.isClosed())
		{
			cursor.close();
		}
		
		// Close database
		db.close();
		
		return listOrder;
	} // end of method
	
	/**
	 * Select order specify by identifier.
	 * @return  Interface order.
	 */
	public IOrder findById(int id)
	{
		if (D) Log.d(TAG, "findById");
		
		// Declare and initialize interface order.
		IOrder order = null;
		
		// Open database for readable.
		db = super.getReadableDatabase();
		
		// Declare and initialize interface result database query
		Cursor cursor = db.query(
				String.format("[%s]", TABLE_NAME)
				, null
				, String.format("[%s]=%s", COLUMN_AS_ID, id)
				, null
				, null
				, null
				, null
				);
		
		// Check existence first record and move cursor at first record
		if (cursor.moveToFirst())
		{
			order = new DTOOrder();
			order.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_AS_ID)));
			order.setTimestamp(Convert.fromStringToDate(cursor.getString(cursor.getColumnIndex(COLUMN_AS_TIMESTAMP))));
			order.setTempTotalAmount(cursor.getInt(cursor.getColumnIndex(COLUMN_AS_TEMP_TOTAL_AMOUNT)));
			order.setTempTotalPrice(cursor.getFloat(cursor.getColumnIndex(COLUMN_AS_TEMP_TOTAL_PRICE)));
			order.setStatus(Convert.toEnumOrderStatus(cursor.getInt(cursor.getColumnIndex(COLUMN_AS_STATUS))));
		}
		
		// Close cursor
		if (cursor != null && !cursor.isClosed())
		{
			cursor.close();
		}
		
		// Close database
		db.close();
		
		return order;
	} // end of method
	
	/**
	 * Update order status.
	 * @param id  Identifier order.
	 * @param status  Status order need updated.
	 * @return Number rows updated.
	 */
	public int updateStatus(long id, EnumOrderStatus status)
	{
		if (D) Log.d(TAG, "updateStatus");
		
		// Declare return result
		int numberRowsUpdated = DatabaseHelper.DEFAULT_NUMBER_UPDATE_ROW;
		
		// Open database to write
		db = super.getWritableDatabase();
		
		// Declare and set content values
		ContentValues values = new ContentValues();
		values.put(COLUMN_AS_STATUS, status.getCode());
		
		numberRowsUpdated = db.update(
				String.format("[%s]", TABLE_NAME)
				, values
				, String.format("[%s]=?", COLUMN_AS_ID)
				, new String[] { Long.toString(id)}
				);
		
		// Close database
		db.close();
		
		return numberRowsUpdated;
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
