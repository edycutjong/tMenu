package com.ect.business;

import java.util.List;

import android.content.Context;

import com.ect.dals.DALOrder;
import com.ect.dals.DALOrderProduct;
import com.ect.dals.DALProduct;
import com.ect.interfaces.IProductPurchase;

/**
 * Classe use to maintain business layer of product purchase.
 * @author Edy Cu Tjong
 * @version 1.0, 01/11/12
 * @version 1.1, 02/07/12
 * *	Change result to interface to float.
 */
public class BLLProductPurchase
{
	// Data member
	private Context context;
	private DALOrder dalOrder;
	private DALOrderProduct dalOrderProduct;
	private DALProduct dalProduct;
	
	/**
	 * Constructor specify by global information.
	 * @param context  Global information.
	 */
	public BLLProductPurchase(Context context)
	{
		this.context = context;
	}
	
	/**
	 * Ensure data access layer of product.
	 */
	protected void ensureDALOrder()
	{
		if (dalOrder == null)
		{
			dalOrder = new DALOrder(this.context);
		}
	}
	
	/**
	 * Ensure data access layer of order product.
	 */
	protected void ensureDALOrderProduct()
	{
		if (dalOrderProduct == null)
		{
			dalOrderProduct = new DALOrderProduct(this.context);
		}
	}
	
	/**
	 * Ensure data access layer of product.
	 */
	protected void ensureDALProduct()
	{
		if (dalProduct == null)
		{
			dalProduct = new DALProduct(this.context);
		}
	}
	
	/**
	 * Load products purchase.
	 */
	public IProductPurchase[] loadProductsPuchase()
	{
		ensureDALProduct();
		List<IProductPurchase> listProductPurchase = dalProduct.selectAllPurchaseInSequence();
		return listProductPurchase.toArray(new IProductPurchase[listProductPurchase.size()]);
	}
	
	/**
	 * Submit products has been purchase.
	 * @param productsPurchase  Products purchase in array.
	 * @return  True whether any product purchase has been inserted.
	 */
	public boolean submitProductsPurchase(IProductPurchase[] productsPurchase)
	{
		// Number row inserted
		Integer numberRowInserted = 0;

		// Check whether any product has been purchase
		if (isAnyProductPurchase(productsPurchase))
		{
			ensureDALOrder();
			ensureDALOrderProduct();
				
			// Declare and initialize summary of amount and total
			Integer sumAmount	= 0;
			Float sumTotal		= 0f;
			
			// Set summary amount and total
			for (IProductPurchase purchase : productsPurchase)
			{
				sumAmount	+= purchase.getAmount();
				sumTotal	+= purchase.getTotal();
			}
			
			// Insert new order
			long idNewOrder = dalOrder.insert(sumAmount, sumTotal);
			
			// Insert all order product
			numberRowInserted = dalOrderProduct.insertAllHasBeenPurchase(idNewOrder, productsPurchase);
		}
		
		return numberRowInserted > 0;
	} // end of method
	
	/**
	 * Check whether any product purchase.
	 * @param productsPurchase  Product purchase.
	 * @return True whether is any product has been purchase otherwise is false.
	 */
	protected boolean isAnyProductPurchase(IProductPurchase[] productsPurchase)
	{
		boolean hasBeenPurchase = false;
		
		// Set summary amount and total
		for (IProductPurchase purchase : productsPurchase)
		{
			if (purchase.getAmount() > 0)
			{
				hasBeenPurchase = true;
				break;
			}
		}
		
		return hasBeenPurchase;
	} // end of method
	
} // end of class
