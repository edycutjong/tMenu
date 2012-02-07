package com.ect.dtos;

import com.ect.interfaces.IProduct;

/**
 * Data transfer object to implement interface product.
 * @author Edy Cu Tjong
 * @version 1.12, 01/12/12
 * @update 1.2
 * *	Change id data type from integer to long.
 * @update 1.1
 * *	Adding and implement data member sequence.
 * @version 1.3, 02/07/12
 * *	Change data type price to float.
 */
public class DTOProduct implements IProduct
{
	// Data member
	private long id;
	private String name;
	private Float price;
	private Integer sequence;
	
	/**
	 * Default constructor.
	 */
	public DTOProduct(){}
	
	/**
	 * Constructor specify by id, name, and price.
	 * @param id  Identifier product.
	 * @param name  Name product.
	 * @param price  Price product.
	 * @param sequence  sequence number has.
	 */
	public DTOProduct(long id, String name, Float price, Integer sequence)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.sequence = sequence;
	} // end of constructor

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Float getPrice() {
		return this.price;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public Integer getSequence() {
		return this.sequence;
	}

	@Override
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

} // end of class
