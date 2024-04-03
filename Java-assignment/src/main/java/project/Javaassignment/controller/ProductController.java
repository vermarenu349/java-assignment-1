package project.Javaassignment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.Javaassignment.beans.Product;
import project.Javaassignment.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService proServ;

	private static int id = 1;
	
	@PostMapping(path="/addProduct" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> addProduct(@RequestBody Product p) {
		p.setId(id);
		id++;
		return  ResponseEntity.status(200).body(proServ.addProduct(p));
	}
	
	@DeleteMapping(path="/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		return  ResponseEntity.status(200).body(proServ.deleteProduct(id));
	}
	
	@GetMapping(path="/getAllProducts")
	public ResponseEntity<List<Product>> listProducts(){
		return  ResponseEntity.status(200).body(proServ.getProducts());
	}
	
	@GetMapping(path="/getBill")
	public ResponseEntity<Map<String, Object>> viewBill(){
		return  ResponseEntity.status(200).body(proServ.generateBills());
	}
	
	
}
