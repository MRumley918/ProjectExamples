
public class Truck {
	int height;
	int width;
	int capacity;
	int boxesOnTruck;

	// when a truck is created the values are set and the items on truck
	// variable is set to zero
	public Truck(int height, int width, int capacity) {
		this.height = height;
		this.width = width;
		this.capacity = capacity;
		boxesOnTruck = 0;

	}

	// overwritten toString method prints out the truck's values
	public String toString() {
		return ("Truck has height " + height + " width " + width + " and capacity " + capacity + "\n " + boxesOnTruck
				+ " used");
	}

}
