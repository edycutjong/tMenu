package com.ect.tmenurestaurant;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Class use to bundle order in layout.
 * @author Edy Cu Tjong
 * @version 1.0, 01/16/12
 */
class OrderWrapper
{
	// Data member
	private View base;
	private TextView time			= null;
	private TextView total_amount	= null;
	private TextView total_price	= null;
	private ImageView status		= null;
	
	/**
	 * Constructor specify by view base.
	 * @param base  View base.
	 */
	OrderWrapper(View base)
	{
		this.base = base;
	}
	
	/**
	 * Get text view of time.
	 * @return  Text view of time.
	 */
	TextView getTime()
	{
		if (time == null)
		{
			time = (TextView)base.findViewById(R.id.textViewOrderTime);
		}
		
		return time;
	}
	
	/**
	 * Get text view of total amount.
	 * @return  Text view of total amount.
	 */
	TextView getTotalAmount()
	{
		if (total_amount == null)
		{
			total_amount = (TextView)base.findViewById(R.id.textViewOrderTotalAmount);
		}
		
		return total_amount;
	}
	
	/**
	 * Get text view price.
	 * @return  Text view of total price.
	 */
	TextView getTotalPrice()
	{
		if (total_price == null)
		{
			total_price = (TextView)base.findViewById(R.id.textViewOrderTotalPrice);
		}
		
		return total_price;
	}
	
	/**
	 * Get image view status.
	 * @return  Image view of status order.
	 */
	ImageView getStatus()
	{
		if (status == null)
		{
			status = (ImageView)base.findViewById(R.id.imageViewOrderStatus);
		}
		
		return status;
	}
} // end of class
