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
		ProductVariety productVariety = productVarietyService.findOne(id);
		return new ResponseEntity<ProductVariety>(productVariety, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProductVarietyByVariety/{variety}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductVariety>> getProductVarietyByVariety(@PathVariable("variety") String variety) {
		List<ProductVariety> productVarietys = productVarietyService.findByVariety(variety);
		return new ResponseEntity<List<ProductVariety>>(productVarietys, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProductVarietyByProduct/{product}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductVariety>> getProductVarietyByProduct(@PathVariable("product") String product) {
		List<ProductVariety> productVarietys = productVarietyService.findByProduct(product);
		return new ResponseEntity<List<ProductVariety>>(productVarietys, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllProductVariety", method = RequestMethod.GET)
	public ResponseEntity<List<ProductVariety>> getAllProductVariety() {
		List<ProductVariety> productVarietys = productVarietyService.findAll();
		return new ResponseEntity<List<ProductVariety>>(productVarietys, HttpStatus.OK);
	}
}
