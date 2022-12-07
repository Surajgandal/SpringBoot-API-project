package com.machine.projection;

import org.springframework.beans.factory.annotation.Value;

public interface CategoryWithIdAndLocation {
	 
		
		@Value("ID: " + "#{target.id}" +","+ " Category Name: " + "#{target.name}")
		String getName();
		
		//String getName();
}
