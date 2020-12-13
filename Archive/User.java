package by.tce.jonline.archive;

import java.util.ArrayList;
import java.util.List;

// доступ к методам работы с архивом с правами пользователя

public class User {
	protected Archive archive = new Archive(); // чтобы было видно в подклассе
	
	// возвращает дело по номеру студента
	public Student getStudent(int number) {
		return archive.getCase(number);
	}
	
	// поиск дела по имени (поскольку одинаковых имен может быть несколько, возвращается массив)
	public List<Student> getStudent(String name) {
		List<Student> students = new ArrayList<>();
		for(Student student : archive.getArchive()) {
			if(student.getName().matches(name+".*")) {
				students.add(student);
			}
		}
		return students;
	}
	
	public boolean addStudent(int number, String name, int course, String faculty) {
		return false;
	}
	
	public boolean editStudent(int number, String name, int course, String faculty) {
		return false;
	}
	
	public boolean removeStudent(int number) {
		return false;
	}

	public void init() {
		archive.initArchive();
		
	}

	// выход (с сохранением)
	public void exit() {
		archive.save();
		
	}

}
