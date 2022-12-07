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


import com.machine.model.Category;
import com.machine.model.Product;
import com.machine.projection.CategoryWithIdAndLocation;
import com.machine.service.catService;
import com.machine.util.ResponseWrapper;

import jakarta.validation.Valid;

@RestController
public class catController {
	
	@Autowired
	private catService uService;

	@GetMapping("/api/categories") 
	  public ResponseEntity<?> getAllCategorys()
	  {
		ResponseWrapper wrapper = new ResponseWrapper();
		wrapper.setSubject("All Category List");
		wrapper.setBody(uService.getAll());
		return new ResponseEntity<>(wrapper,HttpStatus.OK);
	  }
	
	@PostMapping("/api/categories")
	public ResponseEntity<?> addCategory(@RequestBody @Valid Category category, BindingResult bindingResult) {
         ResponseWrapper wrapper = new ResponseWrapper();
		
		if(bindingResult.hasErrors()) {
			wrapper.setSubject("Validation Error");
			List<String> errors = bindingResult.getFieldErrors()
											   .stream()
											   .map(error -> {
												   return error.getField() + " - " + 
											              error.getDefaultMessage();
											   }).collect(Collectors.toList());
			wrapper.setBody(String.join(" , " , errors));
			return new ResponseEntity<>(wrapper, HttpStatus.BAD_REQUEST);
	}else {
		wrapper.setSubject("Category Resource Created");
		wrapper.setBody(this.uService.add(category));
		return new ResponseEntity<>(wrapper,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/api/categories/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
		
		//return uService.delete(id);
		String message = uService.delete(id);
		ResponseWrapper wrapper = new ResponseWrapper();
		if(message == null) {
			wrapper.setSubject("Category Not Found");
			wrapper.setBody(null);
			return new ResponseEntity<>(wrapper,HttpStatus.NOT_FOUND);
		}
		else {
			wrapper.setSubject(message);
			wrapper.setBody(null);
			return new ResponseEntity<>(wrapper,HttpStatus.OK);
	}
	}
	
	
	  @GetMapping("/api/categories/{id}") 
	  public ResponseEntity<?> findCategory(@PathVariable Integer id) {
	  //return uService.find(id); 
	  ResponseWrapper wrapper = new ResponseWrapper();
	  //Category foundUser =  uService.find(id);
	  
	  CategoryWithIdAndLocation foundUser = uService.findWithIdAndLocation(id);
		if(foundUser == null) {
			wrapper.setSubject("Category Not Found");
			wrapper.setBody(null);
			return new ResponseEntity<>(wrapper,HttpStatus.NOT_FOUND);
		}
		else{
			wrapper.setSubject("Category Found: " + id);
          wrapper.setBody(foundUser);
			return new ResponseEntity<>(wrapper,HttpStatus.OK);
		}
	}
	  
	  @PutMapping("/api/categories/{id}") 
	  public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody Category category) { 
		  //return uService.update(id, category); 
		
		      Category updatedUser = uService.update(id, category);
				ResponseWrapper wrapper = new ResponseWrapper();
				if(updatedUser == null) {
					wrapper.setSubject("Category Not Found");
					wrapper.setBody(null);
					return new ResponseEntity<>(wrapper,HttpStatus.NOT_FOUND);
				}
		    else{
					wrapper.setSubject("Category Updated: " + id);
					wrapper.setBody(updatedUser);
					return new ResponseEntity<>(wrapper,HttpStatus.OK);
				}
	 
	  }
	  
	  @PostMapping("/api/categories/{id}/products")
		public ResponseEntity<?> addProduct(@PathVariable Integer id, @RequestBody Product product){
			ResponseWrapper wrapper = new ResponseWrapper();
			wrapper.setSubject("Product Added");
			wrapper.setBody(this.uService.addProduct(id, product));
			return new ResponseEntity<>(wrapper,HttpStatus.OK);
		}
		
		@GetMapping("/api/categories/{id}/products")
		public ResponseEntity<?> getProduct(@PathVariable Integer id){
			ResponseWrapper wrapper = new ResponseWrapper();
			wrapper.setSubject("Products Created by Category: " + id);
			wrapper.setBody(this.uService.getProducts(id));
			return new ResponseEntity<>(wrapper,HttpStatus.OK);
		}
		
		
	  }
	

