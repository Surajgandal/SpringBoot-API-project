package com.machine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.machine.model.Product;
import com.machine.projection.ProductWithIdAndName;


@Repository
public interface proRepository extends CrudRepository<Product, Integer> {
	List<ProductWithIdAndName> findAllProjectedBy();

	Optional<ProductWithIdAndName> findProjectedById(Integer id);
}
