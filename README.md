# Survey Application
	Survey Application provides rest endpoints which will be consumes by rest clients to implement 
	any survey methodology. 
	
- [Survey app](https://market-logic-assignment-survey.herokuapp.com/swagger-ui.html)
- [Postman Collection](https://raw.githubusercontent.com/kaustuvmaji/market-logic-assignment/main/src/test/postman/MarketLogicSurveyApp.postman_collection.json)
 	
# Requirement

	The requirments of the assignment is as a follows

		1. Add/edit/delete questions, answers.
		2. read a list of all questions.
		3. read a question with all answers.
		4. respond to a survey.
		5. survey statistics.
		
# Design Decisions and Short Design Notes

# Application Architecture 

	The project is developed based on the domain driven architecture principles. 

![Application Layer Model](https://raw.githubusercontent.com/kaustuvmaji/market-logic-assignment/main/images/Application.png)

### Domain Layer Details 

Apllication has two basic domains entities

	1. Question Domain : Question has one to many relatation with Answer entity. 
	2. Survey Domain : Survey has one to many relation with SurveyQuestion entity.

### Infrastruction Layer Details 

	Spring data jpa framework and H2 driver provider has been used to communicate with H2 database. 
	Two types of Repository has been used
		- CrudRepository<T, ID>
		- JpaRepository<T, ID>

Database Schema will auto generated each time applications gets start. 

### Application Layer Details 

	All Business functionatlity has been implemented in application layer. 
	Transaction context also gets created from this layer. 
	Using Spring AOP logging funcationality and exception advice has been 
	implented which is part of applicaiton layer. 
	Two services class has been implemented based on the domains. 
		- QAService.
		- SurveyService. 

### Interface Layer Details 

	REST webservices has been implemented in Interface Layer using Spring Rest Controller. 
	Custom util beans has implement to enable 
	http request response logging and swagger API documentation for the application. 

## Technology Selection
	- Dev
		1. Java 11
		2. Spring Boot
			- web
			- data-jpa
		3. Validation
		4. Swagger
	- Test
		1. Spring Boot
			- test
		2. Junit
		3. Apache HTTP components
		4. JaCoCo 
	- Runtime 
		1. H2 Db
	- Build 
		1. Maven
	- Continous Integration 
		1. Maven Git Action
	- Continous Deployment	
		1. Docker
	- Cloud Platform
		1. Heroku

# API Documentation

[Link to Api documentaiton](https://market-logic-assignment-survey.herokuapp.com/v2/api-docs)

# Exposed REST API's

![QuestionAnswerServices](https://raw.githubusercontent.com/kaustuvmaji/market-logic-assignment/main/images/QuestionAnswerServices.png)

![SurveyServices](https://raw.githubusercontent.com/kaustuvmaji/market-logic-assignment/main/images//SurveyServices.png)

# Testability 
	All Rest Services are tested using Integration test script using RestAssured Testing framework . 
	In current test cases exception paths are not covered
![Coverage](.github/badges/jacoco.svg) 
# Compile , test and Package 

	To compile and integration test the application run following commands from project's root directory. 
	As a prerequistic maven, and jdk needs to setup first.

```
mvn clean compile test
```                        
	To package issue following commands
```
mvn package
```                           
	This will create a jar file in target directory of each projects.

	* For compilation , machine need to have maven setup .                            

# Command to run the application

java -jar marketlogic-assignment-survery-0.0.1.jar	

# Deployability and Pre-requisite 

	As per pre-requisite jdk 11 is required to be installed in the machine. 
	Application has been tested in both windows 10 and unix.	

# Assumption

# Area of Improvement
	1.  Security implemented and can be augmented later. 
	2.  Decouple the application and enable microservices architecture based on
	    the two domain Question and Answer.

