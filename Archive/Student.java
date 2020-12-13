package by.tce.jonline.archive;

import java.io.Serializable;

//Класс хранит данные о студенте

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private int number; 	// номер
	private String name; 	// Ф.И.О.
	private int course; 	// курс
	private String faculty;	// факультет
	
	public Student(int number, String name, int course, String faculty) {
		this.number = number;
		this.name = name;
		this.course = course;
		this.faculty = faculty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + course;
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (course != other.course)
			return false;
		if (faculty == null) {
			if (other.faculty != null)
				return false;
		} else if (!faculty.equals(other.faculty))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "number=" + number + ", name=" + name + ", course=" + course + ", faculty=" + faculty;
	}

}
