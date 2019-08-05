import java.util.Random;

public class search {
	// generate box method returns an array of boxes, of the size specified
	public static Box[] generateBoxes(int noOfBoxes) {
		Random rand = new Random();
		// an array is created of the size that is specified
		Box[] a = new Box[noOfBoxes];
		// iterates through the array, populating it with boxes of a random size
		for (int i = 0; i <= noOfBoxes - 1; i++) {
			// the values for the box height and width are between 1 and 500
			a[i] = new Box(rand.nextInt(500) + 1, rand.nextInt(500) + 1);
		}
		// the array is returned
		return a;
	}

	public static int NFTLP(Box[] a, Truck t) {
		// truckcCount starts at 1, since 1 box will require 1 truck
		int truckCount = 1;
		// tw and th are running totals of the height and width remaining in the
		// current truck
		int tw = t.width;
		int th = t.height;
		int boxesontruck = 0;
		// iterates through the number of boxes
		for (int i = 0; i < a.length; i++) {
			// checks to see if the box can fit into the truck
			if (tw - a[i].width > 0 && th - a[i].height > 0 && boxesontruck < t.capacity) {
				// the height and width of the box are subtracted from the
				// running total
				tw -= a[i].width;
				th -= a[i].height;
				boxesontruck++;
				continue;
			} else {
				// if the box can't fit into the current truck, the amount of
				// trucks is increased by 1 and all the variables are reset to
				// their max values
				truckCount++;
				tw = t.width;
				th = t.height;
				boxesontruck = 0;
				// the iterator goes back 1 in the array- this box still needs
				// to be placed into a truck, which it shall now be able to do
				i--;
			}
		}
		// the amount of trucks required is returned
		return truckCount;
	}

	public static int FFTLP(Box[] a, Truck t) {
		// An array of trucks is created, as many trucks as there are boxes.
		// This would be the worst case scenario, where each truck could only
		// hold 1 box
		Truck[] trucks = new Truck[a.length];
		int truckCount = 0;
		// The array is populated with trucks of the values specified in the
		// truck that is passed to this method
		for (int i = 0; i <= trucks.length - 1; i++) {
			trucks[i] = new Truck(t.height, t.width, t.capacity);
		}
		// for loop iterates through the number of boxes
		for (int n = 0; n < a.length; n++) {
			// for loop iterates through the number of trucks
			for (int i = 0; i < trucks.length; i++) {
				// checks to see if the box can fit in any truck in the array
				if (trucks[i].width - a[n].width >= 0 && trucks[i].height - a[n].height >= 0
						&& trucks[i].boxesOnTruck < t.capacity) {
					// the first truck the box can fit in has its height, width
					// and capacity changed accordingly
					trucks[i].boxesOnTruck++;
					trucks[i].height -= a[n].height;
					trucks[i].width -= a[n].width;
					// if an item is placed in a truck then the loop is broken
					// out of, otherwise the item would be placed in every truck
					// it was able to be
					break;

				}

			}
		}
		// for loop iterates through the number of trucks
		for (int i = 0; i < trucks.length; i++) {
			// if statement checks all trucks in the array that have items in
			// them, and increments a counter
			if (trucks[i].boxesOnTruck != 0) {
				truckCount++;
			}
		}
		// the amount of trucks needed is returned
		return truckCount;

	}

	public static void main(String args[]) {
		// this number is the amount of boxes to generate
		int n = 1000;
		// a new truck is made to be passed into the search algorithms
		Truck t = new Truck(5000, 5000, 50);
		// the array of boxes is created using the generateBoxes method
		Box[] a = generateBoxes(n);
		long totaltime = 0;
		int totalcount = 0;
		// the test is performed 1000 times to generate an average
		for (int i = 0; i < 1000; i++) {
			long start = System.nanoTime();
			totalcount += NFTLP(a, t);
			long end = System.nanoTime();
			// the total time is found by subtracting the start time from the
			// end time
			totaltime += (end - start);
		}
		// the data is output to the console
		System.out.println(n + " Boxes");
		System.out.println("NFTLP");
		System.out.println("Average trucks " + (totalcount / 1000) + " Average Time " + (totaltime / 1000));
		long totaltimeff = 0;
		int totalcountff = 0;
		// the process is repeated for first fit
		for (int i = 0; i < 1000; i++) {
			long startff = System.nanoTime();
			totalcountff += FFTLP(a, t);
			long endff = System.nanoTime();
			totaltimeff += (endff - startff);
		}
		System.out.println("FFTLP");
		System.out.println("Average trucks " + (totalcountff / 1000) + " Average Time " + (totaltimeff / 1000));

	}
}