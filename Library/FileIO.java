package by.tce.jonline.library;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// чтение и запись файлов

public class FileIO {
	
	// чтение файлов
    private List<String> read(String fileName) {
    	List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
        	String line = reader.readLine();
        	while(line != null) {
        		lines.add(line);
        		line = reader.readLine();
        	}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	//reader.close();
        }
        return lines;
    }
    
    // формирование списка книг
    public Set<Book> load() {
    	List<String> lines = read("library.txt");
    	Set<Book> books = new HashSet<>();
    	for(String line : lines) {
    		String[] fields;
    		fields = line.split(" - ");
    		books.add(new Book(fields[1], fields[0], fields[2], fields[3].equals("1") ? true : false, fields[4].equals("1") ? true : false));
    	}
    	
    	return books;
    }
    
    // формирование списка пользователей
    public Set<User> getUsers() {
    	List<String> lines = read("users.txt");
    	Set<User> users = new HashSet<>();
    	for(String line : lines) {
    		String[] fields;
    		String pwd="";
    		fields = line.split(" ");
    		for(int i=3; i<fields.length; i++) {
    			pwd+=(char)Integer.parseInt(fields[i]); // импровизированная дешифровка пароля
    		}
    		users.add(new User(fields[0], fields[1].equals("1") ? true : false, fields[2], pwd));
    	}
		return users;
    	
    }

    // перезапись файла со списком книг
	public void replace(Set<Book> books) throws IOException {
		FileWriter nFile = new FileWriter("library.txt");
		
        for(Book book : books) {
        	String str = book.getAuthor()+" - "+book.getTitle()+" - "+(book.getDescription()==null ? "" : book.getDescription())+" - "+(book.iseBook() ? "1" : "0") +" - "+(book.ispBook() ? "1" : "0"); 
            nFile.write(str+"\n");
        }

        nFile.close();
	}

	// дополнение файла со списком книг
	public void appEnd(String title, String author, String description, boolean eBook, boolean pBook) throws IOException {
		FileWriter file = new FileWriter("library.txt", true);
		
		String str ="\n"+ author + " - "+ title + " - "+ (description==null ? "" : description) + " - "+(eBook ? "1" : "0") +" - "+(pBook ? "1" : "0");
		file.write(str);
		file.close();
	}

}
