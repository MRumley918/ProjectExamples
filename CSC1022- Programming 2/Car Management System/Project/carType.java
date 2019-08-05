// Name:Michael Rumley
// Last Modified: 5/5/2016
// Purpose: To classify the different types of car
public class carType  {
	
	// this class contains the two possible car types and toString methods for them
	public static carType Big;
	public static carType Small;
	
	public String toString(carType t){
		if (t== carType.Big){
			return "Big";
		}
		else if (t==carType.Small){
			return "Small";
		}
		return null;
	}
	
	
	
	

}
