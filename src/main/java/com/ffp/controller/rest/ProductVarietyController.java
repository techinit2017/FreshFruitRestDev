package com.ffp.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ffp.data.ProductVariety;
import com.ffp.service.ProductVarietyService;

@RestController
@RequestMapping(value = "/productVariety")
@CrossOrigin(origins = "*")
public class ProductVarietyController {
	
	@Autowired
	private ProductVarietyService productVarietyService;
	
	@RequestMapping(value = "/getProductVarietyByID/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductVariety> getProductVarietyByID(@PathVariable("id") Integer id) {
		HttpStatus httpStatus = HttpStatus.OK;
		ProductVariety productVariety = productVarietyService.findOne(id);
		if (productVariety == null) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<ProductVariety>(productVariety, httpStatus);
	}
	
	@RequestMapping(value = "/getProductVarietyByVariety/{variety}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductVariety>> getProductVarietyByVariety(@PathVariable("variety") String variety) {
		HttpStatus httpStatus = HttpStatus.OK;
		List<ProductVariety> productVarietys = productVarietyService.findByVariety(variety);
		if (productVarietys == null || productVarietys.isEmpty()) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<ProductVariety>>(productVarietys, httpStatus);
	}
	
	@RequestMapping(value = "/getProductVarietyByProduct/{product}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductVariety>> getProductVarietyByProduct(@PathVariable("product") String product) {
		HttpStatus httpStatus = HttpStatus.OK;
		List<ProductVariety> productVarietys = productVarietyService.findByProduct(product);
		if (productVarietys == null || productVarietys.isEmpty()) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<ProductVariety>>(productVarietys, httpStatus);
	}
	
	@RequestMapping(value = "/getAllProductVariety", method = RequestMethod.GET)
	public ResponseEntity<List<ProductVariety>> getAllProductVariety() {
		HttpStatus httpStatus = HttpStatus.OK;
		List<ProductVariety> productVarietys = productVarietyService.findAll();
		if (productVarietys == null || productVarietys.isEmpty()) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<ProductVariety>>(productVarietys, httpStatus);
	}
	
	@RequestMapping(value = "/getProductNames", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getProductNames() {
		List<String> productVarietys = productVarietyService.findDistinctProductNames();
		return new ResponseEntity<List<String>>(productVarietys, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProductByType/{type}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductVariety>> getProductByType(@PathVariable("type") String type) {
		HttpStatus httpStatus = HttpStatus.OK;
		List<ProductVariety> productVarietys = productVarietyService.findByType(type);
		if (productVarietys == null || productVarietys.isEmpty()) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<ProductVariety>>(productVarietys, httpStatus);
	}
}
