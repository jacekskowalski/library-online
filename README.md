# library-online

## Basic functions 

Library online allow you to simple search by the following categories: 

-by ISBN (property industryIdentifiers[0].type)

-by category.

-by author.

Data is loaded from attached books.json file. JSON response is returned.

The app runs on port 8091.

The available endpoints:

/books

/author

/category

To start app run LibraryOnlineApplication 

## The RESTful Architecture

To create frontend presentation layer JavaScript, Bootstrap were used.

To create backend and business layer Java and SpringBoot were used.

Persistance is based on Repository layer

Data are stored in embedded Mongo database- Flapdoodle.

API testing is done thanks to RESTassured and JUnit.

## Testing

The easist way to run is with the use of Intellij -mvn clean install;.

To test app and endpoints run LibraryOnlineApplication and then and then ControllerTest class.

A few tests methods will be performed on endpoints which include testing proper parameters, statuses and if response is not empty


