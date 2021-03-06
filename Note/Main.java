package by.tce.jonline.note;

/* Модуль 6. Задание 2. Разработать консольное приложение, работающее с Заметками в Блокноте.
 * Каждая Заметка это: Заметка (тема, дата создания, e-mail, сообщение.
 * Общие пояснения к практическому заданию.
 * В начале работы приложения данные должны считываться из файла, в конце работы - сохраняться в файл.
 * У пользователя должна быть возможность найти запись по любому параметруили группе параметров 
 *   (группу параметров можно определить самостоятельно), получить требуемые записи в отсортированном виде,
 *   найти записи, текстовое поле которых содержит определенное слово, а также добавить новую запись.
 * Особое условие: поиск, сравнение и валидацию вводимой информации осуществлять с использованием регулярных выражений.
 * Особое условие: проверку введенной информации на валидность должен осуществлять код, непосредственно добавляющий информацию.
 */

public class Main {

	public static void main(String[] args) {
		Logic note = new Logic();
		View view = new View();
		
		// новая заметка
		System.out.println(note.addNote("Поликлиника", "rax_maniana@mail.ru", "Вызвать врача на дом"));
		
		// удаление заметки на указанную дату по ключевому слову
		System.out.println(note.removeNote("28.11.20", "заметка"));
		
		// поиск заметок по ключевому слову, сортировка в хронологическом порядке
		view.view(note.findNote("Школа", 0)); // если требуется сортировка по теме в алфавитном порядке, заменить 0 на 1
		
		// поиск заметок за указанную дату, сортировка в хронологическом порядке
		view.view(note.findNote("27.11.20", 0)); // если требуется сортировка по теме в алфавитном порядке, заменить 0 на 1
		
		// поиск заметок за период, сортировка в хронологическом порядке
		view.view(note.findNote("27.11.20", "29.11.2020", 0)); // если требуется сортировка по теме в алфавитном порядке, заменить 0 на 1
		
		// поиск заметок за период по ключевому слову, сортировка в хронологическом порядке
		view.view(note.findNote("27.11.20", "28.11.2020", "заметка", 0)); // если требуется сортировка по теме в алфавитном порядке, заменить 0 на 1
		
		// все заметки, сортировка в хронологическом порядке
		view.view(note.getNotes(0)); // если требуется сортировка по теме в алфавитном порядке, заменить 0 на 1

	}

}
