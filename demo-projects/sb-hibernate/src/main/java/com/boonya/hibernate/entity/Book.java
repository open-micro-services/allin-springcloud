package com.boonya.hibernate.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books")
@Data
public class Book implements Serializable {

    private static final long serialVersionUID = 4055235145107081639L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
   /* @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "book-id")
    @GenericGenerator(name = "book-id", strategy = "com.boonya.hibernate.util.DBIdentifyGenerator")*/
    @Column(name = "id",nullable = false)
    protected String id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;
}
