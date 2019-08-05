//Name:Michael Rumley
//Last Modified: 14/12/15
//Purpose: Store essential information about beds
public class Bed {
	private int bedtype;
	int totalbeds = 0;
	//get/set methods for bedtype
	public void setBedtype(int x) {
		// ensures only a valid selection is made for bedtype
		if (x >= 3 || x <= 0) {
			throw new IllegalArgumentException("Bed must be of type 1 or 2");
		}
		totalbeds = totalbeds + x;

	}

	public int getBedtype() {
		return totalbeds;

	}

}
