package com.ffp.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ffp.data.Product;
import com.ffp.data.SearchProduct;
import com.ffp.service.ProductService;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin(origins = "*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/getAllProducts", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProducts() {

		List<Product> allProducts = productService.findAll();
		return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProductByID/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductByID(@PathVariable("id") Integer id) {
		Product product = productService.findOne(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProductByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductByName(@PathVariable("name") String name) {
		List<Product> products = productService.findByName(name);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProductByType/{type}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductByType(@PathVariable("type") String type) {
		List<Product> products = productService.findByType(type);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProductBySellerId/{sellerId}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductBySellerId(@PathVariable("sellerId") String sellerId) {
		List<Product> products = productService.findBySellerId(sellerId);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProductByAvailable/{available}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductBySellerId(@PathVariable("available") int available) {
		List<Product> products = productService.findByAvailable(available);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCount", method = RequestMethod.GET)
	public ResponseEntity<Long> getCount() {
		long count = productService.count();
		return new ResponseEntity<Long>(count, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public ResponseEntity<Void> save(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		Product newProduct = productService.save(product);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/getProductByID/{id}").buildAndExpand(newProduct.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		Product newProduct = productService.save(product);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/getProductByID/{id}").buildAndExpand(newProduct.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		productService.delete(product);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		productService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	/**
	 * 
	 * @param searchProduct
	 * @return HttpStatus.OK when exact search is match else HttpStatus.PARTIAL_CONTENT
	 */
	@RequestMapping(value = "/getSearchProducts", method = RequestMethod.POST)
	public ResponseEntity<List<Product>> getSearchProducts(@RequestBody SearchProduct searchProduct) {
		List<Product> allProducts = productService.findSearch(searchProduct, false);
		HttpStatus status = HttpStatus.OK;
		if (allProducts.size() == 0) {
			allProducts = productService.findSearch(searchProduct, true);
			status = HttpStatus.PARTIAL_CONTENT;
		}
		return new ResponseEntity<List<Product>>(allProducts, status);
	}

}
