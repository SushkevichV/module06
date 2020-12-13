package by.tce.jonline.library;

import java.util.Set;

// общая логика

public class Logic {
	private BookList library = new BookList();
	private FileIO file = new FileIO();
	
	{
		library.setBooks(file.load());
	}
	
	// поиск книги
	public Book findBook(String str) {
		for(Book book : getLibrary().getBooks()) {
			if(book.getAuthor().matches(".*"+str+".*")) {
				return book;
			}
			if(book.getTitle().matches(".*"+str+".*")) {
				return book;
			}
		}
		return null;
	}
	
	public Set<Book> getBooks() {
		return getLibrary().getBooks();
	}
	
	public String addBook(String title, String author, String description, boolean eBook, boolean pBook, String email) {
		return null;
	}
	
	public String removeBook(String title, String author) {
		return null;
	}

	public BookList getLibrary() {
		return library;
	}

}
