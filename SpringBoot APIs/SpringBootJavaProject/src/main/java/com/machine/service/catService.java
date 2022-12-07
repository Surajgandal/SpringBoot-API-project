package com.machine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.machine.model.Category;
import com.machine.model.Product;
import com.machine.projection.CategoryWithIdAndLocation;
import com.machine.repository.catRepository;
import com.machine.repository.proRepository;


@Service
public class catService {
	@Autowired
	private catRepository uRepository;
	
	@Autowired
	private proRepository pRepository;
	
//	public Iterable<Category> getAll(){
//		return uRepository.findAll();
//	}
//	
	
	
	public List<CategoryWithIdAndLocation> getAll(){
		return uRepository.findAllProjectedBy();
	}
	
	public CategoryWithIdAndLocation findWithIdAndLocation(Integer id) {
		return uRepository.findProjectedById(id).orElse(null);
	}
	
	
	public Category add(Category u) {
		return uRepository.save(u);
	}
	
	public String delete(Integer id) {
		Category foundUser = this.find(id);
		if(foundUser == null)
			return null;
		else{
			uRepository.deleteById(id);
			return "deleted";
		}
	}


	public Category find(Integer id) {
		return uRepository.findById(id).orElse(null);
	}
	
	public Category update(Integer id, Category user) {
		Category foundUser = this.find(id);
		if(foundUser == null)
			return null;
		else{
			user.setId(id);
			return uRepository.save(user);
			
		}
	}
	
	
	public Product addProduct(Integer category_Id,Product product) {
		Category foundUser = find(category_Id);
		if(foundUser == null)
			return null;
		else {
			product.setCategory(foundUser);
			return this.pRepository.save(product);
		}
	}
	
	public List<Product> getProducts(Integer id){
		Category foundUser = find(id);
		if(foundUser == null)
			return null;
		else {
			return (foundUser).getProduct();
		}		
	}



	
}
