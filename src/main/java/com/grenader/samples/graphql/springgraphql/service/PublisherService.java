package com.grenader.samples.graphql.springgraphql.service;

import com.grenader.samples.graphql.springgraphql.domain.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Publisher}.
 * 
 * @author <a href="mailto:softx.it@gmail.com">Mohammad Ali Azam</a>
 *
 */
public interface PublisherService {

	/**
	 * Save a publisher.
	 *
	 * @param publisher the entity to save.
	 * @return the persisted entity.
	 */
	Publisher save(Publisher publisher);

	/**
	 * Get all the publishers.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	Page<Publisher> findAll(Pageable pageable);

	/**
	 * Get the "id" publisher.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	Optional<Publisher> findOne(Long id);

	/**
	 * Delete the "id" publisher.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id);
}
