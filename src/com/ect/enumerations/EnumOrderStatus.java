package com.ect.enumerations;

/**
 * Enumeration order status.
 * @author Edy Cu Tjong
 * @version 1.0, 01/15/12
 */
public enum EnumOrderStatus
{
	PROCESS_SERVED (0)
	, ALREADY_SERVED (1)
	, ALREADY_PAY (2);
	
	private int code;
	EnumOrderStatus(int code) { this.code = code; }
	public int getCode() { return this.code; }
} // end of enumeration
