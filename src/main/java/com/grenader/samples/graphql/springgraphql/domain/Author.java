package com.grenader.samples.graphql.springgraphql.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * A very simple Author entity
 * 
 * @author <a href="mailto:softx.it@gmail.com">Mohammad Ali Azam</a>
 *
 */
@Entity
@Table(name = "author")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@NotNull
	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JsonManagedReference(value="auth-books")
	private Set<Book> books = new HashSet<>();

	@ManyToOne
	@JsonIgnoreProperties("authors")
	@JsonBackReference(value="pub-author")
	private Publisher publisher;

	/**
	 * No-arg constructor
	 */
	public Author() {
		super();
	}

	/**
	 * Constructor that takes name of it
	 * 
	 * @param name
	 */
	public Author(@NotNull String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Author name(String name) {
		this.name = name;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public Author books(Set<Book> books) {
		this.books = books;
		return this;
	}

	public Author addBooks(Book book) {
		this.books.add(book);
		book.setAuthor(this);
		return this;
	}

	public Author removeBooks(Book book) {
		this.books.remove(book);
		book.setAuthor(null);
		return this;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public Author publisher(Publisher publisher) {
		this.publisher = publisher;
		return this;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Author)) {
			return false;
		}
		return id != null && id.equals(((Author) o).id);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}