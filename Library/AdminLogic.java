package by.tce.jonline.library;

import java.io.IOException;
import java.util.Set;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// логика администратора

public class AdminLogic extends Logic {
	private BookList library = super.getLibrary();
	private FileIO file = new FileIO();
	
	// добавление книги в каталог
	@Override
	public String addBook(String title, String author, String description, boolean eBook, boolean pBook, String email) {
		if(library.addBook(new Book(title, author, description, eBook, pBook))) {
			try {
				//file.appEnd(title, author, description, eBook, pBook);
				file.replace(super.getBooks());
			} catch (IOException e) {
				e.printStackTrace();
			}
			// notification(title, author, description, eBook, pBook, email); // снять комментарий для рассылки уведомлений
			return "\nКнига \""+title+"\" добавлена в каталог";
		} else {
			return "\nКнига уже есть в каталоге";
		}
	}
	
	// удаление книги из каталога
	@Override
	public String removeBook(String title, String author) {
		for(Book book : super.getBooks()) {
			if(book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
				super.getBooks().remove(book);
				try {
					file.replace(super.getBooks());
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "\nКнига \""+title+"\" удалена из каталога";
			}
		}
		return "\nКнига не найдена";
	}
	
	// рассылка уведомлений на E-mail
	private boolean notification(String title, String author, String description, boolean eBook, boolean pBook, String from) {
		String host = "127.0.0.1";
		Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        
		Access user = new Access();
		Set<String> emails = user.getEmailList();
		
		try {
            MimeMessage message = new MimeMessage(session); //email message
            message.setFrom(new InternetAddress(from)); //setting header fields

            for(String email : emails) {
            	message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            }

            message.setSubject("Новая книга в каталоге"); //subject line

            message.setText("В каталог добавлена новая книга: "+author+ " - \""+title+"\"\nОписание книги: "+description);

            Transport.send(message); //Send message
            
        } catch (MessagingException mex) { 
        	   mex.printStackTrace();
        	   return false;
        }
		
		return true;
	}

}
