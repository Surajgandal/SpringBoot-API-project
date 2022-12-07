package com.machine.model;




import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Category {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//@GeneratedValue(strategy= GenerationType.AUTO,generator = "native")
	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SequenceGenerator")
	@SequenceGenerator(name="SequenceGenerator", allocationSize=1)
	private Integer id;
	@Column
	@NotNull(message="Name cannot be null")
	@NotBlank(message="Name cannot be blank")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private List<Product> Product;

	
	
	
}
