package com.springgraphql.springgraphql.repository;

import com.springgraphql.springgraphql.entity.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b ORDER BY b.id LIMIT :limit OFFSET :offset")
    List<Book> findLimitedBooks(@Param("limit") int limit, @Param("offset") int offset);

}

