//Name: Michael Rumley
//Last Modified: 5/5/2016
//Purpose: Main class, contains all the major methods


import java.util.*;

public class main {
	// Calendar.getInstance provides the exact time when the program is run, allowing for
	// an accurate calculation of drivers' age
	Calendar current = Calendar.getInstance();
	
	public int NUMBER_OF_SMALL_CARS = 20;
	public int NUMBER_OF_LARGE_CARS = 10;
	//total cars is the number of large and small cars
	int totalcars = NUMBER_OF_LARGE_CARS + NUMBER_OF_SMALL_CARS;
	// a collection of cars
	Car[] cars = new Car[totalcars];
	// returns the number of cars available of a specified type
	public int availableCars(carType c) {
		if (c == carType.Small) {
			return NUMBER_OF_SMALL_CARS;
		} else if (c == carType.Big) {
			return NUMBER_OF_LARGE_CARS;
		} else {
			throw new IllegalArgumentException("Car type unrecognised");
		}
	}
	// the user specifies a license and car type. then the method checks to make sure the license
	// isn't tied to another car. Then a check is performed to make sure the license is full. Then
	// the license's date of birth is compared to the current year to make sure the driver is old enough
	// to rent the selected car. Once all these checks have been passed, the method searches through the array
	// for the next free car and issues it. Finally the number of cars is decreased so another driver cant rent it.
	public void issueCar(DrivingLicense d, carType c) {
		if (findRentedCar(d) != -1) {
			System.out.println("Car already rented");

		} else if (d.isFull == true) {
			if ((c == carType.Small) && current.get(Calendar.YEAR) - d.driverDateOfBirth.get(Calendar.YEAR) >= 25) {
				int i = findFreeCar();
				cars[i] = new Car(d, c);
				NUMBER_OF_SMALL_CARS = NUMBER_OF_SMALL_CARS - 1;
			} else if ((c == carType.Big)
					&& current.get(Calendar.YEAR) - d.driverDateOfBirth.get(Calendar.YEAR) >= 21) {
				int i = findFreeCar();
				cars[i] = new Car(d, c);
				NUMBER_OF_LARGE_CARS = NUMBER_OF_LARGE_CARS - 1;
			}
		} else {
			System.out.println("Cannot rent car");
		}

	}
	// a driving license is supplied, then the array of cars is searched through. When a car is found that
	// has a license number tied to it that matches the supplied license number it is returned
	public Car getCar(DrivingLicense d) {
		int i;
		for (i = 0; i <= totalcars; i++) {
			if (cars[i].getLicenseNo().equals(d.getLicenseNumber())) {
				return cars[i];

			}

		}
		return null;

	}
	// When the car is returned, the array is searched through to find the car rented by the specified
	// driving license. The car at that position is then nullified, before being re-added to the total 
	// number of cars available.
	public void returnCar(DrivingLicense d) {
		int i = findRentedCar(d);
		cars[i] = null;
		if (cars[i].fuelTankFull()==false){
			System.out.println("Customer owes fuel");
		}
		if (cars[i].type==carType.Big){
			NUMBER_OF_LARGE_CARS = NUMBER_OF_LARGE_CARS + 1;
		}
		else{
			NUMBER_OF_SMALL_CARS = NUMBER_OF_SMALL_CARS + 1;
		}
	}
	// this method searches through the array of cars and returns all entries which are not null 
	public Car getRentedCars(){
		int i;
		for (i=0;i<= totalcars; i++){
			if (cars[i]!= null){
				return cars[i];
			}
		}
		return null;
	}
	// this private method returns the first free position in the array- for each value it checks
	// if the value is null, and if it is returns that position
	private int findFreeCar() {
		int i;
		for (i = 0; i <= totalcars; i++) {
			if (cars[i] == null) {
				break;
			}
		}
		return i;
	}
	// this method checks if a driving license is renting a car, and if so what position in the 
	// array it is
	private int findRentedCar(DrivingLicense d) {
		int i;
		for (i = 0; i <= totalcars;) {
			if (cars[i] != null && cars[i].getLicenseNo().equals(d.getLicenseNumber())) {
				return i;
			}
			else {
				break;
			}
		}
		return -1;
	}

}
