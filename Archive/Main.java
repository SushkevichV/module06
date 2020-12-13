package by.tce.jonline.archive;

/* Модуль 6. Задание 2. Создайте клиент-серверное приложение "Архив".
 * В архиве хранятся дела (например, студентов). Архив находится на сервере.
 * Клиент, в зависимости от прав, может запросить дело на просмотр, внести в него изменения
 * или создать новое дело.
 * Требования к коду (полагаю, на этом сделан основной акцент задания):
 * 	для реализации сетевого соединения использовать сокеты.
 * 	формат хранения данных на сервере - xml-файлы.
 * 
 * Запускается после запуска Server
 */

public class Main {

	public static void main(String[] args) {
		Account account = new Account();
		View view = new View();
		User user = account.getAccess("admin", "111"); // доступ с правами администратора
		
		//user.init(); // первоначальное создание Архива (на случай потери XML-файла)
		
		view.view(user.getStudent(201962427)); // просмотр дела по номеру
		
		user.addStudent(202062872, "Тищенко Н.С.", 1, "Юридический факультет"); // добавление нового дела
		
		user.editStudent(202062872, "Тищенко Н.С.", 2, "Юридический факультет"); // редактирование дела

		view.view(user.getStudent("Тищенко")); // просмотр дел по имени
		
		user.removeStudent(202062872); // удаление дела
				
		user.exit(); // выход (с сохранением)

	}

}