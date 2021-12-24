# Stock API application
 A simple Rest API supporting CRUD operations on stocks using Spring Boot .

## Requirements
1. Docker installed with enough memory.

## Starting the app
1. Checkout the source code in your local machine using web url https://github.com/vijayalaxmi-m/stock-api.git
2. Run `docker-compose up -d` , it may take upto 10-12 minutes.
3. API documentation is available at http://localhost:8080/swagger-ui-custom.html, you can try out all the endpoints from the assignment.
Available API calls:
    1. GET - http://localhost:8080/api/stocks - finds all available stocks
    2. GET - http://localhost:8080/api/stocks/{id} - finds stock by id
    3. POST - http://localhost:8080/api/stocks - saves stock
    4. PATCH - http://localhost:8080/api/stocks/{id} - updates current price by id
    5. DELETE -  http://localhost:8080/api/stocks/{id} - deletes the stock by id

## Technical details:

1. This application is built using Spring boot. 
2. The data is persisted to in-memory H2 database, data.sql has initial data which will loaded at startup.
3. Open API documentation with swagger API with interface to test the endpoints.
4. Docker with instructions to build executable using maven and run the application using Java 11.
5. Docker-compose.yml file to manage docker container exposing 8080 port to access APIs.

