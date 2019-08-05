//Name:Michael Rumley
//Last Modified: 14/12/15
//Purpose: To store important infomation about a hotel
import java.util.*;

public class Hotel {
	private String name;// hotels name
	private List<Room> rooms = new ArrayList<Room>();//creating an array list of rooms
	int check; //integer for getting vacencies 
	//get/set methods for name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//get/set methods for rooms
	public int getRooms() {
		if (rooms.size() <= 0) {
			//there must be 1 or more rooms in a hotel, or else an error is thrown
			throw new IllegalArgumentException("There must be at least one room in the hotel");
		} else {
			//the size of the array is how many rooms are in the hotel
			return rooms.size();
		}
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	// gotvacencies class reads the integer inputted as either free or not 
	// and adds to the check value if the room is free
	public void gotVacencies(int i) {

		if (i == 1) /*room is not occupied*/{
			check = check + 1;
		} else if (i == 0)/*room is occupied*/ {
			check = check + 0;
		} else {
			// error thrown if neither 
			throw new IllegalArgumentException("Invalid answer");
		}

	}
}
