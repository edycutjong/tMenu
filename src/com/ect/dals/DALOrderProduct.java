package com.ect.dals;

import java.util.ArrayList;
import java.util.List;

import com.ect.dtos.DTOProductPurchase;
import com.ect.interfaces.IProductPurchase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class use to connect database specific for order product.
 * @author Edy Cu Tjong
 * @version 1.0, 01/10/12
 */
public class DALOrderProduct extends SQLiteOpenHelper
{
	// Debugging
	public static boolean D = false;
	private final String TAG = "DALOrderProduct";
	
	// Table and column name
	static final String TABLE_NAME				= "OrderProduct";
	static final String COLUMN_AS_ORDER_ID		= "order_id";
	static final String COLUMN_AS_PRODUCT_ID	= "product_id";
	static final String COLUMN_AS_AMOUNT		= "amount";
	
	// Create and drop table
	static final String CREATE_TABLE = 
		"CREATE TABLE IF NOT EXISTS [" + TABLE_NAME + "]\n"
		+ "(\n"
		+ "\t["   + COLUMN_AS_ORDER_ID   + "] INTEGER\n"
		+ "\t, [" + COLUMN_AS_PRODUCT_ID + "] INTEGER\n"
		+ "\t, [" + COLUMN_AS_AMOUNT     + "] INTEGER\n"
		+ "\t, FOREIGN KEY([" + COLUMN_AS_ORDER_ID   + "]) REFERENCES [" + DALOrder.TABLE_NAME   + "]([" + DALOrder.COLUMN_AS_ID   + "])\n"
		+ "\t, FOREIGN KEY([" + COLUMN_AS_PRODUCT_ID + "]) REFERENCES [" + DALProduct.TABLE_NAME + "]([" + DALProduct.COLUMN_AS_ID + "])\n"
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
	public DALOrderProduct(Context context)
	{
		super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
		db = super.getWritableDatabase();
		db.close();
	}
	
	/**
	 * Insert all by id order and product has been purchase.
	 * @param idOrder  Identifier order.
	 * @param productPurchase  Product purchase.
	 * @return  Number of record has been inserted.
	 */
	public Integer insertAllHasBeenPurchase(long idOrder, IProductPurchase[] productPurchase)
	{
		if (D) Log.d(TAG, "insertAllHasBeenPurchase");
		
		// Declare and initialize return result
		Integer numberInsertedRow = 0;
		
		// Initialize database
		db = super.getWritableDatabase();
		
		// Insert all has been purchase
		for (IProductPurchase purchase : productPurchase)
		{
			// Check whether any product has been purchase
			if (purchase.getAmount() > 0)
			{
				// Declare and set values
				ContentValues values = new ContentValues();
				values.put(COLUMN_AS_ORDER_ID, idOrder);
				values.put(COLUMN_AS_PRODUCT_ID, purchase.getId());
				values.put(COLUMN_AS_AMOUNT, purchase.getAmount());
				
				// Insert new record and return id
				long idNew = db.insert(TABLE_NAME, null, values);
				
				// Check whether any error insert row
				if (idNew != DatabaseHelper.DEFAULT_ERROR_ID_INSERT_ROW)
				{
					++numberInsertedRow;
				}
			} // end of if
		} // end of for
		
		// Close database
		db.close();
		
		return numberInsertedRow;
	} // end of method
	
	/**
	 * Select all purchase by order in sequence.
	 * @param id  Identifier order.
	 * @return List interface product purchase.
	 */
	public List<IProductPurchase> selectAllPurchaseByOrderInSequence(int id)
	{
		if (D) Log.d(TAG, "selectAllPurchaseByOrderInSequence");
		
		// Declare list collection of product purchase
		List<IProductPurchase> listProductPurchase = new ArrayList<IProductPurchase>();
		
		// Initial database
		db = super.getReadableDatabase();
		
		// Declare and initialize interface result database query
		Cursor cursor = db.query(
				String.format(
						"[%s] LEFT JOIN [%s] ON [%s].[%s] = [%s].[%s]"
						, DALOrderProduct.TABLE_NAME
						, DALProduct.TABLE_NAME
						, DALOrderProduct.TABLE_NAME
						, DALOrderProduct.COLUMN_AS_ORDER_ID
						, DALProduct.TABLE_NAME
						, DALProduct.COLUMN_AS_ID
						)
				, null
				, String.format(
						"[%s].[%s]=?"
						, DALOrderProduct.TABLE_NAME
						, DALOrderProduct.COLUMN_AS_ORDER_ID
						)
				, new String[] { String.valueOf(id) }
				, null
				, null
				, null
				);
		
		// Check existence first record and move cursor at first record
		if (cursor.moveToFirst())
		{
			// Declare and set product purchase 
			IProductPurchase productPurchase = new DTOProductPurchase();
			productPurchase.setId(cursor.getInt(1)); // Product id
			productPurchase.setAmount(cursor.getInt(2)); // Amount
			productPurchase.setName(cursor.getString(4)); // Name
			productPurchase.setPrice(cursor.getFloat(5)); // Price
			productPurchase.setSequence(cursor.getInt(6)); // Sequence
			
			// Add to list product purchase
			listProductPurchase.add(productPurchase);
		}
		
		// Close cursor
		if (cursor != null && !cursor.isClosed())
		{
			cursor.close();
		}
		
		// Close database
		db.close();
		
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
