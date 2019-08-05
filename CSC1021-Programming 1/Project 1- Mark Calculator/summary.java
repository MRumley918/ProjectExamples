// Author:Michael Rumley
// Date Modified: 12/11/2015
public class summary {

	public static void main(String[] args) {
		System.out.println("All inputted values must be between 0 and 100");

		// user input of marks
		System.out.println("Please enter your mark for CSC1021 Exam ");
		MarkCalculator.Marks[0] = MarkCalculator.keyboard.nextInt();
		System.out.println("Please enter your mark for CSC1021 Coursework ");
		MarkCalculator.Marks[1] = MarkCalculator.keyboard.nextInt();
		System.out.println("Please enter your mark for CSC1022 Exam ");
		MarkCalculator.Marks[2] = MarkCalculator.keyboard.nextInt();
		System.out.println("Please enter your mark for CSC1022 Coursework ");
		MarkCalculator.Marks[3] = MarkCalculator.keyboard.nextInt();
		System.out.println("There is no exam for CSC1023 ");
		System.out.println("Please enter your mark for CSC1023 Coursework ");
		MarkCalculator.Marks[4] = MarkCalculator.keyboard.nextInt();
		System.out.println("Please enter your mark for CSC1024 Exam ");
		MarkCalculator.Marks[5] = MarkCalculator.keyboard.nextInt();
		System.out.println("Please enter your mark for CSC1024 Coursework ");
		MarkCalculator.Marks[6] = MarkCalculator.keyboard.nextInt();
		System.out.println("Please enter your mark for CSC1025 Exam ");
		MarkCalculator.Marks[7] = MarkCalculator.keyboard.nextInt();
		System.out.println("Please enter your mark for CSC1025 Coursework");
		MarkCalculator.Marks[8] = MarkCalculator.keyboard.nextInt();
		System.out.println("Please enter your mark for CSC1026 Exam ");
		MarkCalculator.Marks[9] = MarkCalculator.keyboard.nextInt();
		System.out.println("Please enter your mark for CSC1026 Coursework");
		MarkCalculator.Marks[10] = MarkCalculator.keyboard.nextInt();
		// Calculating the individual module marks
		MarkCalculator.ComputeMarks();
		// Putting these values into a bar chart
		StudentChart.draw();
		// presenting the values as a table
		StudentChart.printsummary();
		// Calculating the overall course result
		MarkCalculator.ComputeResults();

	}

}
