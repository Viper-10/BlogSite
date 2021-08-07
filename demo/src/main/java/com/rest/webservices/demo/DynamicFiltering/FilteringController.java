package com.rest.webservices.demo.DynamicFiltering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping(path = "/some-bean/{id}")
	public MappingJacksonValue handleFiltering(@PathVariable int id){		
		
		// get the bean with path variable in real situation, here we assume the following bean is the 
		// appropriate bean
		SomeBean beanName = new SomeBean("Hello", "hi", "wonderful");
	
		MappingJacksonValue mapping = new MappingJacksonValue(beanName);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		mapping.setFilters(filters);
		
		return mapping;
	}	
	
	@GetMapping(path = "/some-bean")
	public MappingJacksonValue handleFiltering2() {
		List<SomeBean> list = Arrays.asList(new SomeBean("a", "b", "c"), new SomeBean("g", "h", "i"),
										new SomeBean("d", "e", "f")); 
		
		
		MappingJacksonValue mapping = new MappingJacksonValue(list); 
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2");
		
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		mapping.setFilters(filters); 
		
		return mapping;
		
	}
	
}
