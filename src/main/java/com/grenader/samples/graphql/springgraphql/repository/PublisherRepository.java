package com.grenader.samples.graphql.springgraphql.repository;

import com.github.mdaliazam.graphql.domain.Publisher;
import com.grenader.samples.graphql.springgraphql.domain.Publisher;
import com.grenader.samples.graphql.springgraphql.service.PublisherService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Publisher entity
 * 
 * @author <a href="mailto:softx.it@gmail.com">Mohammad Ali Azam</a>
 *
 */
@SuppressWarnings("unused")
@GraphQlRepository
public interface PublisherRepository extends QuerydslPredicateExecutor<Publisher> {
}
