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

## The RESTful Architecture

To create frontend presentation layer JavaScript, Bootstrap were used.

To create backend and business layer Java and SpringBoot were used.

Persistance is based on Reposiory layer

Data are stored in embedded Mongo database- Flapdoodle.

API testing is done thanks to RESTassured and JUnit.

## Testing

To test endpoint just go to api.test package, run the app and then ControllerTest class.

Some tests methods will be performed on endpoints which include testing proper parameters, statuses and if response is not empty


