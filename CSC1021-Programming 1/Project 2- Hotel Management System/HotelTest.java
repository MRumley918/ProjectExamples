//Name:Michael Rumley
//Last Modified: 15/12/15
//Purpose: Print details of a hotel that have been hard coded in

import java.util.*;

public class HotelTest {

	public static void main(String[] args) {
		//initialise the various objects and constants required
		Hotel h = new Hotel();
		Room r = new Room();
		Bed b = new Bed();
		int totalbeds = 0;
		List<Room> rooms = new ArrayList<Room>();
		List<Bed> beds = new ArrayList<Bed>();
		List<Integer> bedsperroom = new ArrayList<Integer>();
		HotelReport report = new HotelReport();
		//these are the values that can change, here they are hard coded in
		String hotelname="test";
		int noofrooms=5;
		
		h.setName(hotelname);
		//iterates through the number of rooms
		for (int i = 1; i <= noofrooms; i++) {
			// here if the room is vacent or not has to be set for all rooms simultaneously,
			//in reality the user would be able to input different values for each room
			int roomfree=0;
			h.gotVacencies(roomfree);
			// a new room object is created and added to the array list of rooms
			Room e = new Room();
			rooms.add(e);
			//again the number of beds in each room has to be the same for each room, 
			//but in the proper system the user can input different numbers
			int bedsinroom=5;
			//checks to make sure the beds in room is a vaid number
			r.checkBeds(bedsinroom);
			//adds the beds in one room to an array list
			bedsperroom.add(bedsinroom);
			//iterates through the number of beds
			for (int i2 = 1; i2 <= bedsinroom; i2++) {
				//a new bed object is created and added to the list of beds
				Bed c = new Bed();
				beds.add(c);
				//a bed type is set. as before, in the real system a different type 
				//can be set for each bed
				int x = 1;
				c.setBedtype(x);
				//a running total of the max occupency is taken
				totalbeds = totalbeds + x;
			}
		}
		//the two arrays are set
		h.setRooms(rooms);
		r.setBeds(beds);
		//all values are fed into the report class, which outputs a formatted report
		report.Report(h, bedsperroom, r, totalbeds);	
				
		

}}
