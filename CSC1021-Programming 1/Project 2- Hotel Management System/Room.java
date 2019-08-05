//name: Michael Rumley
//Last modified: 14/12/15
//Purpose: to store information pertaining to a room

import java.util.*;

public class Room {
	//each room contains a list of beds
	private List<Bed> beds = new ArrayList<Bed>();
	//check to ensure each room contains one or more beds
	public void checkBeds(int b) {
		if (b <= 0) {
			throw new IllegalArgumentException("Every room must contain at least one bed");
		}

	}
	//get/set methods for beds
	public int getAllBeds() {
		return beds.size();
		//returns the maximum number of beds in the hotel
	}
	
	public void setBeds(List<Bed> beds) {
		this.beds = beds;
	}

}
