package com.ffp.controller.rest;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.ffp.data.Demand;
import com.ffp.data.PageParam;
import com.ffp.data.Product;
import com.ffp.data.SearchProduct;
import com.ffp.service.ProductService;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin(origins = "*",exposedHeaders ="RECORD_COUNT")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/getAllProducts", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProducts() {
		HttpStatus httpStatus = HttpStatus.OK;
		List<Product> allProducts = productService.findAll();
		if (allProducts == null || allProducts.isEmpty()) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<Product>>(allProducts, httpStatus);
	}
	
	@RequestMapping(value = "/getProductByID/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductByID(@PathVariable("id") Integer id) {
		HttpStatus httpStatus = HttpStatus.OK;
		Product product = productService.findOne(id);
		if (product == null) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Product>(product, httpStatus);
	}
	
	@RequestMapping(value = "/getProductByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductByName(@PathVariable("name") String name) {
		HttpStatus httpStatus = HttpStatus.OK;
		List<Product> products = productService.findByName(name);
		if (products == null || products.isEmpty()) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<Product>>(products, httpStatus);
	}
	
	@RequestMapping(value = "/getProductByType/{type}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductByType(@PathVariable("type") String type) {
		HttpStatus httpStatus = HttpStatus.OK;
		List<Product> products = productService.findByType(type);
		if (products == null || products.isEmpty()) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<Product>>(products, httpStatus);
	}
	
	@RequestMapping(value = "/getProductBySellerId/{sellerId}", method = RequestMethod.POST)
	public ResponseEntity<List<Product>> getProductBySellerId(@PathVariable("sellerId") String sellerId, @RequestBody PageParam pageParam) {
		HttpStatus httpStatus = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		Page<Product> products = productService.findBySellerId(sellerId, pageParam.getPageRequest());
		if(products!=null ){
			List<Product> productList = products.getContent();
			headers.set("RECORD_COUNT",String.valueOf( products.getTotalElements()));
			httpStatus = HttpStatus.OK;
			return new ResponseEntity<List<Product>>(productList, headers, httpStatus);
		}
		else  {
			httpStatus = HttpStatus.NOT_FOUND;
			return new ResponseEntity<>(httpStatus);
		}
	}
	
	@RequestMapping(value = "/getProductByAvailable/{available}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductBySellerId(@PathVariable("available") Date available) {
		HttpStatus httpStatus = HttpStatus.OK;
		List<Product> products = productService.findByAvailable(available);
		if (products == null || products.isEmpty()) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<Product>>(products, httpStatus);
	}
	
	@RequestMapping(value = "/getCount", method = RequestMethod.GET)
	public ResponseEntity<Long> getCount() {
		long count = productService.count();
		return new ResponseEntity<Long>(count, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public ResponseEntity<Product> save(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		Product newProduct = productService.save(product);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/getProductByID/{id}").buildAndExpand(newProduct.getId()).toUri());
		if (newProduct != null) {
			return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Product> create(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		Product newProduct = productService.save(product);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/getProductByID/{id}").buildAndExpand(newProduct.getId()).toUri());
		if (newProduct != null) {
			return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getSearchProducts", method = RequestMethod.POST)
	public ResponseEntity<List<Product>> getSearchProducts(@RequestBody SearchProduct searchProduct) {
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String,Object> returnMap= productService.findSearch(searchProduct, false);
		List<Product> allProducts = (List<Product>)returnMap.get("LIST");
		if (allProducts == null || allProducts.size() == 0) {
			returnMap = productService.findSearch(searchProduct, true);
			 allProducts = (List<Product>)returnMap.get("LIST");
			if (allProducts == null || allProducts.isEmpty()) {
				httpStatus = HttpStatus.NOT_FOUND;
			} else {
				httpStatus = HttpStatus.PARTIAL_CONTENT;
			}
		}
		HttpHeaders headers = new HttpHeaders();
		headers.set("RECORD_COUNT",String.valueOf( returnMap.get("RECORD_COUNT")));
		return new ResponseEntity<List<Product>>(allProducts,headers, httpStatus);
	}
	
	@RequestMapping(value = "/getSearchProductJson", method = RequestMethod.POST)
	public ResponseEntity<Demand> getSearchProductJson(@RequestBody Demand searchProduct) {
		return new ResponseEntity<Demand>(searchProduct, HttpStatus.OK);
	}

}
