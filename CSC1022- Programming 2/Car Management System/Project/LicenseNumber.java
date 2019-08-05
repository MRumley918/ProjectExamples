// Name: Michael Rumley
// Last Modified: 5/5/2016
// Purpose: To create a driving license number

import java.util.Calendar;

import java.util.Random;

public class LicenseNumber {
	public static String firstName;
	public static String lastName;
	public static String number;
	// java.random creates a random number
	Random rn = new Random();
	int n= rn.nextInt(10);
	// this class generates a license number when called
	public LicenseNumber(String name,Calendar DOB){
		concatenate(name);
		number  = (firstName+lastName+"-"+DOB.get(Calendar.YEAR)+"-"+n);
		
		
	}
	// this method separates the first and last name(as it is entered into the driving license
	// as a single string) and shortens it to the initials
	private void concatenate(String name){
		String delims ="[ ]";
		String [] names= name.split(delims);
 		firstName= names[0].substring(0, 1);
 		lastName= names[1].substring(0,1);
 		
		
	}
	// overwritten toString so it returns type string and not type LicenseNumber
	public String toString(){
		
		return number;
	}
	
}
