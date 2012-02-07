package com.ect.business;

import java.util.List;

import android.content.Context;

import com.ect.dals.DALProduct;
import com.ect.interfaces.IProduct;

/**
 * Class use to maintain business layer of product.
 * @author Edy Cu Tjong
 * @version 1.0, 01/11/12
 */
public class BLLProduct
{
	// Data member
	private Context context;
	private DALProduct dalProduct;
	
	/**
	 * Constructor specify by global information.
	 * @param context  Global information.
	 */
	public BLLProduct(Context context)
	{
		this.context = context;
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
	 * Insert product specify by id.
	 * @param name  Name product.
	 * @param price  Price product in float.
	 * @return New id of product.
	 */
	public long addProduct(String name, float price)
	{
		ensureDALProduct();
		return dalProduct.insert(name, price);
	}
	
	/**
	 * Delete product specify by id.
	 * @param id  Identifier product.
	 * @return True whether is success delete product otherwise is false.
	 */
	public boolean deleteProduct(int id)
	{
		ensureDALProduct();
		return dalProduct.delete(id) > 0;
	}
	
	/**
	 * Update product specify by id.
	 * @param id  Identifier product.
	 * @param name  Name product.
	 * @param price  Price product.
	 * @return True whether is success update product otherwise is false.
	 */
	public boolean editProduct(long id, String name, int price)
	{
		ensureDALProduct();
		return dalProduct.update(id, name, price) > 0;
	}
	
	/**
	 * Load all product in array.
	 * @return Array of interface product.
	 */
	public IProduct[] loadAllProduct()
	{
		ensureDALProduct();
		List<IProduct> listProduct = dalProduct.selectAllOrderBySequence();
		return listProduct.toArray(new IProduct[listProduct.size()]);
	}
} // end of class
