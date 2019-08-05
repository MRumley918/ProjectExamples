
/******************************************************/
/*** Purpose: Test class to illustrate Search class ***/
/***                                                ***/
/*** Author: L. J. Steggles                         ***/
/*** Date: 23/09/2016                               ***/
/******************************************************/

import java.io.*;

public class TestSearch {
	public static void main(String[] args) {

		Search S = new Search(100, 151);

		/** Read in test data **/
		S.readFileIn("data1.txt");
		// reads the search terms
		S.readSearchTerms("search1.txt", 10);
		/** Display data array **/
		S.displayData(20, "Data Array");

		/** Display hash array which will be empty **/
		S.displayHash(20);
		// reads the data file into the hash table
		S.readIntoHash("data1.txt");
		// displays the now completed hash table
		S.displayHash(20);

		// displays the search results for each search type
		S.displaySeqSearch();
		S.displayBinSearch();
		S.displayHashSearch();

		// creates a new instance of the search class for the second set of
		// values
		Search S2 = new Search(1000, 1499);
		// reads the second set of test data
		S2.readFileIn("data2.txt");
		// reads the second set of search terms
		S2.readSearchTerms("search2.txt", 10);
		// displays the data array
		S2.displayData(20, "Data Array");
		// displays the hash table, which should be empty
		S2.displayHash(20);
		// reads the data into the hash table
		S2.readIntoHash("data2.txt");
		// displays the now completed hash table
		S2.displayHash(20);

		// displays the search results for each search type
		S2.displaySeqSearch();
		S2.displayBinSearch();
		S2.displayHashSearch();
	}

}