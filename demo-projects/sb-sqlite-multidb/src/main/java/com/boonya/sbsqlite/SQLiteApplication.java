package com.boonya.sbsqlite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.boonya.sbsqlite.*"})
public class SQLiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SQLiteApplication.class, args);
    }
}