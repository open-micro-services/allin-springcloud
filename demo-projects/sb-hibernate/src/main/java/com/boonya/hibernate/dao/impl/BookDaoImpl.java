package com.boonya.hibernate.dao.impl;

import com.boonya.hibernate.dao.BookDao;
import com.boonya.hibernate.entity.Book;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> getAllBooks() {
        String sql = "FROM Book as book ORDER BY book.id";
        return (List<Book>) entityManager.createQuery(sql).getResultList();
    }

    @Override
    public Book getBookById(String id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public Book addBook(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public void updateBook(Book book) {
        Book book1 = getBookById(book.getId());
        book1.setTitle(book.getTitle());
        book1.setCategory(book.getCategory());
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void deleteBook(String id) {
        entityManager.remove(getBookById(id));
    }

    @Override
    public boolean bookExists(String title, String category) {
        String sql = "FROM Book as book WHERE book.title=?0 and book.category=?1";
        int count = entityManager.createQuery(sql)
            .setParameter(0, title)
            .setParameter(1, category)
            .getResultList().size();
        return count > 0;
    }
}
