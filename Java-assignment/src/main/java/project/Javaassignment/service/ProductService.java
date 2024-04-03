package project.Javaassignment.service;

import java.util.List;
import java.util.Map;

import project.Javaassignment.beans.Product;

public interface ProductService {
	public Product addProduct(Product product);
	public String deleteProduct(int id);
	public List<Product> getProducts();
	public Map<String, Object> generateBills();
}
