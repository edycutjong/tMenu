package com.ect.business;

import java.util.Date;
import java.util.List;

import android.content.Context;

import com.ect.dals.DALOrder;
import com.ect.dals.DALOrderProduct;
import com.ect.dals.DALProduct;
import com.ect.enumerations.EnumOrderStatus;
import com.ect.interfaces.IOrder;
import com.ect.interfaces.IProductPurchase;

/**
 * Class use to maintain business layer of order.
 * @author Edy Cu Tjong
 * @version 1.0, 01/12/12
 */
public class BLLOrder
{
	// Data member
	private Context context;
	private DALOrder dalOrder;
	private DALProduct dalProduct;
	private DALOrderProduct dalOrderProduct;
	
	/**
	 * Constructor specify by context.
	 * @param context  Global information.
	 */
	public BLLOrder(Context context)
	{
		this.context = context;
	}
	
	/**
	 * Ensure creation data access layer of order product.
	 */
	protected void ensureDALOrderProduct()
	{
		if (dalOrderProduct == null)
		{
			dalOrderProduct = new DALOrderProduct(this.context);
		}
	}
	
	/**
	 * Ensure creation data access layer of order.
	 */
	protected void ensureDALOrder()
	{
		if (dalOrder == null)
		{
			dalOrder = new DALOrder(this.context);
		}
	}
	
	/**
	 * Ensure creation data access layer of product.
	 */
	protected void ensureDALProduct()
	{
		if (dalProduct == null)
		{
			dalProduct = new DALProduct(this.context);
		}
	}
	
	/**
	 * Delete order specify by identifier.
	 * @param id  Identifier order.
	 * @return True whether is success to delete otherwise is false.
	 */
	public boolean deleteOrder(int id)
	{
		ensureDALOrder();
		return dalOrder.delete(id) > 0;
	}
	
	/**
	 * Load order specify by identifier.
	 * @param id  Identifier order.
	 * @return Interface order.
	 */
	public IOrder loadOrder(int id)
	{
		ensureDALOrder();
		return dalOrder.findById(id);
	}
	
	/**
	 * Load all order in array from database.
	 * @return  Interface order of array.
	 */
	public IOrder[] loadAllOrders()
	{
		ensureDALOrder();
		List<IOrder> listOrder = dalOrder.selectAllProductByDate(null);
		return listOrder.toArray(new IOrder[listOrder.size()]);
	}
	
	/**
	 * Load all order in array at today from database.
	 * @return  Interface order of array.
	 */
	public IOrder[] loadAllOrdersByToday()
	{
		ensureDALOrder();
		List<IOrder> listOrder = dalOrder.selectAllProductByDate(new Date());
		return listOrder.toArray(new IOrder[listOrder.size()]);
	}
	
	/**
	 * Load all order in array from database specify by date.
	 * @param date  Specific date.
	 * @return  Interface order of array.
	 */
	public IOrder[] loadAllOrdersByDate(Date date)
	{
		ensureDALOrder();
		List<IOrder> listOrder = dalOrder.selectAllProductByDate(date);
		return listOrder.toArray(new IOrder[listOrder.size()]);
	}
	
	/**
	 * Load products purchase.
	 * @param id  Identifier order
	 * @return Array of interface product purchase.
	 */
	public IProductPurchase[] loadProductsPurchase(int id)
	{
		ensureDALOrderProduct();
		List<IProductPurchase> listProductPurchase = dalOrderProduct.selectAllPurchaseByOrderInSequence(id);
		return listProductPurchase.toArray(new IProductPurchase[listProductPurchase.size()]);
	}
	
	/**
	 * Update order status to already served.
	 * @param id  Identifier of order.
	 * @return  True whether is updated otherwise is false.
	 */
	public boolean updateOrderStatusAlreadyServed(long id)
	{
		ensureDALOrderProduct();
		return dalOrder.updateStatus(id, EnumOrderStatus.ALREADY_SERVED) > 0;
	}
	
	/**
	 * Update order status to already been pay.
	 * @param id  Identifier of order.
	 * @return  True whether is updated otherwise is false.
	 */
	public boolean updateOrderStatusAlreadyPay(long id)
	{
		ensureDALOrderProduct();
		return dalOrder.updateStatus(id, EnumOrderStatus.ALREADY_PAY) > 0;
	}
	
} // end of class
