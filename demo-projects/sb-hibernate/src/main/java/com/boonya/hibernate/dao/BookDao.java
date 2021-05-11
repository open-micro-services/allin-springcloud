package com.boonya.hibernate.dao;

import com.boonya.hibernate.entity.Book;

import java.util.List;

public interface BookDao<T> {
    List<Book> getAllBooks();

    Book getBookById(String bookId);

    Book addBook(Book book);

    void updateBook(Book book);

    void deleteBook(String bookId);

    boolean bookExists(String title,String category);
}
