package com.ect.tmenurestaurant;

import android.view.View;
import android.widget.TextView;

/**
 * Class use to bundle each record in menu.
 * @author Edy Cu Tjong
 * @version 1.0, 01/13/12
 */
class MenuWrapper
{
	// Data member
	private View base;
	private TextView name	= null;
	private TextView price	= null;
	private TextView amount	= null;
	private TextView total	= null;
	
	/**
	 * Constructor specify by view base.
	 * @param base  View base.
	 */
	MenuWrapper(View base)
	{
		this.base = base;
	}
	
	/**
	 * Get text view of menu product name.
	 * @return  Product name.
	 */
	TextView getProductName()
	{
		if (name == null)
		{
			name = (TextView)base.findViewById(R.id.textViewMenuName);
		}
		
		return name;
	}
	
	/**
	 * Get text view of menu price.
	 * @return  Text view of price.
	 */
	TextView getProductPrice()
	{
		if (price == null)
		{
			price = (TextView)base.findViewById(R.id.textViewMenuPrice);
		}
		
		return price;
	}
	
	/**
	 * Get text view of menu product amount.
	 * @return  Text view of amount.
	 */
	TextView getProductAmount()
	{
		if (amount == null)
		{
			amount = (TextView)base.findViewById(R.id.textViewMenuAmount);
		}
		
		return amount;
	}
	
	/**
	 * Get text view of menu product total price.
	 * @return Text view of total price.
	 */
	TextView getProductTotal()
	{
		if (total == null)
		{
			total = (TextView)base.findViewById(R.id.textViewMenuTotal);
		}
		
		return total;
	}
	
} // end of class
