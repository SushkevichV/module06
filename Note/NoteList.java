package by.tce.jonline.note;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Список заметок. Этот объект будет записан в файл
 * 
 */

public class NoteList implements Serializable {
	private List<Note> notes = new ArrayList<>();
	private static final long serialVersionUID = 1L;
	
	public NoteList() { 
		
	}
	
	public boolean addNote(String subject, String email, String text) {
		email = email.toLowerCase();
		// по условию проверку введенной информации должен осуществлять код, 
		// непосредственно добавляющий информацию
		Pattern pattern = Pattern.compile("^[a-z0-9]+([\\._-]?[a-z0-9]+)*@[a-z0-9-]+\\.[a-z]{2,4}$");
		Matcher matcher = pattern.matcher(email);
		if(!matcher.matches()) {
			return false;
		}
		
		Calendar date = new GregorianCalendar().getInstance();
		notes.add(new Note(date, subject, email, text));
		
		return true;
	}
	
	public boolean removeNote(Note note) {
		notes.remove(note);
		return true;
	}
	
	public Note getNote(int index) {
		return notes.get(index);
	}
	
	public void setNote(Note note, int index) {
		notes.set(index, note);
	}
	
	public List<Note> getNotes(){
		return notes;
	}
	
	public boolean setNotes(List<Note> notes) {
		this.notes = notes;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
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
		NoteList other = (NoteList) obj;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NoteList [notes=" + notes + "]";
	}

}
