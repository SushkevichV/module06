package by.tce.jonline.port;

public class Main {

	public static void main(String[] args) {
		ShipList ships = new ShipList();
		
		Dock dock = new Dock(ships);
		Thread thread1 = new Thread(dock);
		thread1.setName("1");
		Thread thread2 = new Thread(dock);
		thread2.setName("2");
		Thread thread3 = new Thread(dock);
		thread3.setName("3");
		Thread thread4 = new Thread(dock);
		thread4.setName("4");
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

	}

}
