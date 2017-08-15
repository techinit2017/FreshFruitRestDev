package com.ffp.data;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomPageRequest extends PageRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonCreator
	public CustomPageRequest(@JsonProperty("pageNumber") int page, @JsonProperty("pageSize") int size,
			@JsonProperty("direction") Direction direction, @JsonProperty("property") String... property) {
		super(page, size, direction, property);
		//this(page, size);
		// super(page, size, direction, property);
	}

	public CustomPageRequest(@JsonProperty("pageNumber") int page, @JsonProperty("pageSize") int size) {
		super(page, size);
	}

	public CustomPageRequest(@JsonProperty("pageNumber") int page, @JsonProperty("pageSize") int size, String direction,
			@JsonProperty("property") String... property) {
		super(page, size);
	}

}
