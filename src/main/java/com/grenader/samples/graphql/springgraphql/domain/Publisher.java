package com.grenader.samples.graphql.springgraphql.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A very simple Publisher entity
 * 
 * @author <a href="mailto:softx.it@gmail.com">Mohammad Ali Azam</a>
 *
 */
@Entity
@Table(name = "publisher")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
public class Publisher implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "publisher", fetch = FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JsonManagedReference(value="pub-books")
	private Set<Book> books = new HashSet<>();

	@OneToMany(mappedBy = "publisher", fetch = FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JsonManagedReference(value="pub-author")
	private Set<Author> authors = new HashSet<>();

	/**
	 * No-arg constructor
	 */
	public Publisher() {
		super();
	}

	/**
	 * Constructor that takes name of it
	 * 
	 * @param name
	 */
	public Publisher(@NotNull String name) {
		super();
		this.name = name;
	}

	public Publisher name(String name) {
		this.name = name;
		return this;
	}

	public Publisher books(Set<Book> books) {
		this.books = books;
		return this;
	}

	public Publisher addBooks(Book book) {
		this.books.add(book);
		book.setPublisher(this);
		return this;
	}

	public Publisher removeBooks(Book book) {
		this.books.remove(book);
		book.setPublisher(null);
		return this;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public Publisher authors(Set<Author> authors) {
		this.authors = authors;
		return this;
	}

	public Publisher addAuthors(Author author) {
		this.authors.add(author);
		author.setPublisher(this);
		return this;
	}

	public Publisher removeAuthors(Author author) {
		this.authors.remove(author);
		author.setPublisher(null);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Publisher)) {
			return false;
		}
		return id != null && id.equals(((Publisher) o).id);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}