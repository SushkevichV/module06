package by.tce.jonline.library;

import java.util.HashSet;
import java.util.Set;

// аутентификация

public class Access {
	private FileIO file = new FileIO();
	private Set <User> users = file.getUsers();
	private String currentEmail;
	
	public Access() {
		
	}
	
	// проверка данных пользователя и предоставление доступа
	public Logic getAccess(String login, String pwd) {
		for(User user : users) {
			if(login.equals(user.getLogin()) && pwd.equals(user.getPwd())) {
				currentEmail = user.getEmail();
				if(user.isAdmin()) {
					return new AdminLogic();
				} else {
				return new UserLogic();
				}
			}
		}
		return null;
	}
	
	// получение e-mail авторизованного пользователя
	public String getEmail() {
		return currentEmail;
	}

	// получение списка e-mail
	public Set<String> getEmailList() {
		Set<String> emails = new HashSet<>();
		for(User user : users) {
			emails.add(user.getEmail());
		}
		return emails;
	}
	
}
