package com.springgraphql.springgraphql;

import com.springgraphql.springgraphql.dto.InputCreateBook;
import com.springgraphql.springgraphql.entity.Book;
import com.springgraphql.springgraphql.entity.Review;
import com.springgraphql.springgraphql.repository.BookRepository;
import com.springgraphql.springgraphql.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @QueryMapping
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    @QueryMapping(name = "book")
    public Optional<Book> bookById(@Argument String id) {
        return bookRepository.findById(Long.valueOf(id));
    }

    @QueryMapping(name = "searchBooks")
    public List<Book> searchBooks(@Argument int limit, @Argument int offset) {
        return bookRepository.findLimitedBooks(limit, offset);
    }



    @MutationMapping
    public Book createBook(@Argument InputCreateBook inputCreateBook) {
        Book newBook = new Book();
        newBook.setTitle(inputCreateBook.getTitle());
        newBook.setAuthor(inputCreateBook.getAuthor());
        return bookRepository.save(newBook);
    }

    @MutationMapping
    public Review createReview(@Argument Long bookId, @Argument String text) {
        // Check if the book exists
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            throw new IllegalArgumentException("Book not found with ID: " + bookId);
        }

        // Create a new review
        Review review = new Review();
        review.setBook(book);
        review.setText(text);

        // Save the review
        return reviewRepository.save(review);
    }

    @SchemaMapping
    public List<Review> reviews(Book book) {
        return reviewRepository.findByBook(book);
    }
}
