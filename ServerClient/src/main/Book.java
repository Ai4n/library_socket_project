package main;

import java.io.Serializable;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	private int bookId;
	private Author author;
	private String title;
	private int year;
	private String genre;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Book(int bookId, Author author, String title, int year, String genre) {
		this.bookId = bookId;
		this.author = author;
		this.title = title;
		this.year = year;
		this.genre = genre;
	}

	public Book(Author author, String title, int year, String genre) {
		this.author = author;
		this.title = title;
		this.year = year;
		this.genre = genre;
	}
	
	
	@Override
	public String toString() {
		return author.getName() + ", " + author.getSurname() + ", " + 
	author.getLanguage() + ", " + title + ", " + year + ", " + genre;
	}
}