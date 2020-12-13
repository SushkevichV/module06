package by.tce.jonline.library;

import java.util.Scanner;
import java.util.Set;

// вывод на консоль

public class View {
	public View(){
		
	}
	
	// вывод искомой книги
	public void view(Book book) {
		System.out.printf("%s - \"%s\"\n", book.getAuthor(), book.getTitle());
		System.out.println("Описание: "+ book.getDescription());
		if(book.iseBook() && book.ispBook()) {
			System.out.println("Доступна в электронном и бумажном варианте.\n");
		} else {
			if(book.iseBook()) {
				System.out.println("Доступна в электронном варианте.\n");
			} else {
				System.out.println("Доступна в бумажном варианте.\n");
			}
		}
	}
	
	// вывод списка книг
	public void view(Set <Book> books, int booksOnPage) {
		System.out.println("Список книг библиотеки:\n");
		int i=1;
		for(Book book : books) {
			System.out.printf("%s - \"%s\"\n", book.getAuthor(), book.getTitle());
			System.out.println("Описание: "+ book.getDescription());
			if(book.iseBook() && book.ispBook()) {
				System.out.println("Доступна в электронном и бумажном варианте.\n");
			} else {
				if(book.iseBook()) {
					System.out.println("Доступна в электронном варианте.\n");
				} else {
					System.out.println("Доступна в бумажном варианте.\n");
				}
			}
			i++;
			if (i==booksOnPage) {
				i=1;
				System.out.println("Нажмите Enter для перехода на следующую страницу");
				newPage();
				//System.out.println("\f");
			}
		}
	}

	// импровизированная задержка при листинге
	private void newPage() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
	}

}
