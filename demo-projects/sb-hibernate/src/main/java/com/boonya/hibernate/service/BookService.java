package com.boonya.hibernate.service;

import com.boonya.hibernate.dao.BookDao;
import com.boonya.hibernate.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public Book getBookById(String id){
        Book book= bookDao.getBookById(id);
        return book;
    }

    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }

    public synchronized Book addBook(Book book){
        if(bookDao.bookExists(book.getTitle(),book.getCategory())){
            return null;
        }else {
            bookDao.addBook(book);
            return book;
        }
    }
    public void updateBook(Book book){
        bookDao.updateBook(book);
    }

    public void deleteBook(String id){
        bookDao.deleteBook(id);
    }
}
