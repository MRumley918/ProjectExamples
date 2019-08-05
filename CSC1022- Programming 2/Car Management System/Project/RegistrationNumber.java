//Name: Michael Rumley
//Last Modified: 5/5/2016
//Purpose: To generate a car registration 

import java.util.Random;

public class RegistrationNumber {
	// ints n and r are both created from the random object 'rand'
	Random rand= new Random();
	int n= rand.nextInt(9999);
	int r= rand.nextInt(25);
	// an array of strings- the 26 letters of the alphabet, of which a random letter is selected
	String[] letters= {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	public static String Reg_Number;
	// constructs a registration number, comprised of one letter and 4 numbers
	public RegistrationNumber(){
		Reg_Number= letters[r]+n;
	}
	// Overwritten toString method that returns type string rather than type RegistrationNumber
	public String toString(){
		return Reg_Number;
	}
	
	
	
}

