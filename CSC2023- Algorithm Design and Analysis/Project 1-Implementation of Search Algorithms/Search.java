
/************************************************/
/*** Purpose:  To perform various searches on ***/
/*** 	       arrays of data                 ***/
/***                                          ***/
/***                                          ***/
/*** Author: Michael Rumley                   ***/
/*** Date Last Updated: 29/10/16              ***/
/***                                          ***/
/*** Note: Based on skeleton code provided by ***/
/*** Jason Steggles 23/09/2016                ***/
/************************************************/

import java.io.*;
import java.text.*;
import java.util.*;

public class Search {

	/** Global variables for counting comparisons **/
	public int compSeq = 0;
	public int compBin = 0;
	public int compHash = 0;

	/** Array of values to be searched and size **/
	private int[] A;
	private int size;
	private int[] S;
	private int data;
	/** Hash array and size **/
	private int[] H;
	private int hSize;

	/** Constructor **/
	Search(int n, int hn) {
		/** set size of array **/
		size = n;
		hSize = hn;

		/** Create arrays **/
		A = new int[size];
		H = new int[hSize];

		/** Initialize hash array **/
		/** Assume -1 indicates a location is empty **/
		for (int i = 0; i < hSize; i++) {
			H[i] = -1;
		}
	}

	/********************************************/

	/*** Read a file of numbers into an array ***/
	/********************************************/
	public void readFileIn(String file) {
		try {
			/** Set up file for reading **/
			FileReader reader = new FileReader(file);
			Scanner in = new Scanner(reader);

			/** Loop round reading in data **/
			for (int i = 0; i < size; i++) {
				/** Get net value **/
				A[i] = in.nextInt();
			}
		} catch (IOException e) {
			System.out.println("Error processing file " + file);
		}
	}

	/*********************/

	/*** Hash Function ***/
	/*********************/
	public int hash(int key) {
		return key % hSize;
	}

	// adds a value to the hash table
	private void addToHash(int value) {
		// hashes the value needing to be added
		int result = hash(value);
		// the for loop starts at the hash value and keeps counting up
		for (int i = result;; i++) {
			// if the value is bigger than the size of the hash table it resets
			// to zero(loop around the array)
			if (i >= hSize) {
				i = 0;
			}
			// if the array value is empty then the value is placed there and
			// the loop is exited
			if (H[i] == -1) {
				H[i] = value;
				break;
			}

		}

	}

	// reads a file into the hash table
	public void readIntoHash(String file) {
		try {
			FileReader reader = new FileReader(file);
			Scanner in = new Scanner(reader);
			// while loop adds all data values to the hash table
			while (in.hasNext()) {
				addToHash(in.nextInt());
			}
		} catch (IOException e) {
			System.out.println("Error processing file " + file);
		}
	}

	// searches the hash table
	public int hashSearch(int key) {
		// hashes the search value
		int hashKey = hash(key);
		try {
			// for loop counts upwards from the key of the search value
			for (int i = hashKey;; i++) {
				// increases the number of comparisons on the array
				compHash++;
				// if the end of the array is reached loop around
				if (i > hSize) {
					i = 0;
				}
				// if the key is found then return the position of it
				if (H[i] == key) {
					compHash++;
					return i;

				}
				// if an empty array value is reached or the search ends up back
				// where it started exit the for loop
				if (H[i] == -1 || i == hashKey - 1) {
					compHash++;
					break;
				}
			}
			// a return value of -1 indicates the value isnt found
			return -1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}

	}

	// displays the results of the hash search
	public void displayHashSearch() {
		// initialise an int to store the running total of the number of
		// comparisons made
		int compHashAv = 0;
		System.out.printf("\n\n\n");
		System.out.println("Hash Search:");
		try {
			// for loop repeats process for each element in the array of search
			// terms
			for (int i = 0; i <= data; i++) {
				// performs a hashsearch on the search element
				int n = hashSearch(S[i]);
				// if not found, return the number of comparisons and add to
				// total
				if (n == -1) {
					System.out.println(S[i] + " not in array ( " + compHash + " comparisons made)");
					compHashAv = compHashAv + compHash;
					compHash = 0;
				} else
				// if found, return at what position and how many comparisons
				// needed
				{
					System.out.println("Found " + S[i] + " at position " + n + " after " + compHash + " comparisons");
					n = 0;
					compHashAv = compHashAv + compHash;
					compHash = 0;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		// work out the average number of comparisons by dividing the total by
		// the amount of search terms
		System.out.println("Average number of comparisons " + (compHashAv / data));

	}

	/*****************************/

	/*** Display array of data ***/
	/*****************************/
	public void displayData(int line, String header) {
		/* ** Integer Formatter ** */
		NumberFormat FI = NumberFormat.getInstance();
		FI.setMinimumIntegerDigits(3);

		/** Print header string **/
		System.out.print("\n\n" + header);

		/** Display array data **/
		for (int i = 0; i < size; i++) {
			/** New line? **/
			if (i % line == 0) {
				System.out.println();
			}

			/** Display value **/
			System.out.print(FI.format(A[i]) + " ");
		}
	}

	/**************************/

	/*** Display hash array ***/
	/**************************/
	public void displayHash(int line) {
		/** Integer Formatter **/
		NumberFormat FI = NumberFormat.getInstance();
		FI.setMinimumIntegerDigits(3);

		/** Print header string **/
		System.out.print("\n\nHash Array of size " + hSize);

		/** Display array data **/
		for (int i = 0; i < hSize; i++) {
			/** New line? **/
			if (i % line == 0) {
				System.out.println();
			}

			/** Display value **/
			System.out.print(FI.format(H[i]) + " ");
		}
	}

	/**************************/

	/*** Sequential Search ***/
	/**************************/
	public int seqSearch(int key) {

		try {
			// searches through each value of the array
			for (int i = 0; i <= size - 1; i++) {
				// adds one to the count of comparisons on the array
				compSeq++;
				if (A[i] == key) {
					// if value found
					return i;
				}
				// since the array is in ascending order can stop the search
				// once a key is found
				// that is greater than the search key
				if (A[i] > key) {
					return -1;
				}

			}
			// if value not found
			return -1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}

	}

	public void displaySeqSearch() {
		// initialise an int to keep a total of the number of comparisons
		// performed
		int compSeqAv = 0;
		System.out.printf("\n\n\n");
		System.out.println("Sequential Search:");
		try {
			// for loop performs operation for each search value
			for (int i = 0; i <= data; i++) {
				// sequentially searches for the value
				int n = seqSearch(S[i]);
				// if not found then number of comparisons returned
				if (n == -1) {
					System.out.println(S[i] + " not in array ( " + compSeq + " comparisons made)");
					compSeqAv = compSeqAv + compSeq;
					compSeq = 0;
				} else
				// if found then the position and number of comparisons are
				// returned
				{
					System.out.println("Found " + S[i] + " at position " + n + " after " + compSeq + " comparisons");
					n = 0;
					compSeqAv = compSeqAv + compSeq;
					compSeq = 0;

				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {

		}
		// average number of comparisons found by dividing running total by
		// number of search values
		System.out.println("Average number of comparisons " + (compSeqAv / data));
	}

	/**************************/

	/*** Binary Search ***/
	/**************************/
	public int binSearch(int key, int L, int R) {
		try {
			if (R < L) {
				// if the right value is smaller than the left value then the
				// search is invalid
				return -1;
			}
			// calculates the midpoint of the array
			int m = (R + L) / 2;
			if (key == A[m]) {
				// if the key is the midvalue it is immediately returned
				compBin++;
				return m;
			}
			if (key > A[m]) {
				// if the key is greater than the midvalue then the search is
				// run again with the
				// right hand side values
				compBin++;
				return binSearch(key, m + 1, R);
			} else {
				// if the key is less than the midvalue then the search is run
				// again with the
				// left hand side values
				compBin++;
				return binSearch(key, L, m - 1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}

	public void displayBinSearch() {
		// initialise an int to keep a running total of the number of
		// comparisons
		int compBinAv = 0;
		System.out.printf("\n\n\n");
		System.out.println("Binary Search:");

		try {
			// for loop runs search on each search value
			for (int i = 0; i <= data; i++) {
				// the initial binary search runs across the whole array
				int n = binSearch(S[i], 0, size);
				// if the value is not found then the number of comparisons are
				// returned
				if (n == -1) {
					System.out.println(S[i] + " not in array (" + compBin + " comparisons made) ");
					compBinAv = compBinAv + compBin;
					compBin = 0;
				}
				// if the value is found then the position and the number of
				// comparisons are returned
				else {
					System.out.println("Found " + S[i] + " at position " + n + " after " + compBin + " comparisons");
					n = 0;
					compBinAv = compBinAv + compBin;
					compBin = 0;
				}

			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		// the average number of comparisons are found by dividing the running
		// total by the number of values
		System.out.println("Average number of comparisons " + (compBinAv / data));
	}

	public void readSearchTerms(String file, int terms) {
		// the read search terms method is identical to the readFileIn method,
		// just uses a different array
		try {
			data = 0;
			FileReader reader = new FileReader(file);
			Scanner in = new Scanner(reader);
			S = new int[terms];
			data = terms;

			for (int i = 0; i < terms; i++) {

				S[i] = in.nextInt();
			}
		} catch (IOException e) {
			System.out.println("Error processing file " + file);
		}
	}

} /*** End of class Search ***/