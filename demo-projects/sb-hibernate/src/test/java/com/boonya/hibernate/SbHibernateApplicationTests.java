package com.boonya.hibernate;

import com.boonya.hibernate.entity.Book;
import com.boonya.hibernate.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class SbHibernateApplicationTests {

    @Autowired
    private BookService bookService;

    @Test
    public void contextLoads() {
        // insert
        Book b =new Book();
        b.setTitle("Jenkins");
        b.setCategory("计算机技术科学");
        b = bookService.addBook(b);

        // select
        Book book = bookService.getBookById(b.getBookId());
        List<Book> list = bookService.getAllBooks();
        System.out.println(list);

        // update
        book.setTitle("Jenkins权威指南");
        bookService.updateBook(book);
        Book b2 = bookService.getBookById(b.getBookId());
        System.out.println(b2);

        // delete
        bookService.deleteBook(b.getBookId());
    }

}
