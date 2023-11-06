## Online store in three variants. Homework 2
 [![Current Version](https://img.shields.io/badge/version-0.0.1-green.svg)](https://github.com/IgorAntun/node-chat) 


### General info

This is a homework for the course "Kurs Spring Boot 2: Tworzenie efektywnych aplikacji internetowych". 

The task was to create an online store in three variants. The first variant is "START", a simple online store with the ability to add products to baskets (the product stores the name and price), and based on them, write the price of all products on the console window. The second variant is "PLUS", which additionally allows VAT to be added to the final price. The VAT rate is to be included in the configuration file. The third variant is "PRO", in addition to calculating the tax, it also has the option of calculating a discount, the value of which is included in the configuration file.
To start, the application adds any 5 products with a random price (in the range of PLN 50-300) and displays their total price.

### Technologies
* Java 17
* Spring Boot 3.1.5
* Maven 3.8.2
* Lombok 

### How To Use

To clone and run this application, you'll need [Git](https://github.com/CymerysWojciech/Shop.git)

```shell
# Clone this repository
git clone https://github.com/CymerysWojciech/Shop.git
```
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `pl.budowniczowie.shop.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn shop:run
```

### Documentation


The application will be available at http://localhost:8082.
#### Products
 - Get all products  http://localhost:8082/api/v1/products method GET
 - Get product by id http://localhost:8082/api/v1/products/{id} method GET
 - Add product http://localhost:8082/api/v1/products method POST
{
   "name": "string",
   "description": "string",
   "price": BigDecimal
}
 - Edit product http://localhost:8082/api/v1/products method PUT
 - Delete product http://localhost:8082/api/v1/products/{id} method DELETE
#### Basket
 - Get all products in basket http://localhost:8082/api/v1/basket method GET
 - Get product in basket by id http://localhost:8082/api/v1/basket/{id} method GET
 - Add product to basket http://localhost:8082/api/v1/basket method POST
 [{ "id": int },....]
 - Edit product in basket http://localhost:8082/api/v1/basket method PUT
 - Delete product from basket http://localhost:8082/api/v1/basket/{id} method DELETE

