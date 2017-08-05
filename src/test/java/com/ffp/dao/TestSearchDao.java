//package com.ffp.dao;
//
//import java.util.Iterator;
//import java.util.List;
//
//import org.junit.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.ffp.data.Product;
//import com.ffp.data.SearchProduct;
//
//public class TestSearchDao {
//	
//	@Test
//	public void testDoSearch () {
//		SearchProduct searchProduct = Mockito.mock(SearchProduct.class);
//		Mockito.when(searchProduct.getName()).thenReturn("Fuji");
//		Mockito.when(searchProduct.getType()).thenReturn("Apple");
//		Mockito.when(searchProduct.getPriceBelow()).thenReturn("2000");
//		Mockito.when(searchProduct.getPriceAbove()).thenReturn("4000");
//		Mockito.when(searchProduct.getQuantityAvailable()).thenReturn("1000");
//		Mockito.when(searchProduct.getAvailable()).thenReturn("1");
//		
//		SearchDao searchDao = new SearchDao();
//		searchDao.doSearch(searchProduct);
////		for (Iterator<Product> iterator = 
////				products.iterator(); iterator.hasNext();) {
////			Product product = (Product) iterator.next(); 
////			System.out.print("Name: " + product.getName()); 
////			System.out.print("Type: " + product.getType()); 
////			System.out.println("Quantity: " + product.getQuantityAvailable()); 
////		}
//	}
//}
