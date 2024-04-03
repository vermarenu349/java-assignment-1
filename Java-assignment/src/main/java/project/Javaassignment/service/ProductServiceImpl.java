package project.Javaassignment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import project.Javaassignment.beans.Product;
import project.Javaassignment.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	private static List<Product> products = new ArrayList<>();
	
	@Override
	public Product addProduct(Product product) {
		products.add(product);
		return product;
	}
	
	@Override
	public String deleteProduct(int id) {
		Product p;
		try {
			p = getById(id);
			products.remove(p);
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	
		return "Product Deleted Successfully";
		
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public Map<String, Object> generateBills() {
		Map<String, Object> bills = new HashMap<>();
		bills.put("Product", products);
		double totalPrice = 0;
		for(Product p: products) {
			double price = p.getPrice() * p.getQuantity();
			totalPrice += price;
		}
		double cgst = 9/100 * totalPrice;
		totalPrice += 2 * cgst;
		bills.put("Total Price", totalPrice);
		bills.put("CGST 9%", cgst);
		bills.put("SGST 9%", cgst);
		bills.put("Final Total", totalPrice);
		return bills;
	}
	
	
	public Product getById(int id) throws ProductNotFoundException  {
		
		return products.stream().filter(x->x.getId()==id)
				.findFirst()
				.orElseThrow(()-> new ProductNotFoundException("Product not found for : " + id));
		
	}

}
