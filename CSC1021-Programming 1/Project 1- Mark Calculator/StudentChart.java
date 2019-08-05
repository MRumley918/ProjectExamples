// Author:Michael Rumley
//Date Modified: 11/11/2015
public class StudentChart {

	public static void main(String[] args) {
		// no main method needed
	}

	// purpose: draw a bar chart from the resultant values
	public static void draw() {
		// xaxis
		Bar xaxis = new Bar();
		xaxis.makeVisible();
		xaxis.changeSize(200, 5);
		xaxis.moveVertical(200);
		xaxis.changeColour(Colour.BLACK);

		// yaxis
		Bar yaxis = new Bar();
		yaxis.makeVisible();
		yaxis.changeSize(5, 200);
		yaxis.changeColour(Colour.BLACK);

		// csc1021
		Bar csc1021 = new Bar();
		csc1021.makeVisible();
		csc1021.changeSize((200 / MarkCalculator.ReturnedMark.length) - 5, MarkCalculator.ReturnedMark[0] * 2);
		/*
		 * i have doubled all return marks in order to make them better show up
		 * on the canvas
		 */
		csc1021.moveHorizontal(5);
		csc1021.moveVertical(200 - (MarkCalculator.ReturnedMark[0] * 2));
		if (MarkCalculator.ReturnedMark[0] > 70) {
			csc1021.changeColour(Colour.MAGENTA);
		} else {
			if (MarkCalculator.ReturnedMark[0] > 40) {
				csc1021.changeColour(Colour.GREEN);
			} else {
				if (MarkCalculator.ReturnedMark[0] < 35) {
					csc1021.changeColour(Colour.RED);
				} else {
					csc1021.changeColour(Colour.YELLOW);
				}
			}
		}

		// csc1022
		Bar csc1022 = new Bar();
		csc1022.makeVisible();
		csc1022.changeSize((200 / MarkCalculator.ReturnedMark.length) - 5, MarkCalculator.ReturnedMark[1] * 2);
		csc1022.moveHorizontal(5 + (200 / MarkCalculator.ReturnedMark.length));
		csc1022.moveVertical(200 - (MarkCalculator.ReturnedMark[1] * 2));
		if (MarkCalculator.ReturnedMark[1] > 70) {
			csc1022.changeColour(Colour.MAGENTA);
		} else {
			if (MarkCalculator.ReturnedMark[1] > 40) {
				csc1022.changeColour(Colour.GREEN);
			} else {
				if (MarkCalculator.ReturnedMark[1] < 35) {
					csc1022.changeColour(Colour.RED);
				} else {
					csc1022.changeColour(Colour.YELLOW);
				}
			}
		}

		// csc1023
		Bar csc1023 = new Bar();
		csc1023.makeVisible();
		csc1023.changeSize((200 / MarkCalculator.ReturnedMark.length) - 5, MarkCalculator.ReturnedMark[2] * 2);
		csc1023.moveHorizontal(5 + (2 * (200 / MarkCalculator.ReturnedMark.length)));
		csc1023.moveVertical(200 - (MarkCalculator.ReturnedMark[2] * 2));
		if (MarkCalculator.ReturnedMark[2] > 70) {
			csc1023.changeColour(Colour.MAGENTA);
		} else {
			if (MarkCalculator.ReturnedMark[2] > 40) {
				csc1023.changeColour(Colour.GREEN);
			} else {
				if (MarkCalculator.ReturnedMark[2] < 35) {
					csc1023.changeColour(Colour.RED);
				} else {
					csc1023.changeColour(Colour.YELLOW);
				}
			}
		}

		// csc1024
		Bar csc1024 = new Bar();
		csc1024.makeVisible();
		csc1024.changeSize((200 / MarkCalculator.ReturnedMark.length) - 5, MarkCalculator.ReturnedMark[3] * 2);
		csc1024.moveHorizontal(5 + (3 * (200 / MarkCalculator.ReturnedMark.length)));
		csc1024.moveVertical(200 - (MarkCalculator.ReturnedMark[3] * 2));
		if (MarkCalculator.ReturnedMark[3] > 70) {
			csc1024.changeColour(Colour.MAGENTA);
		} else {
			if (MarkCalculator.ReturnedMark[3] > 40) {
				csc1024.changeColour(Colour.GREEN);
			} else {
				if (MarkCalculator.ReturnedMark[3] < 35) {
					csc1024.changeColour(Colour.RED);
				} else {
					csc1024.changeColour(Colour.YELLOW);
				}
			}
		}

		// csc1025
		Bar csc1025 = new Bar();
		csc1025.makeVisible();
		csc1025.changeSize((200 / MarkCalculator.ReturnedMark.length) - 5, MarkCalculator.ReturnedMark[4] * 2);
		csc1025.moveHorizontal(5 + (4 * (200 / MarkCalculator.ReturnedMark.length)));
		csc1025.moveVertical(200 - (MarkCalculator.ReturnedMark[4] * 2));
		if (MarkCalculator.ReturnedMark[4] > 70) {
			csc1025.changeColour(Colour.MAGENTA);
		} else {
			if (MarkCalculator.ReturnedMark[4] > 40) {
				csc1025.changeColour(Colour.GREEN);
			} else {
				if (MarkCalculator.ReturnedMark[4] < 35) {
					csc1025.changeColour(Colour.RED);
				} else {
					csc1025.changeColour(Colour.YELLOW);
				}
			}
		}

		// csc1026
		Bar csc1026 = new Bar();
		csc1026.makeVisible();
		csc1026.changeSize((200 / MarkCalculator.ReturnedMark.length) - 5, MarkCalculator.ReturnedMark[5] * 2);
		csc1026.moveHorizontal(5 + (5 * (200 / MarkCalculator.ReturnedMark.length)));
		csc1026.moveVertical(200 - (MarkCalculator.ReturnedMark[5] * 2));
		if (MarkCalculator.ReturnedMark[5] > 70) {
			csc1026.changeColour(Colour.MAGENTA);
		} else {
			if (MarkCalculator.ReturnedMark[5] > 40) {
				csc1026.changeColour(Colour.GREEN);
			} else {
				if (MarkCalculator.ReturnedMark[5] < 35) {
					csc1026.changeColour(Colour.RED);
				} else {
					csc1026.changeColour(Colour.YELLOW);
				}
			}
		}

	}

	// purpose: to return the values in a neatly formatted table
	public static void printsummary() {
		String str = "CSC102";
		for (int i = 0; i < MarkCalculator.ReturnedMark.length; i++) {
			System.out.format("%s%15d%25s%n", str + (i + 1), MarkCalculator.ReturnedMark[i],
					MarkCalculator.passstatus[i]);
		}

	}

}
