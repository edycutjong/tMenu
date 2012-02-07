package com.ect.tmenurestaurant;

import com.ect.business.BLLProductPurchase;
import com.ect.interfaces.IProductPurchase;
import com.ect.tmenurestaurant.activity.InputAmountActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Class use to show list product to input purchase.
 * @author Edy Cu Tjong
 * @version 1.0, 01/13/12
 */
public class Menu extends ListActivity
{
	// Request code
	public static final int REQUEST_CODE_INPUT = 0;
	
	// Intent data
	public static final String INTENT_DATA_INPUT_POSITION	= "index";
	public static final String INTENT_DATA_INPUT_AMOUNT		= "amount";
	
	// Data member
	private IProductPurchase[] mProductsPurchase;
	private BLLProductPurchase mBLLProductPurchase;
	private ImageButton mImageButtonAdd;
	private TextView mTextViewCalculateAmount;
	private TextView mTextViewCalculateTotal;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        // Initialize data member
        mBLLProductPurchase = new BLLProductPurchase(this);
        mImageButtonAdd = (ImageButton)findViewById(R.id.bAdd);
        mTextViewCalculateAmount = (TextView)findViewById(R.id.calculateAmount);
        mTextViewCalculateTotal = (TextView)findViewById(R.id.calculateTotal);
        
        // Set list adapter by menu adapter
        reset();
        
        // Set event click
        mImageButtonAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				startActivity(new Intent(Menu.this, Order.class));
				if (0f == Float.valueOf(mTextViewCalculateAmount.getText().toString()))
				{
					Toast.makeText(Menu.this, "No item been order.", Toast.LENGTH_SHORT).show();
				}
				else
				{
					mBLLProductPurchase.submitProductsPurchase(mProductsPurchase);
					Toast.makeText(Menu.this, "Submit Order", Toast.LENGTH_SHORT).show();
					reset();
				}
			}
		});
    }
    
    /**
     * Class use to maintain list menu adapter.
     * @author Edy Cu Tjong
     * @version 1.0, 01/13/12
     */
    class MenuAdapter extends ArrayAdapter<IProductPurchase>
    {
    	/**
    	 * Default constructor.
    	 */
    	MenuAdapter()
    	{
    		super(Menu.this, R.layout.menu_item, mProductsPurchase);
    	}
    	
    	/**
    	 * Override model array adapter to get view.
    	 */
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent)
    	{
    		View row = convertView;
    		MenuWrapper wrapper = null;
    		
    		if (row == null)
    		{
    			LayoutInflater inflater = getLayoutInflater();
    			row = inflater.inflate(R.layout.menu_item, parent, false);
    			wrapper = new MenuWrapper(row);
    			row.setTag(wrapper);
    		}
    		else
    		{
    			wrapper = (MenuWrapper)row.getTag();
    		}
    		
    		// Get interface product purchase 
    		IProductPurchase productPurchase = getModel(position);
    		
    		// Set product
    		wrapper.getProductName().setText(productPurchase.getName());
    		wrapper.getProductPrice().setText(String.format("%.2f", productPurchase.getPrice()));
    		wrapper.getProductAmount().setText(String.valueOf(productPurchase.getAmount()));
    		wrapper.getProductTotal().setText(String.format("%.2f", productPurchase.getTotal()));
    		
    		return (row);
    	}
    } // end of class
    
    /**
     * Get model of interface product purchase specify by position of array.
     * @param position  Position of array.
     * @return Interface product purchase
     */
    private IProductPurchase getModel(int position)
    {
    	return (IProductPurchase) ((MenuAdapter)getListAdapter()).getItem(position);
    }
    
    @Override
    protected void onListItemClick(ListView parent, View v, int position, long id)
    {
    	int amount = mProductsPurchase[position].getAmount();
    	amount = amount == 0 ? 1 : amount;
    	Intent intent = new Intent(this, InputAmountActivity.class);
    	intent.putExtra(INTENT_DATA_INPUT_POSITION, position);
    	intent.putExtra(INTENT_DATA_INPUT_AMOUNT, amount);
    	startActivityForResult(intent, REQUEST_CODE_INPUT);
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	// Check request code
    	switch (requestCode)
    	{
    	case REQUEST_CODE_INPUT:
        	// Check result code
    		switch (resultCode)
    		{
    		case RESULT_OK:
    			int index = data.getExtras().getInt(INTENT_DATA_INPUT_POSITION);
    			int amount = data.getExtras().getInt(INTENT_DATA_INPUT_AMOUNT);
    			mProductsPurchase[index].setAmount(amount);
    	        setListAdapter(new MenuAdapter());
    	        calculate();
    			break;
    		case RESULT_CANCELED:
    			break;
    		}
    		break;
    	}
    }
    
    /**
     * Calculate amount and total.
     */
    protected void calculate()
    {
    	int calAmount = 0;
    	float calTotal = 0f;
    	
    	for (IProductPurchase product : mProductsPurchase)
    	{
    		calAmount += product.getAmount();
    		calTotal += product.getTotal();
    	}
    	
    	mTextViewCalculateAmount.setText(String.valueOf(calAmount));
    	mTextViewCalculateTotal.setText(String.format("%.2f", calTotal));
    }
    
    /**
     * Reset menu order.
     */
    protected void reset()
    {
    	mProductsPurchase = mBLLProductPurchase.loadProductsPuchase();
        setListAdapter(new MenuAdapter());
        mTextViewCalculateAmount.setText("0");
        mTextViewCalculateTotal.setText("0.00");
    }
} // end of class
