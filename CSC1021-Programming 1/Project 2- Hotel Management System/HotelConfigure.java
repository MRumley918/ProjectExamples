//Name:Michael Rumley
//Last Modified: 15/12/15
//Purpose: To create a hotel from user inputted values
import java.util.*;

public class HotelConfigure {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//initialising classes and objects
		//(the configure class needs a keyboard object for user input)
		Scanner keyboard = new Scanner(System.in);
		Hotel h = new Hotel();
		Room r = new Room();
		Bed b = new Bed();
		int totalbeds = 0;
		List<Room> rooms = new ArrayList<Room>();
		List<Bed> beds = new ArrayList<Bed>();
		List<Integer> bedsperroom = new ArrayList<Integer>();
		HotelReport report = new HotelReport();
		// the user needs to input the name of the hotel
		System.out.println("Enter the name of your hotel");
		//keyboard.next defaults to returning a string 
		String hotelname = keyboard.next();
		h.setName(hotelname);
		//the user inputs how many rooms are in the hotel
		System.out.println("How many rooms are in " + hotelname);
		int noofrooms = keyboard.nextInt();
		// for loop iterates through the number of rooms
		for (int i = 1; i <= noofrooms; i++) {
			//for each room the user is asked if it is vacent 
			System.out.println("Is room " + i + " free? (Enter 1 if yes, 0 otherwise)");
			int roomfree = keyboard.nextInt();
			//gotvacencies ensures that a valid input is given her
			h.gotVacencies(roomfree);
			//a new room object is created and added to an array of rooms
			Room e = new Room();
			rooms.add(e);
			// the amount of beds in each room is inputted, checked, and then added to an array
			System.out.println("How many beds are in room " + i);
			int bedsinroom = keyboard.nextInt();
			r.checkBeds(bedsinroom);
			bedsperroom.add(bedsinroom);
			//for loop iterates through the number of beds in a room
			for (int i2 = 1; i2 <= bedsinroom; i2++) {
				//a new bed object is created and added to an array of beds
				Bed c = new Bed();
				beds.add(c);
				// the type of each bed is inputted and checked
				System.out.println("What type is bed " + i2 + " (enter 1 for single bed or 2 for double bed)");
				int x = keyboard.nextInt();
				c.setBedtype(x);
				// the type is then added to a running total to find the max occupency 
				totalbeds = totalbeds + x;

			}
		}
		// the two arrays are passed into thier respective classes
		h.setRooms(rooms);
		r.setBeds(beds);
		//everything is passed into a report class in order to be outputted neatly 
		report.Report(h, bedsperroom, r, totalbeds);

	}

}
