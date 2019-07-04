package com.project.libraryonline;

import com.project.libraryonline.Model.Book;
import com.project.libraryonline.Repositoty.BookRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableMongoRepositories
public class LibraryOnlineApplication {
    @Autowired
    BookRepository bookRepository;
	public static void main(String[] args) {
		SpringApplication.run(LibraryOnlineApplication.class, args);

	}

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            readFromJson();
        };

        }

    public void readFromJson() {
        try {
            long publishedDate;
             ClassLoader classLoader =  getClass().getClassLoader();
            File f = new File(classLoader.getResource("books.json").getFile());
            List<Book> books= new ArrayList();
            String content = new String(Files
                    .readAllBytes(Paths
                            .get(classLoader
                                    .getResource("books.json").toURI())));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < jsonArray.length(); i++) {
                Book book = new Book();
                JSONObject explrObject = jsonArray.getJSONObject(i);
                JSONObject getSpecificObject = explrObject.getJSONObject("volumeInfo");
                String title = getSpecificObject.get("title").toString();
                String subtitle = getSpecificObject
                        .has("subtitle")  ?
                        getSpecificObject
                                .get("subtitle").toString() : "No";
                String publisher = getSpecificObject.has("publisher") ?
                        getSpecificObject.get("publisher").toString() : "N/A";
                JSONArray temp_array = getSpecificObject
                        .getJSONArray("industryIdentifiers");
                JSONObject obj = (JSONObject) temp_array.get(0);
                String isbn = obj.getString("identifier");
                DateTimeFormatter sdf = DateTimeFormatter.ISO_INSTANT.ofPattern("yyyy");
               if(getSpecificObject.has("publishedDate")) {

                            publishedDate= Long.valueOf(getSpecificObject.get("publishedDate").toString().substring(0,4));
                                }else{
                   publishedDate= 0;
               }
                String description = getSpecificObject.has("description") ?
                        getSpecificObject.get("description").toString() : "N/A";
                int pages = getSpecificObject.has("pageCount")  ?
                        Integer.valueOf(getSpecificObject
                                .get("pageCount").toString()) : 0;
                String thumbnail = getSpecificObject
                        .getJSONObject("imageLinks")
                        .get("thumbnail").toString();
                String language = getSpecificObject.get("language").toString();
                String prevLink = getSpecificObject.get("previewLink").toString();
                double rating = getSpecificObject.has("averageRating") ?
                        getSpecificObject.getDouble("averageRating") : 0.0;
                String[] temparray;
                if(getSpecificObject.has("authors")) {
                    int len = getSpecificObject.getJSONArray("authors").length();
                    temparray = new String[len];
                    for (int j = 0; j < len; j++) {
                        temparray[j] = getSpecificObject.getJSONArray("authors").get(j).toString();
                    }
                }else {
                    temparray= new String[]{"N/A"};
                }
                String[] cat_array;
                if(getSpecificObject.has("categories")) {
                    int catLength = getSpecificObject.getJSONArray("categories").length();
                    cat_array = new String[catLength];
                    for (int z = 0; z < catLength; z++) {
                        cat_array[z] = getSpecificObject.getJSONArray("categories").get(z).toString();
                    }
                }else{
                    cat_array= new String[]{"N/A"};
                }
                book.setIsbn(isbn);
                book.setTitle(title);
                book.setSubtitle(subtitle);
                book.setPublisher(publisher);
                book.setPublishedDate(publishedDate);
                book.setDescription(description);
                book.setPageCount(pages);
                book.setThumbnailUrl(thumbnail);
                book.setLanguage(language);
                book.setLanguage(prevLink);
                book.setAverageRating(rating);
                book.setAuthors(temparray);
                book.setCategories(cat_array);
                bookRepository.save(book);


            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
