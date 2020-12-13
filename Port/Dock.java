package by.tce.jonline.port;

import java.util.Random;

class Dock implements Runnable{
	private static int portCapacity = 2000;			// вместимость порта
	private static volatile int portVolume = 1000;	// загруженность порта
	private Random r = new Random();
	private ShipList ships;
	
	public Dock(ShipList ships) {
		this.ships = ships;
	}
	
	@Override
	public void run() {
		while(ships.getShipList().size()>0) {
			Ship ship = getShip();	// запускаем корабли в порт по очереди
			
			ship = toUnLoad(ship); // разгрузка
			ship = toLoad(ship);// погрузка
		}
	}

	private synchronized Ship getShip() {
		return ships.getShip();
	}

	// разгрузка
	private Ship toUnLoad(Ship ship) {
		int freeSpace = portCapacity-getPortVolume();
		// при выводе инфы о корабле для наглядности в скобках указывается количество контейнеров на борту и грузоподъемность
		View.report("Корабль "+ship.getName()+" ("+ship.getVolume()+"/"+ ship.getCapacity()+") пришвартовался к причалу N"+Thread.currentThread().getName()+" для разгрузки "+ship.getVolume()+" контейнера(ов)");
		// полная разгрузка в пределах свободного места в порту
		if(freeSpace>ship.getVolume()) {
			freeSpace = ship.getVolume();
		}
		ship.unload(freeSpace);
		setPortVolume(getPortVolume()+freeSpace);
		View.report("Выгружено "+freeSpace+" контейнера(ов). Разгрузка корабля "+ ship.getName() +" завершена.");
		return ship;
	}
	
	// погрузка
	private Ship toLoad(Ship ship) {
		View.report("Корабль "+ship.getName()+" ("+ship.getVolume()+"/"+ ship.getCapacity()+") пришвартовался к причалу N"+Thread.currentThread().getName()+" для погрузки");
		int loading = 0;
		if(getPortVolume()<ship.getCapacity()) {
			loading = r.nextInt(getPortVolume())+1; // загрузка в пределах количества контейнеров в порту
		} else {
			loading = r.nextInt(ship.getCapacity()-ship.getVolume())+1; // загрузка в пределах свободного места на корабле
		}
		ship.load(loading); // погрузка
		setPortVolume(getPortVolume()-loading);
		View.report("Погрузка "+loading+" контейнера(ов) на корабль "+ship.getName()+" ("+ship.getVolume()+"/"+ ship.getCapacity()+") завершена.");
		return ship;
	}
	
	private synchronized int getPortVolume() {
		return portVolume;
	}
	
	private synchronized void setPortVolume(int volume) {
		portVolume = volume;
	}

}
