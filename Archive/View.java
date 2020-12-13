package by.tce.jonline.archive;

// вывод данных в консоль

import java.util.List;

public class View {
	public void view(Student student) {
		if(student == null) {
        	System.out.println("Дело не найдено");
        } else {
			System.out.println("Номер: "+ student.getNumber());
			System.out.println("ФИО: "+ student.getName());
			System.out.println("Курс: "+ student.getCourse());
			System.out.println("Факультет: "+ student.getFaculty());
			System.out.println();
        }
	}
	
	public void view(List<Student> students) {
		if(students.size() == 0) {
        	System.out.println("Дело не найдено");
        } else {
			for (Student student : students) {
				System.out.println("Номер: "+ student.getNumber());
				System.out.println("ФИО: "+ student.getName());
				System.out.println("Курс: "+ student.getCourse());
				System.out.println("Факультет: "+ student.getFaculty());
				System.out.println();
			}
		}
	}

}
