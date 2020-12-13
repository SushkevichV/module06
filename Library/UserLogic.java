package by.tce.jonline.library;

import java.util.Properties;
import java.util.Set;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// логика пользователя

public class UserLogic extends Logic {
	
	// добавление книги
	@Override
	public String addBook(String title, String author, String description, boolean eBook, boolean pBook, String email) {
		//notification(title, author, description, eBook, pBook, email); // снять комментарий для рассылки уведомлений
		return "\nАдминистратору направлено предложение добавить книгу \""+title+"\" в каталог\n";
	}
	
	// удаление книги
	@Override
	public String removeBook(String title, String author) {
		return "\nАдминистратору направлено предложение удалить книгу \""+title+"\" из каталога\n";
	}
	
	// рассылка уведомлений на E-mail
	private boolean notification(String title, String author, String description, boolean eBook, boolean pBook, String email) {
		String to = "library@mail.net";
		String from = email;
		String host = "127.0.0.1";
		Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
		
		try {
            MimeMessage message = new MimeMessage(session); //email message
            message.setFrom(new InternetAddress(from)); //setting header fields

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Новая книга в каталоге"); //subject line

            message.setText("В каталог добавлена новая книга: "+author+ " - \""+title+"\"");

            Transport.send(message); //Send message
            
        } catch (MessagingException mex) { 
        	   mex.printStackTrace();
        	   return false;
        }
		
		return true;
	}
}
