package com.machine.projection;

import org.springframework.beans.factory.annotation.Value;

public interface ProductWithIdAndName {
	@Value("ID: " + "#{target.id}" +" , "+ " Product type : " + "#{target.type}")
	String getCategoryType();
	
	String getName();
	
	Double getPrice();
}
