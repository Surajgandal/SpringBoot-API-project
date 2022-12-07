package com.machine.repository;





import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.machine.model.Category;
import com.machine.projection.CategoryWithIdAndLocation;



@Repository
public interface catRepository extends CrudRepository<Category, Integer> {
	List<CategoryWithIdAndLocation> findAllProjectedBy();

	Optional<CategoryWithIdAndLocation> findProjectedById(Integer id);
}
