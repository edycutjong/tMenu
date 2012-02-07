package com.ect.interfaces;

/**
 * Interface product amount.
 * @author Edy Cu Tjong
 * @version 1.0, 01/11/12
 */
public interface IProductPurchase extends IProduct
{
	Integer getAmount();
	void setAmount(Integer amount);
	
	Float getTotal();
} // end of interface
