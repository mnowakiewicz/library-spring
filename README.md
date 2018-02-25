# Library

Example project with Spring Boot, Spring Security, Spring Validation, Spring Rest, JPA, Bootstrap and Thymeleaf.

Installation
-------------
* Open the `application.properties` file and set your own configurations for the database connection.
* Go on the project's root folder, and then type from terminal:
```sh
$ mvn spring-boot:run
```

Usage
-----
* Run the apllication and go on `http://localhost:8080/`
* Use the fallowing urls to see the interactions:
    - `/` :index, 2 random Book objects read from database
    - `/books` or `/api/books`: all objects from database
    - `{id}` or `/books/{id}`: details of a single Book object
    - `/login`: login page, you can use default credentials `user111` and `pass111`, or make your own account on 
        the `/register` page. Check how Spring Validation works by leaving inputs empty.
    - `/add` create your own book
    - `/search` search books
    