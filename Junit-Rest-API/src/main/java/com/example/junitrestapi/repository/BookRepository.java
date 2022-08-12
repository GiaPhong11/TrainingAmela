package com.example.junitrestapi.repository;

import com.example.junitrestapi.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Long> {

}
