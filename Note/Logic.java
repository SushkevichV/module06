package by.tce.jonline.note;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {
	
	private NoteList notes = new NoteList();
	private FileIO file = new FileIO();
	
	{
		try {
			notes=file.deserialization("notes.data");
		} catch (InvalidObjectException e) {
			e.printStackTrace();
		}
	}
	
	public String addNote(String ...str ) {
		System.out.println(str[1]);
		if(notes.addNote(str[0], str[1], str[2])) {
			file.serialization(notes, "notes.data");
			return "Заметка сохранена\n";
		} else {
			return "Некорректно заполнено поле E-mail.\n";
		}
	}
	
	// получение списа заметок
	public List<Note> getNotes(int sort){
		if(sort==1) {
			return sort(notes.getNotes());
		}
		return notes.getNotes();
	}
	
	// поиск заметок по ключевому слову или по дате
	public List<Note> findNote(String str, int sort) {
		List<Note> notelist = notes.getNotes();
		List<Note> selectedNotes = new ArrayList<>();
		Pattern pattern = Pattern.compile("^[0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{2,4}$");
		Matcher matcher = pattern.matcher(str);
		
		for(Note note : notelist) {
			if(note.getSubject().matches(".*"+str+".*")) {
				selectedNotes.add(note);
			}
			if(note.getText().matches(".*"+str+".*")) {
				selectedNotes.add(note);
			}
			if(note.getEmail().matches(".*"+str+".*")) {
				selectedNotes.add(note);
			}
			
			// Немного громоздкое сравнение, но сравнивать по константам Calendar еще более громоздко,
			// а отказываться от преимуществ Calendar я не хотел.
			if(matcher.matches()) {
				Calendar nextDay = strToDate(str);		// новая дата из строки
				nextDay.add(Calendar.DAY_OF_MONTH, 1);	// новая дата увеличена на один день
				// сравнивается интервал, т.к. дата из строки создана без учета времени, а дата в Note с учетом времени
				if(note.getDate().after(strToDate(str)) && note.getDate().before(nextDay)) {
					selectedNotes.add(note);
				}
			}
		}
		if(sort==1) {
			return sort(selectedNotes);
		}
		return selectedNotes;
	}
	
	// перевод строки в дату
	private Calendar strToDate(String str) {
		String[] strDate = str.split("\\.");
		int year = Integer.parseInt(strDate[2]);
		// если указаны только две цифры года
		if(year>80 && year<100) {
			year+=1900;
		}
		if(year<=80) {
			year+=2000;
		}
		
		Calendar date = new GregorianCalendar(year, (Integer.parseInt(strDate[1])-1), Integer.parseInt(strDate[0]));
		return date;
	}
	
	// поиск заметок в диапазоне дат
	public List<Note> findNote(String str1, String str2, int sort) {
		List<Note> notelist = notes.getNotes();
		List<Note> selectedNotes = new ArrayList<>();
		Pattern pattern = Pattern.compile("^[0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{2,4}$");
		Matcher matcher = pattern.matcher(str1);
		
		if(matcher.matches()) {
			matcher = pattern.matcher(str2);
			if(matcher.matches()) {
				Calendar nextDay = strToDate(str2);		// новая дата из строки
				nextDay.add(Calendar.DAY_OF_MONTH, 1);	// новая дата увеличена на один день
				for(Note note : notelist) {
					if(note.getDate().after(strToDate(str1)) && note.getDate().before(nextDay)) {
						selectedNotes.add(note);
					}
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
		if(sort==1) {
			return sort(selectedNotes);
		}
		return selectedNotes;
	}
	
	// поиск заметок в диапазоне дат по ключевому слову
		public List<Note> findNote(String str1, String str2, String str3, int sort) {
			List<Note> notelist = notes.getNotes();
			List<Note> selectedNotes = new ArrayList<>();
			Pattern pattern = Pattern.compile("^[0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{2,4}$");
			Matcher matcher = pattern.matcher(str1);
			
			if(matcher.matches()) {
				matcher = pattern.matcher(str2);
				if(matcher.matches()) {
					Calendar nextDay = strToDate(str2);		// новая дата из строки
					nextDay.add(Calendar.DAY_OF_MONTH, 1);	// новая дата увеличена на один день
					for(Note note : notelist) {
						if(note.getDate().after(strToDate(str1)) && note.getDate().before(nextDay)) {
							if(note.getSubject().matches(".*"+str3+".*")) {
								selectedNotes.add(note);
							}
							if(note.getText().matches(".*"+str3+".*")) {
								selectedNotes.add(note);
							}
							if(note.getEmail().matches(".*"+str3+".*")) {
								selectedNotes.add(note);
							}
						}
					}
				} else {
					return null;
				}
			} else {
				return null;
			}
			if(sort==1) {
				return sort(selectedNotes);
			}
			return selectedNotes;
		}
	
	// сортировка заметок по теме в алфавитном порядке
	public List<Note> sort(List <Note> noteList) {
		
		for (int i=0; i<noteList.size(); i++) {
			String max=noteList.get(i).getSubject();
			int maxI=i;
			
			for (int j=i+1; j<noteList.size(); j++) {
				if (max.compareTo(noteList.get(j).getSubject())>0) {
					max=noteList.get(j).getSubject();
					maxI=j;
				}
			}
			if (i!=maxI) {
				Note tmp=noteList.get(i);
				noteList.set(i, noteList.get(maxI));
				noteList.set(maxI, tmp);
			}
		}
		return noteList;
	}
	
	// удаление всех заметок
	public void clearNotes() {
		notes.getNotes().removeAll(getNotes(0));
	}
	
	// удаление заметки за указанную дату по ключевому слову
	public String removeNote(String str1, String str2) {
		List<Note> notelist = notes.getNotes();
		List<Note> selectedNotes = new ArrayList<>();
		Pattern pattern = Pattern.compile("^[0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{2,4}$");
		Matcher matcher = pattern.matcher(str1);
		Note removingNote = null;
		
		for(Note note : notelist) {
			if(matcher.matches()) {
				Calendar nextDay = strToDate(str1);		// новая дата из строки
				nextDay.add(Calendar.DAY_OF_MONTH, 1);	// новая дата увеличена на один день
				// сравнивается интервал, т.к. дата из строки создана без учета времени, а дата в Note с учетом времени
				if(note.getDate().after(strToDate(str1)) && note.getDate().before(nextDay)) {
					if(note.getSubject().matches(".*"+str2+".*")) {
						removingNote = note;
						break;
					} else {
						if(note.getText().matches(".*"+str2+".*")) {
							removingNote = note;
							break;
						} else {
							if(note.getEmail().matches(".*"+str2+".*")) {
								removingNote = note;
								break;
							}
						}
					}
				}
			}
		}
		
		if(removingNote == null) {
			return "Заметка не найдена\n";
		}
		
		notes.removeNote(removingNote);
		file.serialization(notes, "notes.data");
		return "Заметка удалена\n";
	}

}
