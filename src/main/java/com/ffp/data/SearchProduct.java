package com.ffp.data;

public class SearchProduct implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchProduct() {
	}

	public SearchProduct(String name, String type, String priceBelow, String priceAbove, String quantityAvailable,
			String available) {
		super();
		this.name = name;
		this.type = type;
		this.priceBelow = priceBelow;
		this.priceAbove = priceAbove;
		this.quantityAvailable = quantityAvailable;
		this.available = available;
	}
	private String name;
	private String type;
	private String priceBelow;
	private String priceAbove;
	private String quantityAvailable;
	private String available;
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getPriceBelow() {
		return priceBelow;
	}
	public String getPriceAbove() {
		return priceAbove;
	}
	public String getQuantityAvailable() {
		return quantityAvailable;
	}
	public String getAvailable() {
		return available;
	}
}
