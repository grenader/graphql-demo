package com.grenader.samples.graphql.springgraphql.repository;

import com.grenader.samples.graphql.springgraphql.domain.Book;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.graphql.data.GraphQlRepository;

/**
 * Spring Data repository for the Book entity
 * 
 * @author <a href="mailto:softx.it@gmail.com">Mohammad Ali Azam</a>
 *
 */
@SuppressWarnings("unused")
@GraphQlRepository
public interface BookRepository extends QuerydslPredicateExecutor<Book> {
}
