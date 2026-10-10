package com.example.springboot_microservice_task_hirzinfathoni.repository;

import com.example.springboot_microservice_task_hirzinfathoni.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
