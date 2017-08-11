package com.ffp.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.PageRequest;


public class PageParam implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageNumber;
    private int pageSize;
    private List<com.ffp.data.Sort> sort;
    private PageRequest pageRequest;
    
	public PageParam() {
		super();
	}
	
	
	public PageParam(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	public PageParam(int pageNumber, int pageSize, List<com.ffp.data.Sort> sortlist) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.sort = sortlist;
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
	public List<com.ffp.data.Sort> getSort() {
		return sort;
	}
	public void setSort(List<com.ffp.data.Sort> sort) {
		this.sort = sort;
	}
	
	private Sort.Direction getDirection(final String direction) {
		if (direction.equalsIgnoreCase("DESC")) {
			return Sort.Direction.DESC;
		} else {
			return Sort.Direction.ASC;
		}
	}
	
	public PageRequest getPageRequest() {
		if (this.getSort() != null && !this.getSort().isEmpty()) {
			List<Order> orderlist =new ArrayList<>();
			for (com.ffp.data.Sort sort : this.getSort()) {
				orderlist.add(new Order(getDirection(sort.getDirection()), sort.getProperty()));
			}
			pageRequest = new PageRequest(this.getPageNumber(), this.getPageSize(), new Sort(orderlist));
		} else {
			pageRequest = new PageRequest(this.getPageNumber(), this.getPageSize());
		}
		return pageRequest;
		
	}
	
	
}




