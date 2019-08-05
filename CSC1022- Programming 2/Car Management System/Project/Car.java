// Name: Michael Rumley
// Last Modified: 5/5/2016
// Purpose: To contain all the methods associated with a car
public class Car {
	LicenseNumber number;
	public carType type;
	RegistrationNumber rnumber;
	// the capacity of the 2 car types are set as final so they can't be changed
	public final static int LITRES_FOR_SMALL_CAR = 45;
	public final static int LITRES_FOR_LARGE_CAR = 65;
	// these values can changed. a car is assumed to have its maximum fuel capacity at the start
	public int litres_in_small_car = LITRES_FOR_SMALL_CAR;
	public int litres_in_large_car = LITRES_FOR_LARGE_CAR;
	
	// when a car is created, a registration number is created for it
	public Car(DrivingLicense d, carType t) {
		number = d.number;
		type = t;
		rnumber = new RegistrationNumber();
	}
	// overwritten the toString method to provide a user friendly summary of their car
	public String toString() {
		return number + " has a " + type + " car, registration " + rnumber;
	}
	// returns the license number
	public String getLicenseNo() {
		return number.toString();
	}
	// returns the registration number
	public String getRegNo() {
		return rnumber.toString();
	}
	
	// refuel checks the car type, adds the specified amount of fuel then caps it at 
	// the maximum value for that car
	public void refuel(int i) {
		if (type == carType.Big) {
			litres_in_large_car = litres_in_large_car + i;
			if (litres_in_large_car > LITRES_FOR_LARGE_CAR) {
				litres_in_large_car = LITRES_FOR_LARGE_CAR;
			}
		} else {
			litres_in_small_car = litres_in_small_car + i;
			if (litres_in_small_car > LITRES_FOR_SMALL_CAR) {
				litres_in_small_car = LITRES_FOR_SMALL_CAR;
			}
		}
	}
	// the drive method checks the type of car, then checks to ensure the tank has fuel in, then
	// calculates the amount of fuel used in the trip
	public int drive(int i) {
		if (type == carType.Big) {
			if (litres_in_large_car <= 0) {
				System.out.println("Car has no fuel");
			}
			int result;
			if (i <= 50) {
				result = i / 15;
			} else {
				result = 50 / 15 + (i - 50) / 20;
			}
			litres_in_large_car = litres_in_large_car - result;
			return result;
		} else {

			if (litres_in_small_car <= 0) {
				System.out.println("Car has no fuel");
			}
			int result;
			result = i / 25;
			litres_in_small_car = litres_in_small_car - result;
			return result;
		}
	}
	// get fuel returns the amount of fuel left
	public int getFuel() {
		if (type == carType.Big) {
			return litres_in_large_car;
		} else {
			return litres_in_small_car;
		}

	}
	// this checks to see if the fuel in the car is equal to its capacity
	public boolean fuelTankFull(){
		if(type==carType.Big){
			if(litres_in_large_car==LITRES_FOR_LARGE_CAR){
				return true;
			}
		}
		if(litres_in_small_car==LITRES_FOR_SMALL_CAR){
			return true;
		}
		else return false;
	}
}
