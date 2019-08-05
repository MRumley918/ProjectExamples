import java.io.IOException;
import java.util.Scanner;
// Name: Michael Rumley
// Last Modified: 26/10/17
//Purpose: To encrypt/decrypt data using a vigenere cipher

public class Exercise2 {
	// Encrypts the plaintext using the key
	public static String encrypt(String plaintext, String key) {
		// Initially the encrypted string is empty
		String encrypted = "";
		// the text is converted to lower case for simplicity
		plaintext = plaintext.toLowerCase();
		// for each character in the plaintext
		for (int x = 0, y = 0; x < plaintext.length(); x++) {
			char c = plaintext.charAt(x);
			// only works with characters a-z (i.e not punctuation)
			if (c < 'a' || c > 'z') {
				continue;
			}
			// the encrypted data is built up, using the corresponding character
			// value from
			// the key, modded by 26 for the english alphabet
			encrypted += (char) ((c + key.charAt(y) - 2 * 'a') % 26 + 'a');
			// the value of y is increased by 1 and then modded by key length,
			// in order to
			// cycle through the letters of the key
			y = ++y % key.length();
		}
		// the final encrypted string is returned
		return encrypted;
	}

	// inverse of previous method- takes cyphertext and uses key to decrypt it
	public static String decrypt(String cyphertext, String key) {
		// initial string is empty
		String decrypted = "";
		// cyphertext is made lower case for simplicity
		cyphertext = cyphertext.toLowerCase();
		// for each letter in the cyphertext
		for (int x = 0, y = 0; x < cyphertext.length(); x++) {
			char c = cyphertext.charAt(x);
			// only works with a-z
			if (c < 'a' || c > 'z') {
				continue;
			}
			// decrypted text is built up using inverse of equation from
			// encryption
			decrypted += (char) ((c - key.charAt(y) + 26) % 26 + 'a');
			y = ++y % key.length();
		}
		return decrypted;
	}

	// method to calculate the frequency of each letter in a string
	private static int[] letterFreq(String text) {
		// an array of integers size 26 for the english alphabet
		int[] freq = new int[26];
		// for each letter in the text
		for (int i = 0; i < text.length(); i++) {
			// increases the value at the position of the letter
			freq[text.charAt(i) - 'a']++;
		}
		// returns the array
		return freq;
	}

	// finds the position of the highest value in an array (used to calculate
	// the key)
	private static int maxArrayPosition(int[] array) {
		// initially the max value is at array position 0
		int max = 0;
		// for each value in an array(start at position 1)
		for (int i = 1; i < array.length; i++) {
			// if the value in the array is bigger than the current max
			// then its array position becomes the new max
			if (array[i] > array[max]) {
				max = i;
			}
		}
		// the max position is returned
		return max;
	}

	// calculates the index of coincidence given the frequency of letters
	private static double IC(int[] freq) {
		// initial value of ic and totalFreq set to zero
		double ic = 0;
		int totalFreq = 0;
		// each frequency is added together to produce a total frequency
		for (int i = 0; i < freq.length; i++) {
			totalFreq += freq[i];
		}
		// for each letter the ic is calculated and added to the overall ic
		for (int i = 0; i < freq.length; i++) {
			double top = freq[i] * (freq[i] - 1);
			double bottom = totalFreq * (totalFreq - 1);
			ic += top / bottom;
		}
		// ic is returned
		return ic;

	}

	// estimates the length of the key
	private static int keyLengthEstimate(String text) {
		// IC calculated using previous methods
		double IC = IC(letterFreq(text));
		// 0.027 is the IC of english text encrypted with vigenere cypher
		double top = 0.027 * text.length();
		// 0.038 is the IC of a random string, 0.065 is the IC of normal english
		// text
		double bottom = (text.length() - 1) * IC - 0.038 * text.length() + 0.065;
		double keyLength = top / bottom;
		// the double value is rounded up and returned as an int
		return (int) Math.round(keyLength) + 1;
	}

	// estimates a key by splitting the string into a number of arrays equal to
	// the keylength
	private static String keyEstimate(String cyphertext, int keylength) {
		// a number of string arrays are created equal to the length of the key
		String seperatedCypher[] = new String[keylength];
		// Initially the key is blank
		String key = "";
		// initialises each array to be blank
		for (int i = 0; i < keylength; i++) {
			seperatedCypher[i] = "";
		}
		// for each character in the cyphertext
		for (int i = 0; i < cyphertext.length(); i++) {
			// the arrays are populated with characters, modding the counter
			// with the keylength
			// to find the right one to put the char in
			seperatedCypher[i % keylength] += cyphertext.charAt(i);
		}
		// for each separate string generated above
		for (int i = 0; i < keylength; i++) {
			// the frequency of each letter in the string is calculated
			int[] freq = letterFreq(seperatedCypher[i]);
			// the most frequently occuring letter is added to the key
			key += (char) ((maxArrayPosition(freq) - 4) + 'a');
		}
		// the completed key is returned
		return key;
	}

	// the above methods are combined into a general method that takes a text
	// file and produces
	// its key
	public static String findKey(String cyphertext) {
		return keyEstimate(cyphertext, keyLengthEstimate(cyphertext));
	}

	public static void main(String[] args) throws IOException {
		Scanner keyboard = new Scanner(System.in);
		// user inputted key
		System.out.println("Enter a key");
		String key = keyboard.nextLine();
		// textfile processed using method from exercise 1
		String plaintext = Exercise1.readFile("textfile.txt");
		// encrypts the text
		String encrypted = encrypt(plaintext, key);
		System.out.println(Exercise1.characterCount(encrypted));
		
		// read the encrypted text for part 2 of the exercise
		String code = Exercise1.readFile("ex2code.txt");
		// finds the key
		System.out.println("Key is " + findKey(code));
		// decrypts the text using the key
		System.out.println(decrypt(code, findKey(code)));
		keyboard.close();

	}

}
