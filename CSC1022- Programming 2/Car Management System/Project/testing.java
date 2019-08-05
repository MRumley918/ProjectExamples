import java.util.*;
import java.text.*;
public class testing {
	

		
	
	public static void main(String[] args) throws ParseException {
		Calendar date1= Calendar.getInstance();
		date1.set(1960, 3, 24);
		Calendar date2=Calendar.getInstance();
		date2.set(2006, 0, 15);
		DrivingLicense test= new DrivingLicense("Bill Gates",date1,date2,true);
		
		main Main= new main();
		
		System.out.println(test.getName());
		System.out.println(test.DOB());
		System.out.println(test.Full());
		test.createLicenseNumber();
		System.out.println(test.getLicenseNumber());
		
		Main.issueCar(test, carType.Big);
		
		System.out.println(Main.getCar(test));
		System.out.println(Main.getRentedCars());
		
		
		
	}

}
