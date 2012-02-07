package com.ect.tmenurestaurant;

import com.ect.business.BLLProduct;
import com.ect.dals.DatabaseHelper;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainTab extends TabActivity {
	private BLLProduct mBLLProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create database
		createDatabase();
		
		mBLLProduct = new BLLProduct(this);
		mBLLProduct.addProduct("Meal Chiken 5pc.", 3.49f);
		mBLLProduct.addProduct("KFC Snacker", 1.49f);
		mBLLProduct.addProduct("4 Biscuits", 2.69f);
		mBLLProduct.addProduct("Small Popcorn", 1.49f);

        final TabHost tabHost = getTabHost();
        
        tabHost.addTab(tabHost.newTabSpec("stock")
        	.setIndicator(getString(R.string.title_menu))
        	.setContent(new Intent(this, Menu.class)
        		.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        
        tabHost.addTab(tabHost.newTabSpec("incoming")
        	.setIndicator(getString(R.string.title_order))
        	.setContent(new Intent(this, Order.class)
    			.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
    }
    
    /**
     * Create database.
     */
    public void createDatabase()
    {
        // Create database
        DatabaseHelper db = new DatabaseHelper(this);
        db.create();
    }
    
} // end of class
