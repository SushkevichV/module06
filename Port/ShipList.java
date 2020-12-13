package by.tce.jonline.port;
import java.util.ArrayDeque;
import java.util.Random;

// Список кораблей

public class ShipList {
	int count = 8; // количество кораблей в списке
	private ArrayDeque<Ship> ships = new ArrayDeque<Ship>();
	Random r = new Random();
	
	{	// корабли инициализируются автоматически, грузоподъемность и 
		// загруженность задаются случайно
		for (int i=0; i<count; i++) {
			String name = "Ship"+(i+1);
			int capacity = r.nextInt(1000)+1;
			int volume = (int)(Math.random()*capacity)+1;
			ships.add(new Ship(name, capacity, volume));
		}
	}

	public ShipList() {
		
	}
	
	public ArrayDeque<Ship> getShipList() {
		return ships;
	}
	
	public Ship getShip() {
		return ships.poll();
	}

	@Override
	public String toString() {
		return "ShipList [ships=" + ships + "]";
	}

}
