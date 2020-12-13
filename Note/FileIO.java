package by.tce.jonline.note;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InvalidObjectException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.FileNotFoundException;

// Чтение и запись файла

public class FileIO {
	
	public boolean serialization(NoteList notes, String fileName) {
		boolean flag = false;
		File f = new File(fileName);
		ObjectOutputStream ostream = null;
		
		try {
			FileOutputStream fos = new FileOutputStream(f);
			if (fos != null) {
				ostream = new ObjectOutputStream(fos);
				ostream.writeObject(notes); // сериализация
				flag = true;
			}
		} catch (FileNotFoundException e) {
			System.err.println("Файл не может быть создан: "+ e);
		} catch (NotSerializableException e) {
			System.err.println("Класс не поддерживает сериализацию: " + e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				if (ostream != null) {
				ostream.close();
				}
			} catch (IOException e) {
				System.err.println("Ошибка при закрытии потока");
			}
		}
		return flag;
	}
	
	public NoteList deserialization(String fileName) throws InvalidObjectException {
		File fr = new File(fileName);
		ObjectInputStream istream = null;
		try {
			FileInputStream fis = new FileInputStream(fr);
			istream = new ObjectInputStream(fis);
			// десериализация
			NoteList notes = (NoteList) istream.readObject();
			return notes;
		} catch (ClassNotFoundException ce) {
			System.err.println("Класс не существует: " + ce);
		} catch (FileNotFoundException e) {
			System.err.println("Файл для десериализации не существует: "+ e);
		} catch (InvalidClassException ioe) {
			System.err.println("Несовпадение версий классов: " + ioe);
		} catch (IOException ioe) {
			System.err.println("Общая I/O ошибка: " + ioe);
		} finally {
			try {
				if (istream != null) {
					istream.close();
				}
			} catch (IOException e) {
				System.err.println("Ошибка при закрытии потока ");
			}
		}
		
		throw new InvalidObjectException("Объект не восстановлен");
	}

}
