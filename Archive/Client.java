package by.tce.jonline.archive;

import java.io.*;
import java.net.Socket;
import java.util.List;

// запрашивает архив с сервера и возвращает его
// запускается из Archive

public class Client {

    private static Socket clientSocket; //клиент сокет
    private static ObjectInputStream dataIn; 
    private static ObjectOutputStream dataOut;

    public static List<Student> load() {
    	List<Student> archive = null;
        try {
            try {
            	clientSocket = new Socket("localhost", 4004);
                dataIn = new ObjectInputStream(clientSocket.getInputStream());

                try {
                	Object obj = dataIn.readObject();
                	archive = (List<Student>)obj;
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
                
            } finally { 
                clientSocket.close(); // в любом случае необходимо закрыть сокет и потоки
                dataIn.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return archive;
    }
    
    public static boolean save(List<Student> archive) {
        try {
            try {
            	clientSocket = new Socket("localhost", 4004);
                
                dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
                dataOut.writeObject(archive);
                dataOut.flush();

            } finally { 
                System.out.println("Клиент закрыт...");
                clientSocket.close(); // в любом случае необходимо закрыть сокет и потоки
                dataOut.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return true;
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    public static void main(String[] args) {
        try {
            try {
            	String login = "admin"; // имя пользователя
                String pwd = "111"; // пароль
                clientSocket = new Socket("localhost", 4004); // адрес - локальный хост, порт - 4004, такой же как у сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // читать соообщения с сервера
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); // писать туда же
                out.write(login+"\n"+pwd+"\n"); // отправляем логин и пароль на сервер
                out.flush();
                //String serverWord = in.readLine(); // ждём, что скажет сервер
                //System.out.println(serverWord); // получив - выводим на экран
               
                dataIn = new ObjectInputStream(clientSocket.getInputStream());
                dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
                try {
					obj = dataIn.readObject();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
                String type = obj.getClass().toString();
                if(type.contains("Student")) {
                	student = (Student)obj;
                	view.view(student);
                	student.setFaculty("Исторический факультет");
                	dataOut.writeObject(student);
                    dataOut.flush();
                }
                
                try {
					student = (Student)dataIn.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
                view.view(student);
                
                out.write("Иванов\n"); // отправляем запрос на сервер
                out.flush();
                serverWord = in.readLine();
                System.out.println(serverWord);
                serverWord = in.readLine();
                System.out.println(serverWord);
            } finally { 
                System.out.println("Клиент закрыт...");
                clientSocket.close(); // в любом случае необходимо закрыть сокет и потоки
                in.close();
                out.close();
                dataIn.close();
                dataOut.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
*/
