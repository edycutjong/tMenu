package com.ect.dtos;

import com.ect.interfaces.IProductPurchase;

/**
 * Class use to maintain business layer of product amount.
 * @author Edy Cu Tjong
 * @version 1.0, 01/11/12
 */
public class DTOProductPurchase extends DTOProduct implements IProductPurchase
{
	// Data member
	private Integer amount;

	@Override
	public Integer getAmount() {
		return this.amount;
	}

	@Override
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public Float getTotal() {
		return new Float(amount) * super.getPrice();
	}

} // end of class
