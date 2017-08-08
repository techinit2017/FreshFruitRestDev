package com.ffp.data;

import java.io.Serializable;
import java.util.List;


public class PageParam implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageNumber;
    private int pageSize;
    private List<Sort> sort;
    
	public PageParam() {
		super();
	}
	public PageParam(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	public PageParam(int pageNumber, int pageSize, List<Sort> sort) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.sort = sort;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Sort> getSort() {
		return sort;
	}
	public void setSort(List<Sort> sort) {
		this.sort = sort;
	}
	
	
	
	
}




