package com.rest.webservices.demo.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {
	
	@GetMapping(path = "/v1/person")
	public PersonV1 handlePersonV1() {
		return new PersonV1("priyadharshan beepee"); 
	}
	
	@GetMapping(path = "/v2/person")
	public PersonV2 handlePersonV2() {
		return new PersonV2(new Name("sara", "vijayakumar")); 
	}
	
	// below are other useful ways of doing versioning
	
//	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
//	public PersonV1 headerV1() {
//		return new PersonV1("Bob Charlie");
//	}
//
//	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
//	public PersonV2 headerV2() {
//		return new PersonV2(new Name("Bob", "Charlie"));
//	}
//
//	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
//	public PersonV1 producesV1() {
//		return new PersonV1("Bob Charlie");
//	}
//
//	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
//	public PersonV2 producesV2() {
//		return new PersonV2(new Name("Bob", "Charlie"));
//	}
}
