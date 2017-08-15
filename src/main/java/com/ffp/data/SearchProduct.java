package com.ffp.data;

import java.util.Date;

public class SearchProduct implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchProduct() {
	}

	public SearchProduct(String name, String type, String priceBelow, String priceAbove, String quantityAvailable,
			String available, int pageNumber, int pageSize, String country, Date availableAfter, Date availableBefore, String grade, String measurement) {
		super();
		this.name = name;
		this.type = type;
		this.priceBelow = priceBelow;
		this.priceAbove = priceAbove;
		this.quantityAvailable = quantityAvailable;
		this.available = available;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.country = country;
		this.availableAfter = availableAfter;
		this.availableBefore = availableBefore;
		this.grade = grade;
		this.measurement = measurement;
		
	}
	private String name;
	private String type;
	private String priceBelow;
	private String priceAbove;
	private String quantityAvailable;
	private String available;
	private int pageNumber;
	private int pageSize;
	private String country;
	private Date availableAfter;
	private Date availableBefore;
	private String measurement;
	
	public String getMeasurement() {
		return measurement;
	}

	public Date getAvailableAfter() {
		return availableAfter;
	}

	public Date getAvailableBefore() {
		return availableBefore;
	}

	public String getGrade() {
		return grade;
	}
	private String grade;
	
	public String getCountry() {
		return country;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

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
