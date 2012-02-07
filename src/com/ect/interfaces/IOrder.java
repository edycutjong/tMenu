package com.ect.interfaces;

import java.util.Date;

import com.ect.enumerations.EnumOrderStatus;

/**
 * Interface order.
 * @author Edy Cu Tjong
 * @version 1.1, 01/12/12
 * @update 1.1
 * *	Change identifier order from Integer to long.
 * @version 1.2, 02/07/12
 * *	Change data type temp total price to float.
 */
public interface IOrder
{
	long getId();
	void setId(long id);
	
	Date getTimestamp();
	void setTimestamp(Date timestamp);
	
	Integer getTempTotalAmount();
	void setTempTotalAmount(Integer tempTotalAmount);
	
	Float getTempTotalPrice();
	void setTempTotalPrice(Float tempTotalPrice);
	
	EnumOrderStatus getStatus();
	void setStatus(EnumOrderStatus status);
} // end of interface
