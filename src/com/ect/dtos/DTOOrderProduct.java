package com.ect.dtos;

import com.ect.interfaces.IOrder;
import com.ect.interfaces.IOrderProduct;
import com.ect.interfaces.IProduct;

/**
 * Class use to implement interface order product.
 * @author Edy Cu Tjong
 * @version 1.0, 01/10/12
 */
public class DTOOrderProduct implements IOrderProduct
{
	// Data member
	private Integer id;
	private IOrder order;
	private IProduct product;
	private Integer amount;
	
	/**
	 * Default constructor.
	 */
	public DTOOrderProduct(){}
	
	/**
	 * Constructor specify by data member.
	 * @param id  Identifier order product.
	 * @param order  Interface order.
	 * @param product  Interface product.
	 */
	public DTOOrderProduct(
			Integer id
			, IOrder order
			, IProduct product)
	{
		this.id = id;
		this.order = order;
		this.product = product;
	} // end of method
	
	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public IOrder getOrder() {
		return this.order;
	}

	@Override
	public IProduct getProduct() {
		return this.product;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public void setOrder(IOrder order) {
		this.order = order;
	}

	@Override
	public void setProduct(IProduct product) {
		this.product = product;
	}

	@Override
	public Integer getAmount() {
		return this.amount;
	}

	@Override
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

} // end of class
