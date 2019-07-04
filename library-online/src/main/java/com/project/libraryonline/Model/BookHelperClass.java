package com.project.libraryonline.Model;

import org.springframework.stereotype.Component;

@Component
public class BookHelperClass {

    private String author;
    private double averageRating;

    public BookHelperClass() {
    }

    public BookHelperClass(String author, double averageRating) {
        this.author = author;
        this.averageRating = averageRating;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthors(String[] authors) {
        author = author;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
