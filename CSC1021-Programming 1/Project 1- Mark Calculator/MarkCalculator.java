
// Author: Michael Rumley
// Date Modified: 12/11/2015
//

import java.util.*;
import java.lang.Math;

public class MarkCalculator {
	// Initialising arrays
	public static double[] Marks = new double[11];
	public static int[] Results = new int[6];
	public static int[] ReturnedMark = new int[6];
	public static Scanner keyboard = new Scanner(System.in);
	public static String[] passstatus = new String[6];
	// initialise static integers
	static int pass = 0;
	static int fail = 0;
	static int compfail = 0;

	public static void main(String[] args) {

		// no main method needed
	}

	// 1a
	// purpose: calculate final marks based on user input
	public static void ComputeMarks() {

		// Calculate Computed module marks

		Results[0] = (int) Math.round((((Marks[1] * 50) + (Marks[0] * (100 - 50))) / 100));// 1021
		Results[1] = (int) Math.round((((Marks[3] * 40) + (Marks[2] * (100 - 40))) / 100));// 1022
		Results[2] = (int) Marks[4]; // 1023
		Results[3] = (int) Math.round((((Marks[6] * 50) + (Marks[5] * (100 - 50))) / 100));// 1024
		Results[4] = (int) Math.round((((Marks[8] * 20) + (Marks[7] * (100 - 20))) / 100));// 1025
		Results[5] = (int) Math.round((((Marks[10] * 35) + (Marks[9] * (100 - 35))) / 100));// 1026

		/*
		 * If the exam mark and coursework mark are greater than or equal to 35
		 * then the returned mark is the computed module mark However, if either
		 * (or both) the exam or coursework mark is less than 35 then the
		 * returned mark is the minimum(35, computed module mark).
		 */
		// csc1021

		if (Marks[0] > 35) {
			if (Marks[1] > 35) {
				ReturnedMark[0] = Results[0];
			} else {
				ReturnedMark[0] = Math.min(35, Results[0]);

			}
		} else {
			ReturnedMark[0] = Math.min(35, Results[0]);
		}

		// 1022

		if (Marks[2] > 35) {
			if (Marks[3] > 35) {
				ReturnedMark[1] = Results[1];
			} else {
				ReturnedMark[1] = Math.min(35, Results[1]);

			}
		} else {
			ReturnedMark[1] = Math.min(35, Results[1]);
		}
		// 1023

		if (Marks[4] > 35) {

			ReturnedMark[2] = Results[2];
		} else {
			ReturnedMark[2] = Math.min(35, Results[2]);

		}
		// 1024

		if (Marks[5] > 35) {
			if (Marks[6] > 35) {
				ReturnedMark[3] = Results[3];
			} else {
				ReturnedMark[3] = Math.min(35, Results[3]);

			}
		} else {
			ReturnedMark[3] = Math.min(35, Results[3]);
		}
		// 1025

		if (Marks[7] > 35) {
			if (Marks[8] > 35) {
				ReturnedMark[4] = Results[4];
			} else {
				ReturnedMark[4] = Math.min(35, Results[4]);

			}
		} else {
			ReturnedMark[4] = Math.min(35, Results[4]);
		}
		// 1026

		if (Marks[9] > 35) {
			if (Marks[10] > 35) {
				ReturnedMark[5] = Results[5];
			} else {
				ReturnedMark[5] = Math.min(35, Results[5]);

			}
		} else {
			ReturnedMark[5] = Math.min(35, Results[5]);
		}

		// determine whether pass, fail or compensatable fail
		for (int i = 0; i < ReturnedMark.length; i++) {
			int i2;
			i2 = ReturnedMark[i];
			if (i2 > 40) {
				passstatus[i] = "PASS";
				pass = pass + 1;

			} else {
				if (i2 < 35) {
					passstatus[i] = "FAIL";
					fail = fail + 1;
				} else {
					passstatus[i] = "COMPENSATABLE FAIL";
					compfail = compfail + 1;
				}

			}
		}
	}

	// 1b
	// purpose: calculate overall stage result
	public static void ComputeResults() {
		int sum = 0;
		// calculate the sum of all marks
		for (int i = 0; i < ReturnedMark.length; i++) {
			sum += ReturnedMark[i];

		}
		// average= sum of values divided by amount of values
		System.out.println("Stage average is " + Math.round(sum / ReturnedMark.length));
		// classifying the stage result
		if (pass == ReturnedMark.length) {
			System.out.println("Stage Result is PASS");
		} else {
			if (sum >= 40) {
				if (fail == 0) {
					if (compfail <= 2) {
						System.out.println("Stage Result is PASS BY COMPENSATION");
					} else {
						System.out.println("Stage Result is FAIL");
					}
				} else {
					System.out.println("Stage Result is FAIL");
				}
			} else {
				System.out.println("Stage Result is FAIL");
			}

		}

	}
}
