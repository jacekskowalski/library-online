package com.project.libraryonline.Controller;

import com.project.libraryonline.Model.Book;
import com.project.libraryonline.Model.BookHelperClass;
import com.project.libraryonline.Repositoty.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class HomeController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    BookHelperClass bookHelperClass;

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public ResponseEntity<?> getBook(@RequestParam String isbn){
        if( Objects.isNull(bookRepository.findByIsbn(isbn)))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Book getBook = bookRepository.findByIsbn(isbn);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return  new ResponseEntity<>(getBook,responseHeaders,HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getBooksByCategory(@PathVariable("category") String category){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<Book> books = bookRepository.findAll()
                .stream()
                .filter(
                        p-> Arrays.toString(p.getCategories())
                                .toLowerCase()
                                .contains(category.toLowerCase())
                ).collect(Collectors.toList());
        if(books.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(books, responseHeaders,HttpStatus.OK);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<?> getBooksAuthor(@PathVariable("author") String author){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<BookHelperClass> newlist= new ArrayList<>();
        List<Book> books = bookRepository.findAll()
                .stream()
                .filter(
                        j-> Arrays.toString(j.getAuthors())
                                .toLowerCase().trim()
                                .contains(author.toLowerCase().trim())
                )
                .sorted(Comparator.comparing(Book::getAverageRating).reversed())
                .collect(Collectors.toList());
        books.forEach(
                p->
                    newlist.add(new BookHelperClass(author, p.getAverageRating()))
                );
        if(books.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return  new ResponseEntity<>(newlist,responseHeaders,HttpStatus.OK);
    }

    public BookHelperClass getBookHelperClass() {
        return bookHelperClass;
    }

    public void setBookHelperClass(BookHelperClass bookHelperClass) {
        this.bookHelperClass = bookHelperClass;
    }
}
