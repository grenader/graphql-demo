package com.grenader.samples.graphql.springgraphql.service;

import com.github.mdaliazam.graphql.domain.Book;
import com.grenader.samples.graphql.springgraphql.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Book}.
 * 
 * @author <a href="mailto:softx.it@gmail.com">Mohammad Ali Azam</a>
 *
 */
public interface BookService {

	/**
	 * Save a book.
	 *
	 * @param book the entity to save.
	 * @return the persisted entity.
	 */
	Book save(Book book);

	/**
	 * Get all the books.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	Page<Book> findAll(Pageable pageable);

	/**
	 * Get the "id" book.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	Optional<Book> findOne(Long id);

	/**
	 * Delete the "id" book.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id);

}
