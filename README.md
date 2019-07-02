# library-online

Basic functions
Library online allows you to simple search by the following categories: 
-by ISBN.
-by category.
-by author.
JSON response is returned.

The app runs on port 8080.

The available endpoints:
/books
/author
/category

The RESTful Architecture
To create frontend presentation layer JavaScript, Bootstrap were used.
To create backend and business layer Java and SpringBoot were used.
Persistance is based on Reposiory layer
Data are stored in embedded Mongo database- Flapdoodle.
API testing is done thanks to RESTassured and JUnit.

Testing
To test endpoint just run the app and then ControllerTest class.
Some basic tests will be performed on endpoints which include testing proper parameters, statuses and if response is not empty
