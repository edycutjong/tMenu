package com.ect.interfaces;

/**
 * Interface order price.
 * @author Edy Cu Tjong
 * @version 1.0, 01/10/12
 */
public interface IOrderProduct
{
	Integer getId();
	void setId(Integer id);
	
	IProduct getProduct();
	void setProduct(IProduct product);
	
	IOrder getOrder();
	void setOrder(IOrder order);
	
	Integer getAmount();
	void setAmount(Integer amount);
} // end of interface
