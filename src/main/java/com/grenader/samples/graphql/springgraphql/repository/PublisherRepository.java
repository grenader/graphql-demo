package com.grenader.samples.graphql.springgraphql.repository;

import com.grenader.samples.graphql.springgraphql.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Publisher entity
 * 
 * @author <a href="mailto:softx.it@gmail.com">Mohammad Ali Azam</a>
 *
 */
@SuppressWarnings("unused")
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
