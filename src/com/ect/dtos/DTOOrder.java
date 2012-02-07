package com.ect.dtos;

import java.util.Date;

import com.ect.enumerations.EnumOrderStatus;
import com.ect.interfaces.IOrder;

/**
 * Class use to implement interface order for purpose transfer object.
 * @author Edy Cu Tjong
 * @version 1.1, 01/12/12
 * @update 1.1
 * *	Change identifier data type from integer to long.
 * @version 1.2, 02/07/12
 * *	Change data member and override method to float type.
 */
public class DTOOrder implements IOrder
{
	// Data member
	private long id;
	private Date timestamp;
	private Integer tempTotalAmount;
	private Float tempTotalPrice;
	private EnumOrderStatus status;
	
	/**
	 * Default constructor.
	 */
	public DTOOrder(){}
	
	/**
	 * Constructor specify by data member.
	 * @param id  Identifier order.
	 * @param timestamp  Time stamp.
	 * @param temp_total_amount  Temporary total amount.
	 * @param temp_total_price  Temporary total price.
	 * @param status  Status order.
	 */
	public DTOOrder(
			long id
			, Date timestamp
			, Integer temp_total_amount
			, Float temp_total_price
			, EnumOrderStatus status)
	{
		this.id = id;
		this.timestamp = timestamp;
		this.tempTotalAmount = temp_total_amount;
		this.tempTotalPrice = temp_total_price;
		this.status = status;
	} // end of constructor

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public Integer getTempTotalAmount() {
		return this.tempTotalAmount;
	}

	@Override
	public Float getTempTotalPrice() {
		return this.tempTotalPrice;
	}

	@Override
	public Date getTimestamp() {
		return this.timestamp;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void setTempTotalAmount(Integer tempTotalAmount) {
		this.tempTotalAmount = tempTotalAmount;
	}

	@Override
	public void setTempTotalPrice(Float tempTotalPrice) {
		this.tempTotalPrice = tempTotalPrice;
	}

	@Override
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public EnumOrderStatus getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(EnumOrderStatus status) {
		this.status = status;
	}

} // end of class
