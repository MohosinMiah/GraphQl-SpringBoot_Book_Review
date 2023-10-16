package com.springgraphql.springgraphql.repository;

import com.springgraphql.springgraphql.entity.Book;
import com.springgraphql.springgraphql.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBook(Book book);
}
