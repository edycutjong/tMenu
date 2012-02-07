package com.ect.interfaces;

/**
 * Interface product.
 * @author Edy Cu Tjong
 * @version 1.1, 01/12/12
 * @update 1.1
 * *	Change id data type from integer to long
 * @version 1.2, 02/07/12
 * *	Change price to float data type.
 */
public interface IProduct
{
	long getId();
	void setId(long id);
	
	String getName();
	void setName(String name);
	
	Float getPrice();
	void setPrice(Float price);
	
	Integer getSequence();
	void setSequence(Integer sequence);
} // end of interface
