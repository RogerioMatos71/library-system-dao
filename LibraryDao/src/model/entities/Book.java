package model.entities;

import java.util.Objects;

public class Book {
	
	private Integer id;
	private String title;
	private String author;
	private String isbn;
	private String publisher;
	private Integer yearPublication;
	
	
	public Book() {
		
	}
	
	public Book(Integer id, String title, String author, String isbn, String publisher, Integer yearPublication) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publisher = publisher;
		this.yearPublication = yearPublication;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getYearPublication() {
		return yearPublication;
	}

	public void setYearPublication(Integer yearPublication) {
		this.yearPublication = yearPublication;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	

}
