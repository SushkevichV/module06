package by.tce.jonline.archive;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

// Сервер. Запускается первым.
// Передает и принимает архив

public class Server {

    private static Socket clientSocket; // клиент сокет
    private static ServerSocket server; // серверсокет
    private static ObjectInputStream dataIn;
    private static ObjectOutputStream dataOut;
    private static Object obj = null;

    public static void main(String[] args) {
  
            try  {
                server = new ServerSocket(4004); // серверсокет прослушивает порт 4004
                System.out.println("Сервер запущен!");

                clientSocket = server.accept(); // сервер ожидает подключения

                try { 
                    dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
                    dataOut.writeObject(FileIO.load()); //запись в поток объекта из файла
                    dataOut.flush(); // выталкиваем из буфера
                    System.out.println("Передача архива с сервера завершена");
                    
                    clientSocket = server.accept();
                    dataIn = new ObjectInputStream(clientSocket.getInputStream());
                    try {
    					obj = dataIn.readObject();	 // получение архива на сервер
    				} catch (ClassNotFoundException e1) {
    					e1.printStackTrace();
    				}
                    System.out.println("Загрузка архива на сервер завершена");
                    
                    List<Student> archive = (List<Student>)obj;
                    FileIO.create(archive); // запись в файл
                    
                } finally { 
                    clientSocket.close(); // в любом случае сокет будет закрыт
                    dataIn.close();
                    dataOut.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                try {
					server.close();
					System.out.println("Сервер закрыт!");
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
    }
}    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    public static void main(String[] args) {
    	Account account = new Account();
  
            try  {
                server = new ServerSocket(4004); // серверсокет прослушивает порт 4004
                System.out.println("Сервер запущен!");

                clientSocket = server.accept(); // сервер ожидает подключения

                try { 
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));// теперь мы можем принимать сообщения
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));// и отправлять
                    String login = in.readLine(); // ждём имя пользователя
                    String pwd = in.readLine(); // и пароль
                    System.out.println("Пользователь "+login+" авторизован");
                    User user = account.getAccess(login, pwd);
                   
                    //out.write("Пользователь "+login+" авторизован\n");
                    //out.flush(); // выталкиваем все из буфера
                    
                    dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
                    
                    Student student = user.getStudent(201962427);
                    dataOut.writeObject(student);
                    dataOut.flush();
                    
                    Object obj = null;
                    dataIn = new ObjectInputStream(clientSocket.getInputStream());
                    try {
    					obj = dataIn.readObject();
    				} catch (ClassNotFoundException e1) {
    					e1.printStackTrace();
    				}
                    String type = obj.getClass().toString();
                    if(type.contains("Student")) {
                    	student = (Student)obj;
                    	user.replaceStudent(student);
                    }
                    View view = new View();
                    view.view(user.getStudent("Николаев"));
                    
                    
                    String request = in.readLine(); // ждём запрос пользователя
                    System.out.println(request);
                    out.write("Получен запрос на поиск дела.\nДело найдено\n");
                    out.flush();
                    
                } finally { 
                    clientSocket.close(); // в любом случае сокет будет закрыт
                    in.close(); // закрыть потоки 
                    out.close();
                    dataIn.close();
                    dataOut.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                try {
					server.close();
					System.out.println("Сервер закрыт!");
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
       
    }
}*/