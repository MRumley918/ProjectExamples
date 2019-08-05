
public class Box {
	int width;
	int height;
	// when a box is created the height and width are set
	public Box(int height, int width) {
		this.width = width;
		this.height = height;
	}
	// overwritten toString method returns the box's dimensions 
	public String toString() {
		return (this.width + "|" + this.height);
	}

}
