package com.machine.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.machine.model.Product;
import com.machine.projection.ProductWithIdAndName;
import com.machine.service.proService;
import com.machine.util.ResponseWrapper;

import jakarta.validation.Valid;

@RestController
public class proController {
	
	@Autowired
	private proService pService;
	
	@GetMapping("/api/products")
	public ResponseEntity<?> getAllProducts(){
		ResponseWrapper wrapper = new ResponseWrapper();
		wrapper.setSubject("All Product List");
		wrapper.setBody(pService.getAll());
		
		return new ResponseEntity<>(wrapper,HttpStatus.OK);
		
	}
	
	@PostMapping("/api/products")
	public ResponseEntity<?> addProducts(@RequestBody @Valid Product product, BindingResult bindingResult){
		ResponseWrapper wrapper = new ResponseWrapper();
		if(bindingResult.hasErrors()) {
			wrapper.setSubject("Validation Errors");
			List<String> errors = bindingResult.getFieldErrors().stream()
					                                           .map(error ->  {
					                                        	   return error.getField()+ " - " +
					                                                      error.getDefaultMessage();
					                                           }).collect(Collectors.toList());
			wrapper.setBody(String.join(" , ",errors));
			return new ResponseEntity<>(wrapper,HttpStatus.BAD_REQUEST);
		}else {
			wrapper.setSubject("Products Resources Created");
			wrapper.setBody(this.pService.add(product));
			return new ResponseEntity<>(wrapper,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/api/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
		
		String message = pService.delete(id);
		ResponseWrapper wrapper = new ResponseWrapper();
		if(message == null) {
			wrapper.setSubject("Product Not Found");
			wrapper.setBody(null);
			return new ResponseEntity<>(wrapper,HttpStatus.BAD_REQUEST);
		}else {
			wrapper.setSubject(message);
			wrapper.setBody(null);
			return new ResponseEntity<>(wrapper,HttpStatus.OK);
		}
	}
	
	@GetMapping("/api/products/{id}")
	public ResponseEntity<?> findProduct(@PathVariable Integer id){
		ResponseWrapper wrapper = new ResponseWrapper();
		
		ProductWithIdAndName found = pService.findWithIdAndName(id);
		if(found == null) {
			wrapper.setSubject("Product Not Found");
			wrapper.setBody(null);
			return new ResponseEntity<>(wrapper,HttpStatus.NOT_FOUND);
		}else {
			wrapper.setSubject("Product Found : " + id);
			wrapper.setBody(found);
			return new ResponseEntity<>(wrapper,HttpStatus.OK);
		}
		
	}
	
	@PutMapping("/api/products/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product product){
		ResponseWrapper wrapper = new ResponseWrapper();
		Product updateProduct = pService.update(id,product);
		
		if(updateProduct ==  null) {
			wrapper.setSubject("Product Not Found");
			wrapper.setBody(null);
			return new ResponseEntity<>(wrapper,HttpStatus.NOT_FOUND);
		}else {
			wrapper.setSubject("Product Updated : " + id);
			wrapper.setBody(updateProduct);
			return new ResponseEntity<>(wrapper,HttpStatus.OK);
		}
	}

}
