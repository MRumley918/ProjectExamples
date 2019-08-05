// Name: Michael Rumley
// Last Modified: 5/5/2016
// Purpose: To contain all methods associated with the Driving License


import java.util.*;

public class DrivingLicense {
	 	 public String driverName;
	     public Calendar driverDateOfBirth;
	     public  Calendar dateOfIssue;
	     public LicenseNumber number;
	     public boolean isFull;
	    
	    // method sets input values to class specific variables
	    public DrivingLicense(String driver, Calendar dateOfBirth, Calendar Issue, boolean Full){
	    	driverName= driver;
	    	driverDateOfBirth=dateOfBirth;
	    	dateOfIssue= Issue;
	    	isFull=Full;
	    }
	    //returns name
	    public String getName(){
	    	return driverName;
	    }
	    //returns date of birth
	    public Date DOB(){
	    	
	    	return driverDateOfBirth.getTime();
	    }
	    // returns date of issue
	    public Date DOI(){
	    	return dateOfIssue.getTime();
	    }
	    // returns whether the license is full
	    public boolean Full(){
	    	return isFull;
	    	
	    }
	    // creates a license number
	    public void createLicenseNumber(){
	    	 number=  new LicenseNumber(driverName,driverDateOfBirth);
	    	 
	    }
	    // returns the license number
	    public String getLicenseNumber(){
	    	return number.toString();
	    }
	    
	  
}
