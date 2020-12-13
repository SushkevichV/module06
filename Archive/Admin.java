package by.tce.jonline.archive;

//доступ к методам работы с архивом с правами администратора

public class Admin extends User {
	
	// добавление нового дела студента
	public boolean addStudent(int number, String name, int course, String faculty) {
		for(Student student : archive.getArchive()) {
			if(student.getNumber() == number) {
				return false;
			}
		}
		if(archive.addCase(new Student(number, name, course, faculty)) == true) {;
			return true;
		} else {
			return false;
		}
	}
	
	// редактирование дела студента
	public boolean editStudent(int number, String name, int course, String faculty) {
		for(Student student : archive.getArchive()) {
			if(student.getNumber() == number) {
				student.setName(name);
				student.setCourse(course);
				student.setFaculty(faculty);
				return true;
			}
		}
		return false;
	}
	
	// удаление дела студента
	public boolean removeStudent(int number) {
		for(Student student : archive.getArchive()) {
			if(student.getNumber() == number) {
				archive.removeCase(student);
				return true;
			}
		}
		return false;
	}
	
}
