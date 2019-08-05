//Name:Michael Rumley
//Last Modified: 14/12/15
//Purpose: to neatly display all information in a report

import java.util.*;

public class HotelReport {
	public void Report(Hotel h, List<Integer> l, Room r, int x) {
		//prints the name of the hotel and its number of rooms
		System.out.println("Hotel " + h.getName() + " has " + h.getRooms() + " rooms");
		if (h.check >= 1) {
			//if check is positive, then at least one room is free and the hotel has vacencies
			System.out.println(h.getName() + " has vacencies");
		} else {
			// otherwise it doesnt
			System.out.println(h.getName() + " has no vacencies");
		}
		for (int r2 = 1; r2 <= h.getRooms(); r2++) {
			//iterating through the number of rooms in the hotel and getting the number of rooms 
			//stored in array l
			System.out.println("Room " + r2 + " has " + l.get(r2 - 1) + " beds ");
		}
		//Max beds returns the size of the beds array
		System.out.println("Max Beds " + r.getAllBeds());
		//x is a cumulative total of all the bed sizes(1 for single, 2 for double)
		System.out.println("Max occupancy " + x);
	}
}
