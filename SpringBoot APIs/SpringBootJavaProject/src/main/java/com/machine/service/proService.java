package com.machine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machine.model.Product;

import com.machine.projection.ProductWithIdAndName;
import com.machine.repository.proRepository;



@Service
public class proService {

	@Autowired
	private proRepository pRepository;
	
//	public Iterable<Product> getAll(){
//	return pRepository.findAll();
//    }
	
	public List<ProductWithIdAndName> getAll(){
	return pRepository.findAllProjectedBy();
}
	public ProductWithIdAndName findWithIdAndName(Integer id) {
		return pRepository.findProjectedById(id).orElse(null);
	}

	public Product add(Product u) {
		return pRepository.save(u);
	}
	
	public String delete(Integer id) {
		Product found = find(id);
		if(found == null) {
			return null;
		}else {
			return "Deleted";
		}
	}

	public Product find(Integer id) {
		// TODO Auto-generated method stub
		return pRepository.findById(id).orElse(null);
	}
	
	public Product update(Integer id, Product user) {
		Product found = find(id);
		if(found == null) {
			return null;
		}else {
			user.setId(id);
			return pRepository.save(user);
		}
	}
	



	
}
