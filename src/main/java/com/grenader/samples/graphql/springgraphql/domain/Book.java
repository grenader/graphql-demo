package com.grenader.samples.graphql.springgraphql.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A very simple Book entity
 * 
 * @author <a href="mailto:softx.it@gmail.com">Mohammad Ali Azam</a>
 *
 */
@Entity
@Table(name = "book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@NotNull
	@Column(name = "title", nullable = false)
	private String title;

	@NotNull
	@Column(name = "asin", nullable = false)
	private String ASIN;

	@ManyToOne
	@JsonIgnoreProperties("books")
	@JsonBackReference(value="auth-books")
	private Author author;

	@ManyToOne
	@JsonIgnoreProperties("books")
	@JsonBackReference(value="pub-books")
	private Publisher publisher;

	public Book() {
		super();
	}

	public Book(@NotNull String title, @NotNull String ASIN) {
		super();
		this.title = title;
		this.ASIN = ASIN;
	}

	public Book title(String title) {
		this.title = title;
		return this;
	}

	public Book ASIN(String aSIN) {
		this.ASIN = aSIN;
		return this;
	}

	public Book author(Author author) {
		this.author = author;
		return this;
	}

	public Book publisher(Publisher publisher) {
		this.publisher = publisher;
		return this;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Book)) {
			return false;
		}
		return id != null && id.equals(((Book) o).id);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		return result;
	}
}