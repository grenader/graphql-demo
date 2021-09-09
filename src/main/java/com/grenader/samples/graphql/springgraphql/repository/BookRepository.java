package com.grenader.samples.graphql.springgraphql.repository;

import com.grenader.samples.graphql.springgraphql.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Book entity
 * 
 * @author <a href="mailto:softx.it@gmail.com">Mohammad Ali Azam</a>
 *
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
