package com.ect.tmenurestaurant;

import com.ect.business.BLLOrder;
import com.ect.interfaces.IOrder;
import com.ect.util.Convert;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Class use to show list order has been submit.
 * @author Edy Cu Tjong
 * @version 1.0, 01/13/12
 */
public class Order extends ListActivity
{
	// Data member
	private IOrder[] mOrders;
	private BLLOrder mBLLOrder;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        
        // Initialize data member
        mBLLOrder = new BLLOrder(this);
        mOrders = mBLLOrder.loadAllOrders();
        
        // Set list adapter
        setListAdapter(new OrderAdapter());
    }
    
    /**
     * Get model of interface order specify by position of array.
     * @param position  Position of array.
     * @return  Interface order.
     */
    private IOrder getModel(int position)
    {
    	return (IOrder) ((OrderAdapter)getListAdapter()).getItem(position);
    }
    
    /**
     * Class use to maintain order adapter.
     * @author Edy Cu Tjong
     * @version 1.0, 01/16/12
     */
    class OrderAdapter extends ArrayAdapter<IOrder>
    {
    	// Data member
    	
    	/**
    	 * Default constructor.
    	 */
    	OrderAdapter()
    	{
    		super(Order.this, R.layout.order_item, mOrders);
    	}
    	
    	/**
    	 * Override model array adapter to get view.
    	 */
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent)
    	{
    		View row = convertView;
    		OrderWrapper wrapper = null;
    		
    		if (row == null)
    		{
    			LayoutInflater inflater = getLayoutInflater();
    			row = inflater.inflate(R.layout.order_item, parent, false);
    			wrapper = new OrderWrapper(row);
    			row.setTag(wrapper);
    		}
    		else
    		{
    			wrapper = (OrderWrapper)row.getTag();
    		}
    		
    		// Get interface order at current position
    		IOrder order = getModel(position);
    		
    		// Set order
    		wrapper.getTotalAmount().setText(String.valueOf(order.getTempTotalAmount()));
    		wrapper.getTotalPrice().setText(String.format("%.2f", order.getTempTotalPrice()));
    		wrapper.getTime().setText(Convert.fromDateToStatement(order.getTimestamp()));
    		switch (order.getStatus())
    		{
    		case PROCESS_SERVED: wrapper.getStatus().setImageResource(R.drawable.status_process_served);
    			break;
    		case ALREADY_SERVED: wrapper.getStatus().setImageResource(R.drawable.status_already_served);
    			break;
    		case ALREADY_PAY: wrapper.getStatus().setImageResource(R.drawable.status_already_pay);
    			break;
    		}
    		
    		return (row);
    	}
    } // end of class
    
    @Override
    protected void onListItemClick(ListView parent, View v, int position, long id)
    {
    	IOrder order = mOrders[position];
    	
    	switch (order.getStatus())
    	{
    	case PROCESS_SERVED: mBLLOrder.updateOrderStatusAlreadyServed(order.getId());
    		break;
    	case ALREADY_SERVED: mBLLOrder.updateOrderStatusAlreadyPay(order.getId());
    		break;
    	}

        mOrders = mBLLOrder.loadAllOrders();
    	setListAdapter(new OrderAdapter());
    }
    
} // end of class