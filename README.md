# RestfulWebServiceDemoProject

## Project Brief : 
Using Spring boot to implement Restful webservices, and apply HTTP concepts to web projects. 
*** Hasn't been hosted on the WWW. Install locally to view the implementation.  *** 

/* Use eclipse or STS to clone and view the project */ 

## Project Description : 
The project goes through the important HTTP requests such as GET, POST, DELETE and implements the response for the same following the basic standards. 
Uses H2 database(an in memory database), Hibernate (JPA specification) and Spring data JPA. Custom exceptions are thrown with proper messages and 
with appropriate HTTP response status. I18n and versioning are touched as well.  Spring validation including @NotNull, @NotEmpty, @Valid are included. 

### URLs implemented : 

    GET  localhost:8080/users -> Gets the list of users from the database.
    POST localhost:8080/users -> Posts a user and returns the location of the object in response heading. 
    DELETE localhost:8080/users/{id} -> Deletes the user with id -> {id}. 
    GET localhost:8080/users/{id} -> Gets the user with id -> {id}. 
   
    h2 database url : localhost:8080/h2-console
    
    datasource url : jdbc:h2:mem:testdb
    
    
