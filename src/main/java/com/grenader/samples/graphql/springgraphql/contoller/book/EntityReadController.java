package com.grenader.samples.graphql.springgraphql.contoller.book;


import com.grenader.samples.graphql.springgraphql.domain.*;
import com.grenader.samples.graphql.springgraphql.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * GraphQL query resolvers that delegates service methods of all the entities
 *
 * @author <a href="mailto:softx.it@gmail.com">Mohammad Ali Azam</a>
 * @see PublisherService
 * @see AuthorService
 * @see BookService
 */


@Controller
@RequiredArgsConstructor
public class EntityReadController {

    private final PublisherService publisherService;
    private final AuthorService authorService;
    private final BookService bookService;


    /**
     * Returns a Publisher for the given id
     *
     * @param id Primary key of the Publisher
     * @return An instance of Publisher
     */
    @QueryMapping
    public Publisher publisherById(@Argument Long id) {
        try {
            Optional<Publisher> publisher = publisherService.findOne(id);
            if (publisher.isPresent()) {
                return publisher.get();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }


    /**
     * Returns an Author for the given id
     *
     * @param id Primary key of the Author
     * @return An instance of Author
     */
    @QueryMapping
    public Author authorById(@Argument Long id) {
        try {
            Optional<Author> author = authorService.findOne(id);
            if (author.isPresent()) {
                author.get().getBooks();
                return author.get();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }


    /**
     * Returns a Book for the given id
     *
     * @param id Primary key of the Book
     * @return An instance of Book
     */
    @QueryMapping
    public Book bookById(@Argument Long id) {
        try {
            Optional<Book> book = bookService.findOne(id);
            if (book.isPresent()) {
                return book.get();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}

