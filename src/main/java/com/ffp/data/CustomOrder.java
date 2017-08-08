package com.ffp.data;

import java.io.Serializable;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.NullHandling;
import org.springframework.data.domain.Sort.Order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomOrder extends Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	public CustomOrder(@JsonProperty("direction") Direction direction,@JsonProperty("property") String property, @JsonProperty("nullHandling") NullHandling nullHandlingHint) {
		super(direction, property, nullHandlingHint);
	}
}
