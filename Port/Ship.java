package by.tce.jonline.port;

// Корабль

public class Ship {
	private String name;	// Имя
	private int capacity;	// Грузоподъемность
	private int volume;		// Загруженность
	
	public Ship(String name, int capacity, int volume) {
		this.name = name;
		this.capacity = capacity;
		this.volume = volume;
	}
	
	public void load(int volume) {
		this.volume+=volume;
	}
	
	public void unload(int volume) {
		this.volume-=volume;
	}
	
	public int getVolume() {
		return volume;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + volume;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ship other = (Ship) obj;
		if (capacity != other.capacity)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (volume != other.volume)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ship [name=" + name + ", capacity=" + capacity + ", volume=" + volume + "]";
	}

}
