package com.project.libraryonline.Repositoty;

import com.project.libraryonline.Model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends MongoRepository<Book,String> {
    @Query("{isbn :  ?0}")
     Book findByIsbn(String isbn);
    List<Book> findAll();


}
