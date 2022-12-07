package com.machine.model;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Product {
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="yourSequenceGenerator")
//	@GenericGenerator(name = "native",strategy = "native")
	@SequenceGenerator(name="yourSequenceGenerator", allocationSize=1)
	private Integer id;
	
	@Column
	@NotNull(message="Name cannot be null")
	@NotBlank(message="Name cannot be blank")
	private String type;
	@Column
	@NotNull(message="Name cannot be null")
	@NotBlank(message="Name cannot be blank")
	private String name;
	
	@Column
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	
}
