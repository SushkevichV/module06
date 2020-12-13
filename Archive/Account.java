package by.tce.jonline.archive;

// авторизует клиента. Создает объект с правами пользователя или администратора 

public class Account {
	public User getAccess(String login, String pwd){
		if(login.equals("admin")) { // доступ с правами администратора
			if(pwd.equals("111")) {
				return new Admin();
			}
		}
		if(login.equals("user")) {	// доступ с правами пользователя
			if(pwd.equals("111")) {
				return new User();
			}
		}
		return null;
		
	}

}
