package com.grenader.samples.graphql.springgraphql.contoller.book;

import com.grenader.samples.graphql.springgraphql.domain.Author;
import com.grenader.samples.graphql.springgraphql.domain.Book;
import com.grenader.samples.graphql.springgraphql.domain.Publisher;
import com.grenader.samples.graphql.springgraphql.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * GraphQL mutation resolver that delegates service methods of all the entities
 *
 * @author <a href="mailto:softx.it@gmail.com">Mohammad Ali Azam</a>
 * @see PublisherService
 * @see AuthorService
 * @see BookService
 */


@Controller
@RequiredArgsConstructor
public class EntityMutationController {

    private final PublisherService publisherService;
    private final AuthorService authorService;
    private final BookService bookService;

    /**
     * Adds a Publisher entity
     *
     * @param name name of the Publisher
     * @return An instance of Publisher if created successfully
     * @throws Exception If any occurred
     */

    @MutationMapping
    public Publisher addPublisher(@Argument String name) throws Exception {
        Publisher publisher = new Publisher(name);
        return publisherService.save(publisher);
    }


    /**
     * Adds a Author entity
     *
     * @param name        Name of the Author
     * @param publisherId Id of the parent Publisher
     * @return An instance of Author if created successfully
     * @throws Exception If any occurred
     */
    @MutationMapping
    public Author addAuthor(@Argument String name, @Argument Long publisherId) throws Exception {
        Author author = new Author(name);
        author.setPublisher(publisherService.findOne(publisherId).get());
        return authorService.save(author);
    }


    /**
     * Adds a Book entity
     *
     * @param title       Title of the Book
     * @param ISBN        ISBN of the Book
     * @param publisherId Id of the parent Publisher
     * @param authorId    Id of the parent Author
     * @return An instance of Book if created successfully
     * @throws Exception If any occurred
     */
    @MutationMapping
    public Book addBook(@Argument String title, @Argument String ISBN, @Argument Long publisherId, @Argument Long authorId) throws Exception {
        Book book = new Book(title, ISBN);
        book.setAuthor(authorService.findOne(authorId).get());
        book.setPublisher(publisherService.findOne(publisherId).get());
        return bookService.save(book);

    }

}

