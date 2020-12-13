package by.tce.jonline.archive;

import java.util.ArrayList;
import java.util.List;

//архив - список дел

public class Archive {
	private List<Student> archive = new ArrayList<>();
	
	public Archive () {
		//archive = FileIO.load(); 	// прямой доступ
		archive = Client.load();	// доступ через сервер
	}
	
	// добавить дело в архив
	public boolean addCase(Student student) {
		archive.add(student);
		return true;
	}
	
	// удалить дело из архива
	public boolean removeCase(Student student) {
		archive.remove(student);
		return true;
	}
	
	// выбрать дело для просмотра
	public Student getCase(int number) {
		for(Student student : archive) {
			if(student.getNumber()==number) {
				return student;
			}
		}
		return null;
	}
	
	// получить весь архив
	public List<Student> getArchive(){
		return archive;
	}
	
	// сохранить данные в XML-файл
	public boolean save() {
		//FileIO.create(archive);	// прямой доступ 
		Client.save(archive);		// доступ через сервер
		return true;
	}
	
	// первоначальное создание Архива (на случай потери XML-файла)
	public void initArchive() {
		archive.add(new Student(202066493, "Иванов А.С.", 1, "Факультет прикладной математики и информатики"));
		archive.add(new Student(201964852, "Петров В.А.", 2, "Биологический факультет"));
		archive.add(new Student(201760604, "Сидоров А.Н.", 4, "Механико-математический факультет"));
		archive.add(new Student(201869812, "Воронов П.Н.", 3, "Факультет международных отношений"));
		archive.add(new Student(201962427, "Николаев Н.Н.", 2, "Факультет журналистики"));
		archive.add(new Student(202064587, "Ковалев С.Н.", 1, "Экономический факультет"));
		archive.add(new Student(201862712, "Кораблев С.В.", 3, "Исторический факультет"));
		archive.add(new Student(201963587, "Сергеев П.А.", 2, "Юридический факультет"));
		archive.add(new Student(201766453, "Платонов Ю.Г.", 4, "Факультет социокультурных коммуникаций"));
		archive.add(new Student(202062846, "Михалков С.М.", 1, "Факультет радиофизики и компьютерных технологий"));
		FileIO.create(archive);
	}

}
