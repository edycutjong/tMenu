package com.ect.tmenurestaurant;

import android.app.Activity;
import android.os.Bundle;

/**
 * Class use to maintain product.
 * @author Edy Cu Tjong
 * @version 1.0, 01/13/12
 */
public class Product extends Activity
{
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        
        
//    	Intent intent = new Intent(this, Product.class);
//    	intent.putExtra(Menu.INTENT_DATA_INPUT_POSITION, position);
//    	intent.putExtra(Menu.INTENT_DATA_INPUT_AMOUNT, 1);
//    	startActivityForResult(intent, Menu.REQUEST_CODE_INPUT);

//    	Intent intent = new Intent(this, Product.class);
//    	intent.putExtra(Menu.INTENT_DATA_INPUT_POSITION, getIntent().getExtras().getInt(Menu.INTENT_DATA_INPUT_POSITION));
//    	intent.putExtra(Menu.INTENT_DATA_INPUT_AMOUNT, 1);
//        setResult(RESULT_OK, intent);
//        super.onBackPressed();
    }
} // end of class
