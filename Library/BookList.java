package by.tce.jonline.library;

// список книг

import java.util.HashSet;
import java.util.Set;

public class BookList {
	private Set <Book> books = new HashSet<>();
	
	public BookList() {
		
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	public boolean addBook(Book book) {
		return books.add(book);
	}
	
	public boolean removeBook(Book book) {
		return books.remove(book);
	}

}
