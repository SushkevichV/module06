package by.tce.jonline.note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

// вывод данных на консоль

public class View {

	public void view(List<Note> notes) {
		if(notes.size()==0) {
			System.out.println("Не найдено ни одной заметки.");
		}
		
		DateFormat df = new SimpleDateFormat("dd MMMM yyyyг.\n");
		
		for(Note note : notes) {
			System.out.printf(df.format(note.getDate().getTime()));
			System.out.println("Тема: "+ note.getSubject());
			System.out.println("E-mail: "+ note.getEmail());
			System.out.println(note.getText()+"\n");
		}
		System.out.println("---------------\n");
		
	}
	

}
