package by.tce.jonline.library;

/* Модуль 6. Задание 1. Создать консольное приложение "Учет книг в домашней библиотеке"
 * Общие требования к заданию:
 * Система учитывает книги как в электронном, так и в бумажном варианте.
 * Существующие роли: пользователь, администратор.
 * Пользователь может просматривать книги в каталоге, осуществлять поиск книг в каталоге.
 * Администратор может модифицировать каталог.
 * * При добавлении описания книги в каталог оповещение о ней рассылается на e-mail всем пользователям.
 * ** При просмотре каталога желательно реализовать постраничный просмотр.
 * *** Пользователь может предложить добавить книгу в библиотеку, переслав ее администратору на e-mail.
 * Каталог книг хранится в текстовом файле.
 * Данные аутентификации пользователей хранятся в текстовом файле. Пароль не хранится в открытом виде. 
 */

public class Main {

	public static void main(String[] args) {
		View view = new View();
		Access user = new Access();
		
		// Блок операций с правами пользователя
		if (user.getAccess("Reader", "richy")!=null) {
			Logic library = user.getAccess("Reader", "richy");
			
			// поиск книги
			if(library.findBook("Шерлок") != null) {
				System.out.println("Книга найдена:");
				view.view(library.findBook("Шерлок"));
			} else {
				System.out.println("Книга не найдена");
			}
			
			// добавление книги
			System.out.println(library.addBook("Машина времени", "Герберт Уэлс", null, false, true, user.getEmail()));
			
			// просмотр списка книг. В скобках количество книг на "странице"
			view.view(library.getBooks(), 20);
			
		} else {
			System.out.println("Требуется авторизация");
		}

		// Блок операций с правами администратора
		if (user.getAccess("Administrator", "admin")!=null) {
			Logic library = user.getAccess("Administrator", "admin");
			
			// добавление книги
			System.out.println(library.addBook("Машина времени", "Герберт Уэлс", null, false, true, user.getEmail()));
			
			// удаление книги
			System.out.println(library.removeBook("Машина времени", "Герберт Уэлс"));
					
		} else {
			System.out.println("Требуется авторизация");
		}
		
	}
	
}
